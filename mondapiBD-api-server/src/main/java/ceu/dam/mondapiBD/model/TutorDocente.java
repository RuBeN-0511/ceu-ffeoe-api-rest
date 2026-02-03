package ceu.dam.mondapiBD.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class TutorDocente {
	@Id
	private String id;
	private String nombreDocente;
	private boolean activo;	
}
