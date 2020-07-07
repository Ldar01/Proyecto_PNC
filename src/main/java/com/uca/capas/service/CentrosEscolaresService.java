package com.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.CentroEscolar;

public interface CentrosEscolaresService {
	
	public List<CentroEscolar> findAll() throws DataAccessException;
	
	public CentroEscolar findOne(Integer id_institucion) throws DataAccessException;
	
	public void insert(CentroEscolar centroEscolar) throws DataAccessException;
	
	public void delete(Integer id_institucion) throws DataAccessException;
	
	public void updateEstado(Integer id_institucion, Boolean estado);
}
