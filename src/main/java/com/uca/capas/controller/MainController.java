package com.uca.capas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Departamento;
import com.uca.capas.domain.Municipio;
import com.uca.capas.domain.Usuario;
import com.uca.capas.service.DepartamentoService;
import com.uca.capas.service.MunicipioService;
import com.uca.capas.service.UsuarioService;

@Controller
public class MainController {
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private DepartamentoService departamentoService;
	@Autowired
	private MunicipioService municipioService;
	
	@RequestMapping("/")
	public ModelAndView initMain() {
		Usuario usuario = new Usuario();
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("usuario",usuario);
		mav.setViewName("login");
		return mav;
	}

	
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam(value="nombre_usuario") String nombre_usuario, @RequestParam(value="password") String password) {
		ModelAndView mav = new ModelAndView();
		List<Usuario> usuarios = null;
		
		try {
			usuarios=usuarioService.login(nombre_usuario, password);
			if(usuarios.size() > 0) {
				//verificando si es activo
				if(!usuarios.get(0).getEstado()) {
					//no es activo
					mav.addObject("error","El usuario " + usuarios.get(0).getNombre_usuario() +" se encuentra deshabilitado, consulte al administrador del sistema.");
					mav.setViewName("login");
					return mav;
				}
				
				//Todo en orden verifiquemos que permisos tiene el usuario.
				if(usuarios.get(0).getTipo_usuario() == 1) {
					//admin
					System.out.println("Usuario administrador.");
					mav.addObject("usuario",usuarios.get(0));
					mav.setViewName("index_admin");
					return mav;
				}
				
				mav.setViewName("index");
				return mav;
			}
			
			mav.addObject("error","Credenciales Inválidas");
			mav.setViewName("login");
			return mav;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(nombre_usuario);
		System.out.println(password);
		mav.setViewName("login");
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
		
		mav.addObject("usuario",usuario);
		mav.addObject("departamentos",departamentos);
		mav.addObject("municipios",municipios);
		
		mav.setViewName("register");
		return mav;
	}
	
	@RequestMapping("/createAccount")
	public ModelAndView formLibro(@Valid @ModelAttribute Usuario usuario, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(!result.hasErrors()) {
			try {
				usuarioService.insert(usuario);
				mav.addObject("success_msg","¡Usuario creado con éxito!.");
				mav.setViewName("register");
				return mav;
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}

		List<Departamento> departamentos = null;
		List<Municipio> municipios = null;
		departamentos = departamentoService.findAll();
		municipios = municipioService.findAll();
		mav.addObject("departamentos",departamentos);
		mav.addObject("municipios",municipios);
		
		mav.setViewName("register");
		return mav;
	}
}
