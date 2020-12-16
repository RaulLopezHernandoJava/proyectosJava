package com.juegodemesa.accesodatos;

import com.juegodemesa.modelos.Carrito;

public interface DaoCarrito {
	
	public Carrito obtenerporId(int idCarrito);
	public Carrito insertarCarrito(int idCarrito, int idUsuario);
	public Carrito modificarCarrito(int idCarrito);
	public Carrito borrarCarrito(int idCarrito);

}
