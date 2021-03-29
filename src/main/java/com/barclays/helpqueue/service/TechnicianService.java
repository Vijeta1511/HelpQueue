/**
 * 
 */
package com.barclays.helpqueue.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barclays.helpqueue.model.Technician;
import com.barclays.helpqueue.repository.TechnicianRepository;
import com.barclays.helpqueue.repository.TicketRepository;

/**
 * @author vijetaagrawal
 *
 */

@Service
public class TechnicianService {
	
	@Autowired
	private TechnicianRepository repository;
	private TicketRepository ticketRepo;
	
	public TechnicianService(TechnicianRepository repository) {
		super();
		this.repository = repository;
	}
	
	public List<Technician> readAll() {
		return this.repository.findAll();
	}
	
	public List<String> getTechnicianByDepartment(Long id) {
		String department = ticketRepo.findDepartmentById(id);
		List<String> technicians = this.repository.findTechnicianByDepartment(department);
		return technicians;
	}

}
