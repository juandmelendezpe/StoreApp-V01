package edu.alonso.daw.proyectoProgramacion.model;

import java.util.Date;
import java.util.Objects;

public class Venta {

	private int idVenta;
	private String nombreComprador;
	private int cantidad;
	private Date fecha;
	private double precio;
	private int codProducto;
	private Producto producto;
	
	public Venta () {}
	
	public Venta(int idVenta, String nombreComprador, int cantidad, Date fecha, double precio,
			int codProducto, Producto producto) {
		this.idVenta = idVenta;
		this.nombreComprador = nombreComprador;
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.precio = precio;
		this.codProducto = codProducto;
		this.producto = producto;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public String getNombreComprador() {
		return nombreComprador;
	}

	public void setNombreComprador(String nombreComprador) {
		this.nombreComprador = nombreComprador;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(int codProducto) {
		this.codProducto = codProducto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "Venta [idVenta=" + idVenta + ", nombreComprador=" + nombreComprador + ", cantidad=" + cantidad
				+ ", fecha=" + fecha + ", precio=" + precio + ", codProducto=" + codProducto + ", producto=" + producto
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cantidad, codProducto, fecha, idVenta, nombreComprador, precio, producto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venta other = (Venta) obj;
		return cantidad == other.cantidad && codProducto == other.codProducto && Objects.equals(fecha, other.fecha)
				&& idVenta == other.idVenta && Objects.equals(nombreComprador, other.nombreComprador)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(producto, other.producto);
	}	
	
}
