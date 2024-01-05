package edu.alonso.daw.proyectoProgramacion.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.alonso.daw.proyectoProgramacion.model.Producto;
import edu.alonso.daw.proyectoProgramacion.model.Venta;
import edu.alonso.daw.proyectoProgramacion.repository.VentaRepository;
import edu.alonso.daw.proyectoProgramacion.utils.DBConnection;

public class VentaDAOMySql implements VentaDAO {
	
	private static Logger logger = LogManager.getLogger(VentaRepository.class);
	
	private final String SELECT_VENTAS = "SELECT v.idVenta, v.nombreComprador, v.cantidad, v.fecha, v.precio, v.codProducto, "
			+ "p.nombre, p.precio FROM VENTAS v JOIN Productos p ON v.codProducto = p.idProducto";
	
	private final String INSERT_VENTA = "INSERT INTO Ventas(nombreComprador, cantidad, fecha, precio, codProducto) "
			+ "VALUES (?, ?, ?, ?, ?)";

	/**
	 * Recoge todas las ventas de la base de datos
	 * @return una estructura Set con todas las ventas
	 */
	@Override
	public Set<Venta> getAll() {
		Set<Venta> ventas = new HashSet<>();
		
		logger.info("Recogiendo ventas de la BD...");
		
		Connection conn = DBConnection.getInstance().getConnection();
		
		try (PreparedStatement stmt = conn.prepareStatement(SELECT_VENTAS)) {
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Venta v = new Venta();
				Producto p = new Producto();
				
				v.setIdVenta(rs.getInt("idVenta"));
				v.setNombreComprador(rs.getString("nombreComprador"));
				v.setCantidad(rs.getInt("cantidad"));
				v.setFecha(rs.getDate("fecha"));
				v.setPrecio(rs.getDouble("v.precio"));
				v.setCodProducto(rs.getInt("codProducto"));
				
				p.setNombre(rs.getString("nombre"));
				p.setPrecio(rs.getDouble("p.precio"));
				v.setProducto(p);
				
				ventas.add(v);
			}
			
		} catch (SQLException e) {
			logger.error("Error al recoger las ventas de la base de datos.", e);
		}
		
		return ventas;
	}

	
	/**
	 * Incluye una nueva venta en la base de datos
	 * @param venta con los datos de la venta
	 * @return true si la venta se ha añadido, false si no se ha añadido
	 */
	@Override
	public boolean nuevaVenta(Venta venta) {
		int validar = 0;
		
		logger.info("Creando venta en la BD...");
		
		Connection conn = DBConnection.getInstance().getConnection();
		
		try(PreparedStatement stmt = conn.prepareStatement(INSERT_VENTA)) {
			
			stmt.setString(1, venta.getNombreComprador());
			stmt.setInt(2, venta.getCantidad());
			stmt.setDate(3, Date.valueOf(LocalDate.now()));
			stmt.setDouble(4, venta.getPrecio());
			stmt.setInt(5, venta.getCodProducto());
			
			validar = stmt.executeUpdate();
			
		} catch (SQLException e) {
			logger.error("Error al insertar la venta en la base de datos.", e);
		}
		
		return validar == 1;
	}

	

}
