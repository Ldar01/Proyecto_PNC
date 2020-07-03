package com.uca.capas.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, BadCredentialsException {
		Usuario usuario = usuarioRepo.findByUsuario(username);

		List<GrantedAuthority> roles = new ArrayList<>();
		
		Boolean isSomeOneLog = false;

		if(currentUserDetails()!=null) {
			isSomeOneLog = true;
		}
		

		if (usuario == null) {
			throw new BadCredentialsException("Credenciales incorrectas.");
		}
		
		if(isSomeOneLog) {
			throw new BadCredentialsException("Actualmente hay un usuario dentro del sistema. Por favor regresa más tarde.");
		}

		if (usuario.getEstado()) {
			if (usuario.getTipo_usuario() == 1) {
				roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			} else {
				roles.add(new SimpleGrantedAuthority("ROLE_COORDINADOR"));
			}

			UserDetails userDet = new User(usuario.getUsuario(), usuario.getPassword(), roles);

			return userDet;
		} else {
			throw new BadCredentialsException("El usuario " + usuario.getUsuario()
					+ " actualmente se encuentra desactivado. consulte con su administrador de sistema.");
		}

	}

	@Override
	public void updateEstado(Integer id_usuario, Boolean estado) throws DataAccessException {
		usuarioRepo.updateEstado(id_usuario, estado);
	}
	
	public UserDetails currentUserDetails(){
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    if (authentication != null) {
	        Object principal = authentication.getPrincipal();
	        return principal instanceof UserDetails ? (UserDetails) principal : null;
	    }
	    return null;
	}

}
