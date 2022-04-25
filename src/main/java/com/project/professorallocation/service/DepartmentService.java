package com.project.professorallocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professorallocation.model.Department;
import com.project.professorallocation.repository.DepartmentRepository;

@Service
public class DepartmentService {
	private final DepartmentRepository repository;

	public DepartmentService(DepartmentRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Department create(Department department) {
		department.setId(null);
		return saveInternal(department);
	}
	
	public Department update(Department department) {
		Long id = department.getId();
		if(id != null && repository.existsById(id)) {
			return saveInternal(department);
		}else {
			return null;
		}
	}
	
	public void deleteById(Long id) {
		if(repository.existsById(id)){
			repository.deleteById(id);
		}
	}
	
	public Department findById(Long id) {
		return repository.findById(id).orElse(null);	
	}
	
	public List<Department> findAll(){
		return repository.findAll();
	}
	
	private Department saveInternal(Department department) {
		Department createdDepartment = repository.save(department);
		return createdDepartment;
	}

	public List<Department> findAll(Object object) {
		return repository.findAll();
	}

	public void deleteAll() {
		repository.deleteAllInBatch();
	}
}
