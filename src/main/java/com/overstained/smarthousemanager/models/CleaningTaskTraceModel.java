package com.overstained.smarthousemanager.models;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CleaningTaskTraceModel {
	private OffsetDateTime startedAt;
	private OffsetDateTime endedAt;
	private int patches;
	private int[] coords;
}
