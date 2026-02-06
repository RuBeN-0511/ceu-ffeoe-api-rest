package ceu.dam.mondapiBD.service;

import java.time.LocalDate;
import java.util.List;

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

public interface AdminService {

	/**
	 * Autentica a un usuario en el sistema. Verifica que el usuario esté asociado a
	 * un tutor docente y que esté activo. La contraseña debe viajar cifrada con
	 * algún algoritmo HASH. Esta es la única operación permitida sin estar logado.
	 * 
	 * @param username   El nombre de usuario
	 * @param contraseña La contraseña cifrada del usuario
	 * @return El tutor docente asociado al usuario autenticado
	 * @throws UserNotFoundException
	 * @throws IncorrectPasswordException
	 * @throws UserNotActiveException
	 * @throws AuthenticationException    si las credenciales son inválidas o el
	 *                                    usuario está inactivo
	 */
	public TutorDocente login(String username, String contraseña)
			throws UserNotFoundException, IncorrectPasswordException, UserNotActiveException;

	/**
	 * Cambia la contraseña del usuario actual. La contraseña tendrá una longitud
	 * mínima de 8 caracteres. La nueva contraseña debe viajar cifrada con algún
	 * algoritmo HASH.
	 * 
	 * @param contraseñaAntigua La contraseña actual del usuario (cifrada)
	 * @param contraseñaNueva   La nueva contraseña (cifrada, mínimo 8 caracteres)
	 * @throws UserNotFoundException
	 * @throws UserNotLoggedException
	 * @throws ValidationException    si la contraseña antigua es incorrecta o la
	 *                                nueva no cumple requisitos
	 */
	public void cambiarContraseña(String idUsuario, String contraseñaAntigua, String contraseñaNueva)
			throws UserNotFoundException, UserNotLoggedException;

	/**
	 * Consulta todos los usuarios del sistema. Muestra información de usuario,
	 * perfil, nombre completo y estado activo. Puede filtrarse por perfil
	 * (TODOS/TUTORES/ALUMNOS) y por estado (TODOS/ACTIVOS).
	 * 
	 * @return Lista de usuarios registrados en el sistema
	 */
	public List<Usuario> consultarUsuarios();

	/**
	 * Cambia la contraseña de un usuario específico. Permite al administrador
	 * cambiar la contraseña de cualquier usuario del sistema. La contraseña debe
	 * tener al menos 8 caracteres y viajar cifrada con algún algoritmo HASH.
	 * 
	 * @param idUsuario       Identificador del usuario cuya contraseña se va a
	 *                        cambiar
	 * @param contraseñaNueva La nueva contraseña (cifrada, mínimo 8 caracteres)
	 * @throws UserNotFoundException
	 * @throws ValidationException   si la contraseña no cumple los requisitos
	 *                               mínimos
	 * @throws NotFoundException     si el usuario no existe
	 */
	public void cambiarContraseñaUsuario(String idUsuario, String contraseñaNueva) throws UserNotFoundException;

	/**
	 * Activa un usuario que estaba inactivo. Solo puede ejecutarse si el usuario
	 * está actualmente inactivo.
	 * 
	 * @param id Identificador del usuario a activar
	 * @throws UserNotFoundException
	 * @throws ValidationException   si el usuario ya está activo o no existe
	 */
	public void activarUsuario(String id) throws UserNotFoundException;

	/**
	 * Desactiva un usuario que está activo. Solo puede ejecutarse si el usuario
	 * está actualmente activo.
	 * 
	 * @param id Identificador del usuario a desactivar
	 * @throws UserNotFoundException 
	 * @throws ValidationException si el usuario ya está inactivo o no existe
	 */
	public void desactivarUsuario(String id) throws UserNotFoundException;

	/**
	 * Crea un nuevo usuario en el sistema. El nombre de usuario debe ser único. La
	 * contraseña debe tener al menos 8 caracteres y viajar cifrada. Todos los datos
	 * son obligatorios: USUARIO, CONTRASEÑA, PERFIL y ALUMNO/TUTOR asociado. El
	 * usuario se crea activo por defecto.
	 * 
	 * @param nuevoUsuario Objeto con los datos del nuevo usuario
	 * @return El usuario creado
	 * @throws UserExistsException
	 * @throws ValidationException si el nombre de usuario ya existe o los datos no
	 *                             son válidos
	 */
	public Usuario crearUsuario(Usuario nuevoUsuario) throws UserExistsException;

	/**
	 * Edita los datos de un usuario existente. Permite modificar la información del
	 * usuario, incluyendo perfil y asociación con alumno/tutor. El nombre de
	 * usuario debe seguir siendo único si se modifica.
	 * 
	 * @param usuarioEditado Objeto con los datos actualizados del usuario
	 * @return El usuario actualizado
	 * @throws UserNotFoundException
	 * @throws UsernameUsedException
	 * @throws ValidationException   si el nombre de usuario ya existe o los datos
	 *                               no son válidos
	 * @throws NotFoundException     si el usuario no existe
	 */
	public Usuario editarUsuario(Usuario usuarioEditado) throws UserNotFoundException, UsernameUsedException;

	/**
	 * Consulta todas las empresas del sistema. Muestra nombre de empresa, datos del
	 * tutor laboral y estado activo. Puede filtrarse por estado (TODOS/ACTIVOS).
	 * Los registros se ordenan alfabéticamente por nombre de empresa.
	 * 
	 * @return Lista de empresas ordenadas alfabéticamente
	 */
	public List<Empresa> consultarEmpresas();

	/**
	 * Crea una nueva empresa en el sistema. El nombre de la empresa y el nombre del
	 * tutor laboral son obligatorios. Email y teléfono del tutor laboral son
	 * opcionales.
	 * 
	 * @param nuevaEmpresa Objeto con los datos de la nueva empresa
	 * @return La empresa creada
	 * @throws AlreadyExistsException
	 * @throws ValidationException    si faltan datos obligatorios
	 */
	public Empresa crearEmpresa(Empresa nuevaEmpresa) throws AlreadyExistsException;

	/**
	 * Edita los datos de una empresa existente. Todos los datos son obligatorios
	 * salvo email y teléfono del tutor laboral.
	 * 
	 * @param empresaEditada Objeto con los datos actualizados de la empresa
	 * @return La empresa actualizada
	 * @throws ValidationException si faltan datos obligatorios o la empresa no
	 *                             existe
	 */
	public Empresa editarEmpresa(Empresa empresaEditada);

	/**
	 * Consulta todos los tutores docentes del sistema. Muestra nombre y estado
	 * activo. Puede filtrarse por estado (TODOS/ACTIVOS). Los registros se ordenan
	 * alfabéticamente por nombre del tutor.
	 * 
	 * @return Lista de tutores docentes ordenados alfabéticamente
	 */
	public List<TutorDocente> consultarTutores();

	/**
	 * Crea un nuevo tutor docente en el sistema. El nombre del tutor es
	 * obligatorio. El tutor se crea activo por defecto.
	 * 
	 * @param nuevoTutor Objeto con los datos del nuevo tutor docente
	 * @return El tutor docente creado
	 * @throws UserExistsException
	 * @throws ValidationException si falta el nombre del tutor
	 */
	public TutorDocente crearTutorDocente(TutorDocente nuevoTutor, Usuario usuario) throws UserExistsException;

	/**
	 * Edita los datos de un tutor docente existente. Permite modificar el nombre
	 * del tutor y su estado activo.
	 * 
	 * @param tutorEditado Objeto con los datos actualizados del tutor docente
	 * @return El tutor docente actualizado
	 * @throws ValidationException si falta el nombre del tutor
	 * @throws NotFoundException   si el tutor no existe
	 */
	public TutorDocente editarTutorDocente(TutorDocente tutorEditado);

	/**
	 * Consulta todos los alumnos del sistema. Muestra nombre, ciclo, evaluación,
	 * año curso, empresa y tutor académico. Puede filtrarse por estado
	 * (TODOS/ACTIVOS), año, evaluación y ciclo. Los registros se ordenan por tutor
	 * académico.
	 * 
	 * @return Lista de alumnos ordenados por tutor académico
	 */
	public List<Alumno> consultarAlumnos();

	/**
	 * Edita los datos de un alumno existente. Permite modificar nombre, ciclo,
	 * evaluación, año curso, empresa y tutor académico.
	 * 
	 * @param alumnoEditado Objeto con los datos actualizados del alumno
	 * @return El alumno actualizado
	 * @throws ValidationException si faltan datos obligatorios o los datos no son
	 *                             válidos
	 * @throws NotFoundException   si el alumno no existe
	 */
	public Alumno editarAlumno(Alumno alumnoEditado);

	/**
	 * Genera y registra las fechas de un periodo lectivo. Registra todas las fechas
	 * entre inicio y fin excluyendo sábados y domingos. Si ya existen fechas
	 * registradas para ese curso y evaluación, muestra error.
	 * 
	 * @param fechaInicio Fecha de inicio del periodo
	 * @param fechaFin    Fecha de fin del periodo
	 * @param añoCurso    Año del curso académico
	 * @param evaluacion  Evaluación (MARZO o SEPTIEMBRE)
	 * @throws ValidationException si ya existen fechas para ese curso y evaluación
	 */
	public void generarFechas(LocalDate fechaInicio, LocalDate fechaFin, Integer añoCurso, String evaluacion);

	/**
	 * Consulta todas las fechas registradas en el sistema. Muestra fecha, año y
	 * evaluación. Puede filtrarse por año y por evaluación. Los registros se
	 * ordenan cronológicamente por fecha.
	 * 
	 * @return Lista de fechas ordenadas cronológicamente
	 */
	public List<Fecha> consultarFechas();

	/**
	 * Elimina una fecha del sistema. Si existen registros asociados a dicha fecha,
	 * también se eliminarán.
	 * 
	 * @param id Identificador de la fecha a borrar
	 * @throws NotFoundException si la fecha no existe
	 */
	public void borrarFecha(String id);

	/**
	 * Cierra la sesión del usuario actual.
	 */
	public void cerrarSesion(String idUsuario) throws NotFoundException;

	/**
	 * Cierra la aplicación. Finaliza la ejecución del programa.
	 * @throws NotFoundException 
	 */
	public void cerrarAplicacion(String idUsuario) throws NotFoundException;

}
