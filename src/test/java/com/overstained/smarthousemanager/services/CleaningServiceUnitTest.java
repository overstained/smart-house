package com.overstained.smarthousemanager.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.overstained.smarthousemanager.domain.CleaningTaskTrace;
import com.overstained.smarthousemanager.domain.FloorCleaner;
import com.overstained.smarthousemanager.mappers.CleaningInstructionsMapper;
import com.overstained.smarthousemanager.mappers.CleaningResultMapper;
import com.overstained.smarthousemanager.mappers.CleaningRoomMapper;
import com.overstained.smarthousemanager.models.CleaningConfigurationModel;
import com.overstained.smarthousemanager.repositories.CleaningRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CleaningServiceUnitTest {
	
	@Autowired
	private CleaningService cleaningService;
	
	@MockBean
	private CleaningRepository cleaningRepository;
	
	@MockBean
	private CleaningRoomMapper cleaningRoomMapper;
	
	@MockBean
	private CleaningInstructionsMapper cleaningInstructionsMapper;
	
	@MockBean
	private CleaningResultMapper cleaningResultMapper;
	
	@Before
	public void setUp() {
		given(cleaningRepository.save(any(CleaningTaskTrace.class))).willReturn(null);
		given(cleaningResultMapper.apply(any(FloorCleaner.class))).willReturn(null);
	}
	
	@Test
	public void itShouldSkipRunningInstructions_GivenNullInstructionConfig() {
		CleaningConfigurationModel configurationModel = new CleaningConfigurationModel();
		
		cleaningService.cleanRoom(configurationModel);
		
		verify(cleaningRepository, never()).save(any(CleaningTaskTrace.class));
	}
	
	@Test
	public void itShouldSkipRunningInstructions_GivenEmptyInstructionConfig() {
		CleaningConfigurationModel configurationModel = new CleaningConfigurationModel();
		given(cleaningInstructionsMapper.apply(configurationModel)).willReturn(new LinkedList<>());
		
		cleaningService.cleanRoom(configurationModel);
		
		verify(cleaningRepository, never()).save(any(CleaningTaskTrace.class));
	}
	
	@Test
	public void itShouldSkipRunningInstructions_GivenNullRoomSizeConfig() {
		CleaningConfigurationModel configurationModel = new CleaningConfigurationModel();
		given(cleaningInstructionsMapper.apply(configurationModel)).willReturn(new LinkedList<>());
		
		cleaningService.cleanRoom(configurationModel);
		
		verify(cleaningRepository, never()).save(any(CleaningTaskTrace.class));
	}
}
