package ceu.dam.mondapiBD.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceu.dam.mondapiBD.exceptions.IncorrectPasswordException;
import ceu.dam.mondapiBD.exceptions.NotFoundException;
import ceu.dam.mondapiBD.exceptions.UserNotActiveException;
import ceu.dam.mondapiBD.exceptions.UserNotFoundException;
import ceu.dam.mondapiBD.exceptions.UserNotLoggedException;
import ceu.dam.mondapiBD.model.Alumno;
import ceu.dam.mondapiBD.model.RegistroPracticas;
import ceu.dam.mondapiBD.model.ResumenPracticas;
import ceu.dam.mondapiBD.model.Usuario;
import ceu.dam.mondapiBD.repository.AlumnoRepository;
import ceu.dam.mondapiBD.repository.RegistroRepository;
import ceu.dam.mondapiBD.repository.TutorDocenteRepository;
import ceu.dam.mondapiBD.repository.UsuarioRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private TutorDocenteRepository repoTutor;
	@Autowired
	private UsuarioRepository repoUser;
	@Autowired
	private AlumnoRepository repoAlumno;
	@Autowired
	private RegistroRepository repoRegistro;

	@Override
	public Alumno login(String username, String contraseña)
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

		return repoAlumno.findById(userOpt.get().getId())
				.orElseThrow(() -> new UserNotFoundException("No existe el alumno con ese nombre"));
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

			userOpt.get().setContraseña(contraseñaNueva);

			repoUser.save(userOpt.get());

		}

	}

	@Override
	public Alumno consultarAlumno(Alumno alumno) throws NotFoundException {

		return repoAlumno.findById(alumno.getId())
				.orElseThrow(() -> new NotFoundException("El alumno solicitado no se encuentra en la base de datos"));
	}

	@Override
	public ResumenPracticas getResumenPracticas(String idAlumno) throws NotFoundException {
		if (!repoAlumno.existsById(idAlumno)) {
			throw new NotFoundException("No se ha encontrado el alumno indicado");
		}

		List<RegistroPracticas> registros = repoRegistro.findAllByIdAlumno(idAlumno);

		Integer horasTotales = horasTotales();

		Integer horasRealizadas = registros.stream().mapToInt(RegistroPracticas::getCantidadHoras).sum();

		double porcentaje = horasTotales > 0 ? (horasRealizadas * 100.0) / horasTotales : 0;

		Integer horasPendientes = Math.max(0, horasTotales - horasRealizadas);

		return new ResumenPracticas(horasTotales, horasRealizadas, porcentaje, horasPendientes);
	}

	public Integer horasTotales() {
		// Aquí defines las horas totales según tu lógica de negocio
		// Opción 1: Valor fijo
		return 400; // Por ejemplo, 400 horas de prácticas

		// Opción 2: Obtenerlo de configuración
		// return configuracionRepository.findById("HORAS_PRACTICAS")
		// .map(Config::getValor)
		// .orElse(400);

		// Opción 3: Obtenerlo del ciclo formativo del alumno
		// return alumnoRepository.findById(idAlumno)
		// .map(Alumno::getCicloFormativo)
		// .map(CicloFormativo::getHorasPracticas)
		// .orElse(400);
	}
	@Override
	public Integer horasTotales() {
		return null;
	}

	@Override
	public Integer horasRealizadas(String idAlumno) {

		Integer totalHoras = 0;
		List<RegistroPracticas> registros = repoRegistro.findAllByIdAlumno(idAlumno);

		for (RegistroPracticas registro : registros) {

			totalHoras += registro.getCantidadHoras();

		}

		return totalHoras;
	}

	@Override
	public List<RegistroPracticas> consultarRegistros(Alumno alumno) {
		return repoRegistro.findAllByIdAlumno(alumno.getId());
	}

	@Override
	public RegistroPracticas crearRegistros(RegistroPracticas nuevoRegistro) throws NotFoundException {

		if (!repoAlumno.existsById(nuevoRegistro.getIdAlumno())) {
			throw new NotFoundException("El alumno al que desea crear un registro no existe en Base de datos");
		}

		return repoRegistro.save(nuevoRegistro);
	}

	@Override
	public void eliminarRegistro(String idRegistro) {

		repoRegistro.deleteById(idRegistro);
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
