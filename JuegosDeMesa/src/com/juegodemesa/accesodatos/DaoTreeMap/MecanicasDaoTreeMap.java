package com.juegodemesa.accesodatos.DaoTreeMap;

import java.util.TreeMap;

import com.juegodemesa.accesodatos.Daos.Dao;
import com.juegodemesa.modelos.Mecanica;


public class MecanicasDaoTreeMap implements Dao<Mecanica> {

	private static final TreeMap<Long, Mecanica> MECANICAS = new TreeMap<>();
	
	static {
		MECANICAS.put(1L, new Mecanica(1L, "Roll and Write", "Descripcion de mecanica Roll and Write"));
		MECANICAS.put(2L, new Mecanica(2L, "Colocacion de Trabajadores", "Descripcion de Mecanica de Colocacion de Trabajadores"));
	}
	
	//SINGLETON
	private MecanicasDaoTreeMap() {}
	
	private static final MecanicasDaoTreeMap INSTANCIA = new MecanicasDaoTreeMap();
	
	public static MecanicasDaoTreeMap getInstancia() {
		return INSTANCIA;
	}
	//FIN SINGLETON
	
	@Override
	public Iterable<Mecanica> obtenerTodos() {
		return MECANICAS.values();
	}

	@Override
	public Mecanica obtenerPorId(Long id) {
		return MECANICAS.get(id);
	}
}
