package com.project.professorallocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.professorallocation.model.Course;
import com.project.professorallocation.service.CourseService;

@RestController
@RequestMapping(path = "/courses")
public class CourseController {

	  private final CourseService service;

	    public CourseController(CourseService courseService) {
	        super();
	        this.service = courseService;
	    }
	    
	    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<List<Course>> findALL() {
	    	List<Course> allcourses = service.findAll(null);
	    	return new ResponseEntity<>(allcourses, HttpStatus.OK);
	    }
	    
	    @GetMapping(path = "/{course_id}",produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<Course> findById(@PathVariable(name = "course_id") Long id) {
	    	Course item = service.findById(id);
	    	
	    	if (item == null) {
	    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	}else {
	    	return new ResponseEntity<>(item, HttpStatus.OK);
	    }
	    
	}
	    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.CREATED)
	    public ResponseEntity<Course> create(@RequestBody Course course){
	    	Course item = service.create(course);
	    	
	    	return new ResponseEntity<>(item, HttpStatus.CREATED);
	    }
	    
	    //curl -v --request POST --header "Content-Type: application/json" --header "Accept: application/json" --data-raw "{\"name\": \"BackEnd\"}" "http://localhost:8082/courses"
	    
}
