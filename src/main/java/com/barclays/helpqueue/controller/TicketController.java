/**
 * 
 */
package com.barclays.helpqueue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.barclays.helpqueue.model.Ticket;
import com.barclays.helpqueue.service.TicketService;

/**
 * @author vijetaagrawal
 *
 */

@RestController
@RequestMapping("/api/v1/")
public class TicketController {

	@Autowired
	private TicketService service;
	
	public TicketController(TicketService service) {
		super();
		this.service  = service;
	}
	
	// create ticket
	@PostMapping("/tickets/createTicket")
	public ResponseEntity<Ticket> create(@RequestBody Ticket ticket) {
		return new ResponseEntity<>(this.service.create(ticket), HttpStatus.CREATED);
	}
	
	// update ticket by id
	@PutMapping("/tickets/updateTicket/{id}")
	public ResponseEntity<Ticket> updateById(@PathVariable Long id, @RequestBody Ticket ticket) {
		Ticket updatedTicket = this.service.updateById(id, ticket);
		return ResponseEntity.ok(updatedTicket);
	}
	
	// complete ticket and add solution
	@PutMapping("/tickets/completeTicket/{id}")
	public ResponseEntity<Ticket> completeTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
		String solution = ticket.getSolution();
		Ticket updatedTicket = this.service.completeTicket(id, solution);
		return ResponseEntity.ok(updatedTicket);
	}
	
	// assign ticket 
	@PutMapping("/tickets/assignTicket/{id}")
	public ResponseEntity<Ticket> assignTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
		String asignee = ticket.getAsignee();
		Ticket updatedTicket = this.service.assignTicket(id, asignee);
		return ResponseEntity.ok(updatedTicket);
	}	
	
	// delete ticket
	@DeleteMapping("/tickets/deleteTicket/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		if (this.service.deleteById(id)) {
			return ResponseEntity.ok(this.service.deleteById(id));
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	// get queue tickets
	@GetMapping({ "/", "/tickets" })
	public ResponseEntity<List<Ticket>> getAllTicketsInQueue() {
		return ResponseEntity.ok(this.service.getAllTicketsInQueue());
	}
	
	// get completed tickets
	@GetMapping("/tickets/complete")
	public ResponseEntity<List<Ticket>> getAllTicketsComplete() {
		return ResponseEntity.ok(this.service.getAllTicketsComplete());
	}
	
	// get queue tickets for Development 
	@GetMapping("/tickets/development")
	public ResponseEntity<List<Ticket>> getAllTicketByQueueAndDepartmentDevelopment() {
		return ResponseEntity.ok(this.service.getAllTicketByQueueAndDepartmentDevelopment());
	}
	
	// get queue tickets for Deployment
	@GetMapping("/tickets/deployment")
	public ResponseEntity<List<Ticket>> getAllTicketByQueueAndDepartmentDeployment() {
		return ResponseEntity.ok(this.service.getAllTicketByQueueAndDepartmentDeployment());
	}
	
	// get Completed tickets for Development
	@GetMapping("/tickets/development/complete")
	public ResponseEntity<List<Ticket>> getAllTicketsCompleteAndDepartmentDevelopment() {
		return ResponseEntity.ok(this.service.getAllTicketsCompleteAndDepartmentDevelopment());
	}
	
	// get Completed tickets for Deployment
	@GetMapping("/tickets/deployment/complete")
	public ResponseEntity<List<Ticket>> getAllTicketsCompleteAndDepartmentDeployment() {
		return ResponseEntity.ok(this.service.getAllTicketsCompleteAndDepartmentDeployment());
	}
	
	
	
	// get ticket by Id
	@GetMapping("/tickets/{id}")
	public ResponseEntity<Ticket> readById(@PathVariable Long id) {
		Ticket ticket = this.service.readById(id);
		return ResponseEntity.ok(ticket);
	}
	
	// get all tickets
	@GetMapping("/tickets/all")
	public ResponseEntity<List<Ticket>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}
	

	
//	
//	// add ticket to queue
//	@PutMapping("/tickets/addToQueue/{id}")
//	public ResponseEntity<Ticket> addToQueue(@PathVariable Long id) {
//		return new ResponseEntity<>(this.service.addToQueue(id), HttpStatus.ACCEPTED);
//	}
//	
//	// remove ticket from queue
//	@PutMapping("/tickets/removeFromQueue/{id}")
//	public ResponseEntity<Ticket> removeFromQueue(@PathVariable Long id) {
//		return new ResponseEntity<>(this.service.removeFromQueue(id), HttpStatus.ACCEPTED);
//	}
}
