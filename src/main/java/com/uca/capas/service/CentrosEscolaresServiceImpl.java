package com.uca.capas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.CentroEscolar;
import com.uca.capas.repositories.CentroEscolarRepo;

public class CentrosEscolaresServiceImpl implements CentrosEscolaresService{
	
	@Autowired
	private CentroEscolarRepo centrosEscolaresRepo;

	@Override
	public List<CentroEscolar> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return centrosEscolaresRepo.findAll();
	}

}
