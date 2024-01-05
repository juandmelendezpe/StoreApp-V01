package edu.alonso.daw.proyectoProgramacion.dao;

import java.util.Set;

import edu.alonso.daw.proyectoProgramacion.model.Producto;

public interface ProductoDAO {

	Set<Producto> getProductos();

	Set<Producto> getProductosCategoria(int categoria);

	Producto getProductosId(long id);

	boolean insertProducto(Producto p);

	boolean updateProducto(Producto p);

	boolean deleteProducto(long idP);

}
