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

import com.project.professorallocation.model.Professor;
import com.project.professorallocation.service.ProfessorService;

@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {
	
	private final ProfessorService service;

    public ProfessorController(ProfessorService professorService) {
        super();
        this.service = professorService;
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Professor>> findALL() {
    	List<Professor> allprofessors = service.findAll(null);
    	return new ResponseEntity<>(allprofessors, HttpStatus.OK);
    }
    
    @GetMapping(path = "/{prof_id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Professor> findById(@PathVariable(name = "prof_id") Long id) {
    	Professor item = service.findById(id);
    	
    	if (item == null) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}else {
    	return new ResponseEntity<>(item, HttpStatus.OK);
    }
    
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Professor> create(@RequestBody Professor professor){
    	Professor item = service.create(professor);
    	
    	return new ResponseEntity<>(item, HttpStatus.CREATED);
    }
    
    //curl -v --request POST --header "Content-Type: application/json" --header "Accept: application/json" --data-raw "{\"name\": \"Amirton\", \"cpf\": \"12345678911\", \"departmentId\": \"6\"}" "http://localhost:8082/professors"
    
    @PutMapping(path = "/{prof_id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Professor> update(@PathVariable(name = "prof_id") Long id, @RequestBody Professor professor){
    	professor.setId(id);
    	Professor item = service.update(professor);
    	
    	if (item == null) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	} else {
    	return new ResponseEntity<>(item, HttpStatus.OK);}
    	}
    //curl -v --request PUT --header "Content-Type: application/json" --header "Accept: application/json" --data-raw "{\"name\": \"Luiz\", \"cpf\": \"00818990327\", \"departmentId\": \"6\"}" "http://localhost:8082/professors/4"
    
    @DeleteMapping(path = "/{prof_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable(name = "prof_id") Long id){
    	
    	service.deleteById(id);
    	
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //curl -v --request DELETE "http://localhost:8082/professors/4"

}
