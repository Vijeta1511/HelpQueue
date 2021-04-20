package com.barclays.helpqueue.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.barclays.helpqueue.model.Ticket;
import com.barclays.helpqueue.repository.TicketRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TicketServiceIntegrationTest {
	
	@Autowired
	private TicketService service;
	
	@Autowired
	private TicketRepository repo;
	
	@Test
	void createTest() {
		Ticket toSave = new Ticket(11L, "Maven", "Bhagyaraj Iyer", "Maven Build Failed", "Development"); 
		Ticket save = this.service.create(toSave);
		Ticket saved = this.service.readById(11L);
		assertThat(save.getAsignee()).isEqualTo(saved.getAsignee());
		assertThat(save.getAuthor()).isEqualTo(saved.getAuthor());
		assertThat(save.getDepartment()).isEqualTo(saved.getDepartment());
		assertThat(save.getDescription()).isEqualTo(saved.getDescription());
		assertThat(save.getSolution()).isEqualTo(saved.getSolution());
		assertThat(save.getTime_created()).isEqualTo(saved.getTime_created());
		assertThat(save.getTitle()).isEqualTo(saved.getTitle());
		assertThat(save.isStatus_assign()).isEqualTo(saved.isStatus_assign());
		assertThat(save.isStatus_complete()).isEqualTo(saved.isStatus_complete());
		assertThat(save.isStatus_queue()).isEqualTo(saved.isStatus_queue());

	}
		
	@Test
	void updateByIdTest() {
		Ticket newValues = new Ticket(1L, "Jenkins", "Vijeta", "Jenkins build Failure", "Deployment"); 
		Ticket updated = this.service.updateById(1L, newValues);
		assertThat(newValues.getAuthor()).isEqualTo(updated.getAuthor());
		assertThat(newValues.getDepartment()).isEqualTo(updated.getDepartment());
		assertThat(newValues.getDescription()).isEqualTo(updated.getDescription());
		assertThat(newValues.getTitle()).isEqualTo(updated.getTitle());
	}
	
	@Test
	void completeTicketTest() {
		String solution = "mvn clean install";
		Ticket updated  = this.service.completeTicket(1L, solution);
		assertThat(updated.getSolution()).isEqualTo(solution);
		assertThat(updated.isStatus_complete()).isEqualTo(true);
		assertThat(updated.isStatus_queue()).isEqualTo(false);
	}
	
	
	@Test
	void assignTicketTest() {
		String assignee = "Priya Sharma";
		Ticket updated  = this.service.assignTicket(1L, assignee);
		assertThat(updated.getAsignee()).isEqualTo(assignee);
		assertThat(updated.isStatus_assign()).isEqualTo(true);
	}
	
	@Test
	void deleteByIdTest() {
		String deleted  = this.service.deleteById(2L);
		assertThat(this.repo.existsById(2L)).isEqualTo(false);
		assertThat(deleted).isEqualTo("Deleted");
	}
	
	@Test
	void testReadById() {
		Ticket toSave = new Ticket(11L, "Maven", "Bhagyaraj Iyer", "Maven Build Failed", "Development"); 
		Ticket save = this.repo.save(toSave);
		Ticket saved = this.service.readById(11L);
		assertThat(save.getAsignee()).isEqualTo(saved.getAsignee());
		assertThat(save.getAuthor()).isEqualTo(saved.getAuthor());
		assertThat(save.getDepartment()).isEqualTo(saved.getDepartment());
		assertThat(save.getDescription()).isEqualTo(saved.getDescription());
		assertThat(save.getSolution()).isEqualTo(saved.getSolution());
		assertThat(save.getTime_created()).isEqualTo(saved.getTime_created());
		assertThat(save.getTitle()).isEqualTo(saved.getTitle());
		assertThat(save.isStatus_assign()).isEqualTo(saved.isStatus_assign());
		assertThat(save.isStatus_complete()).isEqualTo(saved.isStatus_complete());
		assertThat(save.isStatus_queue()).isEqualTo(saved.isStatus_queue());
	}
	
//	
//	@Test
//	void testGetAllTicketsInQueue() {
//		Ticket tester = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Deployment"); 
//		List<Ticket> expected = Lists.newArrayList(tester);
//		Mockito.when(this.repo.findTicketInQueue(true)).thenReturn(expected);
//		Assertions.assertThat(this.service.getAllTicketsInQueue()).isEqualTo(expected);
//		Mockito.verify(this.repo, Mockito.times(1)).findTicketInQueue(true);
//	}
//	
//	@Test
//	void testGetAllTicketsComplete() {
//		Ticket tester = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Deployment"); 
//		List<Ticket> expected = Lists.newArrayList(tester);
//		Mockito.when(this.repo.findTicketComplete(true)).thenReturn(expected);
//		Assertions.assertThat(this.service.getAllTicketsComplete()).isEqualTo(expected);
//		Mockito.verify(this.repo, Mockito.times(1)).findTicketComplete(true);
//	}
//	
//	@Test
//	void testGetAllTicketByQueueAndDepartmentDevelopment() {
//		Ticket tester = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Development"); 
//		List<Ticket> expected = Lists.newArrayList(tester);
//		Mockito.when(this.repo.findTicketByQueueAndDepartment(true, "Development")).thenReturn(expected);
//		Assertions.assertThat(this.service.getAllTicketByQueueAndDepartmentDevelopment()).isEqualTo(expected);
//		Mockito.verify(this.repo, Mockito.times(1)).findTicketByQueueAndDepartment(true, "Development");
//	}
//	
//	@Test
//	void testGetAllTicketByQueueAndDepartmentDeployment() {
//		Ticket tester = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Deployment"); 
//		List<Ticket> expected = Lists.newArrayList(tester);
//		Mockito.when(this.repo.findTicketByQueueAndDepartment(true, "Deployment")).thenReturn(expected);
//		Assertions.assertThat(this.service.getAllTicketByQueueAndDepartmentDeployment()).isEqualTo(expected);
//		Mockito.verify(this.repo, Mockito.times(1)).findTicketByQueueAndDepartment(true, "Deployment");
//	}
//	
//	@Test
//	void testGetAllTicketsCompleteAndDepartmentDevelopment() {
//		Ticket tester = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Development"); 
//		List<Ticket> expected = Lists.newArrayList(tester);
//		Mockito.when(this.repo.findTicketCompleteAndDepartment(true, "Development")).thenReturn(expected);
//		Assertions.assertThat(this.service.getAllTicketsCompleteAndDepartmentDevelopment()).isEqualTo(expected);
//		Mockito.verify(this.repo, Mockito.times(1)).findTicketCompleteAndDepartment(true, "Development");
//	}
//	
//	@Test
//	void testGetAllTicketsCompleteAndDepartmentDeployment() {
//		Ticket tester = new Ticket(8L, "Jenkins", "Arpit", "Jenkins build Failure", "Deployment"); 
//		List<Ticket> expected = Lists.newArrayList(tester);
//		Mockito.when(this.repo.findTicketCompleteAndDepartment(true, "Deployment")).thenReturn(expected);
//		Assertions.assertThat(this.service.getAllTicketsCompleteAndDepartmentDeployment()).isEqualTo(expected);
//		Mockito.verify(this.repo, Mockito.times(1)).findTicketCompleteAndDepartment(true, "Deployment");
//	}
	
}
