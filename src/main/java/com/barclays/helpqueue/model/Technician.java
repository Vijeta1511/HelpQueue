/**
 * 
 */
package com.barclays.helpqueue.model;

import javax.persistence.*;

/**
 * @author vijetaagrawal
 *
 */

@Entity
@Table(name = "technicians")
public class Technician {
	
	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY )
	private Long id;
	
    @Column(name = "name")
	private String name;
    
    @Column(name = "job_title")
	private String job_title;
    
    @Column(name = "department")
	private String department;


	// Default constructor
	public Technician() {
		super();
	}
	
	// Parameterised constructor
	public Technician(String name, String job_title, String department) {
		super();
		this.name = name;
		this.job_title = job_title;
		this.department = department;
	}
	
	public Technician(Long id, String name, String job_title, String department) {
		super();
		this.id = id;
		this.name = name;
		this.job_title = job_title;
		this.department = department;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
