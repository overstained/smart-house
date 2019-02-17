package com.overstained.smarthousemanager.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.overstained.smarthousemanager.services.CleaningService;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CleaningControllerUnitTest {

	private static final String CLEANER_RUN = "/cleaner/run";

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CleaningService cleaningService;

	@Test
	public void itShouldRespondWithUnprocessableEntityStatus_GivenTriDimensionalRoomSize_toRoomCleaner() throws Exception {
		String body = "{\"roomSize\":[5,5,5],\"coords\":[1,2],\"patches\":[[1,2]],\"instructions\":\"NNEW\"}";
		
		this.mockMvc
				.perform(post(CLEANER_RUN).accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(body))
				.andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	public void itShouldRespondWithUnprocessableEntityStatus_GivenUniDimensionalRoomSize_toRoomCleaner() throws Exception {
		String body = "{\"roomSize\":[5],\"coords\":[1,2],\"patches\":[[1,2]],\"instructions\":\"NNEW\"}";
		
		this.mockMvc
				.perform(post(CLEANER_RUN).accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(body))
				.andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	public void itShouldRespondWithUnprocessableEntityStatus_GivenTriDimensionalCoord_toRoomCleaners() throws Exception {
		String body = "{\"roomSize\":[5,5],\"coords\":[1,2,3],\"patches\":[[1,2]],\"instructions\":\"NNEW\"}";
		
		this.mockMvc
				.perform(post(CLEANER_RUN).accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(body))
				.andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	public void itShouldRespondWithUnprocessableEntityStatus_GivenUniDimensionalCoords_toRoomCleaner() throws Exception {
		String body = "{\"roomSize\":[5,5],\"coords\":[1],\"patches\":[[1,2]],\"instructions\":\"NNEW\"}";
		
		this.mockMvc
				.perform(post(CLEANER_RUN).accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(body))
				.andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	public void itShouldRespondWithSuccessStatus_GivenCorrectConfiguration_toRoomCleaner() throws Exception {
		String body = "{\"roomSize\":[5,5],\"coords\":[1,2],\"patches\":[[1,2]],\"instructions\":\"NNEW\"}";
		
		this.mockMvc
				.perform(post(CLEANER_RUN).accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(body))
				.andExpect(status().isOk());
	}
	
	@Test
	public void itShouldRespondWithSuccessStatus_GivenCorrectCleaningConfiguration_toRoomCleaner() throws Exception {
		String body = "{\"roomSize\":[5,5],\"coords\":[1,2],\"patches\":[[1,2]],\"instructions\":\"NNEW\"}";
		
		this.mockMvc
				.perform(post(CLEANER_RUN).accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(body))
				.andExpect(status().isOk());
	}
	
	@Test
	public void itShouldRespondWithBadRequestStatus_GivenNoDates_toCleaningTracer() throws Exception {
		this.mockMvc
				.perform(get("/cleaner/trace"))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void itShouldRespondWithBadRequestStatus_GivenDatesWithBadFormat_toCleaningTracer() throws Exception {
		this.mockMvc
				.perform(get("/cleaner/trace").param("startDate", "2016.11.04").param("endDate", "2016.11.06"))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void itShouldRespondWithSuccessStatus_GivenCorrectDates_toCleaningTracer() throws Exception {
		this.mockMvc
				.perform(get("/cleaner/trace").param("startDate", "05.11.2016T15:30").param("endDate", "05.11.2016T18:30"))
				.andExpect(status().isOk());
	}
}
