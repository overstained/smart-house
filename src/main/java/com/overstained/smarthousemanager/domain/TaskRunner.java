package com.overstained.smarthousemanager.domain;


/**
 * 
 * @author overstained
 *
 * This interface is meant to be implemented by all of the devices
 * within a house that can perform certain tasks
 *
 * @param <U> instruction type
 * @param <S> environment type
 */
public interface TaskRunner<U, S extends Environment> {
	String DEVICE_ID_ENV_KEY = "DEVICE_ID";
	
	String getID();
	void run(U instruction, S environment);
}
