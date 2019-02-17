package com.overstained.smarthousemanager.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.OffsetDateTime;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.overstained.smarthousemanager.domain.TaskRunner;
import com.overstained.smarthousemanager.models.CleaningConfigurationModel;
import com.overstained.smarthousemanager.models.CleaningResultModel;
import com.overstained.smarthousemanager.models.CleaningTaskTraceModel;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CleaningServiceIT {
	
	@Autowired
	private CleaningService cleaningService;
	
	@Rule
	public final EnvironmentVariables environmentVariables = new EnvironmentVariables();

	
	@Before
	public void setUp() {
		environmentVariables.set(TaskRunner.DEVICE_ID_ENV_KEY, "ID");
	}
	
	@Test
	@Ignore
	public void itShouldSkipRunningInstructions_GivenNullInstructionConfig() {
		int[] coords = new int[] { 1, 2 };
		CleaningConfigurationModel configurationModel = new CleaningConfigurationModel();
		configurationModel.setCoords(coords);

		CleaningResultModel result = cleaningService.cleanRoom(configurationModel);
		
		assertThat(result.getPatches(), equalTo(0));
		assertThat(result.getCoords(), equalTo(coords));
	}
	
	@Test
	@Ignore
	public void itShouldSkipRunningInstructions_GivenEmptyInstructionConfig() {
		int[] coords = new int[] { 1, 2 };
		CleaningConfigurationModel configurationModel = new CleaningConfigurationModel();
		configurationModel.setCoords(coords);
		configurationModel.setInstructions("");

		CleaningResultModel result = cleaningService.cleanRoom(configurationModel);

		assertThat(result.getPatches(), equalTo(0));
		assertThat(result.getCoords(), equalTo(coords));
	}
	
	@Test
	@Ignore
	public void itShouldSkipRunningInstructions_GivenNullRoomSizeConfig() {
		int[] coords = new int[] { 1, 2 };
		CleaningConfigurationModel configurationModel = new CleaningConfigurationModel();
		configurationModel.setCoords(coords);
		configurationModel.setInstructions("");

		CleaningResultModel result = cleaningService.cleanRoom(configurationModel);
		
		assertThat(result.getPatches(), equalTo(0));
		assertThat(result.getCoords(), equalTo(coords));
	}
	
	@Test
	public void itShouldEndInTheSameSpot_GivenIdempotentInstructionConfig() {
		int[] coords = new int[] { 5, 5 };
		int[] roomSize = new int[] {10,10};
		CleaningConfigurationModel configurationModel = new CleaningConfigurationModel();
		configurationModel.setCoords(coords);
		configurationModel.setRoomSize(roomSize);
		configurationModel.setInstructions("NESW");

		OffsetDateTime startDate = OffsetDateTime.now();
		CleaningResultModel result = cleaningService.cleanRoom(configurationModel);
		OffsetDateTime endDate = OffsetDateTime.now();
		CleaningTaskTraceModel model = cleaningService.traceCleaning(startDate, endDate).get(0);

		assertThat(result.getPatches(), equalTo(0));
		assertThat(result.getCoords(), equalTo(coords));
		assertThat(model.getPatches(), equalTo(0));
		assertThat(model.getCoords(), equalTo(coords));
	}
}
