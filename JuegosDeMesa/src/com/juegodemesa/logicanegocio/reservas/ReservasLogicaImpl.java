package com.juegodemesa.logicanegocio.reservas;

import java.util.logging.Logger;

import com.juegodemesa.accesodatos.DaoMySql.ReservasDaoMySql;
import com.juegodemesa.accesodatos.Daos.Dao;
import com.juegodemesa.modelos.Juego;
import com.juegodemesa.modelos.Reserva;
import com.juegodemesa.modelos.Usuario;

public class ReservasLogicaImpl implements ReservasLogica {

	private static final Logger LOGGER = Logger.getLogger(ReservasLogicaImpl.class.getName());

	private static Dao<Reserva> daoReservas = ReservasDaoMySql.getInstancia();

	private ReservasLogicaImpl() {
	};

	private static final ReservasLogicaImpl INSTANCIA = new ReservasLogicaImpl();

	public static ReservasLogicaImpl getInstancia() {
		return INSTANCIA;
	}

	@Override
	public Iterable<Reserva> listarReservas() {
		return daoReservas.obtenerTodos();
	}

	@Override
	public Iterable<Reserva> obtenerReservasPorEmail(String email) {
		return ReservasDaoMySql.getInstancia().obtenerReservasPorEmail(email);
	}

	@Override
	public Reserva obtenerPorId(Long id) {
		return daoReservas.obtenerPorId(id);
	}

	@Override
	public void editarReserva(Reserva reserva) {
		daoReservas.modificar(reserva);

	}

	@Override
	public void insertarReserva(Usuario usuario, Juego juego, String copias) {
		ReservasDaoMySql.getInstancia().insertarReserva(usuario, juego, copias);
	}

	@Override
	public void modificarReserva(int idCarrito) {
		ReservasDaoMySql.getInstancia().modificarReserva(idCarrito);
	}

	@Override
	public void borrarReserva(Long idReserva) {
		daoReservas.borrar(idReserva);

	}

	@Override
	public Iterable<Reserva> obtenerTodos() {
		return daoReservas.obtenerTodos();
	}

}
