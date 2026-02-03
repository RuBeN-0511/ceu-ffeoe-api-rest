package ceu.dam.mondapiBD.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Usuarios {
	@Id
	private String id;
	private String nombreUsuario;
	private String contraseña;
	private String perfil;
	private boolean activo;
}
