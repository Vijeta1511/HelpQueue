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
	

	// Default constructor
	public Ticket() {
		super();
	}
	
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
}
