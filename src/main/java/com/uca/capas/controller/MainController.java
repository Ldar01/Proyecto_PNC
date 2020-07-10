package com.uca.capas.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.validation.Valid;

import com.uca.capas.domain.*;
import com.uca.capas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


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

	@Autowired
	private ExpedienteService expedienteService;

	@Autowired
	private MateriaService materiaService;


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
			
			if(usuario.getRol_delegate().equals("ADMINISTRADOR")) {
				mav.setViewName("index_admin");
				return mav;
			}
		}
		mav.setViewName("index");
		return mav;
	}


	@RequestMapping("/expedientes")
	public ModelAndView expedientes() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("estudiantes_options");
		return mav;
	}

	@RequestMapping("/estudiantes")
	public ModelAndView estudiantes(@RequestParam(required=false,value = "searchN") String searchN,@RequestParam(required=false,value = "searchA") String searchA) {
		ModelAndView mav = new ModelAndView();
		if(searchA.isEmpty() || searchA == null) searchA = "" ;
		if(searchN.isEmpty() || searchN == null) searchN = "" ;

		List<Expediente> expedientes = expedienteService.findExpedienteByNombreOrApellido(searchN,searchA);
		System.out.println("Hola Mundo");
		mav.addObject("expedientes",expedientes);
		mav.setViewName("expedientesS");
		return mav;
	}



	//
	@RequestMapping("/materiasCursadas")
	public ModelAndView materiasCursadas(@RequestParam(value = "id_estudiante") int id_estudiante) {
		List<EstudianteMateriaV> materiasCursadas = estudianteMateriaService.findById_estudiante(id_estudiante);
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
			
			if(usuario.getRol_delegate().equals("ADMINISTRADOR")) {
				mav.setViewName("index_admin");
				return mav;
			}else {
				mav.setViewName("index");
				return mav;
			}
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

	//============ OPTIONS CENTRO ESCOLAR ==============

	@RequestMapping("/centrosEscolares")
	public ModelAndView centrosEscolares() {
		List<CentroEscolar> centrosEscolares = centrosEscolaresService.findAll();

		Municipio municipio = new Municipio();
		ModelAndView mav = new ModelAndView();
		mav.addObject("centrosEscolares", centrosEscolares);
		mav.setViewName("centrosEscolares");
		return mav;
	}

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

	//========= ESTUDIANTES ===============
	//@RequestParam(value = "id_estudiante") int id
	@RequestMapping("/update_estudiante")
	public ModelAndView update_estudiante(@RequestParam(value = "id_estudiante") int id) {
		Estudiante estudiante = estudianteService.findOne(id);
		//List<CentroEscolar> municipios = null;
		List<CentroEscolar> centroEscolars = null;

		centroEscolars = centrosEscolaresService.findAll();

		ModelAndView mav = new ModelAndView();

		mav.addObject("estudiante", estudiante);
		//mav.addObject("municipios", municipios);
		mav.addObject("centroEscolars", centroEscolars);

		mav.setViewName("update_estudiante");
		return mav;
	}
	@RequestMapping("/register_estudiante")
	public ModelAndView register_estudiante() {
		Estudiante estudiante = new Estudiante();
		List<Municipio> municipios = null;
		List<CentroEscolar> centroEscolars = centrosEscolaresService.findAll();

		//municipios = municipioService.findAll();

		ModelAndView mav = new ModelAndView();

		mav.addObject("estudiante", estudiante);
		//mav.addObject("municipios", municipios);
		mav.addObject("centroEscolars", centroEscolars);

		mav.setViewName("register_estudiante");
		return mav;
	}
	@RequestMapping("/createEstudiante")
	public ModelAndView createEstudiante(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		List<CentroEscolar> centroEscolars = centrosEscolaresService.findAll();
		LocalDate localDate = LocalDate.now();
		if (!result.hasErrors()) {
			try {
				System.out.println("edad");
				estudiante.setEdad(calculateAge(estudiante.getF_nacimiento(), localDate));
				System.out.println("edad");
				estudianteService.insert(estudiante);
				mav.addObject("success_msg", "¡Estudiante creado con éxito!.");

				mav.addObject("centroEscolars", centroEscolars);
				mav.setViewName("register_estudiante");
			}
			catch (Exception e) {
				e.printStackTrace();
			}

		}
		mav.setViewName("register_estudiante");
		return mav;
	}
	//============ OPTIONS MATERIAS ========================
	@RequestMapping("/materias")
	public ModelAndView materias() {
		List<Materia> materias = materiaService.findAll();

		ModelAndView mav = new ModelAndView();
		mav.addObject("materias", materias);
		mav.setViewName("materias");
		return mav;
	}

	@RequestMapping("/register_m")
	public ModelAndView register_m() {
		Materia materia = new Materia();

		ModelAndView mav = new ModelAndView();

		mav.addObject("materia", materia);

		mav.setViewName("register_m");
		return mav;
	}

	@RequestMapping("/createM")
	public ModelAndView createM(@Valid @ModelAttribute Materia materia, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if (!result.hasErrors()) {
			try {
				materiaService.insert(materia);
				mav.addObject("success_msg", "¡Materia creada con éxito!.");
				mav.setViewName("register_m");
				return mav;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		mav.setViewName("register_m");
		return mav;
	}

	@RequestMapping("/updatedEstudiante")
	public ModelAndView updatedEstudiante(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		List<Municipio> municipios = null;
		municipios = municipioService.findAll();
		LocalDate localDate =LocalDate.now();
		if (!result.hasErrors()) {
			try {
				System.out.println("edad");
				estudiante.setEdad(calculateAge(estudiante.getF_nacimiento(),localDate));
				System.out.println("edad");
				estudianteService.insert(estudiante);
				mav.addObject("success_msg", "¡Estudiante actualizado con éxito!.");

				mav.addObject("municipios", municipios);
				mav.setViewName("update_estudiante");
				return mav;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		mav.addObject("municipios", municipios);

		mav.setViewName("update_estudiante");
		return mav;
	}

	//EDITAR MATERIA
	@RequestMapping(value = "/editarMateria", method = RequestMethod.POST)
	public ModelAndView editarMateria(@RequestParam(value = "id_materia") int id){
		ModelAndView mav = new ModelAndView();
		Materia materia = materiaService.findOne(id);

		mav.addObject("materia", materia);
		mav.setViewName("register_m");

		return mav;
	}

	//========= END OF OPTIONS MATERIAS =====================

	@Override
	public String getErrorPath() {
		return "/error";
	}

	public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
		if ((birthDate != null) && (currentDate != null)) {
			return Period.between(birthDate, currentDate).getYears();
		} else {
			return 0;
		}
	}
}
