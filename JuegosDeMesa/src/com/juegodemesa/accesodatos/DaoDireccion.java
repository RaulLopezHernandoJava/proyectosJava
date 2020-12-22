package com.juegodemesa.accesodatos;

import com.juegodemesa.modelos.ComunidadAutonoma;
import com.juegodemesa.modelos.Direccion;
import com.juegodemesa.modelos.Provincia;


public interface DaoDireccion extends Dao<Direccion>{
	public Direccion obtenerPorEmail(String email);
	public Provincia obtenerProvinciaPorId(Long idProvincia);
	public ComunidadAutonoma obtenerComunidadAutonomaPorId(Long idComunidad);

}
