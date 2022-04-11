package com.project.professorallocation.model;

import java.sql.Date;
import java.time.DayOfWeek;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "allocation")
public class Allocation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "day")
	private DayOfWeek dayOfWeek;
	
	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date startHour;
	
	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date endtHour;
	
	
	private Long professorId;
	
	private Long courseId;

	public Allocation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Date getStartHour() {
		return startHour;
	}

	public void setStartHour(Date startHour) {
		this.startHour = startHour;
	}

	public Date getEndtHour() {
		return endtHour;
	}

	public void setEndtHour(Date endtHour) {
		this.endtHour = endtHour;
	}

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "Allocation [id=" + id + ", professorId=" + professorId + ", courseId=" + courseId + "]";
	}
	
	

}
