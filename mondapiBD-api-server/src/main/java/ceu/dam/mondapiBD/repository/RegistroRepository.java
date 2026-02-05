package ceu.dam.mondapiBD.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.mondapiBD.model.Fecha;
import ceu.dam.mondapiBD.model.RegistroPracticas;

@Repository
public interface RegistroRepository extends MongoRepository<RegistroPracticas, String> {

	public List<Fecha> findAllByIdFecha(String idFecha);
	public void deleteAllByIdFecha(String idFecha);
	
}
