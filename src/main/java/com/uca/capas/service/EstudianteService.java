package com.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Estudiante;
import org.springframework.stereotype.Service;


public interface EstudianteService {
	
	public List<Estudiante> findAll() throws DataAccessException;
	
	public Estudiante findOne(Integer id_estudiante) throws DataAccessException;
	
	public void insert(Estudiante estudiante) throws DataAccessException;
	
	public void delete(Integer id_estudiante) throws DataAccessException;
	
}
