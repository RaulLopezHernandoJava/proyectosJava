package com.juegodemesa.accesodatos;

import com.juegodemesa.modelos.Usuario;

public interface DaoUsuario extends Dao<Usuario>{
	public Usuario obtenerPorEmail(String email);
	
}
