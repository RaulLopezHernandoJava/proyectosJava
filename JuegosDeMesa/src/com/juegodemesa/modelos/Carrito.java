package com.juegodemesa.modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Carrito {
	private Long id;
	private Integer cantidad;
	private Double total;
	private LocalDate fechaCarrito;
	private Long idUsuario;
	private Long idJuego;
	
	private boolean correcto = true;

	private String errorId, errorCantidad, errorTotal, errorFechaCarrito, errorIdUsuario, errorIdJuego;
	
	
	
	// Constructores
	
	
	public Carrito(Long id, Integer cantidad, Double total, LocalDate fechaCarrito, Long idUsuario, Long idJuego) {
		setId(id);
		setCantidad(cantidad);
		setTotal(total);
		setFechaCarrito(fechaCarrito);
		setIdUsuario(idUsuario);
		setIdJuego(idJuego);
	}
	
	
	public Carrito(String id, String cantidad, String total, String fechaCarrito, String idUsuario, String idJuego) {
		setId(id);
		setCantidad(cantidad);
		setTotal(total);
		setFechaCarrito(fechaCarrito);
		setIdUsuario(idUsuario);
		setIdJuego(idJuego);
		
	}
	
	public Carrito() {};
	
	
	
	// Getters and Setters del objeto Carrito
	

	public Long getId() {
		return id;
	}

	

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setId(String id) {
		Long carritoId;
		try {
			carritoId = id.length() == 0 ? null : Long.parseLong(id);
			setId(carritoId);
		} catch (NumberFormatException e) {
			setErrorId("El id debe ser numérico");
		}
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public void setCantidad(String cantidad) {
		Integer carritoCantidad;
		try {
			carritoCantidad = cantidad.length() == 0 ? null : Integer.parseInt(cantidad);
			setCantidad(carritoCantidad);
		} catch (NumberFormatException e) {
			setErrorId("La cantidad debe ser un valor numerico");
		}
	}
	

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	public void setTotal(String total) {
		Double carritoTotal;
		try {
			carritoTotal = total.length() == 0 ? null : Double.parseDouble(total);
			setTotal(carritoTotal);
		} catch (NumberFormatException e) {
			setErrorId("El total (precio) debe ser un valor numerico");
		}
	}

	public LocalDate getFechaCarrito() {
		return fechaCarrito;
	}

	public void setFechaCarrito(LocalDate fechaCarrito) {
		this.fechaCarrito = fechaCarrito;
	}
	
	public void setFechaCarrito(String fechaCarrito) {
		LocalDate juegoFechaCarrito;
		try {
			juegoFechaCarrito = LocalDate.parse(fechaCarrito, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			setFechaCarrito(juegoFechaCarrito);
		} catch (Exception e) {
			setErrorFechaCarrito("La fecha debe tener un formato 1234-12-12");
		}
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public void setIdUsuario(String idUsuario) {
		Long carritoIdUsuario;
		try {
			carritoIdUsuario = idUsuario.length() == 0 ? null : Long.parseLong(idUsuario);
			setIdUsuario(carritoIdUsuario);
		} catch (NumberFormatException e) {
			setErrorId("El id del usuario debe ser un valor numerico");
		}
	}

	public Long getIdJuego() {
		return idJuego;
	}

	public void setIdJuego(Long idJuego) {
		this.idJuego = idJuego;
	}
	
	public void setIdJuego(String idJuego) {
		Long carritoIdJuego;
		try {
			carritoIdJuego = idJuego.length() == 0 ? null : Long.parseLong(idJuego);
			setIdJuego(carritoIdJuego);
		} catch (NumberFormatException e) {
			setErrorId("El id del juego debe ser un valor numerico");
		}
	}
	
	
	// Getters and Setters de los Errores


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


	public String getErrorCantidad() {
		return errorCantidad;
	}


	public void setErrorCantidad(String errorCantidad) {
		this.errorCantidad = errorCantidad;
	}


	public String getErrorTotal() {
		return errorTotal;
	}


	public void setErrorTotal(String errorTotal) {
		this.errorTotal = errorTotal;
	}


	public String getErrorFechaCarrito() {
		return errorFechaCarrito;
	}


	public void setErrorFechaCarrito(String errorFechaCarrito) {
		this.errorFechaCarrito = errorFechaCarrito;
	}


	public String getErrorIdUsuario() {
		return errorIdUsuario;
	}


	public void setErrorIdUsuario(String errorIdUsuario) {
		this.errorIdUsuario = errorIdUsuario;
	}


	public String getErrorIdJuego() {
		return errorIdJuego;
	}


	public void setErrorIdJuego(String errorIdJuego) {
		this.errorIdJuego = errorIdJuego;
	}
	

}






