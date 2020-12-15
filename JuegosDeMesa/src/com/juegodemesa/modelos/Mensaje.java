package com.juegodemesa.modelos;

public class Mensaje {
	private String tipoMensaje;
	private String textoMensaje;
	
	
	// Constructores
	
	public Mensaje() {
	
	}

	public Mensaje(String tipoMensaje, String textoMensaje) {
		this.tipoMensaje = tipoMensaje;
		this.textoMensaje = textoMensaje;
	}

	
	// Getter y Setters
	
	public String getTipoMensaje() {
		return tipoMensaje;
	}

	public void setTipoMensaje(String tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}

	public String getTextoMensaje() {
		return textoMensaje;
	}

	public void setTextoMensaje(String textoMensaje) {
		this.textoMensaje = textoMensaje;
	}
	
	
	// HashCode and Equals

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((textoMensaje == null) ? 0 : textoMensaje.hashCode());
		result = prime * result + ((tipoMensaje == null) ? 0 : tipoMensaje.hashCode());
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
		Mensaje other = (Mensaje) obj;
		if (textoMensaje == null) {
			if (other.textoMensaje != null)
				return false;
		} else if (!textoMensaje.equals(other.textoMensaje))
			return false;
		if (tipoMensaje == null) {
			if (other.tipoMensaje != null)
				return false;
		} else if (!tipoMensaje.equals(other.tipoMensaje))
			return false;
		return true;
	}
	
	
	
	
}
