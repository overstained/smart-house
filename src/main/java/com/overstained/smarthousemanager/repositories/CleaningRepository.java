package com.overstained.smarthousemanager.repositories;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.overstained.smarthousemanager.domain.CleaningTaskTrace;

public interface CleaningRepository extends MongoRepository<CleaningTaskTrace, String>{
	
	List<CleaningTaskTrace> findByStartedAtBetween(OffsetDateTime startDate, OffsetDateTime endDate);
}
