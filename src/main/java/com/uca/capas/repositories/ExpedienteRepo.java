package com.uca.capas.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.uca.capas.domain.Expediente;


public interface ExpedienteRepo extends JpaRepository<Expediente, Integer> {

    List<Expediente> findExpedienteByNombreOrApellido(String nombre,String apellido);
		
}
