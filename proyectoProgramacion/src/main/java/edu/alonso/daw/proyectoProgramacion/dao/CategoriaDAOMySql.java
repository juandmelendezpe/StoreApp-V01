package edu.alonso.daw.proyectoProgramacion.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.alonso.daw.proyectoProgramacion.model.Categoria;
import edu.alonso.daw.proyectoProgramacion.model.Producto;
import edu.alonso.daw.proyectoProgramacion.utils.DBConnection;

public class CategoriaDAOMySql implements CategoriaDAO {
	
	private static Logger logger = LogManager.getLogger(CategoriaDAOMySql.class);

	private final String SELECT_CATEGORIAS = "SELECT idCategoria, categoria "
			+ "FROM categorias";
	
	@Override
	public Set<Categoria> getCategorias() {
		
	Set<Categoria> categorias = new HashSet<>();
		
	Connection conn = DBConnection.getInstance().getConnection();
		
		try (PreparedStatement stmt = conn.prepareStatement(SELECT_CATEGORIAS)) {
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Categoria cat = new Categoria(rs.getLong("idCategoria"), rs.getString("categoria"));

				categorias.add(cat);
			}
			
		} catch (SQLException e) {
			logger.error("Error al recoger todos los productos de la base de datos.", e);
		}
		
		return categorias;
	}

}
