package com.overstained.smarthousemanager.api;

import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.overstained.smarthousemanager.models.CleaningConfigurationModel;

public class CleaningConfigurationValidator implements Validator{
	 @Override
    public boolean supports(Class<?> clazz) {
        return CleaningConfigurationModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	CleaningConfigurationModel cleaningConfiguration = (CleaningConfigurationModel) target;
    	int[] botInitialCoords = cleaningConfiguration.getCoords();
    	if(botInitialCoords == null || botInitialCoords.length != 2) {
    		errors.reject("coords", "X,Y coords must be provided");
    		return;
    	}
    	int[] roomSize = cleaningConfiguration.getRoomSize();
    	if(roomSize == null || roomSize.length != 2) {
    		errors.reject("roomSize", "X,Y coords must be provided");
    		return;
    	}
        if (botInitialCoords[0]>=roomSize[0] ||
	        botInitialCoords[1]>=roomSize[1] ||
	        botInitialCoords[0]<0 ||
	        botInitialCoords[0]<0) {
            errors.reject("coords", "Coords are outside of room");
            return;
        }
        Set<int[]> patches = cleaningConfiguration.getPatches();
        if(patches != null) {
        	patches.forEach(patchCoords -> {
        		if(patchCoords == null || patchCoords.length != 2) {
            		errors.reject("patches", "X,Y coords must be provided");
        		}
        	});
        }
    }
}
