package com.overstained.smarthousemanager.mappers;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.overstained.smarthousemanager.domain.Patch;
import com.overstained.smarthousemanager.domain.Room;
import com.overstained.smarthousemanager.models.CleaningConfigurationModel;

@Component
public class CleaningRoomMapper implements Function<CleaningConfigurationModel, Room>{

	@Override
	public Room apply(CleaningConfigurationModel configurationModel) {
		Set<Patch> patches = null;
		if(!CollectionUtils.isEmpty(configurationModel.getPatches())) {
			patches = configurationModel.getPatches().stream()
					.map(Patch::new).collect(Collectors.toSet());
		} else {
			patches = new HashSet<>();
		}
		return new Room(configurationModel.getRoomSize(), patches);
	}


}
