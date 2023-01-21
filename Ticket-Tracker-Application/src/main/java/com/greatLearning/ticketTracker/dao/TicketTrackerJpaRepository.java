package com.greatLearning.ticketTracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greatLearning.ticketTracker.model.TicketTracker;

@Repository
public interface TicketTrackerJpaRepository extends JpaRepository<TicketTracker, Long> {

	@Query(value = "select * from ticket t where t.ticket_title like %:keyword% or t.ticket_short_description like %:keyword%", nativeQuery = true)
	List<TicketTracker> findByKeyword(@Param("keyword") String keyword);

}
