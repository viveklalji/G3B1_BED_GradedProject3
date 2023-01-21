package com.greatLearning.ticketTracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatLearning.ticketTracker.dao.TicketTrackerJpaRepository;
import com.greatLearning.ticketTracker.model.TicketTracker;

@Service
public class TicketTrackerServiceImpl implements TicketTrackerService {

	private final TicketTrackerJpaRepository ticketRepository;

	public TicketTrackerServiceImpl(TicketTrackerJpaRepository employeeRepository) {
		this.ticketRepository = employeeRepository;
	}

	@Override
	public List<TicketTracker> getAllTicket() {
		List<TicketTracker> ticketTrackers = this.ticketRepository.findAll();
		return ticketTrackers;
	}

	@Override
	public TicketTracker saveTicket(TicketTracker ticketTracker) {
		return this.ticketRepository.save(ticketTracker);
	}

	@Override
	public TicketTracker getTicketById(Long id) {
		return this.ticketRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("invalid employee id passed"));
	}

	@Override
	public TicketTracker updateTicket(TicketTracker ticketTracker) {
		return this.ticketRepository.save(ticketTracker);
	}

	@Override
	public void deleteTicketById(Long id) {
		this.ticketRepository.deleteById(id);

	}

	@Override
	public List<TicketTracker> getByKeyword(String keyword) {
		return ticketRepository.findByKeyword(keyword);
	}

}
