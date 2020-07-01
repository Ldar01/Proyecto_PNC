package com.uca.capas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.uca.capas.domain.Departamento;
import com.uca.capas.repositories.DepartamentoRepo;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	private DepartamentoRepo departamentoRepo;
	
	@Override
	public List<Departamento> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return departamentoRepo.findAll();
	}

}
