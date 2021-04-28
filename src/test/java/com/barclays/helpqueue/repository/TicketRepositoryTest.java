package com.barclays.helpqueue.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.barclays.helpqueue.model.Ticket;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
public class TicketRepositoryTest {
	
	@Autowired
	private TicketRepository repository;
	
	
	@Test
	void getAllTicketsInQueueTest() {
		List<Ticket> expected = this.repository.findTicketInQueue(true);
		assertThat(expected.size()).isEqualTo(10);
	}
	
	@Test
	void getAllTicketsCompleteTest() {
		List<Ticket> expected = this.repository.findTicketComplete(true);
		assertThat(expected.size()).isEqualTo(0);
	}
	
	@Test
	void getAllTicketByQueueAndDepartmentDevelopmentTest() {
		List<Ticket> expected = this.repository.findTicketByQueueAndDepartment(true, "Development");
		assertThat(expected.size()).isEqualTo(5);
	}
	
	@Test
	void getAllTicketByQueueAndDepartmentDeploymentTest() {
		List<Ticket> expected = this.repository.findTicketByQueueAndDepartment(true, "Deployment");
		assertThat(expected.size()).isEqualTo(5);
	}
	
	@Test
	void getAllTicketsCompleteAndDepartmentDevelopmentTest() {
		List<Ticket> expected = this.repository.findTicketCompleteAndDepartment(true, "Development");
		assertThat(expected.size()).isEqualTo(0);
	}
	
	@Test
	void getAllTicketsCompleteAndDepartmentDeploymentTest() {
		List<Ticket> expected = this.repository.findTicketCompleteAndDepartment(true, "Deployment");
		assertThat(expected.size()).isEqualTo(0);
	}


}
