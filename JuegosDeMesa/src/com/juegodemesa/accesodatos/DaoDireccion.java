package com.juegodemesa.accesodatos;

import com.juegodemesa.modelos.Direccion;

public interface DaoDireccion extends Dao<Direccion>{
	public Direccion obtenerPorEmail(String email);

}
