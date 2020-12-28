package com.juegodemesa.accesodatos.Daos;

import com.juegodemesa.accesodatos.AccesoDatosException;

//IMPLEMENTACION BASICA DE Dao

// Esta implementacion basica obliga a implementar todos estos metodos que se extiendan de esta Interfae Dao
// Metodos : obtnerTodos() , obtenerPorId() , insertar() , modificar() y borrar()
//public interface Dao<T> {
//	Iterable<T> obtenerTodos();
//	T obtenerPorId(Long id);
//	void insertar(T objeto);
//	void modificar(T objeto);
//	void borrar(Long id);
//}

// Esta Interface preimplentada hace que no sea obligatorio implementar todos los metodos de la interface. Pudiendo elegir cuales
// implementar (YA LA INTERFAZ NO OBLIGA). Los metodos que no implementemos arrojaran una excepcion "OPERACION_NO_IMPLEMENTADA"


public interface Dao<T> {
	public static final String OPERACIÓN_NO_IMPLEMENTADA = "Operación no implementada";

	default Iterable<T> obtenerTodos() {
		throw new AccesoDatosException(OPERACIÓN_NO_IMPLEMENTADA);
	}

	default T obtenerPorId(Long id) {
		throw new AccesoDatosException(OPERACIÓN_NO_IMPLEMENTADA);
	}

	default void insertar(T objeto) {
		throw new AccesoDatosException(OPERACIÓN_NO_IMPLEMENTADA);
	}

	default void modificar(T objeto) {
		throw new AccesoDatosException(OPERACIÓN_NO_IMPLEMENTADA);
	}

	default void borrar(Long id) {
		throw new AccesoDatosException(OPERACIÓN_NO_IMPLEMENTADA);
	}
	
	default Iterable<T> filtrarJuegosAutor(String autor) {
		throw new AccesoDatosException(OPERACIÓN_NO_IMPLEMENTADA);
	}
	
	default Iterable<T> filtrarJuegosEditorial(String editorial) {
		throw new AccesoDatosException(OPERACIÓN_NO_IMPLEMENTADA);
	}
	
	default Iterable<T> filtrarJuegosMecanica(String mecanica) {
		throw new AccesoDatosException(OPERACIÓN_NO_IMPLEMENTADA);
	}
	
	default Iterable<T> filtrarJuegosPrecioMinimo(Integer min) {
		throw new AccesoDatosException(OPERACIÓN_NO_IMPLEMENTADA);
	}
	
	
	default Iterable<T> filtrarJuegosPrecioMaximo(Integer max) {
		throw new AccesoDatosException(OPERACIÓN_NO_IMPLEMENTADA);
	}
	
	default Iterable<T> filtrarJuegosEntreDosPrecios(Integer min, Integer max) {
		throw new AccesoDatosException(OPERACIÓN_NO_IMPLEMENTADA);
	}
	
}

