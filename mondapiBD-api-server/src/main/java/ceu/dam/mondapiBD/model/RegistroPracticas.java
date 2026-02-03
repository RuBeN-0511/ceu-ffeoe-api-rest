package ceu.dam.mondapiBD.model;

import lombok.Data;

@Data
public class RegistroPracticas {
	private Alumno id;
	private Fecha fecha;
	private Integer cantidadHoras;
	private String descripcion;
}
