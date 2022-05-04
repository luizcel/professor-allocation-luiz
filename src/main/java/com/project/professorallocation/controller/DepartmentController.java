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

import com.project.professorallocation.model.Department;
import com.project.professorallocation.service.DepartmentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/departments")

public class DepartmentController {

	  private final DepartmentService service;

	    public DepartmentController(DepartmentService departmentService) {
	        super();
	        this.service = departmentService;
	    }
	    
	    @ApiOperation(value = "Find All Departments")
	    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
	    })

	    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<List<Department>> findAll() {
	    	List<Department> allDepartments = service.findAll(null);
	    	return new ResponseEntity<>(allDepartments, HttpStatus.OK);
	    }
	    
	    @ApiOperation(value = "Find Department By Id")
	    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
	    })
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
	    
	    @ApiOperation(value = "Create")
	    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")
            })
	    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.CREATED)
	    public ResponseEntity<Department> create(@RequestBody Department dept){
	    	Department item = service.create(dept);
	    	
	    	return new ResponseEntity<>(item, HttpStatus.CREATED);
	    }
	    
	    //curl -v --request POST --header "Content-Type: application/json" --header "Accept: application/json" --data-raw "{\"name\": \"Departamento de Design\"}" "http://localhost:8082/departments"
	    
	    @ApiOperation(value = "Update")
	    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
	    })
	    @PutMapping(path = "/{dept_id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.CREATED)
	    public ResponseEntity<Department> update(@PathVariable(name = "dept_id") Long id, @RequestBody Department dept){
	    	dept.setId(id);
	    	Department item = service.update(dept);
	    	
	    	if (item == null) {
	    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	} else {
	    	return new ResponseEntity<>(item, HttpStatus.OK);}
	    	}
	    //curl -v --request PUT --header "Content-Type: application/json" --header "Accept: application/json" --data-raw "{\"name\": \"department 2\"}" "http://localhost:8082/departments/6"
	    
	    @ApiOperation(value = "Delete")
	    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content")
	    })
	    @DeleteMapping(path = "/{dept_id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public ResponseEntity<Void> delete(@PathVariable(name = "dept_id") Long id){
	    	
	    	service.deleteById(id);
	    	
	    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    //curl -v --request DELETE "http://localhost:8082/departments/13"
}

