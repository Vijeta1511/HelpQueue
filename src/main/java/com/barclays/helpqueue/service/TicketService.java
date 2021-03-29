/**
 * 
 */
package com.barclays.helpqueue.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barclays.helpqueue.model.Ticket;
import com.barclays.helpqueue.repository.TicketRepository;
import java.util.*;

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
		return this.repository.save(ticket);
	}
	
	public Ticket readById(Long id) {
		Optional<Ticket> ticket = this.repository.findById(id);
		return ticket.get();
	}
	
	public List<Ticket> readAll() {
		return this.repository.findAll();
	}
	
	public Ticket updateById(Long id, Ticket newValues) {
		Ticket toUpdate = this.repository.findById(id).orElseThrow(EntityNotFoundException::new);
		toUpdate.setTitle(newValues.getTitle());
		toUpdate.setAuthor(newValues.getAuthor());
		toUpdate.setDescription(newValues.getDescription());
		toUpdate.setDepartment(newValues.getDepartment());
		return this.repository.save(toUpdate);
	}
	
	public Ticket addToQueue(Long id) {
		Ticket toUpdate = this.repository.findById(id).orElseThrow(EntityNotFoundException::new);
		toUpdate.setStatus_queue(true);
		return this.repository.save(toUpdate);
	}
	
	public Ticket removeFromQueue(Long id) {
		Ticket toUpdate = this.repository.findById(id).orElseThrow(EntityNotFoundException::new);
		toUpdate.setStatus_queue(false);
		return this.repository.save(toUpdate);
	}
	
	public Ticket completeTicket(Long id, String solution) {
		Ticket toUpdate = this.repository.findById(id).orElseThrow(EntityNotFoundException::new);
		toUpdate.setStatus_queue(false);
		toUpdate.setStatus_complete(true);
		toUpdate.setSolution(solution);
		return this.repository.save(toUpdate);
	}
	
	public Ticket assignTicket(Long id, String assignee) {
		Ticket toUpdate = this.repository.findById(id).orElseThrow(EntityNotFoundException::new);
		toUpdate.setStatus_assign(true);
		toUpdate.setAsignee(assignee);
		return this.repository.save(toUpdate);
	}
	
	public boolean deleteById(Long id) {
		this.repository.deleteById(id);
		return this.repository.existsById(id);
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
}
