package com.juegodemesa.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import com.juegodemesa.modelos.Juego;
import com.juegodemesa.modelos.Mecanica;
import com.juegodemesa.modelos.Reserva;
import com.juegodemesa.modelos.Usuario;

public class ReservasDaoMySql implements DaoReserva {
	
	// SINGLETON
		private ReservasDaoMySql() {
		}

		private static final ReservasDaoMySql INSTANCIA = new ReservasDaoMySql();
		

		public static ReservasDaoMySql getInstancia() {
			return INSTANCIA;
		}
		// FIN SINGLETON

		// characterEncoding=UTF-8 cambia la codificación de los PreparedStatement de Windows-1252 a UTF-8
		private static final String URL = "jdbc:mysql://localhost:3306/juegos_bdd?characterEncoding=UTF-8";
		private static final String USER = "debian-sys-maint";
		private static final String PASS = "o8lAkaNtX91xMUcV";
		
		// Ejemplos de Select . HAY QUE MODIFICAR
		
		private static final String SQL_SELECT = "SELECT r.id,u.nombre,u.apellidos,u.email,j.nombre,j.precio,r.cantidad,r.total FROM reservas r JOIN usuarios u ON u.id = r.idUsuario JOIN juegos j ON j.id = r.idJuego";
//		private static final String SQL_SELECT_ID = "SELECT * FROM usuarios j JOIN roles r ON u.id_rol = r.id WHERE u.id = ?";
//		private static final String SQL_SELECT_USER = "SELECT * FROM usuarios u WHERE u.email = ?";
//		private static final String SQL_SELECT_PASSWORD = "SELECT u.password FROM usuarios u WHERE u.email = ?";
		private static final String SQL_INSERT = "INSERT INTO reservas (cantidad,total,idUsuario,idJuego,fecha) VALUES (?, ?, ?, ?, ?)";
//		private static final String SQL_UPDATE = "UPDATE usuarios SET nombre = ?, apellidos = ?, email= ? , id_rol = ? , edad = ? , fechaRegistro = ? WHERE id = ?";
//		private static final String SQL_DELETE = "DELETE FROM usuarios WHERE id = ?";
		
	

		// OBTENER TODAS LAS RESERVAS
		
		@Override
		public Iterable<Reserva> obtenerTodos() {
			try (Connection con = DriverManager.getConnection(URL, USER, PASS);
					Statement s = con.createStatement();
					ResultSet rs = s.executeQuery(SQL_SELECT);) {
				ArrayList<Reserva> reservas = new ArrayList<>();
				System.out.println("Estas son las reservas" + reservas);
				
				Usuario usuario;
				Reserva reserva;
				Juego juego;

				// Para convertir las filas de una tabla de la base de datos en objetos de una
				// colección
				while (rs.next()) {
					usuario = new Usuario(rs.getString("u.nombre"),rs.getString("u.apellidos"),rs.getString("u.email"));
					juego = new Juego(rs.getString("j.nombre"), rs.getInt("j.precio"));
					reserva = new Reserva(rs.getLong("id"), usuario, juego,rs.getInt("cantidad"),rs.getDouble("total"));
					reservas.add(reserva);
				}
				
				

				return reservas;
			} catch (SQLException e) {
				throw new AccesoDatosException("Ha habido un problema al obtener todos los registros de juegso", e);
			}

		}

		@Override
		public Reserva obtenerporId(int idCarrito) {
			return null;
		}
		@Override
		public void insertarReserva(Usuario usuario, Juego juego, String copias) {
			
			try (Connection con = DriverManager.getConnection(URL, USER, PASS);
					PreparedStatement ps = con.prepareStatement(SQL_INSERT);) {
		
				
				// Calculo del total de copias
				Double numeroCopias = Double.parseDouble(copias);
				double total = juego.getPrecio() * numeroCopias;
				
				// Conversion para introduciar copias como int en la base de datos
				int numeroCopiasInt = (int)Math.round(numeroCopias);
				
				Date date = new Date();
			
				
				ps.setInt(1,numeroCopiasInt);
				ps.setDouble(2, total);
				ps.setLong(3,usuario.getId());
				ps.setLong(4,juego.getId());
				ps.setDate(5,new java.sql.Date(date.getTime()));
				
				
				
				int numeroRegistrosInsertados = ps.executeUpdate();
				
				if(numeroRegistrosInsertados == 0) {
					throw new AccesoDatosException("No se ha conseguido insertar el registro");
				} else if(numeroRegistrosInsertados > 1) {
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
		public void borrarReserva(int idCarrito) {
			// TODO Auto-generated method stub
			
		}
	
	

	

}