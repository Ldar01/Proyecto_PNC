package com.uca.capas.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uca.capas.domain.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {
	@Query(nativeQuery = true, value="select * from public.usuarios where nombre_usuario = :nombre_usuario and password=:password")
	public List<Usuario> login(String nombre_usuario, String password) throws DataAccessException;
}
