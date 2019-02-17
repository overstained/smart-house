package com.overstained.smarthousemanager.models;

import lombok.Data;

@Data
public class CleaningResultModel {
	private int patches;
	private int [] coords;
}
