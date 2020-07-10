package com.uca.capas.controller;

import java.util.List;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.uca.capas.domain.*;
import com.uca.capas.service.CentrosEscolaresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
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
	private CentrosEscolaresService centrosEscolaresService;

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

	@RequestMapping("/centrosEscolares")
	public ModelAndView centrosEscolares() {
		List<CentroEscolar> centrosEscolares = centrosEscolaresService.findAll();

		Municipio municipio = new Municipio();
		ModelAndView mav = new ModelAndView();
		mav.addObject("centrosEscolares",centrosEscolares);
		mav.setViewName("centrosEscolares");
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

	//============ OPTIONS CENTRO ESCOLAR ==============

	@RequestMapping("/register_ce")
	public ModelAndView register_ce() {
		CentroEscolar centroEscolar = new CentroEscolar();
		List<Municipio> municipios = null;

		municipios = municipioService.findAll();

		ModelAndView mav = new ModelAndView();

		mav.addObject("centroEscolar", centroEscolar);
		mav.addObject("municipio", centroEscolar.getMunicipio());
		mav.addObject("municipios", municipios);

		mav.setViewName("register_ce");
		return mav;
	}

	@RequestMapping("/createCE")
	public ModelAndView createCE(@Valid @ModelAttribute CentroEscolar centroEscolar, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		List<Municipio> municipios = null;
		if (!result.hasErrors()) {
			try {
				centrosEscolaresService.insert(centroEscolar);
				mav.addObject("success_msg", "¡Centro Escolar creado con éxito!.");
				municipios = municipioService.findAll();
				mav.addObject("municipios", municipios);
				mav.setViewName("register_ce");
				return mav;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		municipios = municipioService.findAll();
		mav.addObject("municipios", municipios);

		mav.setViewName("register_ce");
		return mav;
	}

	//EDITAR CENTRO ESCOLAR
	@RequestMapping(value = "/editarCentroEscolar", method = RequestMethod.POST)
	public ModelAndView editarCentroEscolar(@RequestParam(value = "id_institucion") int id){
		ModelAndView mav = new ModelAndView();
		CentroEscolar centroEscolar = centrosEscolaresService.findOne(id);
		List<Municipio> municipios = null;

		municipios = municipioService.findAll();

		mav.addObject("centroEscolar", centroEscolar);
		mav.addObject("municipio", centroEscolar.getMunicipio());
		mav.addObject("municipios", municipios);
		mav.setViewName("register_ce");

		return mav;
	}

	//========= END OF OPTIONS CENTRO ESCOLAR ===============

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
