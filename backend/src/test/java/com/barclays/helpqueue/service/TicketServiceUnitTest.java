package com.barclays.helpqueue.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.barclays.helpqueue.model.Ticket;
import com.barclays.helpqueue.repository.TicketRepository;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class TicketServiceUnitTest {

	@Autowired
	private TicketService service;
	
	@MockBean
	private TicketRepository repo;
	
	@Test
	void testCreate() {
		Ticket toSave = new Ticket(null, "Maven", "Bhagyaraj Iyer", "Maven Build Failed", "Development"); 
		Ticket saved = new Ticket(11L, "Maven", "Bhagyaraj Iyer", "Maven Build Failed", java.time.LocalDateTime.now(), "Development", null, null, true, false, false);
		Mockito.when(this.repo.save(toSave)).thenReturn(saved);
		Assertions.assertThat(this.service.create(toSave)).isEqualTo(saved);
		Mockito.verify(this.repo, Mockito.times(1)).save(toSave);	
	}
	
	@Test
	void testUpdateById() {
		Ticket newValues = new Ticket(1L, "Jenkins", "Vijeta", "Jenkins build Failure", "Deployment"); 
		Ticket existing = new Ticket(1L, "Maven", "Vijeta", "Maven Build Failed", "Development");
		Ticket updated = new Ticket(1L, newValues.getTitle(), newValues.getAuthor(), 
				newValues.getDescription(), newValues.getDepartment());
		Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(existing));
		Mockito.when(this.repo.save(existing)).thenReturn(updated);
		Assertions.assertThat(this.service.updateById(1L, newValues)).isEqualTo(updated);
		Mockito.verify(this.repo, Mockito.times(1)).save(existing);
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
	}
	
	@Test
	void testCompleteTicket() {
		LocalDateTime time_created = java.time.LocalDateTime.now();
		String solution = "mvn clean install";
		Ticket existing = new Ticket(1L, "Maven", "Vijeta", "Maven Build Failed", time_created, "Development", null, null, true, false, false);
		Ticket updated  = new Ticket(1L, "Maven", "Vijeta", "Maven Build Failed", time_created, "Development", null, solution, false, false, true);
		Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(existing));
		Mockito.when(this.repo.save(existing)).thenReturn(updated);
		Assertions.assertThat(this.service.completeTicket(1L, solution)).isEqualTo(updated);
		Mockito.verify(this.repo, Mockito.times(1)).save(existing);
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
	}
	
	@Test
	void testAssignTicket() {
		LocalDateTime time_created = java.time.LocalDateTime.now();
		String assignee = "Priya Sharma";
		Ticket existing = new Ticket(1L, "Maven", "Vijeta", "Maven Build Failed", time_created, "Development", null, null, true, false, false);
		Ticket updated  = new Ticket(1L, "Maven", "Vijeta", "Maven Build Failed", time_created, "Development", "Priya Sharma", null, true, true, false);
		Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(existing));
		Mockito.when(this.repo.save(existing)).thenReturn(updated);
		Assertions.assertThat(this.service.assignTicket(1L, assignee)).isEqualTo(updated);
		Mockito.verify(this.repo, Mockito.times(1)).save(existing);
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
	}
	
	@Test
	void testDeleteById() {
		Ticket toDelete = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Deployment"); 
		Mockito.when(this.repo.findById(8L)).thenReturn(Optional.of(toDelete));
		Assertions.assertThat(this.service.deleteById(8L)).isEqualTo("Deleted");
		Mockito.verify(this.repo, Mockito.times(1)).findById(8L);
	}
	
	@Test
	void testReadById() {
		Ticket expected = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Deployment"); 
		Mockito.when(this.repo.findById(8L)).thenReturn(Optional.of(expected));
		Assertions.assertThat(this.service.readById(8L)).isEqualTo(expected);
		Mockito.verify(this.repo, Mockito.times(1)).findById(8L);
	}
	
	
	@Test
	void testGetAllTicketsInQueue() {
		Ticket tester = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Deployment"); 
		List<Ticket> expected = Lists.newArrayList(tester);
		Mockito.when(this.repo.findTicketInQueue(true)).thenReturn(expected);
		Assertions.assertThat(this.service.getAllTicketsInQueue()).isEqualTo(expected);
		Mockito.verify(this.repo, Mockito.times(1)).findTicketInQueue(true);
	}
	
	@Test
	void testGetAllTicketsComplete() {
		Ticket tester = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Deployment"); 
		List<Ticket> expected = Lists.newArrayList(tester);
		Mockito.when(this.repo.findTicketComplete(true)).thenReturn(expected);
		Assertions.assertThat(this.service.getAllTicketsComplete()).isEqualTo(expected);
		Mockito.verify(this.repo, Mockito.times(1)).findTicketComplete(true);
	}
	
	@Test
	void testGetAllTicketByQueueAndDepartmentDevelopment() {
		Ticket tester = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Development"); 
		List<Ticket> expected = Lists.newArrayList(tester);
		Mockito.when(this.repo.findTicketByQueueAndDepartment(true, "Development")).thenReturn(expected);
		Assertions.assertThat(this.service.getAllTicketByQueueAndDepartmentDevelopment()).isEqualTo(expected);
		Mockito.verify(this.repo, Mockito.times(1)).findTicketByQueueAndDepartment(true, "Development");
	}
	
	@Test
	void testGetAllTicketByQueueAndDepartmentDeployment() {
		Ticket tester = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Deployment"); 
		List<Ticket> expected = Lists.newArrayList(tester);
		Mockito.when(this.repo.findTicketByQueueAndDepartment(true, "Deployment")).thenReturn(expected);
		Assertions.assertThat(this.service.getAllTicketByQueueAndDepartmentDeployment()).isEqualTo(expected);
		Mockito.verify(this.repo, Mockito.times(1)).findTicketByQueueAndDepartment(true, "Deployment");
	}
	
	@Test
	void testGetAllTicketsCompleteAndDepartmentDevelopment() {
		Ticket tester = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Development"); 
		List<Ticket> expected = Lists.newArrayList(tester);
		Mockito.when(this.repo.findTicketCompleteAndDepartment(true, "Development")).thenReturn(expected);
		Assertions.assertThat(this.service.getAllTicketsCompleteAndDepartmentDevelopment()).isEqualTo(expected);
		Mockito.verify(this.repo, Mockito.times(1)).findTicketCompleteAndDepartment(true, "Development");
	}
	
	@Test
	void testGetAllTicketsCompleteAndDepartmentDeployment() {
		Ticket tester = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Deployment"); 
		List<Ticket> expected = Lists.newArrayList(tester);
		Mockito.when(this.repo.findTicketCompleteAndDepartment(true, "Deployment")).thenReturn(expected);
		Assertions.assertThat(this.service.getAllTicketsCompleteAndDepartmentDeployment()).isEqualTo(expected);
		Mockito.verify(this.repo, Mockito.times(1)).findTicketCompleteAndDepartment(true, "Deployment");
	}
	
}
