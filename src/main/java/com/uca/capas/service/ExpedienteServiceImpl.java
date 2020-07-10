package com.uca.capas.service;

import java.util.List;

import com.uca.capas.domain.Expediente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;



import com.uca.capas.repositories.ExpedienteRepo;
import org.springframework.stereotype.Service;

@Service
public class ExpedienteServiceImpl implements ExpedienteService {
	@Autowired
	ExpedienteRepo expedienteRepo;

	@Override
	public List<Expediente> findExpedienteByNombreOrApellido(String nombre,String apellido) throws DataAccessException {
		return expedienteRepo.findExpedienteByNombreOrApellido(nombre,apellido);
	}

	@Override
	public Expediente findOne(Integer id_estudiante) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Expediente expediente) throws DataAccessException {
		// TODO Auto-generated method stub
		expedienteRepo.save(expediente);
	}

}

