package com.juegodemesa.controladores;

import com.juegodemesa.accesodatos.Dao;
import com.juegodemesa.accesodatos.DaoDireccion;
import com.juegodemesa.accesodatos.DaoReserva;
import com.juegodemesa.accesodatos.DaoUsuario;
import com.juegodemesa.accesodatos.DireccionesDaoMySql;
import com.juegodemesa.accesodatos.JuegosDaoMySql;
import com.juegodemesa.accesodatos.MecanicasDaoTreeMap;
import com.juegodemesa.accesodatos.ReservasDaoMySql;
import com.juegodemesa.accesodatos.RolesDaoTreeMap;
import com.juegodemesa.accesodatos.UsuariosDaoMySql;
import com.juegodemesa.modelos.Juego;
import com.juegodemesa.modelos.Mecanica;
import com.juegodemesa.modelos.Rol;
import com.juegodemesa.modelos.Usuario;

public class Configuracion {
	public static Dao<Juego> dao = JuegosDaoMySql.getInstancia();
	public static DaoUsuario daoUsuario = UsuariosDaoMySql.getInstancia();
	public static Dao<Mecanica> daoMecanica = MecanicasDaoTreeMap.getInstancia();
//	public static Dao<Provincia> daoProvincia DireccionesDaoMySql.getInstancia().
	public static Dao<Rol> daoRol = RolesDaoTreeMap.getInstancia();
	public static DaoReserva daoReserva =  ReservasDaoMySql.getInstancia();
	public static DaoDireccion daoDireccion = DireccionesDaoMySql.getInstancia();
	//public static Dao<Pelicula> dao = PeliculasDaoTreeMap.getInstancia();
}
