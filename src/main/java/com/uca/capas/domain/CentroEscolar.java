package com.uca.capas.domain;

import javax.persistence.*;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "municipio")
	private Municipio municipio;
	
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
	
	public Municipio getMunicipio() {
		return municipio;
	}
	
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	
	public Boolean getEstado() {
		return estado;
	}
	
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getEstado_delegate() {
		return this.estado ? "Activa" : "No Activa";
	}
}
