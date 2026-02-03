package ceu.dam.mondapiBD.model;

import lombok.Data;

@Data
public class RegistroPracticas {
	private Alumnos id;
	private Fecha fecha;
	private Integer cantidadHoras;
	private String descripcion;
}
