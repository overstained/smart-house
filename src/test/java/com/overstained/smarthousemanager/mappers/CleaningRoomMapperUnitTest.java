package com.overstained.smarthousemanager.mappers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import com.overstained.smarthousemanager.domain.Room;
import com.overstained.smarthousemanager.models.CleaningConfigurationModel;

public class CleaningRoomMapperUnitTest {
	
	private CleaningRoomMapper cleaningRoomMapper;
	
	@Before
	public void setUp() {
		cleaningRoomMapper = new CleaningRoomMapper();
	}
	
	@Test
	public void itShouldMapToRoomWithNoPatches_GivenConfigWithNullPatches() {
		CleaningConfigurationModel configurationModel = buildModel();
		
		Room room = cleaningRoomMapper.apply(configurationModel);
		
		assertThat(room.getPatches().size(), equalTo(0));
	}
	
	public CleaningConfigurationModel buildModel(int[]...patches) {
		CleaningConfigurationModel configurationModel = new CleaningConfigurationModel();
		if(patches == null) {
			configurationModel.setPatches(null);
		} else {
			configurationModel.setPatches(new HashSet<>(Arrays.asList(patches)));
		}
		return configurationModel;
	}
} 
