package com.uca.capas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.CurrentSession;
import com.uca.capas.domain.Departamento;
import com.uca.capas.domain.Municipio;
import com.uca.capas.domain.Rol;
import com.uca.capas.domain.Usuario;
import com.uca.capas.service.CurrentSessionService;
import com.uca.capas.service.DepartamentoService;
import com.uca.capas.service.MunicipioService;
import com.uca.capas.service.UsuarioService;
import com.uca.capas.util.PasswordGenerator;

@Controller
public class MainController implements ErrorController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private DepartamentoService departamentoService;
	@Autowired
	private MunicipioService municipioService;
	@Autowired
	private CurrentSessionService currentSessionService;

	// Objeto que me permite determinar si el usuario a iniciado sesion.
	private Usuario usuario;

	@RequestMapping({ "/", "/login" })
	public ModelAndView initMain() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}


	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/home_admin")
	public ModelAndView home_admin() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index_admin");
		return mav;
	}

	@RequestMapping("/error")
	public ModelAndView error() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("404");
		return mav;
	}

	@RequestMapping("/activate_user")
	public ModelAndView activate_user() {
		ModelAndView mav = new ModelAndView();
		List<Usuario> usuarios = null;
		usuarios = usuarioService.findAll();
		mav.addObject("usuarios", usuarios);
		mav.setViewName("activate");
		return mav;
	}

	@RequestMapping("/perfom_activation")
	public ModelAndView perfom_activation(@RequestParam(value = "id_usuario") int id_usuario) {
		ModelAndView mav = new ModelAndView();
		List<Usuario> usuarios = null;

		try {
			Usuario us = new Usuario();
			us = usuarioService.findOne(id_usuario);
			us.setEstado(true);
			usuarioService.insert(us);
			usuarios = usuarioService.findAll();
			mav.addObject("usuarios", usuarios);
			mav.setViewName("activate");
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
		}
		usuarios = usuarioService.findAll();
		mav.addObject("usuarios", usuarios);
		mav.setViewName("activate");
		return mav;
	}

	@RequestMapping("/perfom_desactivation")
	public ModelAndView perfom_desactivation(@RequestParam(value = "id_usuario") int id_usuario) {
		ModelAndView mav = new ModelAndView();
		List<Usuario> usuarios = null;

		try {
			Usuario us = new Usuario();
			us = usuarioService.findOne(id_usuario);
			us.setEstado(false);
			usuarioService.insert(us);
			usuarios = usuarioService.findAll();
			mav.addObject("usuarios", usuarios);
			mav.setViewName("activate");
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
		}
		usuarios = usuarioService.findAll();
		mav.addObject("usuarios", usuarios);
		mav.setViewName("activate");
		return mav;
	}

	@RequestMapping("/perfom_delete")
	public ModelAndView perfom_delete(@RequestParam(value = "id_usuario") int id_usuario) {
		ModelAndView mav = new ModelAndView();
		List<Usuario> usuarios = null;

		try {
			usuarioService.delete(id_usuario);
			usuarios = usuarioService.findAll();
			mav.addObject("usuarios", usuarios);
			mav.setViewName("activate");
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
		}
		usuarios = usuarioService.findAll();
		mav.addObject("usuarios", usuarios);
		mav.setViewName("activate");
		return mav;
	}
	
	@RequestMapping("/register")
	public ModelAndView register() {
		Usuario usuario = new Usuario();
		List<Departamento> departamentos = null;
		List<Municipio> municipios = null;

		departamentos = departamentoService.findAll();
		municipios = municipioService.findAll();

		ModelAndView mav = new ModelAndView();

		mav.addObject("usuario", usuario);
		mav.addObject("departamentos", departamentos);
		mav.addObject("municipios", municipios);

		mav.setViewName("register");
		return mav;
	}

	@RequestMapping("/createAccount")
	public ModelAndView createAccount(@Valid @ModelAttribute Usuario usuario, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if (!result.hasErrors()) {
			try {
				// por defecto le asignamos el rol de coordinador
				Rol rol = new Rol();
				rol.setId_rol(2);
				rol.setNombre_rol("ROLE_COORDINADOR");

				usuario.setRol(rol);

				usuarioService.insert(usuario);
				mav.addObject("success_msg", "¡Usuario creado con éxito!.");
				mav.setViewName("register");
				return mav;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		List<Departamento> departamentos = null;
		List<Municipio> municipios = null;
		departamentos = departamentoService.findAll();
		municipios = municipioService.findAll();
		mav.addObject("departamentos", departamentos);
		mav.addObject("municipios", municipios);

		mav.setViewName("register");
		return mav;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
