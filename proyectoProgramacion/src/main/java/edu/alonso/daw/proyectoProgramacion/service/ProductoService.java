package edu.alonso.daw.proyectoProgramacion.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import edu.alonso.daw.proyectoProgramacion.model.Producto;
import edu.alonso.daw.proyectoProgramacion.repository.ProductoRepository;

public class ProductoService {

	public Set<Producto> obtenerProductos() {
		return ProductoRepository.getInstace().getProductos();
	}

	public Set<Producto> obtenerProductosCategoria(int categoria) {
		return ProductoRepository.getInstace().getProductosCategoria(categoria);
	}

	public Producto obtenerProductosId(long id) {
		return ProductoRepository.getInstace().getProductosId(id);
	}

	public boolean crearProducto(Producto p) {
		return ProductoRepository.getInstace().insertProducto(p);
		
	}

	public boolean modificarProducto(Producto p) {
		return ProductoRepository.getInstace().updateProducto(p);
		}

	public boolean eliminarProductos(long idP) {
		return ProductoRepository.getInstace().deleteProducto(idP);
	}

	
	//TEMPORAL
	public void crearXml() {
		Set<Producto> productos = ProductoRepository.getInstace().getProductos();
		
		String linea1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "\n";
		String linea2 = "<?xml-stylesheet href=\"estilo.xsl\" type=\"text/xsl\"?>" + "\n";
		Path file = Paths.get("Ficheros/comercio.xml");
		
		try(BufferedWriter bw = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
			System.out.println("Escribiendo fichero");
			
			bw.write(linea1);
			bw.write(linea2);
			bw.write("<comercio>" + "\n");
			
			for(Producto p : productos) {
				bw.write("<producto id=\"" + p.getIdProducto() + "\">" + "\n");
				bw.write("<nombre>" + p.getNombre() + "</nombre>" + "\n");
				bw.write("<precio>" + p.getPrecio() + "</precio>" + "\n");
				bw.write("<cantidad>" + p.getCantidad() + "</cantidad>" + "\n");
				bw.write("<ruta_imagen>" + p.getRutaImagen() + "</ruta_imagen>" + "\n");
				bw.write("<categoria>" + p.getCategoria().getNombre() + "</categoria>" + "\n");
				bw.write("</producto>" + "\n");
			}
			
			bw.write("</comercio>");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
