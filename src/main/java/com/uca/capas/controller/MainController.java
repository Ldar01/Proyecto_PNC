package com.uca.capas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Usuario;
import com.uca.capas.service.UsuarioService;

@Controller
public class MainController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping("/")
	public ModelAndView initMain() {
		Usuario usuario = new Usuario();
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("usuario",usuario);
		mav.setViewName("index");
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
				
				//Todo en orden acceder.
				System.out.print("Login success");
				mav.setViewName("login");
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
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("usuario",usuario);
		mav.setViewName("register");
		return mav;
	}
}
