package com.juegodemesa.logicanegocio.direcciones;

import com.juegodemesa.modelos.ComunidadAutonoma;
import com.juegodemesa.modelos.Direccion;
import com.juegodemesa.modelos.Provincia;

public interface DireccionLogica {
	Iterable<Direccion> obtenerTodos();

	void insertar(Direccion direccion);

	void modificar(Direccion direccion);

	Direccion obtenerPorEmail(String email);

	Provincia obtenerProvinciaPorNombre(String nombreProvincia);

	ComunidadAutonoma obtenerComunidadAutonomaPorNombre(String nombreComunidad);

}
