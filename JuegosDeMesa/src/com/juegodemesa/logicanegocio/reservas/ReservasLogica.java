package com.juegodemesa.logicanegocio.reservas;

import com.juegodemesa.modelos.Juego;
import com.juegodemesa.modelos.Reserva;
import com.juegodemesa.modelos.Usuario;

public interface ReservasLogica {

	Iterable<Reserva> listarReservas();

	Iterable<Reserva> obtenerTodos();

	public Iterable<Reserva> obtenerReservasPorEmail(String email);

	Reserva obtenerPorId(Long id);

	void editarReserva(Reserva reserva);

	public void insertarReserva(Usuario usuario, Juego juego, String copias);

	public void modificarReserva(int idCarrito);

	public void borrarReserva(Long idReserva);

}
