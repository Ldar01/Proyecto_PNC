package com.uca.capas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="public", name="municipios")
public class Municipio {
	
	@Id
	@Column(name="id_municipio")
	private Integer id_municipio;
	
	@Column(name="nombre_municipio")
	private String nombre_municipio;
	
	@Column(name="id_departamento")
	private Integer id_departamento;
	
	public Municipio() {
		
	}
	
	public Integer getId_municipio() {
		return id_municipio;
	}
	public void setId_municipio(Integer id_municipio) {
		this.id_municipio = id_municipio;
	}
	public String getNombre_municipio() {
		return nombre_municipio;
	}
	public void setNombre_municipio(String nombre_municipio) {
		this.nombre_municipio = nombre_municipio;
	}
	public Integer getId_departamento() {
		return id_departamento;
	}
	public void setId_departamento(Integer id_departamento) {
		this.id_departamento = id_departamento;
	}
	
	

}
