package ceu.dam.mondapiBD.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "tutorDocente")
public class TutorDocente {
	@Id
	private String id;
	private String nombreDocente;
	private boolean activo;	
}
