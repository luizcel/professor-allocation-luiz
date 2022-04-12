package com.project.professorallocation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "professor")
public class Professor {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	@Column(length = 11, nullable = false, unique = true)
	private String cpf;
	
	@Column(name= "departament_id", nullable = false)
	private Long departamentId;

	@ManyToOne(optional = false)
	@JoinColumn(name="departament_id", nullable = false, updatable = false, insertable = false)
	private Department departament;
	
	
	
	public Professor() {
		super();
	}

	public Professor(Long id, String name, String cpf, Long departamentId) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.departamentId = departamentId;
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", name=" + name + ", cpf=" + cpf + ", departamentId=" + departamentId + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Long getDepartamentId() {
		return departamentId;
	}

	public void setDepartamentId(Long departamentId) {
		this.departamentId = departamentId;
	}

	

}
