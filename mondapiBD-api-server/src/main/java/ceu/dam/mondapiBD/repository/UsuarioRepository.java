package ceu.dam.mondapiBD.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.mondapiBD.model.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

	// username cannot be the same or must not exist

	public Optional<String> findContraseñaByNombreUsuario(String nombreUsuario);

	public Optional<Usuario> findOneByNombreUsuario(String nombreUsuario);

}
