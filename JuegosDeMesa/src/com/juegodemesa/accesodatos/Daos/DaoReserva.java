package com.juegodemesa.accesodatos.Daos;

import com.juegodemesa.modelos.Juego;
import com.juegodemesa.modelos.Reserva;
import com.juegodemesa.modelos.Usuario;

public interface DaoReserva extends Dao<Reserva>{
	
	public Iterable <Reserva> obtenerReservasPorEmail(String email);
	public void insertarReserva(Usuario usuario, Juego juego, String copias);
	public void modificarReserva(int idCarrito);
	public void borrarReserva(Long idReserva);

}
