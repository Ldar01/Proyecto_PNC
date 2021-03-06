package com.uca.capas.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(schema="public",name="expedientes")
public class Expediente {
	
	@Id
	@Column(name="id_estudiante")
	public Integer id_estudiante;

	@Column(name="nombre")
	public String nombre;

	@Column(name="apellido")
	public String apellido;

	@Column(name="materias_ap")
	public int materias_ap;

	@Column(name="materias_rep")
	public int materias_rep;

	@Column(name="promedio")
	public Float promedio;
	
	public Expediente() {
		
	}

	public Integer getId_estudiante() {
		return id_estudiante;
	}

	public void setId_estudiante(Integer id_estudiante) {
		this.id_estudiante = id_estudiante;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getMaterias_ap() {
		return materias_ap;
	}

	public void setMaterias_ap(int materias_ap) {
		this.materias_ap = materias_ap;
	}

	public int getMaterias_rep() {
		return materias_rep;
	}

	public void setMaterias_rep(int materias_rep) {
		this.materias_rep = materias_rep;
	}

	public Float getPromedio() {
		return promedio;
	}

	public void setPromedio(Float promedio) {
		this.promedio = promedio;
	}
	
	

}