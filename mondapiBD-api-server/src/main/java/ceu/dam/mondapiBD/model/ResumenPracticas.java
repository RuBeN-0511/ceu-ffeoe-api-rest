package ceu.dam.mondapiBD.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResumenPracticas {
	
	private Integer horasTotales;
	private Integer horasRealizadas;
	private Double porcentaje;
	private Integer horasPendientes;

}
