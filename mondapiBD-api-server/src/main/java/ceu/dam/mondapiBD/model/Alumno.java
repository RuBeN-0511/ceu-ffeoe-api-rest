package ceu.dam.mondapiBD.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "alumnos")
public class Alumno {
	@Id
	private String id;
	private String nombreAlumno;
	private String ciclo;
	private String evaluacion;
	private Integer anioCurso;
	private Empresas nombreTutuor;
	private Empresas empresaAsignada;
}
