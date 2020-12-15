package com.juegodemesa.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.juegodemesa.modelos.Juego;
import com.juegodemesa.modelos.Mecanica;

public class JuegosDaoMySql implements Dao<Juego> {

	// SINGLETON
	private JuegosDaoMySql() {
	}

	private static final JuegosDaoMySql INSTANCIA = new JuegosDaoMySql();

	public static JuegosDaoMySql getInstancia() {
		return INSTANCIA;
	}
	// FIN SINGLETON

	// characterEncoding=UTF-8 cambia la codificación de los PreparedStatement de Windows-1252 a UTF-8
	private static final String URL = "jdbc:mysql://localhost:3306/juegos_bdd?characterEncoding=UTF-8";
	private static final String USER = "debian-sys-maint";
	private static final String PASS = "o8lAkaNtX91xMUcV";

	private static final String SQL_SELECT = "SELECT * FROM juegos j JOIN mecanicas m ON j.id_mecanica = m.id";
	private static final String SQL_SELECT_ID = "SELECT * FROM juegos j JOIN mecanicas m ON j.id_mecanica = m.id WHERE j.id = ?";
	private static final String SQL_SELECT_AUTOR = "SELECT * FROM juegos j JOIN mecanicas m ON j.id_mecanica = m.id WHERE j.autor = ?";
	private static final String SQL_SELECT_EDITORIAL = "SELECT * FROM juegos j JOIN mecanicas m ON j.id_mecanica = m.id WHERE j.editorial = ?";
	private static final String SQL_INSERT = "INSERT INTO juegos (nombre, autor, editorial, id_mecanica, imagen, fecha_publicacion) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE juegos SET nombre = ?, autor = ?, editorial = ? , id_mecanica = ?, imagen = ? , fecha_publicacion = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM juegos WHERE id = ?";

	// EJEMPLO DE INYECCIÓN DE SQL
	// fecha_estreno = "2000-1-1'); DROP TABLE peliculas;--
	// "INSERT INTO peliculas (titulo, genero, fecha_estreno) VALUES ('" + titulo +
	// "', '" + genero + "', '" + fecha_estreno + "');"
	// "INSERT INTO peliculas (titulo, genero, fecha_estreno) VALUES ('asdf',
	// 'asdf', '2000-1-1'); DROP TABLE peliculas; --');"

	static {
		try {
			// Registramos el driver de MySQL de forma EXPLÍCITA ya que en las aplicaciones
			// web lo necesitan todavía
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha podido cargar el driver de MySQL", e);
		}
	}

	// OBTENER TODOS LOS JUEGOS
	
	@Override
	public Iterable<Juego> obtenerTodos() {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(SQL_SELECT);) {
			ArrayList<Juego> juegos = new ArrayList<>();

			Juego juego;
			Mecanica mecanica;

			// Para convertir las filas de una tabla de la base de datos en objetos de una
			// colección
			while (rs.next()) {
				mecanica = new Mecanica(rs.getLong("m.id"), rs.getString("m.nombre"), rs.getString("m.descripcion"));
				juego = new Juego(rs.getLong("id"), rs.getString("nombre"), rs.getString("autor"),rs.getString("editorial"),
						mecanica,rs.getString("imagen"),rs.getDate("fecha_publicacion").toLocalDate());
				juegos.add(juego);
			}
			
			

			return juegos;
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtener todos los registros de juegso", e);
		}

	}
	
	// OBTENER UN JUEGO

	@Override
	public Juego obtenerPorId(Long id) {

		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);) {

			ps.setLong(1, id);
			System.out.println("El id que se encuentra en el metodo obtener por id es " + id);

			try (ResultSet rs = ps.executeQuery()) {

				Juego juego = null;
				Mecanica mecanica = null;

				if (rs.next()) {
					
						mecanica = new Mecanica(rs.getLong("m.id"), rs.getString("m.nombre"), rs.getString("m.descripcion"));
						juego = new Juego(rs.getLong("j.id"), rs.getString("j.nombre"), rs.getString("j.autor"),rs.getString("j.editorial"),
								mecanica,rs.getString("j.imagen"),rs.getDate("j.fecha_publicacion").toLocalDate());
					
				}
				System.out.println("Este es el metodo de obtener por Id" + juego);

				return juego ;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtener el juego cuyo id es " + id, e);
		}
	}
	
	//INSERTAR JUEGO

	@Override
	public void insertar(Juego juego) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_INSERT);) {

			ps.setString(1, juego.getNombre());
			ps.setString(2, juego.getAutor());
			ps.setString(3, juego.getEditorial());
			ps.setLong(4,juego.getMecanica().getId());
			ps.setString(5, juego.getImagen());
			ps.setObject(6, juego.getFechaPublicacion());
	
			
			
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
	
	// MODIFICAR JUEGO

	@Override
	public void modificar(Juego juego) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_UPDATE);) {

			ps.setString(1, juego.getNombre());
			ps.setString(2, juego.getAutor());
			ps.setString(3, juego.getEditorial());
			ps.setLong(4,juego.getMecanica().getId());
			ps.setString(5, juego.getImagen());
			ps.setObject(6, juego.getFechaPublicacion());
			ps.setLong(7, juego.getId());
			
			int numeroRegistrosModificados = ps.executeUpdate();
			
			if(numeroRegistrosModificados == 0) {
				throw new AccesoDatosException("No se ha encontrado el registro a modificar");
			} else if(numeroRegistrosModificados > 1) {
				throw new AccesoDatosException("SE HA MODIFICADO MÁS DE UN REGISTRO");
			}

		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al modificar el juego", e);
		}
	}

	// BORRAR JUEGO
	@Override
	public void borrar(Long id) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_DELETE);) {

			ps.setLong(1, id);

			int numeroRegistrosBorrados = ps.executeUpdate();
			
			if(numeroRegistrosBorrados == 0) {
				throw new AccesoDatosException("Se ha intentado borrar un id inexistente");
			} else if(numeroRegistrosBorrados > 1) {
				throw new AccesoDatosException("SE HA BORRADO MÁS DE UN REGISTRO");
			}

		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtener el juego cuyo id es " + id, e);
		}
	}
	
	// FILTRAR POR AUTOR
	
	
	public Iterable<Juego> filtrarJuegosAutor(String autor) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_AUTOR);) {
			
			ps.setString(1, autor);
			
			ArrayList<Juego> juegos = new ArrayList<>();
			
			try (ResultSet rs = ps.executeQuery()) {

				Juego juego = null;
				Mecanica mecanica = null;

				if (rs.next()) {
					
						mecanica = new Mecanica(rs.getLong("m.id"), rs.getString("m.nombre"), rs.getString("m.descripcion"));
						juego = new Juego(rs.getLong("j.id"), rs.getString("j.nombre"), rs.getString("j.autor"),rs.getString("j.editorial"),
								mecanica,rs.getString("j.imagen"),rs.getDate("j.fecha_publicacion").toLocalDate());
						juegos.add(juego);
					
				}
				
				return juegos ;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtener los juegos cuyo autor es  " + autor, e);
		}

	}
	
	
	// FILTRAR POR EDITORIAL
	
	public Iterable<Juego> filtrarJuegosEditorial(String editorial) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_EDITORIAL);) {
			
			ps.setString(1, editorial);
			
			ArrayList<Juego> juegos = new ArrayList<>();
			
			try (ResultSet rs = ps.executeQuery()) {

				Juego juego = null;
				Mecanica mecanica = null;

				if (rs.next()) {
					
						mecanica = new Mecanica(rs.getLong("m.id"), rs.getString("m.nombre"), rs.getString("m.descripcion"));
						juego = new Juego(rs.getLong("j.id"), rs.getString("j.nombre"), rs.getString("j.autor"),rs.getString("j.editorial"),
								mecanica,rs.getString("j.imagen"),rs.getDate("j.fecha_publicacion").toLocalDate());
						juegos.add(juego);
					
				}
				
				System.out.println("Estos son los juegos filtrados por editorial" + juegos);
		

				return juegos ;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtener los juegos cuyo editorial es  " + editorial, e);
		}

	}
	
	
	
	
	// FILTRAR POR MECANICA

}
