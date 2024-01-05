package edu.alonso.daw.proyectoProgramacion.repository;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.alonso.daw.proyectoProgramacion.dao.CategoriaDAO;
import edu.alonso.daw.proyectoProgramacion.dao.CategoriaDAOMySql;
import edu.alonso.daw.proyectoProgramacion.model.Categoria;

public class CategoriaRepository {
	private CategoriaDAO dao;
		
	private static CategoriaRepository instance;
		
	private static Logger logger = LogManager.getLogger(CategoriaRepository.class);
		
	public static synchronized CategoriaRepository getInstace() {
		if(instance == null) {
			instance = new CategoriaRepository();
		}
		return instance;
	}
		
	private CategoriaRepository() {
		logger.info("Creando el dao para Categoria...");

		this.dao = new CategoriaDAOMySql();
			
		logger.info("Dao para Categorias creado con éxito");
	}

	public Set<Categoria> getCategorias() {
		return dao.getCategorias();
	}


}
