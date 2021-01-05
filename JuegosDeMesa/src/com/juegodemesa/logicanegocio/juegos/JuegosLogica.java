package com.juegodemesa.logicanegocio.juegos;

import com.juegodemesa.modelos.Juego;
import com.juegodemesa.modelos.Mecanica;

public interface JuegosLogica {

	Iterable<Juego> listarJuegos();

	Juego obtenerPorId(Long id);

	void anadirJuego(Juego juego);

	void editarJuego(Juego juego);

	void borrarJuego(Long id);

	Iterable<Mecanica> obtenerMecanicas();

	Mecanica obtenerMecanicaPorId(Long id);
	

}
