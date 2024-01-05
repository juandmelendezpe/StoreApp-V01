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

public class ProductoDAOMySql implements ProductoDAO {
	
	private static Logger logger = LogManager.getLogger(ProductoDAOMySql.class);

	private final String SELECT_PRODUCTOS = "SELECT idProducto, Precio, p.Nombre, Cantidad, RutaImagen, codCategoria, Categoria, AVG(valoracion) AS valoracion "
			+ "FROM Productos p JOIN Categorias ON codCategoria = idCategoria "
			+ "LEFT JOIN Valoraciones ON idProducto = codProducto "
			+ "GROUP BY idProducto, Precio, Nombre, Cantidad, RutaImagen, Categoria";
	
	private final String SELECT_PRODUCTOS_CATEGORIA = "SELECT idProducto, Precio, p.Nombre, Cantidad, RutaImagen, Categoria, AVG(valoracion) AS valoracion "
			+ "FROM Productos p JOIN Categorias ON codCategoria = idCategoria "
			+ "LEFT JOIN Valoraciones ON idProducto = codProducto "
			+ "WHERE codCategoria = ? and Cantidad >=10 "
			+ "GROUP BY idProducto, Precio, Nombre, Cantidad, RutaImagen"; 
	
	private final String SELECT_PRODUCTO_ID = "SELECT idProducto, Precio, p.Nombre, Cantidad, RutaImagen, codCategoria, Categoria, AVG(valoracion) AS valoracion "
			+ "FROM Productos p JOIN Categorias ON codCategoria = idCategoria "
			+ "LEFT JOIN Valoraciones ON idProducto = codProducto "
			+ "WHERE idProducto = ? "
			+ "GROUP BY idProducto, Precio, Nombre, Cantidad, RutaImagen, codCategoria, Categoria";
	
	private final String INSERT_PRODUCTO = "INSERT INTO Productos(idProducto, Precio, Nombre, Cantidad, RutaImagen, codCategoria) "
			+ "VALUES (?, ?, ?, ?, ?, ?)";
	
	private final String UPDATE_PRODUCTO = "UPDATE Productos SET Precio = ?, Nombre = ?, Cantidad = ?, codCategoria = ? "
			+ "WHERE idProducto = ?";
	
	private final String DELETE_PRODUCTO = "DELETE FROM productos WHERE idProducto = ?";
	
	/**
	 * Recoge todas los productos de la base de datos
	 * @return una estructura Set con todos los productos
	 */
	@Override
	public Set<Producto> getProductos() {
		
	Set<Producto> productos = new HashSet<>();
		
	logger.info("Recogiendo productos de la BD...");
	Connection conn = DBConnection.getInstance().getConnection();
		
		try (PreparedStatement stmt = conn.prepareStatement(SELECT_PRODUCTOS)) {
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Producto p = new Producto();
				
				p.setIdProducto(rs.getLong("idProducto"));
				p.setPrecio(rs.getDouble("Precio"));
				p.setNombre(rs.getString("Nombre"));
				p.setCantidad(rs.getInt("Cantidad"));
				p.setRutaImagen(rs.getString("RutaImagen"));
				p.setCategoria(new Categoria(rs.getInt("codCategoria"), rs.getString("Categoria")));
				
				double valoracion = rs.getDouble("valoracion");
				valoracion = ((double)(Math.round(valoracion * 10))) / 10;
				p.setMediaVloraciones(valoracion);
				
				productos.add(p);
			}
			
		} catch (SQLException e) {
			logger.error("Error al recoger todos los productos de la base de datos.", e);
		}
		
		return productos;
	}

	/**
	 * Recoge de la base de datos todas los productos de datos que pertenezcan a la categoria especificada
	 * @return una estructura Set con todos los productos de la categoria
	 * @param categ numero del id de la categoria
	 */
	@Override
	public Set<Producto> getProductosCategoria(int categ) {
		Set<Producto> productos = new HashSet<>();
		
		logger.info("Recogiendo productos de la BD con categoria:" + categ + "...");
		Connection conn = DBConnection.getInstance().getConnection();
		
		try (PreparedStatement stmt = conn.prepareStatement(SELECT_PRODUCTOS_CATEGORIA)) {
			stmt.setInt(1, categ);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Producto p = new Producto();
				
				p.setIdProducto(rs.getLong("idProducto"));
				p.setPrecio(rs.getDouble("Precio"));
				p.setNombre(rs.getString("Nombre"));
				p.setCantidad(rs.getInt("Cantidad"));
				p.setRutaImagen(rs.getString("RutaImagen"));
				p.setCategoria(new Categoria(categ, rs.getString("Categoria")));
				
				double valoracion = rs.getDouble("valoracion");
				valoracion = ((double)(Math.round(valoracion * 10))) / 10;
				p.setMediaVloraciones(valoracion);
				
				productos.add(p);
			}
			
		} catch (SQLException e) {
			logger.error("Error al recoger los productos de la categoria" + categ +  " de la base de datos.", e);
		}
		
		return productos;
	}

	
	/**
	 * Recoge de la base de datos el producto que le especifiques
	 * @return un objeto Producto
	 * @param id numero del id del producto
	 */
	@Override
	public Producto getProductosId(long id) {
		Producto p = new Producto();
		
		logger.info("Recogiendo el producto de la BD con id:" + id + "...");
		Connection conn = DBConnection.getInstance().getConnection();
		
		try(PreparedStatement stmt = conn.prepareStatement(SELECT_PRODUCTO_ID)) {
			stmt.setLong(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				p.setIdProducto(rs.getLong("idProducto"));
				p.setPrecio(rs.getDouble("Precio"));
				p.setNombre(rs.getString("Nombre"));
				p.setCantidad(rs.getInt("Cantidad"));
				p.setRutaImagen(rs.getString("RutaImagen"));
				p.setCategoria(new Categoria(rs.getInt("codCategoria"), rs.getString("Categoria")));
				
				double valoracion = rs.getDouble("valoracion");
				valoracion = ((double)(Math.round(valoracion * 10))) / 10;
				p.setMediaVloraciones(valoracion);
			} else {
				p = null;
			}
			
		} catch (SQLException e) {
			logger.error("Error al recoger el producto" + id, e);
		}
		
		return p;
	}

	/**
	 * Inserta un producto en la base de datos
	 * @return true si se ha ingresado y false si no se ha podido ingresar
	 * @param p objeto Producto con los valores a ingresar
	 */
	@Override
	public boolean insertProducto(Producto p) {
		
		logger.info("Insertando producto: " + p);
		Connection conn = DBConnection.getInstance().getConnection();
		int insert = 0;
		
		try(PreparedStatement stmt = conn.prepareStatement(INSERT_PRODUCTO)) {
			stmt.setLong(1, p.getIdProducto());
			stmt.setDouble(2, p.getPrecio());
			stmt.setString(3, p.getNombre());
			stmt.setInt(4, p.getCantidad());
			stmt.setString(5, "RUTA");
			stmt.setLong(6, p.getCategoria().getIdCategoria());
			
			insert = stmt.executeUpdate();
			logger.info("Creado con exito");
		} catch (SQLException e) {
			logger.error("Error al insertar el producto: " + p, e);;
		}
		return insert == 1;
	}

	/**
	 * Actualiza un producto en la base de datos
	 * @return true si se ha actualizado y false si no se ha podido actualizar
	 * @param p objeto Producto con los valores para actualizar
	 */
	@Override
	public boolean updateProducto(Producto p) {
		logger.info("Actualizando producto: " + p);
		Connection conn = DBConnection.getInstance().getConnection();
		int update = 0;
		
		try(PreparedStatement stmt = conn.prepareStatement(UPDATE_PRODUCTO)) {

			stmt.setDouble(1, p.getPrecio());
			stmt.setString(2, p.getNombre());
			stmt.setInt(3, p.getCantidad());
			stmt.setLong(4, p.getCategoria().getIdCategoria());
			stmt.setLong(5, p.getIdProducto());
			
			update = stmt.executeUpdate();
			logger.info("Actualizado con exito");
		} catch (SQLException e) {
			logger.error("Error al actualizar el producto: " + p, e);;
		}
		return update == 1;
	}

	/**
	 * Elimina un producto en la base de datos
	 * @return true si se ha eliminado y false si no se ha podido eliminar
	 * @param p objeto Producto con los valores para eliminar
	 */
	@Override
	public boolean deleteProducto(long idP) {
		logger.info("Eliminando producto: " + idP);
		Connection conn = DBConnection.getInstance().getConnection();
		int delete = 0;
		
		try(PreparedStatement stmt = conn.prepareStatement(DELETE_PRODUCTO)) {

			stmt.setLong(1, idP);
			
			delete = stmt.executeUpdate();
			logger.info("Eliminando con exito");
		} catch (SQLException e) {
			logger.error("Error al eliminar el producto: " + idP, e);;
		}
		return delete == 1;
	}

}
