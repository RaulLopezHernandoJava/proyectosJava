package com.juegodemesa.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.juegodemesa.modelos.Direccion;
import com.juegodemesa.modelos.Rol;
import com.juegodemesa.modelos.Usuario;

public class DireccionesDaoMySql implements DaoDireccion {

	private DireccionesDaoMySql() {
	}

	private static final DireccionesDaoMySql INSTANCIA = new DireccionesDaoMySql();

	public static DireccionesDaoMySql getInstancia() {
		return INSTANCIA;
	}

	private static final String URL = "jdbc:mysql://localhost:3306/juegos_bdd?characterEncoding=UTF-8";
	private static final String USER = "debian-sys-maint";
	private static final String PASS = "o8lAkaNtX91xMUcV";

	private static final String SQL_SELECT = "SELECT * FROM direcciones u WHERE active =TRUE";
//	private static final String SQL_SELECT_ID = "SELECT * FROM usuarios j JOIN roles r ON u.id_rol = r.id WHERE u.id = ?";
//	private static final String SQL_SELECT_USER = "SELECT * FROM usuarios u WHERE u.email = ?";
//	private static final String SQL_SELECT_PASSWORD = "SELECT u.password FROM usuarios u WHERE u.email = ?";
//	private static final String SQL_INSERT = "INSERT INTO usuarios (nombre, apellidos, email, id_rol , password,  edad , fecha_registro ) VALUES (?, ?, ?, ?, ?, ?, ?)";
//	private static final String SQL_UPDATE = "UPDATE usuarios SET nombre = ?, apellidos = ?, email= ? , id_rol = ? , edad = ? , fechaRegistro = ? WHERE id = ?";
//	private static final String SQL_DELETE = "DELETE FROM usuarios WHERE id = ?";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha podido cargar el driver de MySQL", e);
		}
	}
	
	// OBTENER TODOS LOS JUEGOS
	
		@Override
		public Iterable<Direccion> obtenerTodos() {
			try (Connection con = DriverManager.getConnection(URL, USER, PASS);
					Statement s = con.createStatement();
					ResultSet rs = s.executeQuery(SQL_SELECT);) {
				ArrayList<Direccion> direcciones= new ArrayList<>();

				Direccion direccion;

				while (rs.next()) {
					
					direccion = new Direccion (rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos"),rs.getString("pais"),
							rs.getString("direccion"),rs.getInt("codigo_postal"),rs.getString("ciudad"),rs.getString("provincia"),rs.getString("telefono"),
							rs.getString("email"),rs.getLong("id_usuario"),rs.getBoolean("active"));

					direcciones.add(direccion);
				}

				return direcciones;
			} catch (SQLException e) {
				throw new AccesoDatosException("Ha habido un problema al obtener todas las direcciones de los usuarios", e);
			}

		}

	@Override
	public Direccion obtenerPorEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
