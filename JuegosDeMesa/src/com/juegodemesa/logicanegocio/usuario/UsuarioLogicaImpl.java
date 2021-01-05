package com.juegodemesa.logicanegocio.usuario;

import java.util.logging.Logger;

import com.juegodemesa.accesodatos.DaoMySql.UsuariosDaoMySql;
import com.juegodemesa.accesodatos.Daos.Dao;
import com.juegodemesa.modelos.Usuario;

public class UsuarioLogicaImpl implements UsuarioLogica {

	private static final Logger LOGGER = Logger.getLogger(UsuarioLogicaImpl.class.getName());

	private static Dao<Usuario> daoUsuarios = UsuariosDaoMySql.getInstancia();

	private UsuarioLogicaImpl() {
	};

	private static final UsuarioLogicaImpl INSTANCIA = new UsuarioLogicaImpl();

	public static UsuarioLogicaImpl getInstancia() {
		return INSTANCIA;
	}

	@Override
	public Iterable<Usuario> obtenerTodos() {
		return daoUsuarios.obtenerTodos();
	}

	@Override
	public Usuario obtenerPorId(Long id) {
		return daoUsuarios.obtenerPorId(id);
	}

	@Override
	public void insertar(Usuario usuario) {
		daoUsuarios.insertar(usuario);

	}

	@Override
	public void modificar(Usuario usuario) {
		daoUsuarios.modificar(usuario);
	}

	@Override
	public void borrar(Long id) {
		daoUsuarios.borrar(id);

	}

	@Override
	public Usuario obtenerPorEmail(String email) {
		return UsuariosDaoMySql.getInstancia().obtenerPorEmail(email);
	}

}
