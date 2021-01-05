package com.juegodemesa.logicanegocio.direcciones;

import java.util.logging.Logger;

import com.juegodemesa.accesodatos.DaoMySql.DireccionesDaoMySql;
import com.juegodemesa.accesodatos.Daos.Dao;
import com.juegodemesa.modelos.ComunidadAutonoma;
import com.juegodemesa.modelos.Direccion;
import com.juegodemesa.modelos.Provincia;

public class DireccionLogicaImpl implements DireccionLogica {

	private static final Logger LOGGER = Logger.getLogger(DireccionLogicaImpl.class.getName());

	private static Dao<Direccion> daoDirecciones = DireccionesDaoMySql.getInstancia();
	// private static Dao<Mecanica> daoMecanicas =
	// MecanicasDaoTreeMap.getInstancia();

	private DireccionLogicaImpl() {
	};

	private static final DireccionLogicaImpl INSTANCIA = new DireccionLogicaImpl();

	public static DireccionLogicaImpl getInstancia() {
		return INSTANCIA;
	}

	@Override
	public Iterable<Direccion> obtenerTodos() {
		return daoDirecciones.obtenerTodos();
	}

	@Override
	public void insertar(Direccion direccion) {
		daoDirecciones.insertar(direccion);
	}

	@Override
	public void modificar(Direccion direccion) {
		daoDirecciones.modificar(direccion);
	}

	@Override
	public Direccion obtenerPorEmail(String email) {
		return DireccionesDaoMySql.getInstancia().obtenerPorEmail(email);
	}

	@Override
	public ComunidadAutonoma obtenerComunidadAutonomaPorNombre(String nombreComunidad) {
		return DireccionesDaoMySql.getInstancia().obtenerComunidadAutonomaPorNombre(nombreComunidad);
	}

	@Override
	public Provincia obtenerProvinciaPorNombre(String nombreProvincia) {
		return DireccionesDaoMySql.getInstancia().obtenerProvinciaPorNombre(nombreProvincia);
	}

}
