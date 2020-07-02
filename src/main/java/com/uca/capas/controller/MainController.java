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

import com.uca.capas.domain.CurrentSession;
import com.uca.capas.domain.Departamento;
import com.uca.capas.domain.Municipio;
import com.uca.capas.domain.Usuario;
import com.uca.capas.service.CurrentSessionService;
import com.uca.capas.service.DepartamentoService;
import com.uca.capas.service.MunicipioService;
import com.uca.capas.service.UsuarioService;
import com.uca.capas.util.PasswordGenerator;

@Controller
public class MainController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private DepartamentoService departamentoService;
	@Autowired
	private MunicipioService municipioService;
	@Autowired
	private CurrentSessionService currentSessionService;
	
	//Objeto que me permite determinar si el usuario a iniciado sesion.
	private Usuario usuario;
	private CurrentSession currentSession = new CurrentSession();
	private String currentIp;
	

	@RequestMapping("/")
	public ModelAndView initMain() {
		getToken();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}

	@RequestMapping("/home")
	public ModelAndView home() {
		
		if (usuario == null) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("error", "Inicia sesión antes.");
			mav.setViewName("login");
			return mav;
		}
		
		//Verificamos si hay alguien en linea
		List<CurrentSession> session = currentSessionService.findAll();

		if(session.size() == 0 ) {
			//No hay nadie en linea
			//le asignamos un token
			currentSession.setIp_session(currentIp);
			//ingresamos la sesion al sistema
			currentSessionService.insert(currentSession);
			
			if(usuario.getTipo_usuario() == 1) {
				ModelAndView mav = new ModelAndView();
				mav.setViewName("index_admin");
				return mav;
			}
			ModelAndView mav = new ModelAndView();
			mav.setViewName("index");
			return mav;
		}
		//Hay uno
		ModelAndView mav = new ModelAndView();
		mav.addObject("error", "Un usuario está utilizando el sistema en este momento, porfavor regrese más tarde.");
		mav.setViewName("login");
		return mav;

		

	}

	//----------AUTENTICACION--------------
	
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam(value = "nombre_usuario") String nombre_usuario,
			@RequestParam(value = "password") String password) {
		ModelAndView mav = new ModelAndView();
		List<Usuario> usuarios = null;

		try {
			usuarios = usuarioService.login(nombre_usuario, password);
			if (usuarios.size() > 0) {
				// verificando si es activo
				if (!usuarios.get(0).getEstado()) {
					// no es activo
					mav.addObject("error", "El usuario " + usuarios.get(0).getNombre_usuario()
							+ " se encuentra deshabilitado, consulte al administrador del sistema.");
					mav.setViewName("login");
					return mav;
				}

				// Todo en orden verifiquemos que permisos tiene el usuario.
				if (usuarios.get(0).getTipo_usuario() == 1) {

					// admin
					System.out.print("Usuario admin");								
					usuario = usuarios.get(0);				
					ModelAndView adminHomeModelAndView =  new ModelAndView("redirect:/home");
					return adminHomeModelAndView;

				}
				usuario = usuarios.get(0);
				ModelAndView homeModelAndView =  new ModelAndView("redirect:/home");
				return homeModelAndView;
			}

			mav.addObject("error", "Credenciales Inválidas");
			mav.setViewName("login");
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(nombre_usuario);
		System.out.println(password);
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout() {
		if (usuario == null) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("error", "Ya ha cerrado sesión");
			mav.setViewName("login");
			return mav;
		}
		
		usuario = null;
		//Eliminamos la ip
		currentSessionService.delete(currentSession.getIp_session());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("error", "Se ha cerrado sesión");
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
	
	//----------FIN MAPPING AUTENTICACION--------------
	
	//---------- GET MAC ADDRESS ---------------------
	private void getToken() {
		currentIp = PasswordGenerator.getPassword(PasswordGenerator.MINUSCULAS+
				PasswordGenerator.MAYUSCULAS+
				PasswordGenerator.ESPECIALES,10);
		
	}
}
