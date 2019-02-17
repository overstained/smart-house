package com.overstained.smarthousemanager.mappers;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.overstained.smarthousemanager.domain.CleaningTaskTrace;
import com.overstained.smarthousemanager.models.CleaningTaskTraceModel;

@Component
public class CleaningTraceMapper implements Function<CleaningTaskTrace, CleaningTaskTraceModel> {

	@Override
	public CleaningTaskTraceModel apply(CleaningTaskTrace taskTrace) {
		return new CleaningTaskTraceModel(taskTrace.getStartedAt(), taskTrace.getEndedAt(),
				taskTrace.getNumberOfPatchesCleaned(), taskTrace.getLastCoordinates());
	}

}
