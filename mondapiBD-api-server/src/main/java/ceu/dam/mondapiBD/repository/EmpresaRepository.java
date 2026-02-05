package ceu.dam.mondapiBD.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.mondapiBD.model.Empresa;

@Repository
public interface EmpresaRepository extends MongoRepository<Empresa, String> {
	
	

}
