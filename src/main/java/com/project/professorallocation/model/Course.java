package com.project.professorallocation.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String name;

	@Transient
	private List<Allocation> allocations;
	
	public Long getId() {
		return id;
	}
	
	public Course() {
		super();
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", nome=" + name + "]";
	}

	@JsonIgnore
	public List<Allocation> getAllocations() {
	        return allocations;
	    }

	    public void setAllocations(List<Allocation> allocations) {
	        this.allocations = allocations;
	    }
	
	
}
