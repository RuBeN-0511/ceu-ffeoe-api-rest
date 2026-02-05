package ceu.dam.mondapiBD.dto.request;

import lombok.Data;

@Data
public class CambioContraseñaRequest {

	private String idUsuario;
	private String contraseñaAntigua;
	private String contraseñaNueva;
}
