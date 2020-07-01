package com.uca.capas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uca.capas.domain.Usuario;
import com.uca.capas.repositories.UsuarioRepo;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepo usuarioRepo;

	@Override
	public List<Usuario> findAll() throws DataAccessException {
		return usuarioRepo.findAll();
	}

	@Override
	public Usuario findOne(Integer id_usuario) throws DataAccessException {
		return usuarioRepo.getOne(id_usuario);
	}

	@Override
	@Transactional
	public void insert(Usuario usuario) throws DataAccessException {
		usuarioRepo.save(usuario);
		
	}

	@Override
	public void delete(Integer id_usuario) throws DataAccessException {
		usuarioRepo.deleteById(id_usuario);
	
	}

	@Override
	public List<Usuario> login(String usuario, String contrasena) throws DataAccessException {
		return usuarioRepo.login(usuario, contrasena);		
	}


}
