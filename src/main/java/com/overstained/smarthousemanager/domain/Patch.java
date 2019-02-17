package com.overstained.smarthousemanager.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Patch {
	@NotNull
	@Size(min = 2, max =2)
	private final int[] coordinates;

	@Override
	public boolean equals(Object o) {
		if (o instanceof Patch) {
			Patch otherPatch = (Patch) o;
			int[] otherCoordinates = otherPatch.getCoordinates();
			return otherCoordinates[0] == coordinates[0] && otherCoordinates[1] == coordinates[1];
		}
		return false;
	}

	/*
	 * Bijection from N*N to N - 2^i*j-2^(i-1)  - computes fast and avoids collisions in hash table
	 * based data structures by ensuring no two objects can hit the same bucket
	 */
	@Override
	public int hashCode() {
		return coordinates[1] << coordinates[0] - 1 << (coordinates[0] - 1);
	}
}
