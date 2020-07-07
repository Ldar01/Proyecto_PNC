package com.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.CentroEscolar;

public interface CentrosEscolaresService {
	public List<CentroEscolar> findAll() throws DataAccessException;
}
