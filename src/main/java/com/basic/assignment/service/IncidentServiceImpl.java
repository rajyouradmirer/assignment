package com.basic.assignment.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.assignment.Exception.IncidentNotFoundException;
import com.basic.assignment.Exception.UserNotFoundException;
import com.basic.assignment.entity.Incident;
import com.basic.assignment.entity.User;
import com.basic.assignment.repositary.IncidentRepo;
import com.basic.assignment.repositary.UserRepo;

@Service
public class IncidentServiceImpl implements IncidentService {

	@Autowired
	private IncidentRepo incidentRepo;

	@Autowired
	private UserRepo userRepo;

	@Override
	public Incident createIncident(Incident incident, Long user_id) {
		Optional<User> user = userRepo.findById(user_id);

		if (user.isPresent()) {

			String incidentId = "RMG" + (10000 + new Random().nextInt(99999)) + LocalDate.now().getYear();

			incident.setUser(user.get());
			incident.setIncidentId(incidentId);
			incident.setReportedDateTime(LocalDateTime.now());
			Incident save = incidentRepo.save(incident);
			return save;
		} else {
			throw new UserNotFoundException("user not found");
		}
	}

	@Override
	public List<Incident> getUserIncidents(Long userId) {

		List<Incident> list = incidentRepo.findByUser_id(userId);

		if (list.isEmpty()) {
			throw new UserNotFoundException("user not found");
		} else {
			return list;
		}

	}

	@Override
	public Optional<Incident> getIncidentById(String incidentId) {

		Optional<Incident> byIncidentId = incidentRepo.findByIncidentId(incidentId);
		
		if(byIncidentId.isPresent()) {
			return byIncidentId;
		}else {
			throw new IncidentNotFoundException(incidentId);
		}
	}

	@Override
	public Incident updateIncident(Incident existingIncident) {

		return incidentRepo.save(existingIncident);
	}

}
