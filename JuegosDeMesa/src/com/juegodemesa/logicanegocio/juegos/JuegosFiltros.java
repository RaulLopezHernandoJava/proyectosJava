package com.juegodemesa.logicanegocio.juegos;

import com.juegodemesa.modelos.Juego;

public interface JuegosFiltros {
	
	Iterable<Juego> filtrarJuegosAutor(String autor);
	Iterable<Juego> filtrarJuegosEditorial(String editorial); 
	Iterable<Juego> filtrarJuegosMecanica(String mecanica);
	Iterable<Juego> filtrarJuegosPrecioMinimo(Integer min);
	Iterable<Juego> filtrarJuegosPrecioMaximo(Integer max); 
    Iterable<Juego> filtrarJuegosEntreDosPrecios(Integer min, Integer max);

}
