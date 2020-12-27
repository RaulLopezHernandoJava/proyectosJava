package com.juegodemesa.accesodatos.Daos;

import com.juegodemesa.modelos.ComunidadAutonoma;
import com.juegodemesa.modelos.Direccion;
import com.juegodemesa.modelos.Provincia;


public interface DaoDireccion extends Dao<Direccion>{
	public Direccion obtenerPorEmail(String email);
	public Provincia obtenerProvinciaPorNombre(String nombreProvincia);
	public ComunidadAutonoma obtenerComunidadAutonomaPorNombre(String nombreComunidad);

}
