package ceu.dam.mondapiBD.service;

import java.util.List;

import ceu.dam.mondapiBD.exceptions.AuthenticationException;
import ceu.dam.mondapiBD.exceptions.IncorrectPasswordException;
import ceu.dam.mondapiBD.exceptions.NotFoundException;
import ceu.dam.mondapiBD.exceptions.UserNotActiveException;
import ceu.dam.mondapiBD.exceptions.UserNotFoundException;
import ceu.dam.mondapiBD.exceptions.UserNotLoggedException;
import ceu.dam.mondapiBD.exceptions.ValidationException;
import ceu.dam.mondapiBD.model.Alumno;
import ceu.dam.mondapiBD.model.RegistroPracticas;
import ceu.dam.mondapiBD.model.ResumenPracticas;
import ceu.dam.mondapiBD.model.Usuario;

public interface UserService {

	/**
	 * Realiza el login de un usuario en el sistema. Cifra la contraseña para
	 * compararla con la almacenada. Verifica que el usuario esté activo antes de
	 * permitir el acceso.
	 *
	 * @param username   Nombre de usuario
	 * @param contraseña Contraseña en texto plano (será cifrada internamente)
	 * @return El usuario autenticado si las credenciales son correctas
	 * @throws IncorrectPasswordException
	 * @throws UserNotFoundException
	 * @throws UserNotActiveException
	 * @throws AuthenticationException    si las credenciales son incorrectas
	 * @throws BusinessException          si el usuario no está activo
	 */
	public Alumno login(String username, String contraseña)
			throws UserNotFoundException, IncorrectPasswordException, UserNotActiveException;

	/**
	 * Cambia la contraseña de un usuario existente. La contraseña nueva tendrá una
	 * longitud mínima de 8 caracteres. El usuario tiene que estar activo para poder
	 * cambiar la contraseña.
	 *
	 * @param id                Identificador del usuario
	 * @param contraseñaAntigua Contraseña actual del usuario
	 * @param contraseñaNueva   Nueva contraseña (mínimo 8 caracteres)
	 * @throws UserNotLoggedException
	 * @throws UserNotFoundException
	 * @throws ValidationException     si la contraseña nueva no cumple los
	 *                                 requisitos
	 * @throws AuthenticationException si la contraseña antigua no es correcta
	 * @throws BusinessException       si el usuario no está activo
	 * @throws NotFoundException       si el usuario no existe
	 */
	public void cambiarContraseña(String id, String contraseñaAntigua, String contraseñaNueva)
			throws UserNotFoundException, UserNotLoggedException;

	/**
	 * Consulta todos los usuarios relacionados con un alumno. Devuelve todos los
	 * datos del alumno con todas sus entidades asociadas (tutor, docentes y
	 * empresa).
	 *
	 * @param alumno El alumno del cual se quieren consultar los usuarios
	 *               relacionados
	 * @return Lista de usuarios asociados al alumno (tutores, docentes, etc.)
	 * @throws NotFoundException si el alumno no existe
	 */
	public Alumno consultarAlumno(Alumno alumno) throws NotFoundException;

	/**
	 * Genera un resumen completo de las prácticas de un alumno. Este resumen debe
	 * mostrar: i. Cantidad total de horas que hay que realizar ii. Cantidad total
	 * de horas realizadas (y tanto por ciento del total) iii. Cantidad total de
	 * horas pendientes
	 *
	 * @throws NotFoundException si no se encuentra la información del alumno
	 */
	public ResumenPracticas getResumenPracticas(String idAlumno) throws NotFoundException;

	/**
	 * Calcula el total de horas que debe realizar el alumno en sus prácticas.
	 *
	 * @return Cantidad total de horas requeridas
	 */
	public Integer horasTotales();

	/**
	 * Calcula el total de horas que el alumno ha realizado hasta el momento.
	 *
	 * @return Cantidad total de horas completadas
	 */
	public Integer horasRealizadas(String idAlumno);

	/**
	 * Consulta todos los registros de prácticas de un alumno. Devuelve una lista
	 * con todos los registros asociados al alumno.
	 *
	 * @param alumno El alumno del cual se quieren consultar los registros
	 * @return Lista de registros de prácticas del alumno
	 * @throws NotFoundException si el alumno no existe
	 */
	public List<RegistroPracticas> consultarRegistros(Alumno alumno);

	/**
	 * Crea un nuevo registro de prácticas. La fecha debe estar disponible (sin
	 * registro previo), las horas deben ser mayores a 0, menores o iguales a 8, y
	 * con saltos de 0.5. El id del alumno en el nuevo registro no puede ser null
	 *
	 * @param Registro Nuevo
	 * @return El registro de prácticas creado
	 * @throws NotFoundException
	 * @throws ValidationException si los datos no cumplen las validaciones
	 * @throws BusinessException   si la fecha ya tiene un registro asociado
	 */
	public RegistroPracticas crearRegistros(RegistroPracticas nuevoRegistro) throws NotFoundException;

	/**
	 * Elimina un registro de prácticas existente. Borra permanentemente el registro
	 * de la base de datos.
	 *
	 * @param idRegistro Identificador del registro a eliminar
	 * @throws NotFoundException si el registro no existe
	 */
	public void eliminarRegistro(String idRegistro);

	/**
	 * Cierra la sesión del usuario actual.
	 * 
	 * @throws NotFoundException
	 */
	public void cerrarSesion(String usuarioId) throws NotFoundException;

	/**
	 * Cierra la aplicación. Finaliza la ejecución del programa.
	 * 
	 * @throws NotFoundException
	 */
	public void cerrarAplicacion(String usuarioId) throws NotFoundException;

}
