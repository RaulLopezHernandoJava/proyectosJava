package com.juegodemesa.accesodatos.DaoTreeMap;

import java.util.TreeMap;

import com.juegodemesa.accesodatos.Daos.Dao;
import com.juegodemesa.modelos.Mecanica;
import com.juegodemesa.modelos.Rol;


public class RolesDaoTreeMap implements Dao<Rol> {

	private static final TreeMap<Long, Rol> ROLES = new TreeMap<>();
	
	static {
		ROLES.put(1L, new Rol(1L, "Administrador", "El administador tiene la capacidad de añadir , editar y borrar los juegos de la web "));
		ROLES.put(2L, new Rol(2L, "Usuario", "El usuario solo tiene la capacidad de vsisualizar el listado de juegos de la web"));
	}
	
	//SINGLETON
	private RolesDaoTreeMap() {}
	
	private static final RolesDaoTreeMap INSTANCIA = new RolesDaoTreeMap();
	
	public static RolesDaoTreeMap getInstancia() {
		return INSTANCIA;
	}
	//FIN SINGLETON
	
	@Override
	public Iterable<Rol> obtenerTodos() {
		return ROLES.values();
	}

	@Override
	public Rol obtenerPorId(Long id) {
		return ROLES.get(id);
	}
}
