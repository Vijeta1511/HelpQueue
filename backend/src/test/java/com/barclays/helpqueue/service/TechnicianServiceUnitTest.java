package com.barclays.helpqueue.service;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.barclays.helpqueue.model.Technician;
import com.barclays.helpqueue.repository.TechnicianRepository;
import com.barclays.helpqueue.repository.TicketRepository;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class TechnicianServiceUnitTest {
	
	@Autowired
	private TechnicianService service;
	
	@MockBean
	private TechnicianRepository TechnicianRepo;
	
	@MockBean
	private TicketRepository TicketRepo;
	
	@Test
	void getTechnicianByDepartmentTest() {
		Technician tester = new Technician("Priya", "Python Developer", "Development"); 
		List<Technician> expected = Lists.newArrayList(tester);
		String department = "Development";
		Mockito.when(this.TicketRepo.findDepartmentById(2L)).thenReturn(department);
		Mockito.when(this.TechnicianRepo.findTechnicianByDepartment(department)).thenReturn(expected);
		Assertions.assertThat(this.service.getTechnicianByDepartment(2L)).isEqualTo(expected);
		Mockito.verify(this.TechnicianRepo, Mockito.times(1)).findTechnicianByDepartment(department);
		Mockito.verify(this.TicketRepo, Mockito.times(1)).findDepartmentById(2L);

	}

}
