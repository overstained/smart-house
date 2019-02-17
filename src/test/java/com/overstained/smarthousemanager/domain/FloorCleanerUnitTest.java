package com.overstained.smarthousemanager.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class FloorCleanerUnitTest {

	private FloorCleaner floorCleaner;

	@Before
	public void setUp() {
		floorCleaner = new FloorCleaner();
	}

	@Test
	public void itShouldStayInSameSpot_GivenInstructionsThatOnlyBumpItIntoTheWall() {
		char[] instructions = "SSSSSSS".toCharArray();
		Room room = new Room(new int[] { 5, 5 }, Collections.emptySet());

		for (char instruction : instructions) {
			floorCleaner.run(instruction, room);
		}

		assertThat(floorCleaner.getCurrentCoordinates(), equalTo(new int[] { 0, 0 }));
	}

	@Test
	public void itShouldStayInSameSpot_GivenIdempotentInstructions() {
		char[] instructions = "NESW".toCharArray();
		Room room = new Room(new int[] { 5, 5 }, Collections.emptySet());

		for (char instruction : instructions) {
			floorCleaner.run(instruction, room);
		}

		assertThat(floorCleaner.getCurrentCoordinates(), equalTo(new int[] { 0, 0 }));
	}

	@Test
	public void itShouldReachTheNorthWall_GivenEnoughMoveNorthInstructions() {
		char[] instructions = "NNNNNN".toCharArray();
		Room room = new Room(new int[] { 5, 5 }, Collections.emptySet());

		for (char instruction : instructions) {
			floorCleaner.run(instruction, room);
		}

		assertThat(floorCleaner.getCurrentCoordinates(), equalTo(new int[] { 0, 5 }));
	}

	@Test
	public void itShouldReachTheEastWall_GivenEnoughMoveEastInstructions() {
		char[] instructions = "EEEEEE".toCharArray();
		Room room = new Room(new int[] { 5, 5 }, Collections.emptySet());

		for (char instruction : instructions) {
			floorCleaner.run(instruction, room);
		}

		assertThat(floorCleaner.getCurrentCoordinates(), equalTo(new int[] { 5, 0 }));
	}

	@Test
	public void itShouldCollectAllDirtPatches_GivenAppropriateInstructions() {
		char[] instructions = "NNEENNE".toCharArray();
		Set<Patch> patches = Arrays.asList(patchOf(0, 1), patchOf(0, 2), patchOf(1, 2), patchOf(2, 2), patchOf(3, 4))
				.stream().collect(Collectors.toSet());
		int numberOfPatches = patches.size();
		Room room = new Room(new int[] { 5, 5 }, patches);

		for (char instruction : instructions) {
			floorCleaner.run(instruction, room);
		}

		assertThat(patches.size(), equalTo(0));
		assertThat(floorCleaner.getNumberOfPatchesCleaned(), equalTo(numberOfPatches));
	}

	private Patch patchOf(int x, int y) {
		return new Patch(new int[] { x, y });
	}
}
