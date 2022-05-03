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

import com.project.professorallocation.model.Allocation;
import com.project.professorallocation.model.Course;
import com.project.professorallocation.service.AllocationService;

@RestController
@RequestMapping(path = "/allocations")
public class AllocationController {

	private final AllocationService service;

	public AllocationController(AllocationService service) {
		super();
		this.service = service;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Allocation>> findAll() {
		List<Allocation> allAllocations = service.findAll();
		return new ResponseEntity<>(allAllocations, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Allocation> findById(@PathVariable(name = "id") Long id) {
		Allocation allocation = service.findById(id);
		if (allocation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(allocation, HttpStatus.OK);
		}
	}

	@GetMapping(path = "/professor/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Allocation>> findByProfessorId(@PathVariable(name = "id") Long id) {
		List<Allocation> allAllocations = service.findByProfessor(id);
		return new ResponseEntity<>(allAllocations, HttpStatus.OK);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Allocation> create(@RequestBody Allocation allocation) {
		try {
			Allocation item = service.create(allocation);
			return new ResponseEntity<>(item, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	//curl -v --request POST --header "Content-Type: application/json" --header "Accept: application/json" --data-raw "{\"dayOfWeek\": \"MONDAY\", \"startHour\":\"19:00-0300\", \"endHour\": \"21:00-0300\", \"professorId\":\"1\", \"courseId\":\"2\"}" "http://localhost:8082/allocations"

	@PutMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Allocation> update(@PathVariable(name = "id") Long id, @RequestBody Allocation allocation){
    	allocation.setId(id);
    	Allocation item = service.update(allocation);
    	
    	if (item == null) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	} else {
    	return new ResponseEntity<>(item, HttpStatus.OK);}
    	}
    //curl -v --request PUT --header "Content-Type: application/json" --header "Accept: application/json" --data-raw "{\"dayOfWeek\": \"MONDAY\", \"startHour\":\"19:00-0300\", \"endHour\": \"21:00-0300\", \"professorId\":\"1\", \"courseId\":\"2\"}" "http://localhost:8082/allocations/30"
    
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id){
    	
    	service.deleteById(id);
    	
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //curl -v --request DELETE "http://localhost:8082/allocations/35"
}
