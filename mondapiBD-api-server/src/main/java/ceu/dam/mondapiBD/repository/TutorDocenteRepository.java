package ceu.dam.mondapiBD.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.mondapiBD.model.TutorDocente;

@Repository
public interface TutorDocenteRepository extends MongoRepository<TutorDocente, String> {

	Optional<TutorDocente> findByIdUsuario(String idUsuario);

}
