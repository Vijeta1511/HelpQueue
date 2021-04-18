/**
 * 
 */
package com.barclays.helpqueue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.barclays.helpqueue.model.Technician;
import com.barclays.helpqueue.service.TechnicianService;

/**
 * @author vijetaagrawal
 *
 */

@RestController
@RequestMapping("/api/v1/")
public class TechnicianController {
	@Autowired
	private TechnicianService service;
	
	public TechnicianController(TechnicianService service) {
		super();
		this.service  = service;
	}
	
		
	// get assignee names 
	@GetMapping("/tickets/assignTicket/{id}")
	public ResponseEntity<List<Technician>> assignTicket(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.getTechnicianByDepartment(id));
	}	
		
}

