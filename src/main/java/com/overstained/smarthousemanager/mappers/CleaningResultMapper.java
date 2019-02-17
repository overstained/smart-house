package com.overstained.smarthousemanager.mappers;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.overstained.smarthousemanager.domain.FloorCleaner;
import com.overstained.smarthousemanager.models.CleaningResultModel;

@Component
public class CleaningResultMapper implements Function<FloorCleaner, CleaningResultModel>{

	@Override
	public CleaningResultModel apply(FloorCleaner cleaner) {
		CleaningResultModel resultModel = new CleaningResultModel();
		resultModel.setPatches(cleaner.getNumberOfPatchesCleaned());
		resultModel.setCoords(cleaner.getCurrentCoordinates());
		return resultModel;
	}

}
