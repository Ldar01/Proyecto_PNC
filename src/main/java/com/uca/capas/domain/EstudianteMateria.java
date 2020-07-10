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
@Table(schema="public",name="materias_cursadas")
public class EstudianteMateria {
	
	@Id
	@Column(name="id_materia_cursada")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_materia_cursada;
	
	@Column(name="anio")
	@Min(2005)
	@Max(2020)
	private int anio;
	
	@Column(name="id_materia")
	private int id_materia;
	
	@Column(name="id_estudiante")
	private int id_estudiante;
	
	@Column(name="ciclo")
	private String ciclo;
	
	@Column(name="nota")
	@DecimalMin("0.0")
	@DecimalMax("10.0")
	private Float nota;
	
	public EstudianteMateria() {}

	public Integer getId_materia_cursada() {
		return id_materia_cursada;
	}

	public void setId_materia_cursada(Integer id_materia_cursada) {
		this.id_materia_cursada = id_materia_cursada;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getId_materia() {
		return id_materia;
	}

	public void setId_materia(int id_materia) {
		this.id_materia = id_materia;
	}

	public int getId_estudiante() {
		return id_estudiante;
	}

	public void setId_estudiante(int id_estudiante) {
		this.id_estudiante = id_estudiante;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public Float getNota() {
		return nota;
	}

	public void setNota(Float nota) {
		this.nota = nota;
	}
	
	

}