package com.project.professorallocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professorallocation.model.Professor;
import com.project.professorallocation.repository.ProfessorRepository;

@Service
public class ProfessorService {
	private final ProfessorRepository repository;

	public ProfessorService(ProfessorRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Professor create(Professor professor) {
		professor.setId(null);
		return saveInternal(professor);
	}

	public Professor update(Professor professor) {
		Long id = professor.getId();
		if (id != null && repository.existsById(id)) {
			return saveInternal(professor);
		} else {
			return null;
		}
	}
	
	public void deleteById(Long id) {
		if(repository.existsById(id)) {
		repository.deleteById(id); 
		}
	}
	
	public Professor findById(Long id) {
		return repository.findById(id).orElse(null);	
	}
	
	public List<Professor> findAll(){
		return repository.findAll();
	}
	
	private Professor saveInternal(Professor professor) {
		Professor createdProfessor = repository.save(professor);
		return createdProfessor;
	}

	public List<Professor> findAll(String name) {
		return repository.findAll();
	}

	public List<Professor> findByDepartment(Long departmentId) {
		return repository.findAll();
	}

	public void deleteAll() {
		repository.deleteAllInBatch();
	}
	
}
