package com.barclays.helpqueue.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.barclays.helpqueue.model.Ticket;
import com.barclays.helpqueue.service.TicketService;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class TicketControllerUnitTest {

	@Autowired
	private TicketController controller;
	
	@MockBean
	private TicketService service;
	
	@Test
	void testReadByID() {
		Ticket tester = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Deployment"); 
		Mockito.when(this.service.readById(8L)).thenReturn(tester);
		ResponseEntity<Ticket> expected = new ResponseEntity<Ticket>(tester, HttpStatus.OK);
		Assertions.assertThat(this.controller.readById(8L)).isEqualTo(expected);
		Mockito.verify(this.service, Mockito.times(1)).readById(8L);
	}
	
	@Test
	void testGetAllTicketsInQueue() {
		Ticket tester = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Deployment"); 
		List<Ticket> expected = Lists.newArrayList(tester);
		Mockito.when(this.service.getAllTicketsInQueue()).thenReturn(expected);
		ResponseEntity<List<Ticket>> result = new ResponseEntity<List<Ticket>>(expected, HttpStatus.OK);
		Assertions.assertThat(this.controller.getAllTicketsInQueue()).isEqualTo(result);
		Mockito.verify(this.service, Mockito.times(1)).getAllTicketsInQueue();
	}
	
	@Test
	void testGetAllTicketsComplete() {
		Ticket tester = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Deployment"); 
		List<Ticket> expected = Lists.newArrayList(tester);
		Mockito.when(this.service.getAllTicketsComplete()).thenReturn(expected);
		ResponseEntity<List<Ticket>> result = new ResponseEntity<List<Ticket>>(expected, HttpStatus.OK);
		Assertions.assertThat(this.controller.getAllTicketsComplete()).isEqualTo(result);
		Mockito.verify(this.service, Mockito.times(1)).getAllTicketsComplete();
	}
	
	@Test
	void testGetAllTicketByQueueAndDepartmentDevelopment() {
		Ticket tester = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Development"); 
		List<Ticket> expected = Lists.newArrayList(tester);
		Mockito.when(this.service.getAllTicketByQueueAndDepartmentDevelopment()).thenReturn(expected);
		ResponseEntity<List<Ticket>> result = new ResponseEntity<List<Ticket>>(expected, HttpStatus.OK);
		Assertions.assertThat(this.controller.getAllTicketByQueueAndDepartmentDevelopment()).isEqualTo(result);
		Mockito.verify(this.service, Mockito.times(1)).getAllTicketByQueueAndDepartmentDevelopment();
	}
	
	@Test
	void testGetAllTicketByQueueAndDepartmentDeployment() {
		Ticket tester = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Deployment"); 
		List<Ticket> expected = Lists.newArrayList(tester);
		Mockito.when(this.service.getAllTicketByQueueAndDepartmentDeployment()).thenReturn(expected);
		ResponseEntity<List<Ticket>> result = new ResponseEntity<List<Ticket>>(expected, HttpStatus.OK);
		Assertions.assertThat(this.controller.getAllTicketByQueueAndDepartmentDeployment()).isEqualTo(result);
		Mockito.verify(this.service, Mockito.times(1)).getAllTicketByQueueAndDepartmentDeployment();
	}
	
	@Test
	void testGetAllTicketsCompleteAndDepartmentDevelopment() {
		Ticket tester = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Development"); 
		List<Ticket> expected = Lists.newArrayList(tester);
		Mockito.when(this.service.getAllTicketsCompleteAndDepartmentDevelopment()).thenReturn(expected);
		ResponseEntity<List<Ticket>> result = new ResponseEntity<List<Ticket>>(expected, HttpStatus.OK);
		Assertions.assertThat(this.controller.getAllTicketsCompleteAndDepartmentDevelopment()).isEqualTo(result);
		Mockito.verify(this.service, Mockito.times(1)).getAllTicketsCompleteAndDepartmentDevelopment();
	}
	
	@Test
	void testGetAllTicketsCompleteAndDepartmentDeployment() {
		Ticket tester = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Deployment"); 
		List<Ticket> expected = Lists.newArrayList(tester);
		Mockito.when(this.service.getAllTicketsCompleteAndDepartmentDeployment()).thenReturn(expected);
		ResponseEntity<List<Ticket>> result = new ResponseEntity<List<Ticket>>(expected, HttpStatus.OK);
		Assertions.assertThat(this.controller.getAllTicketsCompleteAndDepartmentDeployment()).isEqualTo(result);
		Mockito.verify(this.service, Mockito.times(1)).getAllTicketsCompleteAndDepartmentDeployment();
	}
	
	@Test
	void testCreate() {
		Ticket tester = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Deployment"); 
		Mockito.when(this.service.create(tester)).thenReturn(tester);
		ResponseEntity<Ticket> expected = new ResponseEntity<Ticket>(tester, HttpStatus.CREATED);
		Assertions.assertThat(this.controller.create(tester)).isEqualTo(expected);
		Mockito.verify(this.service, Mockito.times(1)).create(tester);
	}
	
	@Test
	void testUpdateById() {
		Ticket tester = new Ticket("Jenkins", "Arpit", "Jenkins build Failure", "Deployment"); 
		Mockito.when(this.service.updateById(8L, tester)).thenReturn(tester);
		ResponseEntity<Ticket> expected = new ResponseEntity<Ticket>(tester, HttpStatus.OK);
		Assertions.assertThat(this.controller.updateById(8L, tester)).isEqualTo(expected);
		Mockito.verify(this.service, Mockito.times(1)).updateById(8L, tester);
	}
	
	@Test
	void testCompleteTicket() {
		Ticket tester = new Ticket("Jenkins", "Arpit", "Jenkins build Failure", "Deployment"); 
		String solution = tester.getSolution();
		Mockito.when(this.service.completeTicket(8L, solution)).thenReturn(tester);
		ResponseEntity<Ticket> expected = new ResponseEntity<Ticket>(tester, HttpStatus.OK);
		Assertions.assertThat(this.controller.completeTicket(8L, tester)).isEqualTo(expected);
		Mockito.verify(this.service, Mockito.times(1)).completeTicket(8L, solution);
	}
	
	@Test
	void testAssignTicket() {
		Ticket tester = new Ticket("Jenkins", "Arpit", "Jenkins build Failure", "Deployment"); 
		String assignee = tester.getAsignee();
		Mockito.when(this.service.assignTicket(8L, assignee)).thenReturn(tester);
		ResponseEntity<Ticket> expected = new ResponseEntity<Ticket>(tester, HttpStatus.OK);
		Assertions.assertThat(this.controller.assignTicket(8L, tester)).isEqualTo(expected);
		Mockito.verify(this.service, Mockito.times(1)).assignTicket(8L, assignee);
	}
	
	@Test
	void testDeleteTicket() {
		String deleted = "Deleted";
		Mockito.when(this.service.deleteById(2L)).thenReturn(deleted);
		Map<String, Boolean> result = new HashMap<>();
		result.put(deleted, Boolean.TRUE);
		ResponseEntity<Map<String, Boolean>> expected = new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.OK);
		Assertions.assertThat(this.controller.deleteById(2L)).isEqualTo(expected);
		Mockito.verify(this.service, Mockito.times(1)).deleteById(2L);
	}
	
}
