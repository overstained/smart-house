package com.overstained.smarthousemanager.mappers;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.overstained.smarthousemanager.models.CleaningConfigurationModel;

@Component
public class CleaningInstructionsMapper implements Function<CleaningConfigurationModel, List<Character>>{

	@Override
	public List<Character> apply(CleaningConfigurationModel configurationModel) {
		if(StringUtils.isBlank(configurationModel.getInstructions())) {
			return new LinkedList<>();
		}
		return configurationModel.getInstructions().chars().mapToObj(i->(char)i).collect(Collectors.toList());
	}

}
