package ceu.dam.mondapiBD.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "usuarios")
public class Usuarios {
	@Id
	private String id;
	private String nombreUsuario;
	private String contraseña;
	private String perfil;
	private boolean activo;
}
