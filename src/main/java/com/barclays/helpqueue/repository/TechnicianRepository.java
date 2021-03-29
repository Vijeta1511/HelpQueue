package com.barclays.helpqueue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.barclays.helpqueue.model.Technician;

public interface TechnicianRepository extends JpaRepository<Technician, Long>{

	@Query(value = "SELECT name "
			+ "FROM technicians "
			+ "WHERE department = ?1", nativeQuery = true)
	List<String> findTechnicianByDepartment(String department);
}
