/**
 * 
 */
package com.barclays.helpqueue.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.barclays.helpqueue.model.Ticket;

/**
 * @author vijetaagrawal
 *
 */

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
		
	@Query(value = "SELECT * "
			+ "FROM tickets "
			+ "WHERE status_queue = ?1 "
			+ "ORDER BY time_created ASC", nativeQuery = true)
	List<Ticket> findTicketInQueue(Boolean status_queue);
	
	@Query(value = "SELECT * "
			+ "FROM tickets "
			+ "WHERE status_complete  = ?1 "
			+ "ORDER BY time_created ASC", nativeQuery = true)
	List<Ticket> findTicketComplete(Boolean status_complete);
	
	@Query(value = "SELECT * "
			+ "FROM tickets "
			+ "WHERE status_queue  = ?1 AND department = ?2  "
			+ "ORDER BY time_created ASC", nativeQuery = true)
	List<Ticket> findTicketByQueueAndDepartment(Boolean status_queue, String department);
	
	@Query(value = "SELECT * "
			+ "FROM tickets "
			+ "WHERE status_complete  = ?1 AND department = ?2  "
			+ "ORDER BY time_created ASC", nativeQuery = true)
	List<Ticket> findTicketCompleteAndDepartment(Boolean status_complete, String department);
	
	@Query(value = "SELECT department "
			+ "FROM tickets "
			+ "WHERE id  = ?1", nativeQuery = true)
	String findDepartmentById(Long id);
//	
//	@Query(value = "INSERT INTO tickets (author, department, description, title) values (?, ?, ?, ?)", nativeQuery = true)
//	Ticket createTicket(Boolean status_queue);
}
