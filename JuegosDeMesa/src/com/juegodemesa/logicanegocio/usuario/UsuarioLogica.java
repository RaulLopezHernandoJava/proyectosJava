package com.juegodemesa.logicanegocio.usuario;

import com.juegodemesa.modelos.Usuario;

public interface UsuarioLogica {
	Iterable<Usuario> obtenerTodos();
	Usuario obtenerPorId(Long id);
	void insertar(Usuario usuario);
	void modificar(Usuario usuario);
	void borrar(Long id);
	Usuario obtenerPorEmail(String email);
}
