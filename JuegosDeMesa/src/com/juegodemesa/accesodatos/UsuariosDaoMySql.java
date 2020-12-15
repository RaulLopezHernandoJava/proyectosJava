package com.juegodemesa.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.juegodemesa.controladores.Configuracion;
import com.juegodemesa.modelos.Mecanica;
import com.juegodemesa.modelos.Rol;
import com.juegodemesa.modelos.Usuario;

public class UsuariosDaoMySql implements DaoUsuario {

	// SINGLETON
	private UsuariosDaoMySql() {
	}

	private static final UsuariosDaoMySql INSTANCIA = new UsuariosDaoMySql();
	

	public static UsuariosDaoMySql getInstancia() {
		return INSTANCIA;
	}
	// FIN SINGLETON

	// characterEncoding=UTF-8 cambia la codificación de los PreparedStatement de Windows-1252 a UTF-8
	private static final String URL = "jdbc:mysql://localhost:3306/juegos_bdd?characterEncoding=UTF-8";
	private static final String USER = "debian-sys-maint";
	private static final String PASS = "o8lAkaNtX91xMUcV";

	private static final String SQL_SELECT = "SELECT * FROM usuarios u JOIN roles r ON u.id_rol = r.id";
	private static final String SQL_SELECT_ID = "SELECT * FROM usuarios j JOIN roles r ON u.id_rol = r.id WHERE u.id = ?";
	private static final String SQL_SELECT_USER = "SELECT * FROM usuarios u WHERE u.email = ?";
	private static final String SQL_SELECT_PASSWORD = "SELECT u.password FROM usuarios u WHERE u.email = ?";
	private static final String SQL_INSERT = "INSERT INTO usuarios (nombre, apellidos, email, id_rol , password,  edad , fecha_registro ) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE usuarios SET nombre = ?, apellidos = ?, email= ? , id_rol = ? , edad = ? , fechaRegistro = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM usuarios WHERE id = ?";

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
	public Iterable<Usuario> obtenerTodos() {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(SQL_SELECT);) {
			ArrayList<Usuario> usuarios= new ArrayList<>();

			Usuario usuario;
			Rol rol;

			// Para convertir las filas de una tabla de la base de datos en objetos de una
			// colección
			while (rs.next()) {
				rol = new Rol(rs.getLong("r.id"), rs.getString("r.nombre"), rs.getString("r.descripcion"));
				usuario = new Usuario (rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos"),rs.getString("email"),
						rol,rs.getInt("edad"),rs.getDate("fecha_registro").toLocalDate());

				usuarios.add(usuario);
			}

			return usuarios;
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtener todos los registros de los usuarios", e);
		}

	}
	
	// OBTENER UN JUEGO

	@Override
	public Usuario obtenerPorId(Long id) {

		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);) {

			ps.setLong(1, id);

			try (ResultSet rs = ps.executeQuery()) {

				Usuario usuario = null;
				Rol rol = null;

				if (rs.next()) {
					rol = new Rol(rs.getLong("r.id"), rs.getString("r.nombre"), rs.getString("r.descripcion"));
					usuario = new Usuario (rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos"),rs.getString("email"),
							rol,rs.getInt("edad"),rs.getDate("fecha_publicacion").toLocalDate());
				}

				return usuario ;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtener el usuario cuyo id es " + id, e);
		}
	}
	
	//INSERTAR JUEGO

	@Override
	public void insertar(Usuario usuario) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_INSERT);) {

			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getApellidos());
			ps.setString(3, usuario.getEmail());
			ps.setLong(4,usuario.getRol().getId());
			ps.setString(5,usuario.getPassword());
			ps.setInt(6, usuario.getEdad());
			ps.setObject(7, usuario.getFechaRegistro());
	
			
			
			int numeroRegistrosInsertados = ps.executeUpdate();
			
			if(numeroRegistrosInsertados == 0) {
				throw new AccesoDatosException("No se ha conseguido insertar el registro");
			} else if(numeroRegistrosInsertados > 1) {
				throw new AccesoDatosException("SE HA INSERTADO MÁS DE UN REGISTRO");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccesoDatosException("Ha habido un problema al insertar a el usuario");
			
		}
	}
	
	// MODIFICAR JUEGO

	@Override
	public void modificar(Usuario usuario) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_UPDATE);) {

			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getApellidos());
			ps.setString(3, usuario.getEmail());
			ps.setLong(4,usuario.getRol().getId());
			ps.setInt(5, usuario.getEdad());
			ps.setObject(5, usuario.getFechaRegistro());
			ps.setLong(6, usuario.getId());
			
			int numeroRegistrosModificados = ps.executeUpdate();
			
			if(numeroRegistrosModificados == 0) {
				throw new AccesoDatosException("No se ha encontrado el registro a modificar");
			} else if(numeroRegistrosModificados > 1) {
				throw new AccesoDatosException("SE HA MODIFICADO MÁS DE UN REGISTRO");
			}

		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al modificar a el usuario", e);
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
			throw new AccesoDatosException("Ha habido un problema al obtener a el usuario cuyo id es " + id, e);
		}
	}


	
	@Override
	public Usuario obtenerPorEmail(String email) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_USER);) {

			ps.setString(1, email);

			try (ResultSet rs = ps.executeQuery()) {

				Usuario usuario = null;
				Rol rol = null;

				if (rs.next()) {
					usuario = new Usuario (rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos"),rs.getString("email"),rs.getString("password"),
							rol,rs.getInt("edad"),rs.getDate("fecha_registro").toLocalDate());
				}

				return usuario ;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtener el usuario cuyo email es " + email, e);
		}
	
	}

	

}
