package ceu.dam.mondapiBD.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "fechas")
public class Fecha {
	private Date fecha;
	private Integer anioCurso;
	private String evaluacion;
}
