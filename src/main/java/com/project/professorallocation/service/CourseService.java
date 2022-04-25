package com.project.professorallocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professorallocation.model.Course;
import com.project.professorallocation.repository.CourseRepository;

@Service
public class CourseService {
	private final CourseRepository repository;

	public CourseService(CourseRepository repository) {
		super();
		this.repository = repository;
	}

	public Course create(Course course) {
		course.setId(null);
		return saveInternal(course);
	}

	public Course update(Course course) {
		Long id = course.getId();
		if (id != null && repository.existsById(id)) {
			return saveInternal(course);
		} else {
			return null;
		}
	}
	
	public void deleteById(Long id) {
		if(repository.existsById(id)) {
		repository.deleteById(id); 
		}
	}
	
	public Course findById(Long id) {
		return repository.findById(id).orElse(null);	
	}
	
	public List<Course> findAll(){
		return repository.findAll();
	}
	
	private Course saveInternal(Course course) {
		Course createdCourse = repository.save(course);
		return createdCourse;
	}

	public void deleteAll() {	
		repository.deleteAllInBatch();
	}

	public List<Course> findAll(String name) {
		return repository.findAll();
	}

}
