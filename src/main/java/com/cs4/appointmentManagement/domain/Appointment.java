package com.cs4.appointmentManagement.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Appointment implements Serializable {
	/**
	 * 
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1594161114542673925L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private Long Id;
	
	/**
	 * 
	 */
	private Date dateTime;
	
	/**
	 * 
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	private Doctor doctor;
	
	/**
	 * 
	 */
	@Lob
	private String description;
	
	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 
	 */
	private int status;
	
	/**
	 * @return
	 */
	public Long getId() {
		return Id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id) {
		Id = id;
	}

	/**
	 * @return
	 */
	public Date getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime
	 */
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * @return
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * @param doctor
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * @return
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @param patient
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * 
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	private Patient patient;
	
}
