package edu.alonso.daw.proyectoProgramacion.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.alonso.daw.proyectoProgramacion.model.Producto;
import edu.alonso.daw.proyectoProgramacion.model.Valoraciones;
import edu.alonso.daw.proyectoProgramacion.utils.DBConnection;

public class ValoracionDAOMySql implements ValoracionDAO {

/**
 * @author juan
 */
	private final String SELECT_VALORACIONES = " SELECT V.* "+ "FROM VALORACIONES V " 
			+ "  JOIN Productos P ON P.idProducto = V.codProducto";
	
	private final String SELECT_VALORACIONES_PRODCTO = " SELECT idValoracion, nombre, valoracion, comentario"
			+ " FROM VALORACIONES V " 
			+ " WHERE codProducto = ?";
	
	private final String INSERT_VALORACION = "INSERT INTO Valoraciones(nombre,valoracion,comentario,codProducto)"
	+ "VALUES ( ?,?,?,?)";
	
	
	private static Logger logger = LogManager.getLogger(ValoracionDAOMySql.class);
	
	/**
	 * Recorre todos las valoraciones de la base de datos
	 * @return una estructura Set con todos las comentrios
	 */
	@Override
	public Set<Valoraciones> getAll() {
		
		Set<Valoraciones> valoraciones = new HashSet<>();
		
		try (PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(SELECT_VALORACIONES)){
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Valoraciones Valor = new Valoraciones();
				Producto product = new Producto();
				
						Valor.setIdValoracion(rs.getLong("idValoracion"));
						Valor.setNombre(rs.getString("nombre"));
						Valor.setValoracion(rs.getInt("valoracion"));
						Valor.setComentario(rs.getNString("comentario"));
						Valor.setCodProducto(rs.getInt("codProducto"));
						
						product.setIdProducto(rs.getInt("codProducto"));
				
				valoraciones.add(Valor);
			}

		} catch (SQLException e) {
			logger.error("Error al recoger todos las valoraciones", e);

		}
		return valoraciones;
	}
	
	/**
	 * inserta una nueva valoracion a la base de  datos
	 * @return te retorno la validacion true si la creacion fue con exito
	 * @param valoraciones objeto vloraciones con los valores a ingresar
	 * 
	 */
	@Override
	public boolean crearValoracion(Valoraciones valoraciones, int idProducto) {
		int validar = 0;
		Connection conn = DBConnection.getInstance().getConnection();
		try(PreparedStatement stmt = conn.prepareStatement(INSERT_VALORACION)) {
			stmt.setString(1, valoraciones.getNombre());
			stmt.setInt(2, valoraciones.getValoracion());
			stmt.setString(3, valoraciones.getComentario());
			stmt.setInt(4, idProducto);
			
			validar = stmt.executeUpdate();
			
		} catch (SQLException e) {
			logger.error("Error al insertar la Valoracion.", e);;
		}
		return validar == 1;
	}
	
	/**
	 * Buscar una valoracion por id de producto en la base de datos
	 * @return retorna true si fue encontrado la valoracion con realcion al id del producto
	 */
	@Override
	public Set<Valoraciones> getValoracionId(int idProducto) {
		Set<Valoraciones> valoraciones = new HashSet<>();
		
		try (PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(SELECT_VALORACIONES_PRODCTO)){
			
			stmt.setInt(1, idProducto);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Valoraciones Valor = new Valoraciones();
				
						Valor.setIdValoracion(rs.getLong("idValoracion"));
						Valor.setNombre(rs.getString("nombre"));
						Valor.setValoracion(rs.getInt("valoracion"));
						Valor.setComentario(rs.getString("comentario"));
						Valor.setCodProducto(idProducto);
				
				valoraciones.add(Valor);
			}

		} catch (SQLException e) {
			logger.error("Error al recoger todos las valoraciones", e);

		}
		return valoraciones;
	}
	
}