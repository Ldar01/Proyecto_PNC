package com.uca.capas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.repositories.EstudianteRepo;


public class EstudianteMateriaServiceImpl implements EstudianteService {
	@Autowired
	private EstudianteRepo estudianteRepo;

	@Override
	public List<Estudiante> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return estudianteRepo.findAll();
		
	}

	@Override
	public Estudiante findOne(Integer id_estudiante) throws DataAccessException {
		// TODO Auto-generated method stub
		return estudianteRepo.getOne(id_estudiante);
	}

	@Override
	public void insert(Estudiante estudiante) throws DataAccessException {
		// TODO Auto-generated method stub
		estudianteRepo.save(estudiante);
	}

	@Override
	public void delete(Integer id_estudiante) throws DataAccessException {
		// TODO Auto-generated method stub
		estudianteRepo.deleteById(id_estudiante);
	}
	
}
