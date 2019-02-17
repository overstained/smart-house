package com.overstained.smarthousemanager.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.overstained.smarthousemanager.models.CleaningConfigurationModel;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CleaningInstructionsMapperUnitTest {

	private CleaningInstructionsMapper instructionsMapper;

	@Before
	public void setUp() {
		instructionsMapper = new CleaningInstructionsMapper();
	}

	@Test
	public void isShouldReturnEmptyString_GivenNullStringOfInstructions() {
		CleaningConfigurationModel configurationModel = buildModel(null);

		List<Character> instructions = instructionsMapper.apply(configurationModel);

		assertThat(instructions.size(), equalTo(0));
	}

	@Test
	public void isShouldReturnEmptyString_GivenEmptyStringOfInstructions() {
		CleaningConfigurationModel configurationModel = buildModel("");

		List<Character> instructions = instructionsMapper.apply(configurationModel);

		assertThat(instructions.size(), equalTo(0));
	}

	@Test
	public void isShouldReturnEmptyString_GivenBlankStringOfInstructions() {
		CleaningConfigurationModel configurationModel = buildModel(" \r\n");

		List<Character> instructions = instructionsMapper.apply(configurationModel);

		assertThat(instructions.size(), equalTo(0));
	}

	@Test
	public void itShouldSplitToListOfChars_GivenNonBlankStringOfInstructions() {
		CleaningConfigurationModel configurationModel = buildModel("NSSEW");

		List<Character> instructions = instructionsMapper.apply(configurationModel);

		assertThat(instructions.size(), equalTo(configurationModel.getInstructions().length()));
		assertThat(instructions.stream().map(String::valueOf).collect(Collectors.joining()),
				equalTo(configurationModel.getInstructions()));
	}

	private CleaningConfigurationModel buildModel(String instructions) {
		CleaningConfigurationModel configurationModel = new CleaningConfigurationModel();
		configurationModel.setInstructions(instructions);
		return configurationModel;
	}

}
