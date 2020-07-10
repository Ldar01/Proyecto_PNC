package com.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.domain.Expediente;
import org.springframework.stereotype.Service;


public interface ExpedienteService {
	
	public List<Expediente> findByPrimer_nombreOrPrimer_apellido(String primer_nombre,String primer_apellido) throws DataAccessException;
	
	public Expediente findOne(Integer id_estudiante) throws DataAccessException;
	
	public void insert(Expediente expediente) throws DataAccessException;
	
	//public void delete(Integer id_estudiante) throws DataAccessException;
	
}
