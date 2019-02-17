package com.overstained.smarthousemanager.domain;

import java.util.Arrays;

/**
 * @author overstained
 * 
 * The robot is aware of its coordinates in space.
 * Not necessarily in the context of a particular room but
 * within the whole space N*N.
 * I'm assuming the starting coordinates of the robot should always be provided.
 * If not, it could default to the origin [0,0].
 * It should also be aware of its environment. 
 * 
 */
public class FloorCleaner implements TaskRunner<Character, Room> {
	private int[] coordinates;
	private int numberOfPatchesCleaned;
	
	public FloorCleaner() {
		this(new int[] {0,0});
	}
	
	public FloorCleaner(int[] coordinates) {
		this.coordinates = coordinates;
	}
	
	@Override
	public String getID() {
		return System.getenv(DEVICE_ID_ENV_KEY);
	}

	@Override
	public void run(Character instruction, Room room) {
		switch(instruction) {
			case 'N':
				moveNorth(room);
			break;
			case 'S':
				moveSouth(room);
			break;
			case 'W':
				moveWest(room);
			break;
			case 'E':
				moveEast(room);
			break;
		}
	}
	
	public void moveNorth(Room room) {
		int [] roomSize = room.getRoomSize();
		if(coordinates[1] < roomSize[1]) {
			coordinates[1] += 1;
			checkIfDirtPatchAndClean(room);
		}
	}
	
	public void moveSouth(Room room) {
		if(coordinates[1] > 0) {
			coordinates[1] -= 1;
			checkIfDirtPatchAndClean(room);
		}
	}
	
	public void moveWest(Room room) {
		if(coordinates[0] > 0) {
			coordinates[0] -= 1;
			checkIfDirtPatchAndClean(room);
		}
	}
	
	public void moveEast(Room room) {
		int [] roomSize = room.getRoomSize();
		if(coordinates[0] < roomSize[0]) {
			coordinates[0] += 1;
			checkIfDirtPatchAndClean(room);
		}
	}
	
	public int getNumberOfPatchesCleaned() {
		return numberOfPatchesCleaned;
	}
	
	public int[] getCurrentCoordinates() {
		return Arrays.copyOf(coordinates, coordinates.length);
	}
	
	private void checkIfDirtPatchAndClean(Room room) {
		Patch possiblePatch = new Patch(coordinates);
		if(room.getPatches().contains(possiblePatch)) {
			numberOfPatchesCleaned += 1;
			room.getPatches().remove(possiblePatch);
		}
	}

}
