package edu.alonso.daw.proyectoProgramacion.repository;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import edu.alonso.daw.proyectoProgramacion.dao.ValoracionDAO;
import edu.alonso.daw.proyectoProgramacion.dao.ValoracionDAOMySql;
import edu.alonso.daw.proyectoProgramacion.model.Producto;
import edu.alonso.daw.proyectoProgramacion.model.Valoraciones;
import edu.alonso.daw.proyectoProgramacion.utils.ConfigLoader;


public class ValoracionRepo {

	private ValoracionDAO dao ;
	
	private static ValoracionRepo instance;
	private static Logger logger = LogManager.getLogger(ValoracionRepo.class);
	
	
	public static synchronized ValoracionRepo getInstance() {
		if (instance == null) {
			instance = new ValoracionRepo();
		}
		return instance;
		
	}
	private ValoracionRepo() {
		
		logger.info("Creando el dao para Valoracion");
		
	/*
	 * 	if(ConfigLoader.getInstance().getDb_type().equalsIgnoreCase("mysql")) {
	 */
			this.dao = new ValoracionDAOMySql();
		
		logger.info("Dao para valoracion creada con exito");
		
		}
	
	public Set<Valoraciones> getAll() {
		return dao.getAll();
	
	
	}
	public boolean crearValoracion(Valoraciones valoraciones, int idproducto ) {
		return dao.crearValoracion(valoraciones, idproducto);
	
	
	//public Set<Valoraciones>getValoraciones(){
		//return dao.getValoraciones();
	}
	public Set<Valoraciones> getValoracionId(int idProducto) {
		return dao.getValoracionId(idProducto);
	}
	
		
	}