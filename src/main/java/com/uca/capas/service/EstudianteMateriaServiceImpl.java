package com.uca.capas.service;

import java.util.List;

import com.uca.capas.repositories.EstudianteMateriaVRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.domain.EstudianteMateria;
import com.uca.capas.domain.EstudianteMateriaV;
import com.uca.capas.repositories.EstudianteMateriaRepo;
import com.uca.capas.repositories.EstudianteRepo;
import org.springframework.stereotype.Service;

@Service
public class EstudianteMateriaServiceImpl implements EstudianteMateriaService {
	@Autowired
	private EstudianteMateriaRepo estudianteMateriaRepo;

	@Autowired
	private EstudianteMateriaVRepo estudianteMateriaVRepo;

	@Override
	public List<EstudianteMateriaV> findById_estudiante(int id_estudiante) throws DataAccessException {
		// TODO Auto-generated method stub
		return estudianteMateriaVRepo.findById_estudiante(id_estudiante);
	}

	@Override
	public void insert(EstudianteMateria estudianteMateria) throws DataAccessException {
		// TODO Auto-generated method stub
		estudianteMateriaRepo.save(estudianteMateria);
	}

	@Override
	public void delete(Integer id_materia_cursada) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}


	
}
