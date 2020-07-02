package com.uca.capas.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uca.capas.domain.Usuario;
import com.uca.capas.repositories.UsuarioRepo;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	@Autowired
	private UsuarioRepo usuarioRepo;

	@Autowired
	private BCryptPasswordEncoder encoder;

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
		// Encriptamos la contraseña
		usuario.setPassword(encoder.encode(usuario.getPassword()));
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepo.findByUsuario(username);

		List<GrantedAuthority> roles = new ArrayList<>();
		
		if(usuario.getEstado()) {
			roles.add(new SimpleGrantedAuthority(usuario.getRol().getNombre_rol()));

			UserDetails userDet = new User(usuario.getNombre_usuario(), usuario.getPassword(), roles);
			return userDet;
		}else {
			throw new UsernameNotFoundException("El usuario está desactivado.");
		}

		
	}

}
