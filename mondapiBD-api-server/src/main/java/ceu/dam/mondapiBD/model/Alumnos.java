package ceu.dam.mondapiBD.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Alumnos {
	@Id
	private String id;
	private String nombreAlumno;
	private String ciclo;
	private String evaluacion;
	private Integer anioCurso;
	private Empresas nombreTutuor;
	private Empresas empresaAsignada;
}
