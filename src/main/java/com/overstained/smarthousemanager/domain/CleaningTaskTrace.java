package com.overstained.smarthousemanager.domain;

import java.time.OffsetDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class CleaningTaskTrace {
	
	@Id
	private String id;
	
	private String deviceId;
	
	private int[] lastCoordinates;
	
	private int numberOfPatchesCleaned;
	
    private OffsetDateTime startedAt;
    
    private OffsetDateTime endedAt;
}
