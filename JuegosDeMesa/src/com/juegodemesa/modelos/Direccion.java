package com.juegodemesa.modelos;

public class Direccion {
	private Long id;
	private String nombre;
	private String apellidos;
	private String direccion;
	private Integer codigoPostal;
	private String ciudad;
	private String telefono;
	private String email;
	private ComunidadAutonoma comunidadAutonoma;
	private Provincia provincia;
	private Long idUsuario;
	private Boolean active;
	
	
	private boolean correcto = true;

	private String errorId, errorNombre, errorApellidos, errorDireccion, 
				   errorCodigoPostal, errorCiudad, errorComunidadAutonoma, errorProvincia, errorTelefono,errorEmail,errorIdUsuario,errorActive;
	
	
	
	// Constructores

	public Direccion(Long id, String nombre, String apellidos, String direccion, Integer codigoPostal,
			String ciudad, ComunidadAutonoma comunidadAutonoma, Provincia provincia, String telefono, String email, Long idUsuario,Boolean active) {
		setId(id);
		setNombre(nombre);
		setApellidos(apellidos);
		setDireccion(direccion);
		setCodigoPostal(codigoPostal);
		setCiudad(ciudad);
		setComunidadAutonoma(comunidadAutonoma);
		setProvincia(provincia);
		setTelefono(telefono);
		setEmail(email);
		setIdUsuario(idUsuario);
		setActive(active);
	}
	
	
	public Direccion(String id, String nombre, String apellidos, String direccion, Integer codigoPostal,
					String ciudad,ComunidadAutonoma comunidadAutonoma,Provincia provincia, String telefono,String email, String idUsuario) {
		setId(id);
		setNombre(nombre);
		setApellidos(apellidos);
		setDireccion(direccion);
		setCodigoPostal(codigoPostal);
		setCiudad(ciudad);
		setComunidadAutonoma(comunidadAutonoma);
		setProvincia(provincia);
		setTelefono(telefono);
		setEmail(email);
		setIdUsuario(idUsuario);
		
	}
	
	public Direccion(String nombre, String apellidos,String direccion, String codigoPostal,
			String ciudad, ComunidadAutonoma comunidadAutonoma, Provincia provincia, String telefono, String email) {
		setNombre(nombre);
		setApellidos(apellidos);
		setDireccion(direccion);
		setCodigoPostal(codigoPostal);
		setCiudad(ciudad);
		setComunidadAutonoma(comunidadAutonoma);
		setProvincia(provincia);
		setTelefono(telefono);
		setEmail(email);
	}
	
	public Direccion(String nombre, String apellidos, String direccion, String codigoPostal, String ciudad,
			String telefono, String email,  Long idUsuario, Provincia provincia, ComunidadAutonoma comunidad, Boolean active) {
		setNombre(nombre);
		setApellidos(apellidos);
		setDireccion(direccion);
		setCodigoPostal(codigoPostal);
		setCiudad(ciudad);
		setTelefono(telefono);
		setEmail(email);
		setIdUsuario(idUsuario);
		setProvincia(provincia);
		setComunidadAutonoma(comunidad);
		setActive(active);
	}
	
	public Direccion(String id,String nombre, String apellidos, String direccion, String codigoPostal, String ciudad,
			String telefono, String email,  Long idUsuario, Provincia provincia, ComunidadAutonoma comunidad, Boolean active) {
		setId(id);
		setNombre(nombre);
		setApellidos(apellidos);
		setDireccion(direccion);
		setCodigoPostal(codigoPostal);
		setCiudad(ciudad);
		setTelefono(telefono);
		setEmail(email);
		setIdUsuario(idUsuario);
		setProvincia(provincia);
		setComunidadAutonoma(comunidad);
		setActive(active);
	}
	
	public Direccion(String id, String nombre, String apellidos, String direccion, String codigoPostal,
			String ciudad, String telefono, String email, Long idUsuario, Long provinciaId, Long comunidadId,
			Boolean active) {
		setId(id);
		setNombre(nombre);
		setApellidos(apellidos);
		setDireccion(direccion);
		setCodigoPostal(codigoPostal);
		setCiudad(ciudad);
		setTelefono(telefono);
		setEmail(email);
		setIdUsuario(idUsuario);
		setProvincia(provinciaId);
		setComunidadAutonoma(comunidadId);
		setActive(active);
	}
	
	
	
	
	public Direccion() {
	
	}

	
	// Getters y Setters






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


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public Integer getCodigoPostal() {
		return codigoPostal;
	}


	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	public void setCodigoPostal(String codigoPostal) {
		Integer usuarioCodigoPostal;
		try {
			usuarioCodigoPostal = codigoPostal.length() == 0 || codigoPostal.length() < 5? null : Integer.parseInt(codigoPostal);
			setCodigoPostal(usuarioCodigoPostal);
		} catch (NumberFormatException e) {
			setErrorId("El Codigo Postal debe ser debe ser numérico y tener al menos 5 digitos");
		}
	}
	


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	
	public ComunidadAutonoma getComunidadAutonoma() {
		return comunidadAutonoma;
	}


	public void setComunidadAutonoma(ComunidadAutonoma comunidadAutonoma) {
		this.comunidadAutonoma = comunidadAutonoma;
	}
	
	public void setComunidadAutonoma(Long idComunidad) {
		Long comunidadId;
		try {
			comunidadId = idComunidad.longValue() == 0 ? null : comunidadAutonoma.getId();
			setComunidadAutonoma(comunidadId);
		} catch (NumberFormatException e) {
			setErrorId("El id de la provencia debe ser un long");
		}
	}



	public Provincia getProvincia() {
		return provincia;
	}


	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	public void setProvincia(Long idProvincia) {
		Long provinciaId;
		try {
			provinciaId = idProvincia.longValue() == 0 ? null : provincia.getId();
			setProvincia(provinciaId);
		} catch (NumberFormatException e) {
			setErrorId("El id de la provencia debe ser un long");
		}
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Long getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public void setIdUsuario(String idUsuario) {
		Long usuarioId;
		try {
			usuarioId = idUsuario.length() == 0 ? null : Long.parseLong(idUsuario);
			setId(usuarioId);
		} catch (NumberFormatException e) {
			setErrorId("El id de usuario debe ser numérico");
		}
	}
	
	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}

	// Getters y Setters para Validaciones
	
	


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
		correcto = false;
		this.errorId = errorId;
	}


	public String getErrorNombre() {
		return errorNombre;
	}


	public void setErrorNombre(String errorNombre) {
		correcto = false;
		this.errorNombre = errorNombre;
	}


	public String getErrorApellidos() {
		return errorApellidos;
	}


	public void setErrorApellidos(String errorApellidos) {
		correcto = false;
		this.errorApellidos = errorApellidos;
	}


	public String getErrorDireccion() {
		return errorDireccion;
	}


	public void setErrorDireccion(String errorDireccion) {
		correcto = false;
		this.errorDireccion = errorDireccion;
	}


	public String getErrorCodigoPostal() {
		return errorCodigoPostal;
	}


	public void setErrorCodigoPostal(String errorCodigoPostal) {
		correcto = false;
		this.errorCodigoPostal = errorCodigoPostal;
	}


	public String getErrorCiudad() {
		return errorCiudad;
	}


	public void setErrorCiudad(String errorCiudad) {
		correcto = false;
		this.errorCiudad = errorCiudad;
	}
	
	public String getErrorComunidadAutonoma() {
		return errorComunidadAutonoma;
	}


	public void setErrorComunidadAutonoma(String errorComunidadAutonoma) {
		correcto = false;
		this.errorComunidadAutonoma = errorComunidadAutonoma;
	}


	public String getErrorProvincia() {
		return errorProvincia;
	}


	public void setErrorProvincia(String errorProvincia) {
		correcto = false;
		this.errorProvincia = errorProvincia;
	}


	public String getErrorTelefono() {
		return errorTelefono;
	}


	public void setErrorTelefono(String errorTelefono) {
		correcto = false;
		this.errorTelefono = errorTelefono;
	}


	public String getErrorEmail() {
		return errorEmail;
	}


	public void setErrorEmail(String errorEmail) {
		correcto = false;
		this.errorEmail = errorEmail;
	}


	public String getErrorIdUsuario() {
		return errorIdUsuario;
	}


	public void setErrorIdUsuario(String errorIdUsuario) {
		correcto = false;
		this.errorIdUsuario = errorIdUsuario;
	}
	

	public String getErrorActive() {
		return errorActive;
	}


	public void setErrorActive(String errorActive) {
		correcto = false;
		this.errorActive = errorActive;
	}


	// HashCode() and Equals()
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result + ((codigoPostal == null) ? 0 : codigoPostal.hashCode());
		result = prime * result + ((comunidadAutonoma == null) ? 0 : comunidadAutonoma.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
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
		Direccion other = (Direccion) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (codigoPostal == null) {
			if (other.codigoPostal != null)
				return false;
		} else if (!codigoPostal.equals(other.codigoPostal))
			return false;
		if (comunidadAutonoma == null) {
			if (other.comunidadAutonoma != null)
				return false;
		} else if (!comunidadAutonoma.equals(other.comunidadAutonoma))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}

	
	// toString()

	@Override
	public String toString() {
		return "Direccion [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion
				+ ", codigoPostal=" + codigoPostal + ", ciudad=" + ciudad + ", telefono=" + telefono + ", email="
				+ email + ", comunidadAutonoma=" + comunidadAutonoma + ", provincia=" + provincia + ", idUsuario="
				+ idUsuario + ", active=" + active + "]";
	}

	
}


