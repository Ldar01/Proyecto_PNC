package com.uca.capas.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uca.capas.domain.CentroEscolar;

public interface CentroEscolarRepo extends JpaRepository<CentroEscolar, Integer> {
	
	@Query(nativeQuery = true, value = "update instituciones set estado = :estado where id_institucion = :id_institucion")
	public void updateEstado(Integer id_institucion, Boolean estado) throws DataAccessException;

}
