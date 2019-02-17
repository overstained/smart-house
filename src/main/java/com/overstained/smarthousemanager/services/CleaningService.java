package com.overstained.smarthousemanager.services;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.overstained.smarthousemanager.domain.CleaningTaskTrace;
import com.overstained.smarthousemanager.domain.FloorCleaner;
import com.overstained.smarthousemanager.domain.Room;
import com.overstained.smarthousemanager.models.CleaningConfigurationModel;
import com.overstained.smarthousemanager.models.CleaningResultModel;
import com.overstained.smarthousemanager.models.CleaningTaskTraceModel;
import com.overstained.smarthousemanager.repositories.CleaningRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CleaningService {

	private final CleaningRepository cleaningRepository;
	
	/**
	 * I have sepparated the mappers from the services and managed them as spring beans
	 * in order to loosen the coupling.
	 */
	private final Function<CleaningConfigurationModel, Room> cleaningRoomMapper;
	private final Function<CleaningConfigurationModel, List<Character>> cleaningInstructionsMapper;
	private final Function<FloorCleaner, CleaningResultModel> cleaningResultMapper;
	private final Function<CleaningTaskTrace, CleaningTaskTraceModel> cleaningTraceMapper;

	public CleaningResultModel cleanRoom(CleaningConfigurationModel configurationModel) {
		Room room = cleaningRoomMapper.apply(configurationModel);
		FloorCleaner floorCleaner = new FloorCleaner(configurationModel.getCoords());
		List<Character> instructions = cleaningInstructionsMapper.apply(configurationModel);

		if (!CollectionUtils.isEmpty(instructions) && room.getRoomSize() != null) {
			CleaningTaskTrace taskTrace = new CleaningTaskTrace();
			taskTrace.setStartedAt(OffsetDateTime.now(ZoneOffset.UTC));
			instructions.forEach(cardinal -> floorCleaner.run((char) cardinal, room));
			taskTrace.setEndedAt(OffsetDateTime.now(ZoneOffset.UTC));
			taskTrace.setDeviceId(floorCleaner.getID());
			taskTrace.setNumberOfPatchesCleaned(floorCleaner.getNumberOfPatchesCleaned());
			taskTrace.setLastCoordinates(floorCleaner.getCurrentCoordinates());
			cleaningRepository.save(taskTrace);
		}

		return cleaningResultMapper.apply(floorCleaner);
	}
	
	public List<CleaningTaskTraceModel> traceCleaning(OffsetDateTime startDate, OffsetDateTime endDate) {
		return cleaningRepository
				.findByStartedAtBetween(startDate, endDate)
				.stream()
				.map(cleaningTraceMapper)
				.collect(Collectors.toList());
	}
}
