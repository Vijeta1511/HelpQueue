package com.barclays.helpqueue.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.barclays.helpqueue.model.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class TicketControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper jsonConvertor;

	@Test
	void getAllTicketsInQueueTest() throws Exception {
		this.mvc.perform( MockMvcRequestBuilders
				.get("/api/v1/tickets")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
	}
	
	@Test
	void getAllTicketsCompleteTest() throws Exception {
		this.mvc.perform( MockMvcRequestBuilders
				.get("/api/v1/tickets/complete")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
	}
	
	@Test
	void getAllTicketByQueueAndDepartmentDevelopmentTest() throws Exception {
		this.mvc.perform( MockMvcRequestBuilders
				.get("/api/v1/tickets/development")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
	}
	
	@Test
	void getAllTicketByQueueAndDepartmentDeploymentTest() throws Exception {
		this.mvc.perform( MockMvcRequestBuilders
				.get("/api/v1/tickets/deployment")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
	}
	
	@Test
	void getAllTicketsCompleteAndDepartmentDevelopmentTest() throws Exception {
		this.mvc.perform( MockMvcRequestBuilders
				.get("/api/v1/tickets/development/complete")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
	}
	
	@Test
	void getAllTicketsCompleteAndDepartmentDeploymentTest() throws Exception {
		this.mvc.perform( MockMvcRequestBuilders
				.get("/api/v1/tickets/deployment/complete")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
	}
	
	@Test
	void completeTicketTest() throws Exception {
	  Ticket toComplete = new Ticket(2L, "Maven", "Bhagyaraj Iyer", "Maven Build Failed", java.time.LocalDateTime.now(), "Development", null, "mvn clean install", true, false, false);
	  mvc.perform( MockMvcRequestBuilders
	      .put("/api/v1/tickets/completeTicket/{id}", 2)
	      .content(this.jsonConvertor.writeValueAsString(toComplete))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.solution").value("mvn clean install"));    
	}
	
	@Test
	void assignTicketTest() throws Exception {
	  Ticket toAssign = new Ticket(2L, "Maven", "Bhagyaraj Iyer", "Maven Build Failed", java.time.LocalDateTime.now(), "Development", "Priya Sharma", null, true, false, false);
	  mvc.perform( MockMvcRequestBuilders
	      .put("/api/v1/tickets/assignTicket/{id}", 2)
	      .content(this.jsonConvertor.writeValueAsString(toAssign))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.asignee").value("Priya Sharma"));    
	}
	
	@Test
	void createTest() throws Exception{
		Ticket toCreate= new Ticket("Jenkins", "Vijeta", "Jenkins build Failure", "Deployment"); 
		this.mvc.perform( MockMvcRequestBuilders
				.post("/api/v1/tickets/createTicket")
				.content(this.jsonConvertor.writeValueAsString(toCreate))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}
						
		
	@Test
	void readByIdTest() throws Exception {
		this.mvc.perform( MockMvcRequestBuilders
				.get("/api/v1/tickets/{id}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
		
	}
	
	@Test
	void updateByIdTest() throws Exception {
	  Ticket newValues= new Ticket("Jenkins", "Vijeta", "Jenkins build Failure", "Deployment"); 
	  mvc.perform( MockMvcRequestBuilders
	      .put("/api/v1/tickets/updateTicket/{id}", 2)
	      .content(this.jsonConvertor.writeValueAsString(newValues))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Jenkins"))
	      .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Vijeta"))
	      .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Jenkins build Failure"))
	      .andExpect(MockMvcResultMatchers.jsonPath("$.department").value("Deployment"));
	}
	
	@Test
	void deleteByIdTest() throws Exception 
	{
	  mvc.perform( MockMvcRequestBuilders
			  .delete("/api/v1/tickets/deleteTicket/{id}", 10))
	          .andExpect(status().isOk());
	}
	
	
	
	
	
	
}
