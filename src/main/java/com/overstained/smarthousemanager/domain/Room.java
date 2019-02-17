package com.overstained.smarthousemanager.domain;

import java.util.Set;

import lombok.Data;

@Data
public class Room implements Environment {
	private final int[] roomSize;
	private final Set<Patch> patches;
}
