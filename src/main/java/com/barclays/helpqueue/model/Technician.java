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
	
//    @OneToMany( targetEntity=Ticket.class )
//    private List ticketlist;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((job_title == null) ? 0 : job_title.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Technician other = (Technician) obj;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (job_title == null) {
			if (other.job_title != null)
				return false;
		} else if (!job_title.equals(other.job_title))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Technician [id=" + id + ", name=" + name + ", job_title=" + job_title + ", department=" + department
				+ "]";
	}
	
	

//	public List getTicketlist() {
//	    return ticketlist;
//	}
//
//	public void setTicketlist(List ticketlist) {
//	    this.ticketlist = ticketlist;
//	}

}
