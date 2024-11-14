package com.basic.assignment.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Incident {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String incidentId;  // RMG + Random 5-digits + Current Year

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private String field;
    private String reporterName;
    private String details;
    private LocalDateTime reportedDateTime;
    private String priority;
    private String status;
	
    public Incident() {
		super();
		
	}

	public Incident(Long id, String incidentId, User user, String reporterName, String details,
			LocalDateTime reportedDateTime, String priority, String status,String field) {
		super();
		this.id = id;
		this.incidentId = incidentId;
		this.user = user;
		this.reporterName = reporterName;
		this.details = details;
		this.reportedDateTime = reportedDateTime;
		this.priority = priority;
		this.status = status;
		this.field=field;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getReporterName() {
		return reporterName;
	}

	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public LocalDateTime getReportedDateTime() {
		return reportedDateTime;
	}

	public void setReportedDateTime(LocalDateTime reportedDateTime) {
		this.reportedDateTime = reportedDateTime;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
    
	
    
    
    

}
