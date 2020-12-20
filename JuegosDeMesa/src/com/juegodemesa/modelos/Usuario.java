package com.juegodemesa.modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario {
	private Long id;
	private String nombre, apellidos, email,password;
	private Rol rol;
	private Integer edad;
	private LocalDate fechaRegistro;
	

	private boolean correcto = true;

	private String errorId, errorNombre, errorApellidos, errorEmail, errorPassword, errorRol, errorEdad, errorFechaRegistro;

	// Constructores
	
	
	public Usuario(Long id, String nombre, String apellidos, String email, Rol rol, Integer edad,
			LocalDate fechaRegistro) {
		setId(id);
		setNombre(nombre);
		setApellidos(apellidos);
		setEmail(email);
		setRol(rol);
		setEdad(edad);
		setFechaRegistro(fechaRegistro);
	}
	

	public Usuario(Long id, String nombre, String apellidos, String email, String password, Rol rol, Integer edad,
			LocalDate fechaRegistro) {
		setId(id);
		setNombre(nombre);
		setApellidos(apellidos);
		setEmail(email);
		setRol(rol);
		setPassword(password);
		setEdad(edad);
		setFechaRegistro(fechaRegistro);
	}
	
	
	

	public Usuario(String nombre, String apellidos, String email, String rol, String edad, String fechaRegistro) {
		setNombre(nombre);
		setApellidos(apellidos);
		setEmail(email);
		setEdad(edad);
		setFechaRegistro(fechaRegistro);
	}
	

	public Usuario(String nombre, String apellidos, String email, String password,Rol rol ,String edad, String fechaRegistro) {
		setNombre(nombre);
		setApellidos(apellidos);
		setEmail(email);
		setPassword(password);
		setRol(rol);
		setEdad(edad);
		setFechaRegistro(fechaRegistro);
	}
	
	public Usuario(String nombre, String apellidos,String email) {
		setNombre(nombre);
		setApellidos(apellidos);
		setEmail(email);
	}
	

	public Usuario() {

	}

// Getters y Setters atributos de Usuario

	// Id

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setId(String id) {
		Long juegoId;
		try {
			juegoId = id.length() == 0 ? null : Long.parseLong(id);
			setId(juegoId);
		} catch (NumberFormatException e) {
			setErrorId("El id debe ser numérico");
		}
	}

	// Nombre

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null || nombre.trim().length() < 3) {
			setErrorNombre("No se admiten nombres de menos de 3 caracteres");
		}
		this.nombre = nombre;
	}

	// Apellidos

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		if (apellidos == null || apellidos.trim().length() < 3) {
			setErrorApellidos("No se admiten apellidos de menos de 3 caracteres");
		}
		this.apellidos = apellidos;
	}

	// Email

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
//		Pattern pattern = Pattern.compile(
//				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
//		Matcher mather = pattern.matcher(email);
//
//		if (!mather.find()) {
//			setErrorEmail("Este email no tiene un formato valido");
//		}
		this.email = email;

	}

	// Rol

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
	// Password
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	// Edad

	public Integer getEdad() {
		return edad;
	}


	public void setEdad(Integer edad) {
		if (edad.intValue() < 18 || edad.intValue() > 99) {
			setErrorEdad("El rango de edad comprendido va desde los 18 hasta los 99 años");
		}
		this.edad = edad;
	}

	public void setEdad(String edad) {
		Integer juegoEdad;
		try {
			juegoEdad = edad.length() == 0 ? null : Integer.parseInt(edad);
			setEdad(juegoEdad);
		} catch (NumberFormatException e) {
			setErrorId("La edad debe ser un numero");
		}
	}

	// Fecha Registro

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = LocalDate.now();
	}

	public void setFechaRegistro(String fechaRegistro) {
		LocalDate juegoFechaRegistro;
		try {
			juegoFechaRegistro = LocalDate.parse(fechaRegistro, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			setFechaRegistro(juegoFechaRegistro);
		} catch (Exception e) {
			setErrorFechaRegistro("La fecha debe tener un formato 1234-12-12");
		}
	}

	// Getters y Setters para Validar los atributos de usuario

	public boolean isCorrecto() {
		return correcto;
	}

	public void setCorrecto(boolean correcto) {
		this.correcto = correcto;
	}

	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

	public String getErrorNombre() {
		return errorNombre;
	}

	public void setErrorNombre(String errorNombre) {
		this.errorNombre = errorNombre;
	}

	public String getErrorApellidos() {
		return errorApellidos;
	}

	public void setErrorApellidos(String errorApellidos) {
		this.errorApellidos = errorApellidos;
	}

	public String getErrorEmail() {
		return errorEmail;
	}

	public void setErrorEmail(String errorEmail) {
		this.errorEmail = errorEmail;
	}

	public String getErrorRol() {
		return errorRol;
	}

	public void setErrorRol(String errorRol) {
		this.errorRol = errorRol;
	}
	
	public String getErrorPassword() {
		return errorPassword;
	}

	public void setErrorPassword(String errorPassword) {
		this.errorPassword = errorPassword;
	}

	public String getErrorEdad() {
		return errorEdad;
	}

	public void setErrorEdad(String errorEdad) {
		this.errorEdad = errorEdad;
	}

	public String getErrorFechaRegistro() {
		return errorFechaRegistro;
	}

	public void setErrorFechaRegistro(String errorFechaRegistro) {
		this.errorFechaRegistro = errorFechaRegistro;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((edad == null) ? 0 : edad.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fechaRegistro == null) ? 0 : fechaRegistro.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((rol == null) ? 0 : rol.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (edad == null) {
			if (other.edad != null)
				return false;
		} else if (!edad.equals(other.edad))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fechaRegistro == null) {
			if (other.fechaRegistro != null)
				return false;
		} else if (!fechaRegistro.equals(other.fechaRegistro))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (rol == null) {
			if (other.rol != null)
				return false;
		} else if (!rol.equals(other.rol))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", password=" + password + ", rol=" + rol + ", edad=" + edad + ", fechaRegistro=" + fechaRegistro
				+ "]";
	}
	
	
	
	
}
