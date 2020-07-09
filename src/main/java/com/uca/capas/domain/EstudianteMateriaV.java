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
public class EstudianteMateriaV {
	
	@Id
	@Column(name="id_materia_cursada")
	private Integer id_materia_cursada;
	
	@Column(name="anio")
	private int anio;
	
	@Column(name="materia")
	private String materia;
	
	@Column(name="ciclo")
	private String ciclo;
	
	@Column(name="nota")
	private Float nota;
	
	@Column(name="resultado")
	private String resultado;
	
	public EstudianteMateriaV() {}

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

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
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

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	


}