package com.uca.capas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(schema = "public", name = "instituciones")
public class CentroEscolar {
	
	@Id
	@Column(name = "id_institucion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_institucion;
	
	@NotEmpty(message = "Este campo no puede estar vacio.")
	@Size(message = "El nombre de la institucion no debe tener más de 100 caracteres", max = 100)
	@Column(name = "nombre_institucion")
	private String nombre_institucion;
	
	@Column(name = "municipio")
	private Integer municipio;
	
	@Column(name = "estado")
	private Boolean estado;
	
	public CentroEscolar() {}
	
	public Integer getId_institucion() {
		return id_institucion;
	}
	
	public void setId_institucion(Integer id_institucion) {
		this.id_institucion = id_institucion;
	}
	
	public String getNombre_institucion() {
		return nombre_institucion;
	}
	
	public void setNombre_institucion(String nombre_institucion) {
		this.nombre_institucion = nombre_institucion;
	}
	
	public Integer getMunicipio() {
		return municipio;
	}
	
	public void setMunicipio(Integer municipio) {
		this.municipio = municipio;
	}
	
	public Boolean getEstado() {
		return estado;
	}
	
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
}
