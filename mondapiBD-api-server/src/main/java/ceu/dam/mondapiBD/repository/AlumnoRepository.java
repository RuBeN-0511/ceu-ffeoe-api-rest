package ceu.dam.mondapiBD.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.mondapiBD.model.Alumno;

@Repository
public interface AlumnoRepository extends MongoRepository<Alumno, String> {

}
