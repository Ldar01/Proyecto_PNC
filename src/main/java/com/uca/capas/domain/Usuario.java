package com.uca.capas.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(schema = "public", name = "Usuarios")
public class Usuario {

	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_usuario;

	@NotEmpty(message = "Este campo no puede estar vacio.")
	@Size(message = "El nombre de usuario no debe tener más de 30 caracteres", max = 30)
	@Column(name = "usuario")
	private String usuario;

	@NotEmpty(message = "Este campo no puede estar vacio.")
	@Column(name = "password")
	private String password;

	@Column(name = "estado")
	private Boolean estado = false; // Por defecto no activa

	@Column(name = "tipo_usuario")
	private Integer tipo_usuario = 2;

	@NotEmpty(message = "Este campo no puede estar vacio.")
	@Size(message = "El nombre no debe tener más de 12 caracteres", max = 12)
	@Column(name = "nombre")
	private String nombre;

	@NotEmpty(message = "Este campo no puede estar vacio.")
	@Size(message = "El apellido no debe tener más de 12 caracteres", max = 12)
	@Column(name = "apellido")
	private String apellido;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Este campo no puede quedar vacío")
	@Column(name = "f_nacimiento")
	private Date f_nacimiento;

	@NotNull(message = "Este campo no puede quedar vacío")
	@Column(name = "edad")
	private Integer edad;

	@Column(name = "departamento")
	private Integer departamento;

	@Column(name = "municipio")
	private Integer municipio;

	@NotEmpty(message = "Este campo no puede estar vacio.")
	@Size(message = "La dirección no debe tener más de 100 caracteres", max = 100)
	@Column(name = "direccion")
	private String direccion;

	public Usuario() {

	}

	public Integer getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(Integer tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre_usuario() {
		return usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.usuario = nombre_usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getF_nacimiento() {
		return f_nacimiento;
	}

	public void setF_nacimiento(Date f_nacimiento) {
		this.f_nacimiento = f_nacimiento;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Integer getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Integer departamento) {
		this.departamento = departamento;
	}

	public Integer getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Integer municipio) {
		this.municipio = municipio;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getRol_delegate() {
		return this.tipo_usuario == 1 ? "ADMIN" : "COORDINADOR";
	}


}
