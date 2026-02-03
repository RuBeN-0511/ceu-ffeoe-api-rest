package ceu.dam.mondapiBD.service;

import ceu.dam.mondapiBD.model.Usuario;

public interface MondapiService {
	
	/**
	 * Cifrar contraseña
	 * Verificar que el usuario esté activo
	 */
	public Usuario login(String username, String contraseña);
	
	/*
	 * La contraseña tendrá una longitud de 8 carácteres
	 */
	public void cambiarContraseña(String contraseñaAntigua, String contraseñaNueva);
}
