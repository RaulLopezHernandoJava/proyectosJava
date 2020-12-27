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
