package com.juegodemesa.controladores;

import com.juegodemesa.accesodatos.DaoMySql.DireccionesDaoMySql;
import com.juegodemesa.accesodatos.DaoMySql.JuegosDaoMySql;
import com.juegodemesa.accesodatos.DaoMySql.ReservasDaoMySql;
import com.juegodemesa.accesodatos.DaoMySql.UsuariosDaoMySql;
import com.juegodemesa.accesodatos.DaoTreeMap.MecanicasDaoTreeMap;
import com.juegodemesa.accesodatos.DaoTreeMap.RolesDaoTreeMap;
import com.juegodemesa.accesodatos.Daos.Dao;
import com.juegodemesa.accesodatos.Daos.DaoDireccion;
import com.juegodemesa.accesodatos.Daos.DaoReserva;
import com.juegodemesa.accesodatos.Daos.DaoUsuario;
import com.juegodemesa.logicanegocio.direcciones.DireccionLogica;
import com.juegodemesa.logicanegocio.direcciones.DireccionLogicaImpl;
import com.juegodemesa.logicanegocio.juegos.JuegosFiltros;
import com.juegodemesa.logicanegocio.juegos.JuegosLogica;
import com.juegodemesa.logicanegocio.juegos.JuegosLogicaImpl;
import com.juegodemesa.logicanegocio.reservas.ReservasLogica;
import com.juegodemesa.logicanegocio.reservas.ReservasLogicaImpl;
import com.juegodemesa.logicanegocio.usuario.UsuarioLogica;
import com.juegodemesa.logicanegocio.usuario.UsuarioLogicaImpl;
import com.juegodemesa.modelos.Juego;
import com.juegodemesa.modelos.Mecanica;
import com.juegodemesa.modelos.Rol;
import com.juegodemesa.modelos.Usuario;

public class Configuracion {
	
	public static JuegosLogica juegoLogica = JuegosLogicaImpl.getInstancia();
	public static JuegosFiltros juegoFiltro = JuegosLogicaImpl.getInstancia();
	public static ReservasLogica reservaLogica = ReservasLogicaImpl.getInstancia();
	public static UsuarioLogica usuarioLogica = UsuarioLogicaImpl.getInstancia();
	public static DireccionLogica direccionLogica = DireccionLogicaImpl.getInstancia();
	
	public static Dao<Mecanica> daoMecanica = MecanicasDaoTreeMap.getInstancia();
	public static Dao<Rol> daoRol = RolesDaoTreeMap.getInstancia();
	//public static DaoReserva daoReserva =  ReservasDaoMySql.getInstancia();
	//public static DaoDireccion daoDireccion = DireccionesDaoMySql.getInstancia();
	//public static Dao<Pelicula> dao = PeliculasDaoTreeMap.getInstancia();
}
