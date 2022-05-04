package com.project.professorallocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.professorallocation.model.Course;
import com.project.professorallocation.service.CourseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/courses")
public class CourseController {

	  private final CourseService service;

	    public CourseController(CourseService courseService) {
	        super();
	        this.service = courseService;
	    }
	    
	    @ApiOperation(value = "Find All Courses")
	    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
	    })
	    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<List<Course>> findAll() {
	    	List<Course> allcourses = service.findAll(null);
	    	return new ResponseEntity<>(allcourses, HttpStatus.OK);
	    }
	    
	    @ApiOperation(value = "Find Course By Id")
	    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
	    })
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
	   
	    @ApiOperation(value = "Create a Course")
	    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")
	    })
	    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.CREATED)
	    public ResponseEntity<Course> create(@RequestBody Course course){
	    	Course item = service.create(course);
	    	
	    	return new ResponseEntity<>(item, HttpStatus.CREATED);
	    }
	    
	    //curl -v --request POST --header "Content-Type: application/json" --header "Accept: application/json" --data-raw "{\"name\": \"BackEnd\"}" "http://localhost:8082/courses"
	    
	    @ApiOperation(value = "Update a Course")
	    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
	    })
	    @PutMapping(path = "/{course_id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.CREATED)
	    public ResponseEntity<Course> update(@PathVariable(name = "course_id") Long id, @RequestBody Course course){
	    	course.setId(id);
	    	Course item = service.update(course);
	    	
	    	if (item == null) {
	    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	} else {
	    	return new ResponseEntity<>(item, HttpStatus.OK);}
	    	}
	    //curl -v --request PUT --header "Content-Type: application/json" --header "Accept: application/json" --data-raw "{\"name\": \"Design\"}" "http://localhost:8082/courses/3"
	    
	    @ApiOperation(value = "Delete")
	    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content")
	    })
	    @DeleteMapping(path = "/{course_id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public ResponseEntity<Void> delete(@PathVariable(name = "course_id") Long id){
	    	
	    	service.deleteById(id);
	    	
	    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    //curl -v --request DELETE "http://localhost:8082/courses/3"
}

