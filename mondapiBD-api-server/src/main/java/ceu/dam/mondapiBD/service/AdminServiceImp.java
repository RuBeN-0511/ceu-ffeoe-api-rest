package ceu.dam.mondapiBD.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ceu.dam.mondapiBD.exceptions.AlreadyExistsException;
import ceu.dam.mondapiBD.exceptions.IncorrectPasswordException;
import ceu.dam.mondapiBD.exceptions.NotFoundException;
import ceu.dam.mondapiBD.exceptions.UserExistsException;
import ceu.dam.mondapiBD.exceptions.UserNotActiveException;
import ceu.dam.mondapiBD.exceptions.UserNotFoundException;
import ceu.dam.mondapiBD.exceptions.UserNotLoggedException;
import ceu.dam.mondapiBD.exceptions.UsernameUsedException;
import ceu.dam.mondapiBD.model.Alumno;
import ceu.dam.mondapiBD.model.Empresa;
import ceu.dam.mondapiBD.model.Fecha;
import ceu.dam.mondapiBD.model.TutorDocente;
import ceu.dam.mondapiBD.model.Usuario;
import ceu.dam.mondapiBD.repository.AlumnoRepository;
import ceu.dam.mondapiBD.repository.EmpresaRepository;
import ceu.dam.mondapiBD.repository.FechaRepository;
import ceu.dam.mondapiBD.repository.RegistroRepository;
import ceu.dam.mondapiBD.repository.TutorDocenteRepository;
import ceu.dam.mondapiBD.repository.UsuarioRepository;

@Service
public class AdminServiceImp implements AdminService {

	@Autowired
	private UsuarioRepository repoUser;
	@Autowired
	private TutorDocenteRepository repoTutor;
	@Autowired
	private EmpresaRepository repoEmpresa;
	@Autowired
	private AlumnoRepository repoAlumno;
	@Autowired
	private FechaRepository repoFecha;
	@Autowired
	private RegistroRepository repoRegistro;

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

	public void activarUsuario(String idUsuario) throws UserNotFoundException {
		cambiarEstadoUsuario(idUsuario, true);
	}

	@Override
	public void desactivarUsuario(String idUsuario) throws UserNotFoundException {
		cambiarEstadoUsuario(idUsuario, false);
	}

	private void cambiarEstadoUsuario(String idUsuario, boolean activar) throws UserNotFoundException {
		Usuario usuario = repoUser.findById(idUsuario)
				.orElseThrow(() -> new UserNotFoundException("No se ha encontrado el usuario indicado"));

		usuario.setActivo(activar);
		repoUser.save(usuario);
	}

	@Override

	// TODO: revisar como optimizar este metodo
	public Usuario crearUsuario(Usuario nuevoUsuario) throws UserExistsException {

		Optional<Usuario> userOpt = repoUser.findOneByNombreUsuario(nuevoUsuario.getNombreUsuario());

		if (!userOpt.isPresent()) {
			throw new UserExistsException("Ya existe un registo con ese nombre de usuario");
		}
		userOpt.get().setActivo(true);

		return repoUser.save(nuevoUsuario);

	}

	@Override
	public Usuario editarUsuario(Usuario usuarioEditado) throws UserNotFoundException, UsernameUsedException {

		Optional<Usuario> userOpt = repoUser.findById(usuarioEditado.getId());

		if (userOpt.isEmpty()) {
			throw new UserNotFoundException("El usuario indicado no existe en Base de datos");
		}
		Optional<Usuario> usernameUsed = repoUser.findOneByNombreUsuario(usuarioEditado.getNombreUsuario());

		if (usernameUsed.isPresent()) {
			throw new UsernameUsedException("El nombre de usuario ya esta en uso");
		}

		return repoUser.save(usuarioEditado);
	}

	@Override
	public List<Empresa> consultarEmpresas() {

		return repoEmpresa.findAll(Sort.by("nombreEmpresa").ascending());
	}

	@Override
	public Empresa crearEmpresa(Empresa nuevaEmpresa) throws AlreadyExistsException {
		Optional<Empresa> empresaOpt = repoEmpresa.findById(nuevaEmpresa.getNombreEmpresa());

		if (empresaOpt.isPresent()) {
			throw new AlreadyExistsException("Ya existe la empresa en base de datos");
		}

		return repoEmpresa.save(nuevaEmpresa);
	}

	@Override
	public Empresa editarEmpresa(Empresa empresaEditada) {

		return repoEmpresa.save(empresaEditada);
	}

	@Override
	public List<TutorDocente> consultarTutores() {

		return repoTutor.findAll(Sort.by("nombreDocente"));
	}

	@Override
//	Puede pasarse solo un String del nombre
	public TutorDocente crearTutorDocente(TutorDocente nuevoTutor, Usuario usuario) throws UserExistsException {

		usuario.setPerfil("TUTOR DOCENTE");

		Usuario newUser = crearUsuario(usuario);
		nuevoTutor.setIdUsuario(newUser.getId());
		nuevoTutor.setActivo(true);

		TutorDocente newTutor = repoTutor.save(nuevoTutor);
		newUser.setIdTutor(newTutor.getId());

		repoUser.save(newUser);

		return newTutor;

	}

	@Override
	public TutorDocente editarTutorDocente(TutorDocente tutorEditado) {

		return repoTutor.save(tutorEditado);
	}

	// se ordena en el front
	@Override
	public List<Alumno> consultarAlumnos() {

		return repoAlumno.findAll();
	}

	@Override
	public Alumno editarAlumno(Alumno alumnoEditado) {

		return repoAlumno.save(alumnoEditado);
	}

	@Override
	public void generarFechas(LocalDate fechaInicio, LocalDate fechaFin, Integer añoCurso, String evaluacion) {
	}

	@Override
	public List<Fecha> consultarFechas() {
		return repoFecha.findAll(Sort.by("fecha"));
	}

	@Override
	public void borrarFecha(String id) {

		repoRegistro.deleteAllByIdFecha(id);

		repoFecha.deleteById(id);

	}

	@Override
	public void cerrarSesion(String idUsuario) throws NotFoundException {

		Usuario userOpt = repoUser.findById(idUsuario).orElseThrow(() -> new NotFoundException(
				"El usuario con el que desea cerrar sesion no se encuentra en Base de datos"));

		userOpt.setEstaLogueado(false);
	}

	@Override
	public void cerrarAplicacion(String idUsuario) throws NotFoundException {

		cerrarSesion(idUsuario);

		System.exit(0);
	}

}
