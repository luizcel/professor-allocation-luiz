package com.project.professorallocation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.model.Course;

import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")

public class CourseServiceTests {

    @Autowired
    CourseService courseService;

    @Test
    public void findAll() {
        List<Course> courses = courseService.findAll();

        courses.forEach(System.out::println);
    }

    @Test
    public void findAllByName() {
        String name = "course";

        List<Course> courses = courseService.findAll(name);

        courses.forEach(System.out::println);
    }

    @Test
    public void findById() {
        Long id = 1L;

        Course course = courseService.findById(id);

        System.out.println(course);
    }

    @Test
    public void create() {
        Course course = new Course();
        course.setId(null);
        course.setName("Course 1");

        course = courseService.create(course);

        System.out.println(course);
    }

    @Test
    public void update() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Course 2");

        course = courseService.update(course);

        System.out.println(course);
    }

    @Test
    public void deleteById() {
        Long id = 1L;

        courseService.deleteById(id);
    }

    @Test
    public void deleteAll() {
        courseService.deleteAll();
    }
}

