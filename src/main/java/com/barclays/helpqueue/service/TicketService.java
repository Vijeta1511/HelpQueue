/**
 * 
 */
package com.barclays.helpqueue.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barclays.helpqueue.exception.ResourceNotFoundException;
import com.barclays.helpqueue.model.Ticket;
import com.barclays.helpqueue.repository.TicketRepository;

/**
 * @author vijetaagrawal
 *
 */
@Service
public class TicketService {
	
	
	@Autowired
	private TicketRepository repository;
	
	public TicketService(TicketRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Ticket create(Ticket ticket) {
		ticket.setAsignee(null);
		ticket.setSolution(null);
		ticket.setTime_created(java.time.LocalDateTime.now());
		ticket.setStatus_assign(false);
		ticket.setStatus_complete(false);
		ticket.setStatus_queue(true);
		return this.repository.save(ticket);
	}
	
	public Ticket updateById(Long id, Ticket newValues) {
		Ticket toUpdate = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ticket does not exist with id :" + id));
		toUpdate.setTitle(newValues.getTitle());
		toUpdate.setAuthor(newValues.getAuthor());
		toUpdate.setDescription(newValues.getDescription());
		toUpdate.setDepartment(newValues.getDepartment());
		Ticket updatedTicket = this.repository.save(toUpdate);
		return updatedTicket;
	}
	
	public Ticket completeTicket(Long id, String solution) {
		Ticket toUpdate = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ticket does not exist with id :" + id));
		toUpdate.setStatus_queue(false);
		toUpdate.setStatus_complete(true);
		toUpdate.setSolution(solution);
		Ticket updatedTicket = this.repository.save(toUpdate);
		return updatedTicket;
	}
	
	public Ticket assignTicket(Long id, String assignee) {
		Ticket toUpdate = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ticket does not exist with id :" + id));
		toUpdate.setStatus_assign(true);
		toUpdate.setAsignee(assignee);
		Ticket updatedTicket = this.repository.save(toUpdate);
		return updatedTicket;
	}
	
	public String deleteById(Long id) {
		Ticket toDelete = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ticket does not exist with id :" + id));
		this.repository.delete(toDelete);
		String delete = "Deleted";
		return delete;
	}
	
	
	public List<Ticket> getAllTicketsInQueue() {
		return this.repository.findTicketInQueue(true);
	}
	
	public List<Ticket> getAllTicketsComplete() {
		return this.repository.findTicketComplete(true);
	}
	
	public List<Ticket> getAllTicketByQueueAndDepartmentDevelopment() {
		return this.repository.findTicketByQueueAndDepartment(true, "Development");
	}
	
	public List<Ticket> getAllTicketByQueueAndDepartmentDeployment() {
		return this.repository.findTicketByQueueAndDepartment(true, "Deployment");
	}
	
	public List<Ticket> getAllTicketsCompleteAndDepartmentDevelopment() {
		return this.repository.findTicketCompleteAndDepartment(true, "Development");
	}
	
	public List<Ticket> getAllTicketsCompleteAndDepartmentDeployment() {
		return this.repository.findTicketCompleteAndDepartment(true, "Deployment");
	}
	
	
	public Ticket readById(Long id) {
		Ticket ticket = this.repository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Ticket does not exist with id :" + id));
		return ticket;
	}
	
	public List<Ticket> readAll() {
		return this.repository.findAll();
	}
	
//	public Ticket addToQueue(Long id) {
//		Ticket toUpdate = this.repository.findById(id).orElseThrow(EntityNotFoundException::new);
//		toUpdate.setStatus_queue(true);
//		return this.repository.save(toUpdate);
//	}
//	
//	public Ticket removeFromQueue(Long id) {
//		Ticket toUpdate = this.repository.findById(id).orElseThrow(EntityNotFoundException::new);
//		toUpdate.setStatus_queue(false);
//		return this.repository.save(toUpdate);
//	}
}
