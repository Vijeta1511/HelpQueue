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
	
	@Autowired
	private TicketRepository ticketRepo;
	
	public TechnicianService(TechnicianRepository repository) {
		super();
		this.repository = repository;
	}
	
	public List<Technician> getTechnicianByDepartment(Long id) {
		String department = ticketRepo.findDepartmentById(id);
		List<Technician> technicians = this.repository.findTechnicianByDepartment(department);
		return technicians;
	}

}
