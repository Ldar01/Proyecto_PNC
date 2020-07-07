package com.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Materia;

public interface MateriaService {
	
	public List<Materia> findAll() throws DataAccessException;
	
	public Materia findOne(Integer id_materia) throws DataAccessException;
	
	public void insert(Materia materia) throws DataAccessException;
	
	public void delete(Integer id_materia) throws DataAccessException;
	
	public void updateEstado(Integer id_materia, Boolean estado) throws DataAccessException;
	
}
