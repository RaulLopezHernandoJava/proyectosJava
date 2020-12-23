package com.juegodemesa.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.juegodemesa.modelos.ComunidadAutonoma;
import com.juegodemesa.modelos.Direccion;
import com.juegodemesa.modelos.Provincia;

public class DireccionesDaoMySql implements DaoDireccion, Dao<Direccion> {

	private DataSource dataSource;

	private DireccionesDaoMySql() {
		try {
			InitialContext initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			dataSource = (DataSource) envCtx.lookup("jdbc/juegos");
		} catch (NamingException e) {
			throw new AccesoDatosException("No se ha encontrado el pool de conexiones", e);
		}
	}

	private static final DireccionesDaoMySql INSTANCIA = new DireccionesDaoMySql();

	public static DireccionesDaoMySql getInstancia() {
		return INSTANCIA;
	}

	private static final String SQL_SELECT = "SELECT * FROM direcciones d JOIN provincias p ON d.id_provincia = p.id JOIN comunidadesAutonomas c ON d.id_comunidad = c.id";
//	private static final String SQL_SELECT_ID = "SELECT * FROM usuarios j JOIN roles r ON u.id_rol = r.id WHERE u.id = ?";
	private static final String SQL_SELECT_PROVINCIA = "SELECT * FROM provincias p WHERE p.nombre = ?";
	private static final String SQL_SELECT_COMUNIDAD = "SELECT * FROM comunidadesAutonomas c WHERE c.nombre = ?";
	private static final String SQL_SELECT_DIRECCION = "SELECT * FROM direcciones d JOIN provincias p ON d.id_provincia = p.id JOIN comunidadesAutonomas c ON d.id_comunidad = c.id WHERE d.email = ?";
//	private static final String SQL_SELECT_PASSWORD = "SELECT u.password FROM usuarios u WHERE u.email = ?";
	private static final String SQL_INSERT = "INSERT INTO direcciones (nombre,apellidos,direccion,codigo_postal,ciudad,telefono,email,id_usuario,id_provincia,id_comunidad,active ) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE direcciones SET nombre = ?, apellidos = ?, direccion= ? , codigo_postal = ? , ciudad = ? ,"
			+ " telefono = ?, email=?, id_usuario =?, id_comunidad = ?,id_provincia =? , active =? WHERE id = ?";
//	private static final String SQL_DELETE = "DELETE FROM usuarios WHERE id = ?";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha podido cargar el driver de MySQL", e);
		}
	}

	// OBTENER TODAS LAS DIRECCIONES

	@Override
	public Iterable<Direccion> obtenerTodos() {
		try (Connection con = dataSource.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(SQL_SELECT);) {
			ArrayList<Direccion> direcciones = new ArrayList<>();

			Direccion direccion;

			while (rs.next()) {
				direccion = new Direccion(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("direccion"), rs.getInt("codigo_postal"), rs.getString("ciudad"),
						rs.getLong("id_comunidad"), rs.getLong("id_provincia"), rs.getString("telefono"),
						rs.getString("email"), rs.getLong("id_usuario"), rs.getBoolean("active"));

				direcciones.add(direccion);
			}

			return direcciones;
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtener todas las direcciones de los usuarios", e);
		}

	}

	// INSERTAR DIRECCION

	public void insertar(Direccion direccion) {
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_INSERT);) {

			ps.setString(1, direccion.getNombre());
			ps.setString(2, direccion.getApellidos());
			ps.setString(3, direccion.getDireccion());
			ps.setInt(4, direccion.getCodigoPostal());
			ps.setString(5, direccion.getCiudad());
			ps.setString(6, direccion.getTelefono());
			ps.setString(7, direccion.getEmail());
			ps.setLong(8, direccion.getIdUsuario());
			ps.setLong(9, direccion.getProvincia().getId());
			ps.setLong(10, direccion.getComunidadAutonoma().getId());
			ps.setBoolean(11, direccion.getActive());

			int numeroRegistrosInsertados = ps.executeUpdate();

			if (numeroRegistrosInsertados == 0) {
				throw new AccesoDatosException("No se ha conseguido insertar el registro");
			} else if (numeroRegistrosInsertados > 1) {
				throw new AccesoDatosException("SE HA INSERTADO MÁS DE UN REGISTRO");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccesoDatosException("Ha habido un problema al insertar a el usuario");

		}
	}

	// MODIFICAR DIRECCION

	@Override
	public void modificar(Direccion direccion) {
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_UPDATE);) {

			ps.setString(1, direccion.getNombre());
			ps.setString(2, direccion.getApellidos());
			ps.setString(3, direccion.getDireccion());
			ps.setInt(4, direccion.getCodigoPostal());
			ps.setString(5, direccion.getCiudad());
			ps.setString(6, direccion.getTelefono());
			ps.setString(7, direccion.getEmail());
			ps.setLong(8, direccion.getIdUsuario());
			ps.setLong(9, direccion.getComunidadAutonoma());
			ps.setLong(10, direccion.getProvincia());
			ps.setBoolean(11, direccion.getActive());
			ps.setLong(12, direccion.getId());

			int numeroRegistrosModificados = ps.executeUpdate();

			if (numeroRegistrosModificados == 0) {
				throw new AccesoDatosException("No se ha encontrado el registro a modificar");
			} else if (numeroRegistrosModificados > 1) {
				throw new AccesoDatosException("SE HA MODIFICADO MÁS DE UN REGISTRO");
			}

		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al modificar al modificar la direccion", e);
		}
	}

	@Override
	public Direccion obtenerPorEmail(String email) {
		try (Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_DIRECCION);) {

			ps.setString(1, email);

			try (ResultSet rs = ps.executeQuery()) {

				Direccion direccion = null;

				if (rs.next()) {
					direccion = new Direccion(rs.getLong("d.id"), rs.getString("d.nombre"), rs.getString("d.apellidos"),
							rs.getString("d.direccion"), rs.getInt("d.codigo_postal"), rs.getString("d.ciudad"),
							rs.getLong("d.id_comunidad"), rs.getLong("d.id_provincia"), rs.getString("d.telefono"),
							rs.getString("d.email"), rs.getLong("d.id_usuario"), rs.getBoolean("d.active"));
				}

				System.out.println(direccion);

				return direccion;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException(
					"Ha habido un problema al obtener la direccion del usuario cuyo email es " + email, e);
		}
	}

	@Override
	public Provincia obtenerProvinciaPorId(Long idProvincia) {

		try (Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_PROVINCIA);) {

			ps.setLong(1, idProvincia);

			try (ResultSet rs = ps.executeQuery()) {

				Provincia provincia = null;

				if (rs.next()) {
					provincia = new Provincia(rs.getLong("p.id"), rs.getString("p.nombre"),
							rs.getLong("p.id_comunidad"));

				}

				System.out.println(provincia);

				return provincia;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException(
					"Ha habido un problema al obtener la provincia de la direccion cuyo id es " + idProvincia, e);
		}

	}

	@Override
	public ComunidadAutonoma obtenerComunidadAutonomaPorId(Long idComunidad) {

		try (Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_COMUNIDAD);) {

			ps.setLong(1, idComunidad);

			try (ResultSet rs = ps.executeQuery()) {

				ComunidadAutonoma comunidadAutonoma = null;

				if (rs.next()) {
					comunidadAutonoma = new ComunidadAutonoma(rs.getLong("c.id"), rs.getString("c.nombre"));

				}

				System.out.println(comunidadAutonoma);

				return comunidadAutonoma;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException(
					"Ha habido un problema al obtener el nombre de la comunidad autonoma de la direccion cuyo id es "
							+ idComunidad,
					e);
		}

	}

}
