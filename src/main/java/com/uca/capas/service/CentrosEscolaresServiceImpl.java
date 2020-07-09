package com.uca.capas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.CentroEscolar;
import com.uca.capas.repositories.CentroEscolarRepo;
import org.springframework.stereotype.Service;

@Service
public class CentrosEscolaresServiceImpl implements CentrosEscolaresService{
	
	@Autowired
	private CentroEscolarRepo centrosEscolaresRepo;

	@Override
	public List<CentroEscolar> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return centrosEscolaresRepo.findAll();
	}

	@Override
	public CentroEscolar findOne(Integer id_institucion) throws DataAccessException {
		// TODO Auto-generated method stub
		return centrosEscolaresRepo.getOne(id_institucion);
	}

	@Override
	public void insert(CentroEscolar centroEscolar) throws DataAccessException {
		// TODO Auto-generated method stub
		centrosEscolaresRepo.save(centroEscolar);
	}

	@Override
	public void delete(Integer id_institucion) throws DataAccessException {
		// TODO Auto-generated method stub
		centrosEscolaresRepo.deleteById(id_institucion);
	}

	@Override
	public void updateEstado(Integer id_institucion, Boolean estado) {
		// TODO Auto-generated method stub
		centrosEscolaresRepo.updateEstado(id_institucion, estado);
	}

}
