package com.juegodemesa.accesodatos.DaoMySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.juegodemesa.accesodatos.AccesoDatosException;
import com.juegodemesa.accesodatos.Daos.Dao;
import com.juegodemesa.modelos.Juego;
import com.juegodemesa.modelos.Mecanica;

public class JuegosDaoMySql implements Dao<Juego> {

	private static final Logger LOGGER = Logger.getLogger(JuegosDaoMySql.class.getName());

	private DataSource dataSource;

	// SINGLETON
	private JuegosDaoMySql() {
		try {
			InitialContext initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			dataSource = (DataSource) envCtx.lookup("jdbc/juegos");
		} catch (NamingException e) {
			throw new AccesoDatosException("No se ha encontrado el pool de conexiones", e);
		}
	}

	private static final JuegosDaoMySql INSTANCIA = new JuegosDaoMySql();

	public static JuegosDaoMySql getInstancia() {
		return INSTANCIA;
	}
	// FIN SINGLETON

	// characterEncoding=UTF-8 cambia la codificación de los PreparedStatement de
	// Windows-1252 a UTF-8

	private static final String SQL_SELECT = "SELECT * FROM juegos j JOIN mecanicas m ON j.id_mecanica = m.id WHERE j.active = TRUE";
	private static final String SQL_SELECT_ID = "SELECT * FROM juegos j JOIN mecanicas m ON j.id_mecanica = m.id WHERE j.id = ? AND j.active=TRUE ";
	private static final String SQL_SELECT_AUTOR = "SELECT * FROM juegos j JOIN mecanicas m ON j.id_mecanica = m.id WHERE j.autor = ? AND j.active=TRUE";
	private static final String SQL_SELECT_EDITORIAL = "SELECT * FROM juegos j JOIN mecanicas m ON j.id_mecanica = m.id WHERE j.editorial = ? AND j.active=TRUE";
	private static final String SQL_SELECT_MECHANIC = "SELECT * FROM juegos j JOIN mecanicas m ON j.id_mecanica = m.id WHERE m.nombre = ? AND j.active = TRUE";
	private static final String SQL_SELECT_MIN_PRICE = "SELECT * FROM juegos j JOIN mecanicas m ON j.id_mecanica WHERE j.precio >= ? AND j.active = TRUE";
	private static final String SQL_SELECT_MAX_PRICE = "SELECT * FROM juegos j JOIN mecanicas m ON j.id_mecanica WHERE j.precio <= ? AND j.active = TRUE";
	private static final String SQL_SELECT_PRICES = "SELECT * FROM juegos j JOIN mecanicas m ON j.id_mecanica WHERE j.precio BETWEEN ? AND ? AND j.active = TRUE";
	private static final String SQL_INSERT = "INSERT INTO juegos (nombre, autor, editorial, id_mecanica, imagen, fecha_publicacion) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE juegos SET nombre = ?, autor = ?, editorial = ? , id_mecanica = ?, imagen = ? , fecha_publicacion = ? WHERE id = ?";
	private static final String SQL_DELETE = "UPDATE juegos r SET active = FALSE where j.id = ?";

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
		try (Connection con = dataSource.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(SQL_SELECT);) {
			ArrayList<Juego> juegos = new ArrayList<>();

			Juego juego;
			Mecanica mecanica;

			// Para convertir las filas de una tabla de la base de datos en objetos de una
			// colección
			while (rs.next()) {
				mecanica = new Mecanica(rs.getLong("m.id"), rs.getString("m.nombre"), rs.getString("m.descripcion"));
				juego = new Juego(rs.getLong("id"), rs.getString("nombre"), rs.getString("autor"),
						rs.getString("editorial"), mecanica, rs.getDouble("precio"),
						rs.getDate("fecha_publicacion").toLocalDate(), rs.getBoolean("active"));
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

		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);) {

			ps.setLong(1, id);
			System.out.println("El id que se encuentra en el metodo obtener por id es " + id);

			try (ResultSet rs = ps.executeQuery()) {

				Juego juego = null;
				Mecanica mecanica = null;

				if (rs.next()) {

					mecanica = new Mecanica(rs.getLong("m.id"), rs.getString("m.nombre"),
							rs.getString("m.descripcion"));
					juego = new Juego(rs.getLong("j.id"), rs.getString("j.nombre"), rs.getString("j.autor"),
							rs.getString("j.editorial"), mecanica, rs.getDouble("j.precio"),
							rs.getDate("j.fecha_publicacion").toLocalDate(), rs.getBoolean("j.active"));

				}
				System.out.println("Este es el metodo de obtener por Id" + juego);

				return juego;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtener el juego cuyo id es " + id, e);
		}
	}

	// INSERTAR JUEGO

	@Override
	public void insertar(Juego juego) {
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_INSERT);) {

			ps.setString(1, juego.getNombre());
			ps.setString(2, juego.getAutor());
			ps.setString(3, juego.getEditorial());
			ps.setLong(4, juego.getMecanica().getId());
			ps.setObject(6, juego.getFechaPublicacion());

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

	// MODIFICAR JUEGO

	@Override
	public void modificar(Juego juego) {
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_UPDATE);) {

			ps.setString(1, juego.getNombre());
			ps.setString(2, juego.getAutor());
			ps.setString(3, juego.getEditorial());
			ps.setLong(4, juego.getMecanica().getId());
			ps.setObject(6, juego.getFechaPublicacion());
			ps.setLong(7, juego.getId());

			int numeroRegistrosModificados = ps.executeUpdate();

			if (numeroRegistrosModificados == 0) {
				throw new AccesoDatosException("No se ha encontrado el registro a modificar");
			} else if (numeroRegistrosModificados > 1) {
				throw new AccesoDatosException("SE HA MODIFICADO MÁS DE UN REGISTRO");
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Ha habido un problema al modificar el juego", e);
			throw new AccesoDatosException("Ha habido un problema al modificar el juego", e);
		}
	}

	// BORRAR JUEGO
	@Override
	public void borrar(Long id) {
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_DELETE);) {

			ps.setLong(1, id);

			int numeroRegistrosBorrados = ps.executeUpdate();

			if (numeroRegistrosBorrados == 0) {
				throw new AccesoDatosException("Se ha intentado borrar un id inexistente");
			} else if (numeroRegistrosBorrados > 1) {
				throw new AccesoDatosException("SE HA BORRADO MÁS DE UN REGISTRO");
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Ha habido un problema al borrar el juego", e);
			throw new AccesoDatosException("Ha habido un problema al obtener el juego cuyo id es " + id, e);
		}
	}

	// FILTRAR POR AUTOR

	public Iterable<Juego> filtrarJuegosAutor(String autor) {
		try (Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_AUTOR);) {

			ps.setString(1, autor);

			ArrayList<Juego> juegos = new ArrayList<>();

			try (ResultSet rs = ps.executeQuery()) {

				Juego juego = null;
				Mecanica mecanica = null;
				if (rs.next()) {

					mecanica = new Mecanica(rs.getLong("m.id"), rs.getString("m.nombre"),
							rs.getString("m.descripcion"));
					juego = new Juego(rs.getLong("j.id"), rs.getString("j.nombre"), rs.getString("j.autor"),
							rs.getString("j.editorial"), mecanica, rs.getDouble("j.precio"),
							rs.getDate("j.fecha_publicacion").toLocalDate(), rs.getBoolean("j.active"));
					juegos.add(juego);

				}

				return juegos;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtener los juegos cuyo autor es  " + autor, e);
		}

	}

	// FILTRAR POR EDITORIAL

	public Iterable<Juego> filtrarJuegosEditorial(String editorial) {
		try (Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_EDITORIAL);) {

			System.out.println("LLego aqui " + editorial);

			ps.setString(1, editorial);

			ArrayList<Juego> juegos = new ArrayList<>();

			try (ResultSet rs = ps.executeQuery()) {

				Juego juego = null;
				Mecanica mecanica = null;

				while (rs.next()) {

					mecanica = new Mecanica(rs.getLong("m.id"), rs.getString("m.nombre"),
							rs.getString("m.descripcion"));
					juego = new Juego(rs.getLong("j.id"), rs.getString("j.nombre"), rs.getString("j.autor"),
							rs.getString("j.editorial"), mecanica, rs.getDouble("precio"),
							rs.getDate("j.fecha_publicacion").toLocalDate(), rs.getBoolean("active"));
					juegos.add(juego);
				}

				System.out.println("Estos son los juegos filtrados por editorial" + juegos);

				return juegos;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException(
					"Ha habido un problema al obtener los juegos cuyo editorial es  " + editorial, e);
		}

	}

	// FILTRAR POR MECANICA

	public Iterable<Juego> filtrarJuegosMecanica(String mecanicaString) {
		try (Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_MECHANIC);) {

			System.out.println("Esta es la mecanica que llega al metodo" + mecanicaString);

			ps.setString(1, mecanicaString);

			ArrayList<Juego> juegos = new ArrayList<>();

			try (ResultSet rs = ps.executeQuery()) {

				Juego juego = null;
				Mecanica mecanica = null;

				while (rs.next()) {

					mecanica = new Mecanica(rs.getLong("m.id"), rs.getString("m.nombre"),
							rs.getString("m.descripcion"));
					juego = new Juego(rs.getLong("j.id"), rs.getString("j.nombre"), rs.getString("j.autor"),
							rs.getString("j.editorial"), mecanica, rs.getDouble("precio"),
							rs.getDate("j.fecha_publicacion").toLocalDate(), rs.getBoolean("active"));
					juegos.add(juego);
				}

				System.out.println("Estos son los juegos filtrados por mecanica" + juegos);

				return juegos;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException(
					"Ha habido un problema al obtener los juegos cuya mecanica es  " + mecanicaString, e);
		}

	}

	// FILTRAR POR PRECIO MINIMO

	public Iterable<Juego> filtrarJuegosPrecioMinimo(Integer min) {
		try (Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_MIN_PRICE);) {

			ps.setInt(1, min);

			ArrayList<Juego> juegos = new ArrayList<>();

			try (ResultSet rs = ps.executeQuery()) {

				Juego juego = null;
				Mecanica mecanica = null;

				while (rs.next()) {

					mecanica = new Mecanica(rs.getLong("m.id"), rs.getString("m.nombre"),
							rs.getString("m.descripcion"));
					juego = new Juego(rs.getLong("j.id"), rs.getString("j.nombre"), rs.getString("j.autor"),
							rs.getString("j.editorial"), mecanica, rs.getDouble("precio"),
							rs.getDate("j.fecha_publicacion").toLocalDate(), rs.getBoolean("active"));
					juegos.add(juego);
				}

				System.out.println("Estos son los juegos cuyo precio minimo es" + min  + " : " +  juegos);

				return juegos;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtene los precios de los juegos", e);
		}

	}

	public Iterable<Juego> filtrarJuegosPrecioMaximo(Integer max) {
		try (Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_MAX_PRICE);) {

			ps.setInt(1, max);

			ArrayList<Juego> juegos = new ArrayList<>();

			try (ResultSet rs = ps.executeQuery()) {

				Juego juego = null;
				Mecanica mecanica = null;

				while (rs.next()) {

					mecanica = new Mecanica(rs.getLong("m.id"), rs.getString("m.nombre"),
							rs.getString("m.descripcion"));
					juego = new Juego(rs.getLong("j.id"), rs.getString("j.nombre"), rs.getString("j.autor"),
							rs.getString("j.editorial"), mecanica, rs.getDouble("precio"),
							rs.getDate("j.fecha_publicacion").toLocalDate(), rs.getBoolean("active"));
					juegos.add(juego);
				}

				System.out.println("Estos son los juegos cuyo precio maximo es" + max  + " : " +  juegos);

				return juegos;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtene los precios de los juegos", e);
		}

	}

	public Iterable<Juego> filtrarJuegosEntreDosPrecios(Integer min, Integer max) {
		try (Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_PRICES);) {

			ps.setInt(1, min);
			ps.setInt(2, max);

			ArrayList<Juego> juegos = new ArrayList<>();

			try (ResultSet rs = ps.executeQuery()) {

				Juego juego = null;
				Mecanica mecanica = null;

				while (rs.next()) {

					mecanica = new Mecanica(rs.getLong("m.id"), rs.getString("m.nombre"),
							rs.getString("m.descripcion"));
					juego = new Juego(rs.getLong("j.id"), rs.getString("j.nombre"), rs.getString("j.autor"),
							rs.getString("j.editorial"), mecanica, rs.getDouble("precio"),
							rs.getDate("j.fecha_publicacion").toLocalDate(), rs.getBoolean("active"));
					juegos.add(juego);
				}

				System.out.println("Estos son los juegos filtrados por precio" + juegos);

				return juegos;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtene los precios de los juegos", e);
		}

	}

}
