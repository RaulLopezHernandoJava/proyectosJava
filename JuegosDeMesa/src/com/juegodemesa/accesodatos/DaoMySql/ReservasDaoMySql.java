package com.juegodemesa.accesodatos.DaoMySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.juegodemesa.accesodatos.AccesoDatosException;
import com.juegodemesa.accesodatos.Daos.DaoReserva;
import com.juegodemesa.modelos.Juego;
import com.juegodemesa.modelos.Reserva;
import com.juegodemesa.modelos.Usuario;

public class ReservasDaoMySql implements DaoReserva {

	private static final Logger LOGGER = Logger.getLogger(ReservasDaoMySql.class.getName());
	
	// SINGLETON
	private DataSource dataSource;

	private ReservasDaoMySql() {
		try {
			InitialContext initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			dataSource = (DataSource) envCtx.lookup("jdbc/juegos");
		} catch (NamingException e) {
			throw new AccesoDatosException("No se ha encontrado el pool de conexiones", e);
		}
	}

	private static final ReservasDaoMySql INSTANCIA = new ReservasDaoMySql();

	public static ReservasDaoMySql getInstancia() {
		return INSTANCIA;
	}
	// FIN SINGLETON

	// characterEncoding=UTF-8 cambia la codificación de los PreparedStatement de
	// Windows-1252 a UTF-8

	// Ejemplos de Select . HAY QUE MODIFICAR

	private static final String SQL_SELECT = "SELECT r.id,u.nombre,u.apellidos,u.email,j.nombre,j.precio,j.imagen,r.cantidad,r.total FROM reservas r JOIN usuarios u ON u.id = r.idUsuario JOIN juegos j ON j.id = r.idJuego WHERE r.active =TRUE";
	private static final String SQL_SELECT_EMAIL = "SELECT r.id,u.nombre,u.apellidos,u.email,j.nombre,j.precio,j.imagen,r.cantidad,r.total FROM reservas r JOIN usuarios u ON u.id = r.idUsuario JOIN juegos j ON j.id = r.idJuego where u.email=?";
//		private static final String SQL_SELECT_USER = "SELECT * FROM usuarios u WHERE u.email = ?";
//		private static final String SQL_SELECT_PASSWORD = "SELECT u.password FROM usuarios u WHERE u.email = ?";
	private static final String SQL_INSERT = "INSERT INTO reservas (cantidad,total,idUsuario,idJuego,fecha) VALUES (?, ?, ?, ?, ?)";
//		private static final String SQL_UPDATE = "UPDATE usuarios SET nombre = ?, apellidos = ?, email= ? , id_rol = ? , edad = ? , fechaRegistro = ? WHERE id = ?";
	private static final String SQL_DELETE_RESERVA = "UPDATE reservas r SET active = FALSE where r.id = ?";

	// OBTENER TODAS LAS RESERVAS

	@Override
	public Iterable<Reserva> obtenerTodos() {
		try (Connection con = dataSource.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(SQL_SELECT);) {
			ArrayList<Reserva> reservas = new ArrayList<>();
			System.out.println("Estas son todas las reservas de los usuarios" + reservas);

			Usuario usuario;
			Reserva reserva;
			Juego juego;

			// Para convertir las filas de una tabla de la base de datos en objetos de una
			// colección
			while (rs.next()) {
				usuario = new Usuario(rs.getString("u.nombre"), rs.getString("u.apellidos"), rs.getString("u.email"));
				juego = new Juego(rs.getString("j.nombre"), rs.getInt("j.precio"), rs.getString("j.imagen"));
				reserva = new Reserva(rs.getLong("id"), usuario, juego, rs.getInt("r.cantidad"),
						rs.getDouble("r.total"), rs.getBoolean("r.active"));
				reservas.add(reserva);
			}

			return reservas;
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtener todos las reservas de los juegos", e);
		}

	}

	@Override
	public Iterable<Reserva> obtenerReservasPorEmail(String email) {

		try (Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_EMAIL);) {
			ArrayList<Reserva> reservas = new ArrayList<>();
			ps.setString(1, email);

			try (ResultSet rs = ps.executeQuery()) {

				Usuario usuario = null;
				Reserva reserva = null;
				Juego juego = null;

				while (rs.next()) {
					usuario = new Usuario(rs.getString("u.nombre"), rs.getString("u.apellidos"),
							rs.getString("u.email"));
					juego = new Juego(rs.getString("j.nombre"), rs.getInt("j.precio"), rs.getString("j.imagen"));
					reserva = new Reserva(rs.getLong("id"), usuario, juego, rs.getInt("r.cantidad"),
							rs.getDouble("r.total"), rs.getBoolean("active"));
					reservas.add(reserva);
				}

				return reservas;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtner las reservas del usuario " + email, e);
		}
	}

	@Override
	public void insertarReserva(Usuario usuario, Juego juego, String copias) {

		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_INSERT);) {

			// Calculo del total de copias
			Double numeroCopias = Double.parseDouble(copias);
			double total = juego.getPrecio() * numeroCopias;

			// Conversion para introduciar copias como int en la base de datos
			int numeroCopiasInt = (int) Math.round(numeroCopias);

			Date date = new Date();

			ps.setInt(1, numeroCopiasInt);
			ps.setDouble(2, total);
			ps.setLong(3, usuario.getId());
			ps.setLong(4, juego.getId());
			ps.setDate(5, new java.sql.Date(date.getTime()));

			int numeroRegistrosInsertados = ps.executeUpdate();

			if (numeroRegistrosInsertados == 0) {
				throw new AccesoDatosException("No se ha conseguido insertar el registro");
			} else if (numeroRegistrosInsertados > 1) {
				throw new AccesoDatosException("SE HA INSERTADO MÁS DE UN REGISTRO");
			}

		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al insertar el juego", e);
		}

	}

	@Override
	public void modificarReserva(int idCarrito) {
		// TODO Auto-generated method stub

	}

	@Override
	public void borrarReserva(Long idReserva) {
		try (Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_DELETE_RESERVA);) {

			ps.setLong(1, idReserva);

			int numeroRegistrosBorrados = ps.executeUpdate();

			if (numeroRegistrosBorrados == 0) {
				throw new AccesoDatosException("Se ha intentado borrar una reserva inexistente");
			} else if (numeroRegistrosBorrados > 1) {
				throw new AccesoDatosException("SE HA BORRADO MÁS DE UNA RESERVA");
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Ha habido un problema al borrar la reserva", e);
			throw new AccesoDatosException("Ha habido un problema al obtner la reserva cuyo id es " + idReserva, e);
		}

	}

}
