package com.uca.capas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.domain.EstudianteMateria;
import com.uca.capas.domain.EstudianteMateriaV;
import com.uca.capas.repositories.EstudianteMateriaRepo;
import com.uca.capas.repositories.EstudianteRepo;


public class EstudianteServiceImpl implements EstudianteMateriaService {
	@Autowired
	private EstudianteMateriaRepo estudianteMateriaRepo;

	@Override
	public List<EstudianteMateriaV> findById_estudiante(int id_estudiante) throws DataAccessException {
		// TODO Auto-generated method stub
		return estudianteMateriaRepo.findById_estudiante(id_estudiante);
	}

	@Override
	public void insert(EstudianteMateria estudianteMateria) throws DataAccessException {
		// TODO Auto-generated method stub
		estudianteMateriaRepo.save(estudianteMateria);
		
	}

	@Override
	public void delete(Integer id_materia_cursada) throws DataAccessException {
		// TODO Auto-generated method stub
		estudianteMateriaRepo.deleteById(id_materia_cursada);
		
	}

	
	
}
