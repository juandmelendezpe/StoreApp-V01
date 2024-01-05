package edu.alonso.daw.proyectoProgramacion.dao;

import java.util.Set;

import edu.alonso.daw.proyectoProgramacion.model.Venta;

public interface VentaDAO {

	Set<Venta> getAll();

	boolean nuevaVenta(Venta venta);

	

}
