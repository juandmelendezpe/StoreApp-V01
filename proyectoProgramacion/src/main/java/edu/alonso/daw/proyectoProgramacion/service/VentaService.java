package edu.alonso.daw.proyectoProgramacion.service;

import java.util.Set;

import edu.alonso.daw.proyectoProgramacion.model.Producto;
import edu.alonso.daw.proyectoProgramacion.model.Venta;
import edu.alonso.daw.proyectoProgramacion.repository.ProductoRepository;
import edu.alonso.daw.proyectoProgramacion.repository.VentaRepository;

public class VentaService {

	public Set<Venta> listadoVentas() {
		return VentaRepository.getInstace().getAll();
	}

	public boolean nuevaVenta(Venta venta) {
		boolean comprobar = false;
		Producto p = ProductoRepository.getInstace().getProductosId(venta.getCodProducto());
		
		if(p.getCantidad() >= venta.getCantidad()) {
			venta.setPrecio(p.getPrecio());
			comprobar = VentaRepository.getInstace().nuevaVenta(venta);
			
			if(comprobar) {
				p.setCantidad(p.getCantidad() - venta.getCantidad());
				ProductoRepository.getInstace().updateProducto(p);
			}
		}
		
		return comprobar;
	}

}
