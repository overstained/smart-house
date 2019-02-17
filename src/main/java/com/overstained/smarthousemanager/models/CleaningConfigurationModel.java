package com.overstained.smarthousemanager.models;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class CleaningConfigurationModel {

	@NotNull
	private int[] roomSize;

	@NotNull
	private int[] coords;

	private Set<int[]> patches = new HashSet<>();

	@NotBlank
	@Pattern(regexp = "^[NSWE]+$")
	private String instructions;
}
