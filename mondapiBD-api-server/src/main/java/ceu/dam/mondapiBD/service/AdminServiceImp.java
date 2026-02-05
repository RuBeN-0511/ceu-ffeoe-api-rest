package ceu.dam.mondapiBD.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceu.dam.mondapiBD.model.Alumno;
import ceu.dam.mondapiBD.model.Empresa;
import ceu.dam.mondapiBD.model.Fecha;
import ceu.dam.mondapiBD.model.TutorDocente;
import ceu.dam.mondapiBD.model.Usuario;
import ceu.dam.mondapiBD.repository.TutorDocenteRepository;
import ceu.dam.mondapiBD.repository.UsuarioRepository;

@Service
public class AdminServiceImp implements AdminService {

	@Autowired
	private UsuarioRepository repoUser;
	@Autowired
	private TutorDocenteRepository repoTutor;

	@Override
	public TutorDocente login(String username, String contraseña)
			throws UserNotFoundException, IncorrectPasswordException, UserNotActiveException {

		Optional<Usuario> userOpt = repoUser.findOneByNombreUsuario(username);

		if (userOpt.isEmpty()) {
			throw new UserNotFoundException("El nombre de usuario indicado no existe");
		} else if (!userOpt.get().getContraseña().equals(DigestUtils.sha3_512Hex(contraseña))) {
			throw new IncorrectPasswordException("La contraseña y el nombre de usuario no coincide");
		} else if (!userOpt.get().getActivo()) {
			throw new UserNotActiveException("El usuario no se encuentra activo para hacer login");

		}

		userOpt.get().setEstaLogueado(true);

		repoUser.save(userOpt.get());

		return repoTutor.findByIdUsuario(userOpt.get().getId())
				.orElseThrow(() -> new UserNotFoundException("No existe Tutor con el usuario indicado indicado"));

	}

	@Override
	public void cambiarContraseña(String idUsuario, String contraseñaAntigua, String contraseñaNueva)
			throws UserNotFoundException, UserNotLoggedException {

		Optional<Usuario> userOpt = repoUser.findById(idUsuario);

		if (userOpt.isEmpty()) {
			throw new UserNotFoundException("El usuario indicado no existe en BBDD");
		} else if (!userOpt.get().getEstaLogueado()) {
			throw new UserNotLoggedException("El usuario no se encuentra logeado");
		} else if (userOpt.get().getContraseña().equals(contraseñaAntigua)) {

			userOpt.get().setContraseña(DigestUtils.sha3_512Hex(contraseñaNueva));

			repoUser.save(userOpt.get());

		}

	}

	// TODO: verificar que el usuario esta logeado
	@Override
	public List<Usuario> consultarUsuarios() {

		return repoUser.findAll();

	}

	@Override
	public void cambiarContraseñaUsuario(String idUsuario, String contraseñaNueva) throws UserNotFoundException {

		Optional<Usuario> userOpt = repoUser.findById(idUsuario);
		if (userOpt.isEmpty()) {
			throw new UserNotFoundException("El usuario indicado no existe en BBDD");
		}

		userOpt.get().setContraseña(DigestUtils.sha3_512Hex(contraseñaNueva));

		repoUser.save(userOpt.get());

	}

//	TODO: si se va a llamar al metodo desde la lista de usuarios, realmente es necesario este metodo?
	@Override
	public void activarUsuario(String idUsuario) throws UserNotFoundException {

		Optional<Usuario> userOpt = repoUser.findById(idUsuario);

		if (userOpt.isEmpty()) {
			throw new UserNotFoundException("No se ha encontrado el usuario indicado");
		} else if (!userOpt.get().getActivo()) {
			userOpt.get().setActivo(true);
		}

	}

	// TODO: Se pueden juntar en un mismo metodo llamada alternar
	@Override
	public void desactivarUsuario(String idUsuario) {
	}

	@Override

	// TODO: revisar como optimizar este metodo
	public Usuario crearUsuario(Usuario nuevoUsuario) throws UserExistsException {

		Optional<Usuario> userOpt = repoUser.findOneByNombreUsuario(nuevoUsuario.getNombreUsuario());

		if (userOpt.isPresent()) {
			throw new UserExistsException("Ya existe un registo con ese nombre de usuario");
		}

		return repoUser.save(nuevoUsuario);

	}

	@Override
	public Usuario editarUsuario(Usuario usuarioEditado) {
		return null;
	}

	@Override
	public List<Empresa> consultarEmpresas() {
		return null;
	}

	@Override
	public Empresa crearEmpresa(Empresa nuevaEmpresa) {
		return null;
	}

	@Override
	public Empresa editarEmpresa(Empresa empresaEditada) {
		return null;
	}

	@Override
	public List<TutorDocente> consultarTutores() {
		return null;
	}

	@Override
	public TutorDocente crearTutorDocente(TutorDocente nuevoTutor) {
		return null;
	}

	@Override
	public TutorDocente editarTutorDocente(TutorDocente tutorEditado) {
		return null;
	}

	@Override
	public List<Alumno> consultarAlumnos() {
		return null;
	}

	@Override
	public Alumno editarAlumno(Alumno alumnoEditado) {
		return null;
	}

	@Override
	public void generarFechas(LocalDate fechaInicio, LocalDate fechaFin, Integer añoCurso, String evaluacion) {
	}

	@Override
	public List<Fecha> consultarFechas() {
		return null;
	}

	@Override
	public void borrarFecha(String id) {
	}

	@Override
	public void cerrarSesion() {
	}

	@Override
	public void cerrarAplicacion() {

		System.exit(0);
	}

}
