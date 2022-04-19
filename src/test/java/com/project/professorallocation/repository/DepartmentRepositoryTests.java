package com.project.professorallocation.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.model.Department;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentRepositoryTests {

    @Autowired
    private DepartmentRepository repository;
    
    @Test
    public void findAll() {
     
        List<Department> items = repository.findAll();
        
        System.out.println("Quantidade de elementos retornados:" + items.size());

        for(Department item : items) {
        	System.out.println(item);
        }
    }
    
    @Test
    public void create() {
    	Department departmentBeingCreated = new Department();
    	departmentBeingCreated.setName("Departamento de enfermagem");
    	
    	departmentBeingCreated = repository.save(departmentBeingCreated);
    	System.out.println(departmentBeingCreated);
    }
    
    @Test
    public void findSpecificDepartment() {
    	Department dept = repository.findById(2L).orElse(null);
    	System.out.println(dept);
    }
    
    @Test
    public void findDepartmentByPartOfTheName() {
    	List<Department> depts = repository.findByNameLike("%gem%");
    	
    	System.out.println("elementos retornados:"+ depts.size());
    	depts.forEach(System.out::println);
    }
    
    @Test
    public void update() {
    Department departmentBeingUpdated = new Department();
    departmentBeingUpdated.setId(9L);
    departmentBeingUpdated.setName("Departamento de Filosofia");
    
    departmentBeingUpdated = repository.save(departmentBeingUpdated);
    System.out.println(departmentBeingUpdated);
    }
    
    @Test
    public void delete() {
    	repository.deleteById(5L);
    }
    
    @Test
    public void deleteAllItems() {
    	repository.deleteAllInBatch();
    }
}