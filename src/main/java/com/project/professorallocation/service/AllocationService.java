package com.project.professorallocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professorallocation.model.Allocation;
import com.project.professorallocation.model.Course;
import com.project.professorallocation.model.Professor;
import com.project.professorallocation.repository.AllocationRepository;

@Service
public class AllocationService {
	private final AllocationRepository repository;
    private final ProfessorService professorService;
    private final CourseService courseService;

    public AllocationService(AllocationRepository allocationRepository, ProfessorService professorService, CourseService courseService) {
        super();
        this.repository = allocationRepository;
        this.professorService = professorService;
        this.courseService = courseService;
    }

	public Allocation findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public List<Allocation> findAll() {
		return repository.findAll();
	}
	
	public List<Allocation> findByProfessor(Long id) {
		return repository.findByProfessorId(id);
	}

	public void deleteById(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		}
	}
	
	public void deleteAllInBatch() { 
	}

	public Allocation create(Allocation allocation) {
		allocation.setId(null);
		return saveInternal(allocation);

	}
	
	public Allocation update(Allocation allocation) {
		Long id = allocation.getId();
		if (id != null && repository.existsById(id)) {
			return saveInternal(allocation);
		} else {
			return null;
		}
	}

	Allocation saveInternal(Allocation allocation) {
		if(!isEndHourGreaterThanStartHour(allocation) || hasCollision(allocation)) {
			throw new RuntimeException("There is a time collision for this allocation");
		} else {
		
		allocation = repository.save(allocation);
		
		Professor professor = professorService.findById(allocation.getProfessorId());
        allocation.setProfessor(professor);

        Course course = courseService.findById(allocation.getCourseId());
        allocation.setCourse(course);
		
		return allocation;
		}
	}

	private boolean isEndHourGreaterThanStartHour(Allocation allocation) {
		return allocation != null && allocation.getStartHour() != null && allocation.getEndHour() != null
	            && allocation.getEndHour().compareTo(allocation.getStartHour()) > 0;
	}

	private boolean hasCollision(Allocation newAllocation) {
		boolean hasCollision = false;
		
		List<Allocation> currentAllocations = repository.findByProfessorId(newAllocation.getProfessorId());

		for (Allocation item : currentAllocations) {
			if (hasCollision(item, newAllocation)) {
				hasCollision = true;
				break;
			}
		}
		return hasCollision;
	}

	private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
		return !currentAllocation.getId().equals(newAllocation.getId())
				&& currentAllocation.getProfessorId().equals(newAllocation.getProfessorId())
				&& currentAllocation.getDayOfWeek().equals(newAllocation.getDayOfWeek())
				&& currentAllocation.getStartHour().compareTo(newAllocation.getEndHour()) < 0
				&& newAllocation.getStartHour().compareTo(currentAllocation.getEndHour()) < 0;
	}

}
