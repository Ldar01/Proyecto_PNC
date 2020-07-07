package com.uca.capas.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uca.capas.domain.Materia;

public interface MateriaRepo extends JpaRepository<Materia, Integer>{

	@Query(nativeQuery = true, value = "update materias set estado = :estado where id_materia = :id_materia")
	public void updateEstado(Integer id_materia, Boolean estado) throws DataAccessException;
	
}
