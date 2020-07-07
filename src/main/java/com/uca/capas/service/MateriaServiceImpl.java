package com.uca.capas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Materia;
import com.uca.capas.repositories.MateriaRepo;

public class MateriaServiceImpl implements MateriaService{

	@Autowired
	private MateriaRepo materiaRepo;
	
	@Override
	public List<Materia> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return materiaRepo.findAll();
	}

	@Override
	public Materia findOne(Integer id_materia) throws DataAccessException {
		// TODO Auto-generated method stub
		return materiaRepo.getOne(id_materia);
	}

	@Override
	public void insert(Materia materia) throws DataAccessException {
		// TODO Auto-generated method stub
		materiaRepo.save(materia);
	}

	@Override
	public void delete(Integer id_materia) throws DataAccessException {
		// TODO Auto-generated method stub
		materiaRepo.deleteById(id_materia);
	}

	@Override
	public void updateEstado(Integer id_materia, Boolean estado) throws DataAccessException {
		// TODO Auto-generated method stub
		materiaRepo.updateEstado(id_materia, estado);
	}

}
