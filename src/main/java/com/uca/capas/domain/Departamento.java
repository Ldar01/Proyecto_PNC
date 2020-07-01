package com.uca.capas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="public", name="departamentos")
public class Departamento {
	
	@Id
	@Column(name="id_departamento")
	private Integer id_departamento;
	
	@Column(name="nombre_departamento")
	private String nombre_departamento;
	
	public Departamento() {
		
	}
	
	public Integer getId_departamento() {
		return id_departamento;
	}
	public void setId_departamento(Integer id_departamento) {
		this.id_departamento = id_departamento;
	}
	public String getNombre_departamento() {
		return nombre_departamento;
	}
	public void setNombre_departamento(String nombre_departamento) {
		this.nombre_departamento = nombre_departamento;
	}
	
}
