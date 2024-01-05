package edu.alonso.daw.proyectoProgramacion.model;

import java.util.Objects;

public class Valoraciones {

	private long idValoracion;
	private String nombre;
	private int valoracion;
	private String comentario;
	private int codProducto;

	
	public Valoraciones() {}
	

	public Valoraciones(long idValoracion, String nombre, int valoracion, String comentario,
			int codProducto) {
		this.idValoracion = idValoracion;
		this.nombre = nombre;
		this.valoracion = valoracion;
		this.comentario = comentario;
		this.codProducto = codProducto;

	}

	public long getIdValoracion() {
		return idValoracion;
	}

	public void setIdValoracion(long idValoracion) {
		this.idValoracion = idValoracion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(int codProducto) {
		this.codProducto = codProducto;
	}


	@Override
	public int hashCode() {
		return Objects.hash(codProducto, comentario, idValoracion, nombre, valoracion);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Valoraciones other = (Valoraciones) obj;
		return codProducto == other.codProducto && Objects.equals(comentario, other.comentario)
				&& idValoracion == other.idValoracion && Objects.equals(nombre, other.nombre)
				&& valoracion == other.valoracion;
	}


	@Override
	public String toString() {
		return "Valoraciones [idValoracion=" + idValoracion + ", nombre=" + nombre + ", valoracion=" + valoracion
				+ ", comentario=" + comentario + ", codProducto=" + codProducto + "]";
	}
 
}