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

import com.project.professorallocation.model.Department;
import com.project.professorallocation.service.DepartmentService;

@RestController
@RequestMapping(path = "/departments")

public class DepartmentController {

	  private final DepartmentService service;

	    public DepartmentController(DepartmentService departmentService) {
	        super();
	        this.service = departmentService;
	    }
	    
	    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<List<Department>> findALL() {
	    	List<Department> allDepartments = service.findAll(null);
	    	return new ResponseEntity<>(allDepartments, HttpStatus.OK);
	    }
	    
	    @GetMapping(path = "/{dept_id}",produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<Department> findById(@PathVariable(name = "dept_id") Long id) {
	    	Department item = service.findById(id);
	    	
	    	if (item == null) {
	    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	}else {
	    	return new ResponseEntity<>(item, HttpStatus.OK);
	    }
	    
	}
	    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.CREATED)
	    public ResponseEntity<Department> create(@RequestBody Department dept){
	    	Department item = service.create(dept);
	    	
	    	return new ResponseEntity<>(item, HttpStatus.CREATED);
	    }
	    
	    //curl -v --request POST --header "Content-Type: application/json" --header "Accept: application/json" --data-raw "{\"name\": \"Departamento de Design\"}" "http://localhost:8082/departments"
	    
}
