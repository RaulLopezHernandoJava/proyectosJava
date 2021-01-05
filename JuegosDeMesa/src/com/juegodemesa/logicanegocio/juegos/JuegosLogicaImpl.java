package com.juegodemesa.logicanegocio.juegos;

import java.util.logging.Logger;

import com.juegodemesa.accesodatos.DaoMySql.JuegosDaoMySql;
import com.juegodemesa.accesodatos.DaoTreeMap.MecanicasDaoTreeMap;
import com.juegodemesa.accesodatos.Daos.Dao;
import com.juegodemesa.modelos.Juego;
import com.juegodemesa.modelos.Mecanica;

public class JuegosLogicaImpl implements JuegosLogica,JuegosFiltros {

	private static final Logger LOGGER = Logger.getLogger(JuegosLogicaImpl.class.getName());

	private static Dao<Juego> daoJuegos = JuegosDaoMySql.getInstancia();
	private static Dao<Mecanica> daoMecanicas = MecanicasDaoTreeMap.getInstancia();

	private JuegosLogicaImpl() {
	};

	private static final JuegosLogicaImpl INSTANCIA = new JuegosLogicaImpl();

	public static JuegosLogicaImpl getInstancia() {
		return INSTANCIA;
	}

	@Override
	public Iterable<Juego> listarJuegos() {
		return daoJuegos.obtenerTodos();
	}

	@Override
	public void anadirJuego(Juego juego) {
		daoJuegos.insertar(juego);

	}

	@Override
	public void editarJuego(Juego juego) {
		daoJuegos.modificar(juego);

	}

	@Override
	public void borrarJuego(Long id) {
		daoJuegos.borrar(id);

	}

	@Override
	public Iterable<Mecanica> obtenerMecanicas() {
		return daoMecanicas.obtenerTodos();
	}

	@Override
	public Juego obtenerPorId(Long id) {
		return daoJuegos.obtenerPorId(id);
	}

	@Override
	public Mecanica obtenerMecanicaPorId(Long id) {
		LOGGER.info(id.toString());
		return daoMecanicas.obtenerPorId(id);
	}
	

	@Override
	public Iterable<Juego> filtrarJuegosAutor(String autor) {
		return daoJuegos.filtrarJuegosAutor(autor);
	}

	@Override
	public Iterable<Juego> filtrarJuegosEditorial(String editorial) {
		return daoJuegos.filtrarJuegosEditorial(editorial);
	}

	@Override
	public Iterable<Juego> filtrarJuegosMecanica(String mecanica) {
		return daoJuegos.filtrarJuegosMecanica(mecanica);
	}

	@Override
	public Iterable<Juego> filtrarJuegosPrecioMinimo(Integer min) {
		return daoJuegos.filtrarJuegosPrecioMinimo(min);
	}

	@Override
	public Iterable<Juego> filtrarJuegosPrecioMaximo(Integer max) {
		return daoJuegos.filtrarJuegosPrecioMaximo(max);
	}

	@Override
	public Iterable<Juego> filtrarJuegosEntreDosPrecios(Integer min, Integer max) {
		return daoJuegos.filtrarJuegosPrecioMinimo(min);
	}

}
