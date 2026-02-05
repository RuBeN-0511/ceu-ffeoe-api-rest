package ceu.dam.mondapiBD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ceu.dam.mondapiBD.dto.request.CambioContraseñaRequest;
import ceu.dam.mondapiBD.dto.request.UsernamePasswordRequest;
import ceu.dam.mondapiBD.exceptions.IncorrectPasswordException;
import ceu.dam.mondapiBD.exceptions.UserNotActiveException;
import ceu.dam.mondapiBD.exceptions.UserNotFoundException;
import ceu.dam.mondapiBD.exceptions.UserNotLoggedException;
import ceu.dam.mondapiBD.model.TutorDocente;
import ceu.dam.mondapiBD.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService service;

	@PostMapping("/login")
	public TutorDocente login(@RequestBody UsernamePasswordRequest user)
			throws UserNotFoundException, IncorrectPasswordException, UserNotActiveException {
		return service.login(user.getUsername(), user.getPassword());
	}

	@PutMapping("/cambiarContraseña")
	public void cambiarContraseña(@RequestBody CambioContraseñaRequest cambio)
			throws UserNotFoundException, UserNotLoggedException {
		service.cambiarContraseña(cambio.getIdUsuario(), cambio.getContraseñaAntigua(), cambio.getContraseñaNueva());
	}
}
