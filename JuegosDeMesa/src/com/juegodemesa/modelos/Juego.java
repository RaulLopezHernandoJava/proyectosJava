package com.juegodemesa.modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Juego {
	
	private Long id;
	private String nombre, autor, editorial, imagen;
	private Mecanica mecanica;
	private LocalDate fechaPublicacion;
	
	
	private boolean correcto = true;
	
	private String errorId, errorNombre, errorAutor, errorEditorial, errorMecanica , errorImagen, errorFechaPublicacion;
	
	
	
	// Constructor por Defecto
	
	
	// Constructor con todos los argumentos con sus valores que hemos definido previamente

	public Juego(Long id, String nombre, String autor, String editorial, Mecanica mecanica ,String imagen, LocalDate fechaPublicacion) {
		setId(id);
		setNombre(nombre);
		setAutor(autor);
		setEditorial(editorial);
		setMecanica(mecanica);
		setImagen(imagen);
		setFechaPublicacion(fechaPublicacion);
		
	}
	
	// Constructor para construir objetos todo String
	
	public Juego(String id, String nombre, String autor, String editorial, Mecanica mecanica ,String imagen, String fechaPublicacion) {
		setId(id);
		setNombre(nombre);
		setAutor(autor);
		setEditorial(editorial);
		setMecanica(mecanica);
		setImagen(imagen);
		setFechaPublicacion(fechaPublicacion);
		
	}





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
		if(nombre == null || nombre.trim().length() < 3) {
			setErrorNombre("No se admiten títulos de menos de 3 caracteres");
		}
	
		this.nombre = nombre;
	}





	public String getAutor() {
		return autor;
	}





	public void setAutor(String autor) {
		if(autor == null || autor.trim().length() < 3) {
			setErrorAutor("No se admiten títulos de menos de 3 caracteres");
		}
	
		this.autor = autor;
	}





	public String getEditorial() {
		return editorial;
	}





	public void setEditorial(String editorial) {
		if(editorial == null || editorial.trim().length() < 3) {
			setErrorEditorial("El nombre de la editorial tiene que tener una longitud mayor a 3 caracteres");
		}
		
		this.editorial = editorial;
	}
	
	
	
	
	public Mecanica getMecanica() {
		return mecanica;
	}

	public void setMecanica(Mecanica mecanica) {
		this.mecanica = mecanica;
	}
	
	
	

	public String getImagen() {
		return imagen;
	}





	public void setImagen(String imagen) {
	
		this.imagen = imagen;
	}
	

	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}


	public void setFechaPublicacion(LocalDate fechaPublicacion) {
	
		this.fechaPublicacion = fechaPublicacion;
	}
	
	public void setFechaPublicacion(String fechaPublicacion) {
		
		LocalDate juegoFechaPublicacion;
		try {
			juegoFechaPublicacion = LocalDate.parse(fechaPublicacion, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			setFechaPublicacion(juegoFechaPublicacion);
		} catch (Exception e) {
			setErrorFechaPublicacion("La fecha debe tener un formato 1234-12-12");
		}
	}
	
	


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





	public String getErrorAutor() {
		return errorAutor;
	}





	public void setErrorAutor(String errorAutor) {
		correcto = false;
		this.errorAutor = errorAutor;
	}





	public String getErrorEditorial() {
		return errorEditorial;
	}



	public void setErrorEditorial(String errorEditorial) {
		correcto = false;
		this.errorEditorial = errorEditorial;
	}
	
	public String getErrorMecanica() {
		return errorMecanica;
	}
	
	public void setErrorMecanica(String errorMecanica) {
		correcto = false;
		this.errorMecanica = errorMecanica;
		
	}

	public String getErrorImagen() {
		return errorImagen;
	}





	public void setErrorImagen(String errorImagen) {
		correcto = false;
		this.errorImagen = errorImagen;
	}


	public String getErrorFechaPublicacion() {
		return errorFechaPublicacion;
	}
	
	

	public void setErrorFechaPublicacion(String errorFechaPublicacion) {
		correcto = false;
		this.errorFechaPublicacion = errorFechaPublicacion;
	}


	


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((editorial == null) ? 0 : editorial.hashCode());
		result = prime * result + ((fechaPublicacion == null) ? 0 : fechaPublicacion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imagen == null) ? 0 : imagen.hashCode());
		result = prime * result + ((mecanica == null) ? 0 : mecanica.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Juego other = (Juego) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (editorial == null) {
			if (other.editorial != null)
				return false;
		} else if (!editorial.equals(other.editorial))
			return false;
		if (fechaPublicacion == null) {
			if (other.fechaPublicacion != null)
				return false;
		} else if (!fechaPublicacion.equals(other.fechaPublicacion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imagen == null) {
			if (other.imagen != null)
				return false;
		} else if (!imagen.equals(other.imagen))
			return false;
		if (mecanica == null) {
			if (other.mecanica != null)
				return false;
		} else if (!mecanica.equals(other.mecanica))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Juego [id=" + id + ", nombre=" + nombre + ", autor=" + autor + ", editorial=" + editorial + ", imagen="
				+ imagen + ", mecanica=" + mecanica + ", fechaPublicacion=" + fechaPublicacion + "]";
	}

	
}
