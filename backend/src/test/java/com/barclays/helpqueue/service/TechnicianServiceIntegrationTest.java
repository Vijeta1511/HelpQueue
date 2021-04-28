package com.barclays.helpqueue.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.barclays.helpqueue.model.Technician;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TechnicianServiceIntegrationTest {
	
	@Autowired
	private TechnicianService service;
	
	@Test
	void getTechnicianByDepartmentTest() {
		List<Technician> expected = this.service.getTechnicianByDepartment(1L);
		assertThat(expected.size()).isEqualTo(5);
	}	

}
