/**
 * 
 */
package com.barclays.helpqueue.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vijetaagrawal
 *
 */

@Entity
@Table(name = "tickets")
public class Ticket {
	
	@Id 
    @GeneratedValue( strategy=GenerationType.IDENTITY )
	private Long id;
	
    @Column(name = "title", nullable = false)
	private String title;
    
    @Column(name = "author", nullable = false)
	private String author;
    
    @Column(name = "description", nullable = false)
	private String description;
    
    @Column(name = "time_created", nullable = false)
	private LocalDateTime time_created;
    
    @Column(name = "department", nullable = false)
	private String department;
    
    @Column(name = "asignee")
	private String asignee = null;
    
    @Column(name = "solution")
	private String solution = null;
    
    @Column(name = "status_queue", nullable = false)
	private boolean status_queue;
    
    @Column(name = "status_assign", nullable = false)
	private boolean status_assign;
    
    @Column(name = "status_complete", nullable = false)
	private boolean status_complete;
	
//	@ManyToOne
//    @JoinColumn(name = "technician_id")
//    private Technician technician;
	

	// Default constructor
	public Ticket() {
		super();
	}
	

//	// Parameterised constructor
//	public Ticket(String title, String author, String description, String department) {
//		super();
//		this.title = title;
//		this.author = author;
//		this.description = description;
//		this.time_created = java.time.LocalDateTime.now();
//		this.department = department;
//		this.status_queue = false;
//		this.status_assign = false;
//		this.status_complete = false;
//	}
//	
//
//	public Ticket(Long id, String title, String author, String description, String department) {
//		super();
//		this.id = id;
//		this.title = title;
//		this.author = author;
//		this.description = description;
//		this.time_created = java.time.LocalDateTime.now();
//		this.department = department;
//		this.status_queue = false;
//		this.status_assign = false;
//		this.status_complete = false;
//	}
//	
	
	public Ticket(Long id, String title, String author, String description, LocalDateTime time_created, String department,
			String asignee, String solution, boolean status_queue, boolean status_assign, boolean status_complete) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
		this.time_created = time_created;
		this.department = department;
		this.asignee = asignee;
		this.solution = solution;
		this.status_queue = status_queue;
		this.status_assign = status_assign;
		this.status_complete = status_complete;
	}


	public Ticket(String title, String author, String description, LocalDateTime time_created, String department,
			String asignee, String solution, boolean status_queue, boolean status_assign, boolean status_complete) {
		super();
		this.title = title;
		this.author = author;
		this.description = description;
		this.time_created = time_created;
		this.department = department;
		this.asignee = asignee;
		this.solution = solution;
		this.status_queue = status_queue;
		this.status_assign = status_assign;
		this.status_complete = status_complete;
	}


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public LocalDateTime getTime_created() {
		return time_created;
	}



	public void setTime_created(LocalDateTime time_created) {
		this.time_created = time_created;
	}



	public String getDepartment() {
		return department;
	}



	public void setDepartment(String department) {
		this.department = department;
	}



	public String getAsignee() {
		return asignee;
	}



	public void setAsignee(String asignee) {
		this.asignee = asignee;
	}



	public String getSolution() {
		return solution;
	}



	public void setSolution(String solution) {
		this.solution = solution;
	}



	public boolean isStatus_queue() {
		return status_queue;
	}



	public void setStatus_queue(boolean status_queue) {
		this.status_queue = status_queue;
	}



	public boolean isStatus_assign() {
		return status_assign;
	}



	public void setStatus_assign(boolean status_assign) {
		this.status_assign = status_assign;
	}



	public boolean isStatus_complete() {
		return status_complete;
	}



	public void setStatus_complete(boolean status_complete) {
		this.status_complete = status_complete;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignee == null) ? 0 : asignee.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((solution == null) ? 0 : solution.hashCode());
		result = prime * result + (status_assign ? 1231 : 1237);
		result = prime * result + (status_complete ? 1231 : 1237);
		result = prime * result + (status_queue ? 1231 : 1237);
		result = prime * result + ((time_created == null) ? 0 : time_created.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Ticket other = (Ticket) obj;
		if (asignee == null) {
			if (other.asignee != null)
				return false;
		} else if (!asignee.equals(other.asignee))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (solution == null) {
			if (other.solution != null)
				return false;
		} else if (!solution.equals(other.solution))
			return false;
		if (status_assign != other.status_assign)
			return false;
		if (status_complete != other.status_complete)
			return false;
		if (status_queue != other.status_queue)
			return false;
		if (time_created == null) {
			if (other.time_created != null)
				return false;
		} else if (!time_created.equals(other.time_created))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Ticket [id=" + id + ", title=" + title + ", author=" + author + ", description=" + description
				+ ", time_created=" + time_created + ", department=" + department + ", asignee=" + asignee
				+ ", solution=" + solution + ", status_queue=" + status_queue + ", status_assign=" + status_assign
				+ ", status_complete=" + status_complete + "]";
	}
	
	



//	public Technician getTechnician() {
//		return technician;
//	}
//
//
//
//	public void setTechnician(Technician technician) {
//		this.technician = technician;
//	}


}
