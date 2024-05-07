package edu.alonso.daw.proyectoProgramacion;

import java.util.Scanner;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.alonso.daw.proyectoProgramacion.utils.DBConnection;
import edu.alonso.daw.proyectoProgramacion.service.ProductoService;
import edu.alonso.daw.proyectoProgramacion.service.ValoracionService;
import edu.alonso.daw.proyectoProgramacion.service.VentaService;
import edu.alonso.daw.proyectoProgramacion.model.Categoria;
import edu.alonso.daw.proyectoProgramacion.model.Producto;
import edu.alonso.daw.proyectoProgramacion.model.Valoraciones;
import edu.alonso.daw.proyectoProgramacion.model.Venta;

public class Init {
	
	private static Logger logger = LogManager.getLogger(Init.class);
	


	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int opt = 0;
		
		
		Producto p;
		Venta v = new Venta();
		ProductoService ps = new ProductoService();
		Set<Producto> productos;
		long idP;
		double precio;
		String nombre;
		int cantidad;
		int idC;
		int idProd;

		// se llama al metodo ValoracionService
		Valoraciones valor = new Valoraciones();
		
		ValoracionService vs = new ValoracionService();
		Set<Valoraciones> valoraciones;
		long idValoracion;
		String nombre_V ;
		int valoracion;
		String comentario;
		int codProducto;
		
		 
		
		VentaService vens = new VentaService();
		Set<Venta> ventas = null;

		do {

			System.out.println("\n----FUNCIONALIDADES----");
			System.out.println("1-MOSTRAR TODOS LOS PRODUCTOS");
			System.out.println("2-MOSTRAR PRODUCTOS POR CATEGORÍA");
			System.out.println("3-MOSTRAR DATOS DE UN PRODUCTO");
			System.out.println("4-CREAR PRODUCTO");
			System.out.println("5-MODIFICAR PRODUCTO");
			System.out.println("6-BORRAR PRODUCTO");
			System.out.println("7-MOSTRAR TODAS LAS VENTAS");
			System.out.println("8-NUEVA VENTA");
			System.out.println("9-CREAR XML");
			System.out.println("10-NUEVA VALORACION");
			System.out.println("11-MOSTRAR VALORACIONES DE UN PRODUCTO");
			System.out.println("12-SALIR");
			System.out.println("\n\n");

			do {
				System.out.println("Introduzca el número de la opción: ");
				opt = Integer.valueOf(sc.nextLine());
			} while (opt < 1 || opt > 12);

			switch (opt) {
			
			case 1:
				System.out.println("----MOSTRAR TODOS LOS PRODUCTOS----");
				productos = ps.obtenerProductos();
				
				for (Producto producto : productos) {
					System.out.println(producto);
				}
				
				break;
				
			case 2:
				System.out.println("----MOSTRAR TODOS LOS PRODUCTOS----");
				
				System.out.println("Escribe la categoria de los productos: ");
				
				int categoria = Integer.valueOf(sc.nextLine());
				
				productos = ps.obtenerProductosCategoria(categoria);
				
				for (Producto producto : productos) {
					System.out.println(producto);
				}
				break;
			case 3:
				System.out.println("----MOSTRAR PRODUCTO ID----");
				
				System.out.println("Escribe el id del producto: ");
				
				idP = Long.valueOf(sc.nextLine());
				
				p = ps.obtenerProductosId(idP);
				
				System.out.println(p);
				
				break;
				
			case 4:
				System.out.println("----CREAR PRODUCTO----");
				
				System.out.println("Escribe el nombre del productos: ");
				nombre = sc.nextLine();
				
				System.out.println("Escribe el precio del productos: ");
				precio = Double.valueOf(sc.nextLine());
				
				System.out.println("Escribe la cantidad del productos: ");
				cantidad = Integer.valueOf(sc.nextLine());
				
				System.out.println("Escribe el id de la categoria del productos: ");
				idC = Integer.valueOf(sc.nextLine());
				p = new Producto();
				
				p.setCantidad(cantidad);
				p.setNombre(nombre);
				p.setPrecio(precio);
				p.setCategoria(new Categoria(idC));
				
				System.out.println(ps.crearProducto(p)? "Se ha creado correctamente" : "Ha ocurrdo un error");
				
				break;
	
			case 5:
				System.out.println("----MODIFICAR PRODUCTO----");
				
				System.out.println("Escribe el id del producto que quieres modificar: ");
				idP = Long.valueOf(sc.nextLine());
				
				System.out.println("Escribe el precio del productos: ");
				precio = Double.valueOf(sc.nextLine());
				
				System.out.println("Escribe el nombre del producto: ");
				nombre = sc.nextLine();

				System.out.println("Escribe la cantidad del productos: ");
				cantidad = Integer.valueOf(sc.nextLine());
				
				System.out.println("Escribe el id de la categoria del productos: ");
				idC = Integer.valueOf(sc.nextLine());
				p = new Producto();
				
				p.setIdProducto(idP);
				p.setCantidad(cantidad);
				p.setNombre(nombre);
				p.setPrecio(precio);
				p.setCategoria(new Categoria(idC));
				
				System.out.println(ps.modificarProducto(p)? "Se ha modificado correctamente" : "Ha ocurrdo un error");
				
				break;
				
			case 6:
				System.out.println("----MOSTRAR PRODUCTO ID----");
				
				System.out.println("Escribe el id del productos que quieres eliminar: ");
				
				idP = Long.valueOf(sc.nextLine());
				
				System.out.println(ps.eliminarProductos(idP)? "Se ha eliminado correctamente" : "Ha ocurrdo un error");
				
				
				break;

			case 7:
				System.out.println("----MOSTRAR TODAS LAS VENTAS----");
				ventas = vens.listadoVentas();
				
				for(Venta venta : ventas) {
					System.out.println(venta);
				}
				
				break;
				
			case 8:
				System.out.println("----NUEVA VENTA----");
				
				System.out.println("Introduce tu nombre: ");
				nombre = sc.nextLine();
				
				//Se recoge desde la vista
				System.out.println("Introduce el id del producto que quiere comprar: ");
				idProd = Integer.valueOf(sc.nextLine());
				
				System.out.println("Introduce la cantidad de productos que quiere comprar: ");
				cantidad = Integer.valueOf(sc.nextLine());
				
				//Se recoge desde la vista
				System.out.println("Precio producto: ");
				precio = Double.valueOf(sc.nextLine());
				
				v.setNombreComprador(nombre);
				v.setCodProducto(idProd);
				v.setCantidad(cantidad);
				v.setPrecio(precio);
				
				boolean validar = vens.nuevaVenta(v);
				
				if(validar) {
					System.out.println("Venta incluida con éxito");
				} else {
					System.out.println("No se ha podido incluir la venta");
				}
				
				break;
				
			case 9:
				System.out.println("----CREAR ARCHIVO XML----");
				
				ps.crearXml();
				
				break;
			case 10:
					System.out.println("----NUEVA VALORACION----");
					//Se recoge desde la vista
					System.out.println("Ingrese su nombre");
					nombre_V= sc.nextLine();
					//Se recoge desde la vista
					System.out.println("Escribe su comentario");
					comentario = sc.nextLine();	
					//Se recoge desde la vista
					System.out.println("seleccione su valoracion");
					valoracion = Integer.valueOf(sc.nextLine());
					//Se recoge desde la vista
					System.out.println("selectione el cod Producto");
					codProducto = Integer.valueOf(sc.nextLine());
					valor = new Valoraciones();
					
					valor.setNombre(nombre_V);
					valor.setComentario(comentario);
					valor.setValoracion(valoracion);
					valor.setCodProducto(codProducto);
					
					System.out.println(vs.crearValoracion(valor,codProducto)? "Se ha creado correctamente" : "Ha ocurrdo un error");

				
					break;
			case 11:
				System.out.println("----MOSTRAR VALORACIONES DE UN PRODUCTO----");
					
				valoraciones = vs.listadoValoraciones();
				
				for(Valoraciones valoration : valoraciones) {
					System.out.println(valoration);
				}
				break;
   
			default:
				break;
			}
		} while (opt != 12);
		
		// Fin
		DBConnection.getInstance().destroyConnection();
		
		sc.close();

	}

}
