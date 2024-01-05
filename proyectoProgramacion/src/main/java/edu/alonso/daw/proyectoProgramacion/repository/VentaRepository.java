package edu.alonso.daw.proyectoProgramacion.repository;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.alonso.daw.proyectoProgramacion.dao.VentaDAO;
import edu.alonso.daw.proyectoProgramacion.dao.VentaDAOMySql;
import edu.alonso.daw.proyectoProgramacion.model.Venta;

public class VentaRepository {

	private VentaDAO dao;
	
	private static VentaRepository instance;
	
	private static Logger logger = LogManager.getLogger(VentaRepository.class);
	
	public static synchronized VentaRepository getInstace() {
		if(instance == null) {
			instance = new VentaRepository();
		}
		return instance;
	}
	
	private VentaRepository() {
		logger.info("Creando el dao para Venta...");

		this.dao = new VentaDAOMySql();
		
		logger.info("Dao para Venta creado con éxito");
	}

	public Set<Venta> getAll() {
		return dao.getAll();
	}

	public boolean nuevaVenta(Venta venta) {
		return dao.nuevaVenta(venta);
	}
	
}
