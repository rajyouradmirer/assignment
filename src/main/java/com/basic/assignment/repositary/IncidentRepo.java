package com.basic.assignment.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.basic.assignment.entity.Incident;
import java.util.List;
import java.util.Optional;


@Repository
public interface IncidentRepo extends JpaRepository<Incident, Long> {
	
	
	List<Incident> findByUser_id(Long user);
	
	 Optional<Incident> findByIncidentId(String incidentId);
}
