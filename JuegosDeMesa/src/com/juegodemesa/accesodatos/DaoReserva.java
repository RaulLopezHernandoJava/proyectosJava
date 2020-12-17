package com.juegodemesa.accesodatos;

import com.juegodemesa.modelos.Juego;
import com.juegodemesa.modelos.Reserva;
import com.juegodemesa.modelos.Usuario;

public interface DaoReserva extends Dao<Reserva>{
	
	public Reserva obtenerporId(int idCarrito);
	public void insertarReserva(Usuario usuario, Juego juego, String copias);
	public void modificarReserva(int idCarrito);
	public void borrarReserva(int idCarrito);

}
