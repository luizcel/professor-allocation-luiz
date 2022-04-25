package com.project.professorallocation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.model.Professor;

import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")

public class ProfessorServiceTest {


    @Autowired
    ProfessorService professorService;

    @Test
    public void findAll() {
        List<Professor> professors = professorService.findAll(null);

        professors.forEach(System.out::println);
    }

    @Test
    public void findAllByName() {
        String name = "professor";

        List<Professor> professors = professorService.findAll(name);

        professors.forEach(System.out::println);
    }

    @Test
    public void findByDepartment() {
        Long departmentId = 1L;

        List<Professor> professors = professorService.findByDepartment(departmentId);

        professors.forEach(System.out::println);
    }

    @Test
    public void findById() {
        Long id = 1L;

        Professor professor = professorService.findById(id);

        System.out.println(professor);
    }

    @Test
    public void create() {
        Professor professor = new Professor();
        professor.setId(null);
        professor.setName("Professor 1");
        professor.setCpf("111.111.111-11");
        professor.setDepartmentId(1L);

        professor = professorService.create(professor);

        System.out.println(professor);
    }

    @Test
    public void update() {
        Professor professor = new Professor();
        professor.setId(1L);
        professor.setName("Professor 2");
        professor.setCpf("222.222.222-22");
        professor.setDepartmentId(1L);

        professor = professorService.update(professor);

        System.out.println(professor);
    }

    @Test
    public void deleteById() {
        Long id = 1L;

        professorService.deleteById(id);
        
        Professor professor = professorService.findById(id);
        System.out.println(professor);
    }

    @Test
    public void deleteAll() {
        professorService.deleteAll();
        
        List<Professor> professors = professorService.findAll(null);
        professors.forEach(System.out::println);
    }
}
