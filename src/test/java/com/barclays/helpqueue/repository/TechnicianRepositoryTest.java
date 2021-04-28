package com.barclays.helpqueue.repository;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.barclays.helpqueue.model.Technician;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
public class TechnicianRepositoryTest {

	@Autowired
	private TechnicianRepository repository;
	
	
	@Test
	void findTechnicianByDepartmentTest() {
		String department = "Development";
		List<Technician> expected = this.repository.findTechnicianByDepartment(department);
		assertThat(expected.size()).isEqualTo(5);
	}
}
