package com.uca.capas.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(schema="public",name="estudiantes")
public class Estudiante {
	
	@Id
	@Column(name="id_estudiante")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_estudiante;
	
	@Column(name="primer_nombre")
	@NotEmpty
	@Size(min=1,max=12,message = "El campo no debe tener más de 12 caracteres")
	private String primer_nombre;
	
	@Column(name="segundo_nombre")
	@NotEmpty
	@Size(min=1,max=12,message = "El campo no debe tener más de 12 caracteres")
	private String segundo_nombre;
	
	@Column(name="primer_apellido")
	@NotEmpty
	@Size(min=1,max=12,message = "El campo no debe tener más de 12 caracteres")
	private String primer_apellido;
	
	@Column(name="segundo_apellido")
	@NotEmpty
	@Size(min=1,max=12,message = "El campo no debe tener más de 12 caracteres")
	private String segundo_apellido;
	
	@Column(name="carnet")
	@NotEmpty
	@Size(min=1,max=9,message = "El campo no debe tener más de 9 caracteres")
	private String carnet;

	@Column(name="f_nacimiento")
	@NotNull(message = "Este campo no puede quedar vacío")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate f_nacimiento;
	
	@Column(name="edad")
	private Integer edad;
	
	@Column(name="direccion")
	@NotEmpty
	@Size(min=1,max=100,message = "El campo no debe tener más de 100 caracteres")
	private String direccion;
	
	@Column(name="tel_f")
	@NotEmpty
	@Size(min=1,max=8,message = "El campo no debe tener más de 8 caracteres")
	private String tel_f;
	
	@Column(name="tel_m")
	@NotEmpty
	@Size(min=1,max=8,message = "El campo no debe tener más de 8 caracteres")
	private String tel_m;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "institucion")
	private CentroEscolar institucion;
	
	@Column(name="nombre_padre")
	@NotEmpty
	@Size(min=1,max=50,message = "El campo no debe tener más de 50 caracteres")
	private String nombre_padre;
	
	@Column(name="nombre_madre")
	@NotEmpty
	@Size(min=1,max=8,message = "El campo no debe tener más de 50 caracteres")
	private String nombre_madre;


	public Estudiante() {
	}


	

	public Integer getId_estudiante() {
		return id_estudiante;
	}




	public void setId_estudiante(Integer id_estudiante) {
		this.id_estudiante = id_estudiante;
	}




	public String getPrimer_nombre() {
		return primer_nombre;
	}


	public void setPrimer_nombre(String primer_nombre) {
		this.primer_nombre = primer_nombre;
	}


	public String getSegundo_nombre() {
		return segundo_nombre;
	}


	public void setSegundo_nombre(String segundo_nombre) {
		this.segundo_nombre = segundo_nombre;
	}


	public String getPrimer_apellido() {
		return primer_apellido;
	}


	public void setPrimer_apellido(String primer_apellido) {
		this.primer_apellido = primer_apellido;
	}


	public String getSegundo_apellido() {
		return segundo_apellido;
	}


	public void setSegundo_apellido(String segundo_apellido) {
		this.segundo_apellido = segundo_apellido;
	}


	public String getCarnet() {
		return carnet;
	}


	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}


	public LocalDate getF_nacimiento() {
		return f_nacimiento;
	}


	public void setF_nacimiento(LocalDate f_nacimiento) {
		this.f_nacimiento = f_nacimiento;
	}


	public Integer getEdad() {
		return edad;
	}


	public void setEdad(Integer edad) {
		this.edad = edad;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getTel_f() {
		return tel_f;
	}


	public void setTel_f(String tel_f) {
		this.tel_f = tel_f;
	}


	public String getTel_m() {
		return tel_m;
	}


	public void setTel_m(String tel_m) {
		this.tel_m = tel_m;
	}


	public CentroEscolar getInstitucion() {
		return institucion;
	}


	public void setInstitucion(CentroEscolar institucion) {
		this.institucion = institucion;
	}


	public String getNombre_padre() {
		return nombre_padre;
	}


	public void setNombre_padre(String nombre_padre) {
		this.nombre_padre = nombre_padre;
	}


	public String getNombre_madre() {
		return nombre_madre;
	}


	public void setNombre_madre(String nombre_madre) {
		this.nombre_madre = nombre_madre;
	}
	
	

}