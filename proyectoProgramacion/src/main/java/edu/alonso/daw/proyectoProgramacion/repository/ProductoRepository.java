package edu.alonso.daw.proyectoProgramacion.repository;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.alonso.daw.proyectoProgramacion.dao.ProductoDAO;
import edu.alonso.daw.proyectoProgramacion.dao.ProductoDAOMySql;
import edu.alonso.daw.proyectoProgramacion.model.Producto;

public class ProductoRepository {
	private ProductoDAO dao;
		
	private static ProductoRepository instance;
		
	private static Logger logger = LogManager.getLogger(ProductoRepository.class);
		
	public static synchronized ProductoRepository getInstace() {
		if(instance == null) {
			instance = new ProductoRepository();
		}
		return instance;
	}
		
	private ProductoRepository() {
		logger.info("Creando el dao para Producto...");

		this.dao = new ProductoDAOMySql();
			
		logger.info("Dao para Producto creado con éxito");
	}

	public Set<Producto> getProductos() {
		return dao.getProductos();
	}

	public Set<Producto> getProductosCategoria(int categoria) {
		return dao.getProductosCategoria(categoria);
	}

	public Producto getProductosId(long id) {
		return dao.getProductosId(id);
	}

	public boolean insertProducto(Producto p) {
		return dao.insertProducto(p);
	}

	public boolean updateProducto(Producto p) {
		return dao.updateProducto(p);
	}

	public boolean deleteProducto(long idP) {
		return dao.deleteProducto(idP);
	}


}
