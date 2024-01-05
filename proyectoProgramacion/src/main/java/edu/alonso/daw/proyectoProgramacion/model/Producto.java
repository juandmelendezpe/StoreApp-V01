package edu.alonso.daw.proyectoProgramacion.model;

import java.util.Objects;

public class Producto {

	private long idProducto;
	private double precio;
	private String nombre;
	private int cantidad;
	private String rutaImagen;
	private Categoria categoria;
	private double mediaVloraciones;
	
	public long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getRutaImagen() {
		return rutaImagen;
	}
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	public double getMediaVloraciones() {
		return mediaVloraciones;
	}
	public void setMediaVloraciones(double mediaVloraciones) {
		this.mediaVloraciones = mediaVloraciones;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cantidad, categoria, idProducto, mediaVloraciones, nombre, precio, rutaImagen);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return cantidad == other.cantidad && Objects.equals(categoria, other.categoria)
				&& idProducto == other.idProducto && mediaVloraciones == other.mediaVloraciones
				&& Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(rutaImagen, other.rutaImagen);
	}
	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", precio=" + precio + ", nombre=" + nombre + ", cantidad="
				+ cantidad + ", rutaImagen=" + rutaImagen + ", categoria=" + categoria + ", mediaVloraciones="
				+ mediaVloraciones + "]";
	}

	
	
	
}
