package com.project.professorallocation.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professorallocation.model.Allocation;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTests {
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");
	
	@Autowired
	private AllocationRepository repository;
	
	@Test
	public void findAll() {
		List<Allocation> items = repository.findAll();
		
		System.out.println("Quantidade de elementos retornados:" + items.size());
		for(Allocation item : items) {
			System.out.println(item);
			}
	}
	
	@Test
	public void create() throws ParseException{
		Allocation allocation = new Allocation();
		allocation.setDayOfWeek(DayOfWeek.TUESDAY);
		allocation.setStartHour(sdf.parse("19:00-0300"));
		allocation.setEndHour(sdf.parse("21:00-0300"));
		allocation.setProfessorId(1L);
		allocation.setCourseId(1L);
		
		allocation = repository.save(allocation);
		
		System.out.println(allocation);
	}
}