package ceu.dam.mondapiBD.model;

import java.util.Date;

import lombok.Data;

@Data
public class Fecha {
	private Date fecha;
	private Integer anioCurso;
	private String evaluacion;
}
