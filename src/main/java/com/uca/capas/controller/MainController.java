package com.uca.capas.controller;

import java.util.List;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.uca.capas.domain.*;
import com.uca.capas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	private CentrosEscolaresService centrosEscolaresService;

	@Autowired
	private EstudianteMateriaService estudianteMateriaService;

	@Autowired
	private EstudianteService estudianteService;

	@RequestMapping({ "/", "/login" })
	public ModelAndView initMain() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}

	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			Usuario usuario = usuarioService.findOneByUsuario(username);
			mav.addObject("userRol", usuario.getRol_delegate());
		}
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/centrosEscolares")
	public ModelAndView centrosEscolares() {
		List<CentroEscolar> centrosEscolares = centrosEscolaresService.findAll();

		Municipio municipio = new Municipio();
		ModelAndView mav = new ModelAndView();
		mav.addObject("centrosEscolares", centrosEscolares);
		mav.setViewName("centrosEscolares");
		return mav;
	}

	//@RequestParam(value = "id_estudiante") int id_estudiante
	@RequestMapping("/materiasCursadas")
	public ModelAndView materiasCursadas() {
		List<EstudianteMateriaV> materiasCursadas = estudianteMateriaService.findById_estudiante(1);
		Estudiante estudiante = estudianteService.findOne(1);
		Municipio municipio = new Municipio();
		ModelAndView mav = new ModelAndView();
		mav.addObject("estudiante",estudiante);
		mav.addObject("materiasCursadas",materiasCursadas);
		mav.setViewName("materiasCursadas");
		return mav;
	}

	@RequestMapping("/home_admin")
	public ModelAndView home_admin() {
		ModelAndView mav = new ModelAndView();

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			Usuario usuario = usuarioService.findOneByUsuario(username);
			mav.addObject("userRol", usuario.getRol_delegate());
		}

		mav.setViewName("index_admin");
		return mav;
	}

	@RequestMapping("/home_coord")
	public ModelAndView home_coord() {
		ModelAndView mav = new ModelAndView();

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			Usuario usuario = usuarioService.findOneByUsuario(username);
			mav.addObject("userRol", usuario.getRol_delegate());
		}

		mav.setViewName("index_coord");
		return mav;
	}

	@RequestMapping("/error")
	public ModelAndView error() {
		ModelAndView mav = new ModelAndView();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			Usuario usuario = usuarioService.findOneByUsuario(username);
			mav.addObject("userRol", usuario.getRol_delegate());
		}
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
			usuarioService.updateEstado(id_usuario, true);
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
			usuarioService.updateEstado(id_usuario, false);
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
		List<Departamento> departamentos = null;
		List<Municipio> municipios = null;
		if (!result.hasErrors()) {
			try {
				Usuario existusuario = usuarioService.findOneByUsuario(usuario.getUsuario());
				if(existusuario != null) {
					mav.addObject("error_msg", "El usuario que intenta ingresar ya está en uso.");
					departamentos = departamentoService.findAll();
					municipios = municipioService.findAll();
					mav.addObject("departamentos", departamentos);
					mav.addObject("municipios", municipios);
					mav.setViewName("register");
					return mav;
				}
				usuarioService.insert(usuario);
				mav.addObject("success_msg", "¡Usuario creado con éxito!.");
				departamentos = departamentoService.findAll();
				municipios = municipioService.findAll();
				mav.addObject("departamentos", departamentos);
				mav.addObject("municipios", municipios);
				mav.setViewName("register");
				return mav;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

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
