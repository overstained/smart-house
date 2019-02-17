package com.overstained.smarthousemanager.api;

import java.time.OffsetDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.overstained.smarthousemanager.models.CleaningConfigurationModel;
import com.overstained.smarthousemanager.models.CleaningResultModel;
import com.overstained.smarthousemanager.models.CleaningTaskTraceModel;
import com.overstained.smarthousemanager.services.CleaningService;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cleaner")
@AllArgsConstructor
public class CleaningController {

	private final CleaningService cleaningService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new CleaningConfigurationValidator());
	}

	@PostMapping(value="/run", consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Runs a cleaning process for current device, given environment and instructions")
	public CleaningResultModel cleanRoom(@RequestBody @Valid CleaningConfigurationModel configuration) {
		return cleaningService.cleanRoom(configuration);
	}

	@GetMapping("/trace")
	@ApiOperation(value = "Returns cleaning history for this device")
	public List<CleaningTaskTraceModel> traceCleaning(@RequestParam(value = "startDate") OffsetDateTime startDate,
			@RequestParam(value = "endDate") OffsetDateTime endDate) {
		return cleaningService.traceCleaning(startDate, endDate);
	}
}
