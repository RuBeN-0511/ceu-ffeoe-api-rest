package ceu.dam.mondapiBD.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.mondapiBD.model.Fecha;

@Repository
public interface FechaRepository extends MongoRepository<Fecha, String> {
}
