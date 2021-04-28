package com.barclays.helpqueue.controller;


import java.util.List;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.barclays.helpqueue.model.Technician;
import com.barclays.helpqueue.service.TechnicianService;


@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class TechnicianControllerUnitTest {
	
	@Autowired
	private TechnicianController controller;
	
	@MockBean
	private TechnicianService service;
	
	@Test
	void testAssignTicket() {
		Technician tester = new Technician("Priya", "Python Developer", "Development"); 
		List<Technician> expected = Lists.newArrayList(tester);
		Mockito.when(this.service.getTechnicianByDepartment(2L)).thenReturn(expected);
		ResponseEntity<List<Technician>> result = new ResponseEntity<List<Technician>>(expected, HttpStatus.OK);
		Assertions.assertThat(this.controller.assignTicket(2L)).isEqualTo(result);
		Mockito.verify(this.service, Mockito.times(1)).getTechnicianByDepartment(2L);
	}


}
