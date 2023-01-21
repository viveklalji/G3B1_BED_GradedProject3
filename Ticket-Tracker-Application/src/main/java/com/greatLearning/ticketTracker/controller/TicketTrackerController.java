package com.greatLearning.ticketTracker.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.greatLearning.ticketTracker.model.TicketTracker;
import com.greatLearning.ticketTracker.service.TicketTrackerService;

@Controller
public class TicketTrackerController {

	private final TicketTrackerService ticketTrackerService;

	public TicketTrackerController(TicketTrackerService ticketTrackerService) {
		this.ticketTrackerService = ticketTrackerService;
	}

	@GetMapping("/tickets")
	public String listTickets(Model model) {
		model.addAttribute("tickets", this.ticketTrackerService.getAllTicket());
		return "tickets";
	}

	@GetMapping("/tickets/edit/{id}")
	public String editTicketForm(@PathVariable Long id, Model model) {
		model.addAttribute("ticket", ticketTrackerService.getTicketById(id));
		return "edit_ticket";
	}

	@GetMapping("/tickets/new")
	public String createTicketForm(Model model) {
		TicketTracker ticketTracker = new TicketTracker();
		model.addAttribute("ticket", ticketTracker);
		return "create_ticket";
	}

	@GetMapping("/tickets/view/{id}")
	public String viewTicketForm(@PathVariable Long id, Model model) {
		model.addAttribute("ticket", ticketTrackerService.getTicketById(id));
		return "view_ticket";
	}

	@PostMapping("/tickets")
	public String saveTicket(@ModelAttribute("ticket") TicketTracker ticketTracker) {
		ticketTrackerService.saveTicket(ticketTracker);
		return "redirect:/tickets";
	}

	@GetMapping("/tickets/{id}")
	public String deleteTicket(@PathVariable Long id) {
		ticketTrackerService.deleteTicketById(id);
		return "redirect:/tickets";
	}

	@PostMapping("/tickets/{id}")
	public String updateTicket(@PathVariable Long id, @ModelAttribute("ticket") TicketTracker ticketTracker,
			Model model) {

		TicketTracker existingTicket = ticketTrackerService.getTicketById(id);
		existingTicket.setId(id);
		existingTicket.setTicketTitle(ticketTracker.getTicketTitle());
		existingTicket.setTicketShortDescription(ticketTracker.getTicketShortDescription());
		existingTicket.setTicketContent(ticketTracker.getTicketContent());

		ticketTrackerService.updateTicket(existingTicket);
		return "redirect:/tickets";
	}

	@GetMapping(path = { "/", "/search" })
	public String searchForm(TicketTracker ticketTracker, Model model, String keyword) {
		if (keyword != null) {
			List<TicketTracker> list = ticketTrackerService.getByKeyword(keyword);
			model.addAttribute("tickets", list);
		} else {
			List<TicketTracker> list = ticketTrackerService.getAllTicket();
			model.addAttribute("list", list);
		}
		return "tickets";
	}
}
