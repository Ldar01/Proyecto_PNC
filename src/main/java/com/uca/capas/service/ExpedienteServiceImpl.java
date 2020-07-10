package com.uca.capas.service;

import java.util.List;

import com.uca.capas.domain.Expediente;
import org.springframework.dao.DataAccessException;



import com.uca.capas.repositories.ExpedienteRepo;
import org.springframework.stereotype.Service;

@Service
public class ExpedienteServiceImpl implements ExpedienteService {
	
	ExpedienteRepo expedienteRepo;

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

	@Override
	public List<Expediente> findByPrimer_nombreOrPrimer_apellido(String primer_nombre, String primer_apellido)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return expedienteRepo.findByPrimer_nombreOrPrimer_apellido(primer_nombre, primer_apellido);
	}

}
