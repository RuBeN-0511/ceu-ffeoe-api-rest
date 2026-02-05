package ceu.dam.mondapiBD.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "tutoresDocentes")
public class TutorDocente {
	@Id
	private String id;
	private String idTutor;
	private String nombreDocente;
	private Boolean activo;
}
