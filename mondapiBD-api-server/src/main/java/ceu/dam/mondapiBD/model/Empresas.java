package ceu.dam.mondapiBD.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Empresas {
	@Id
	private String id;
	private String tutorLaboral;
	private String emailTutor;
	private Integer numeroTelefono;
	private boolean activo;
	
}
