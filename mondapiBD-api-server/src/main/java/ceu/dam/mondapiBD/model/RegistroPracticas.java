package ceu.dam.mondapiBD.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "registrosPracticas")
public class RegistroPracticas {
	private String idAlumno;
	private String idRegistro;
	private String idFecha;
	private Integer cantidadHoras;
	private String descripcion;
}
