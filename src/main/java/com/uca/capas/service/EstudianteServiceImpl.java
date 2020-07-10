package com.uca.capas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.domain.EstudianteMateria;
import com.uca.capas.domain.EstudianteMateriaV;
import com.uca.capas.repositories.EstudianteMateriaRepo;
import com.uca.capas.repositories.EstudianteRepo;
import org.springframework.stereotype.Service;

@Service
public class EstudianteServiceImpl implements EstudianteService {
	@Autowired
	private EstudianteRepo estudianteRepo;


	@Override
	public List<Estudiante> findAll() throws DataAccessException {
		return estudianteRepo.findAll();
	}

	@Override
	public Estudiante findOne(Integer id_estudiante) throws DataAccessException {
		return estudianteRepo.getOne(id_estudiante);
	}

	@Override
	public void insert(Estudiante estudiante) throws DataAccessException {
		estudianteRepo.save(estudiante);
	}

	@Override
	public void delete(Integer id_estudiante) throws DataAccessException {

	}


}
