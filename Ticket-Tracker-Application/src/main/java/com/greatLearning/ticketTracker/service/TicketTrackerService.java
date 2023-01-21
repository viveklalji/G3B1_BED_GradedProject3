package com.greatLearning.ticketTracker.service;

import java.util.List;

import com.greatLearning.ticketTracker.model.TicketTracker;

public interface TicketTrackerService {
	
	List<TicketTracker> getAllTicket();

	TicketTracker saveTicket(TicketTracker ticketTracker);

	TicketTracker getTicketById(Long id);

	TicketTracker updateTicket(TicketTracker ticketTracker);	

	void deleteTicketById(Long id);
	
	List<TicketTracker> getByKeyword(String keyword);

}
