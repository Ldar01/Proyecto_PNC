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
@Table(schema = "public", name = "materias")
public class Materia {

	@Id
	@Column(name = "id_institucion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_materia;
	
	@NotEmpty(message = "Este campo no puede estar vacio.")
	@Size(message = "El nombre de la materia no debe tener más de 20 caracteres", max = 20)
	@Column(name = "nombre_materia")
	private String nombre_materia;
	
	@Column(name = "estado")
	private Boolean estado;
	
	public Materia() {}

	public Integer getId_materia() {
		return id_materia;
	}

	public void setId_materia(Integer id_materia) {
		this.id_materia = id_materia;
	}

	public String getNombre_materia() {
		return nombre_materia;
	}

	public void setNombre_materia(String nombre_materia) {
		this.nombre_materia = nombre_materia;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}	
}
