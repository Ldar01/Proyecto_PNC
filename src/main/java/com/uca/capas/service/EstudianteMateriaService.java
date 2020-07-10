package com.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.EstudianteMateria;
import com.uca.capas.domain.EstudianteMateriaV;
import org.springframework.stereotype.Service;


public interface EstudianteMateriaService {
	
	List<EstudianteMateriaV> findById_estudiante(int id_estudiante) throws DataAccessException;
	
	public void insert(EstudianteMateria estudianteMateria) throws DataAccessException;
	
	public void delete(Integer id_materia_cursada) throws DataAccessException;
	
}
