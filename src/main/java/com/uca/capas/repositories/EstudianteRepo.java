package com.uca.capas.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.domain.Usuario;

public interface EstudianteRepo extends JpaRepository<Estudiante, Integer> {
	
	
}
