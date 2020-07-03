package com.uca.capas.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uca.capas.domain.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {
	@Query(nativeQuery = true, value = "update usuarios set estado = :estado where id_usuario = :id_usuario")
	public void updateEstado(Integer id_usuario, Boolean estado) throws DataAccessException;

	Usuario findByUsuario(String nombre);
}
