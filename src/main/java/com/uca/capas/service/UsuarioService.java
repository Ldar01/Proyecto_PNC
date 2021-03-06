package com.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Usuario;

public interface UsuarioService {
	
	public List<Usuario> findAll() throws DataAccessException;

	public Usuario findOne(Integer id_usuario) throws DataAccessException;
	
	public Usuario findOneByUsuario(String username) throws DataAccessException;

	public void insert(Usuario usuario) throws DataAccessException;

	public void delete(Integer id_usuario) throws DataAccessException;

	public void updateEstado(Integer id_usuario, Boolean estado) throws DataAccessException;
	
}
