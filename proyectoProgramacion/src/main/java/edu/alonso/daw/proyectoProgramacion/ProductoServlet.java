package edu.alonso.daw.proyectoProgramacion;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import edu.alonso.daw.proyectoProgramacion.model.Categoria;
import edu.alonso.daw.proyectoProgramacion.model.Producto;
import edu.alonso.daw.proyectoProgramacion.model.Valoraciones;
import edu.alonso.daw.proyectoProgramacion.service.CategoriaService;
import edu.alonso.daw.proyectoProgramacion.service.ProductoService;
import edu.alonso.daw.proyectoProgramacion.service.ValoracionService;
import edu.alonso.daw.proyectoProgramacion.utils.DBConnection;


public class ProductoServlet extends GenericServlet {
    private static final long serialVersionUID = 1L;
    private ProductoService productoService;
    private CategoriaService categoriaService;
    private ValoracionService valoracionService;
    
	private static Logger logger = LogManager.getLogger(ProductoServlet.class);
	
	
	// Problema: navegadores web no soportan nativamente los métodos HTTP PUT y DELETE en los formularios, solo los métodos GET y POST son soportados.
	// Solucuión, redirigir todas las peticinoes desde el doPost.
	

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        productoService = new ProductoService();
        categoriaService = new CategoriaService();
        valoracionService = new ValoracionService();
    }

    
    public String removeUrlPrefix(String uri) {
        int index = uri.lastIndexOf("/") - 1;
        if (index != -1) {
            return uri.substring(index + 1);
        } else {
            return uri;
        }
    }


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        configureResponseHeaders(response);
        
        logger.info("Obtención de productos");
        
        String uri = request.getRequestURI();
        String cleanUri = removeUrlPrefix(uri);

        if ("/listado".equals(cleanUri)) {
        	String idParam = request.getParameter("id");
        	
        	if (idParam != null && !idParam.isEmpty()) {
                int id = Integer.parseInt(idParam);
                
                request.setCharacterEncoding("UTF-8");
                WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
                Set<Producto> allProductos = productoService.obtenerProductosCategoria(id);
                ctx.setVariable("productos", allProductos);
                Set<Categoria> allCategorias = categoriaService.obtenerCategorias();
                ctx.setVariable("categorias", allCategorias);
                
                TemplateEngine engine = configThymeleaf.getTemplateEngine();
                engine.process("principal", ctx, response.getWriter());
            } else {
            	showListado(request, response);
            }
        	
        } else if ("/formulario".equals(cleanUri)) {
            String idParam = request.getParameter("id");
            
            if (idParam != null && !idParam.isEmpty()) {
                long id = Long.parseLong(idParam);
                Producto producto = productoService.obtenerProductosId(id);
                request.setAttribute("producto", producto);
            }
            
            TemplateEngine engine = configThymeleaf.getTemplateEngine();
            WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
            Set<Categoria> allCategorias = categoriaService.obtenerCategorias();
            ctx.setVariable("categorias", allCategorias);
            
            engine.process("formularioProducto", ctx, response.getWriter());
            
        } else if("/mostrar".equals(cleanUri)) {
        	String idParam = request.getParameter("id");
        	
        	if (idParam != null && !idParam.isEmpty()) {
        		long id = Long.parseLong(idParam);
                Producto producto = productoService.obtenerProductosId(id);
                request.setAttribute("producto", producto);
                Set<Valoraciones> valoraciones = valoracionService.listadoValoracionesProducto((int)id);
                request.setAttribute("valoraciones", valoraciones);
        	}
        	
        	TemplateEngine engine = configThymeleaf.getTemplateEngine();
            WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
            engine.process("producto", ctx, response.getWriter());
            
        } else {
            // Redirigir a la página de inicio o a otra opción predeterminada
            response.sendRedirect(request.getContextPath());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        configureResponseHeaders(response);
        
        logger.info("Recibiendo solicitud...");

        String uri = request.getRequestURI();
        String cleanUri = removeUrlPrefix(uri);
        if ("/formulario".equals(cleanUri)) {
            String idParam = request.getParameter("id");

            if (idParam != null && !idParam.isEmpty()) {
                // Si el id no está vacío, se trata de una actualización, entonces reenviamos la solicitud a doPut
                logger.info("Reenviando la solicitud a doPut para actualización...");
                doPut(request, response);
            } else {
                // Si el id está vacío, es una creación
                logger.info("Creación de un producto...");

                String nombre = request.getParameter("nombre");
                String cantidadParam = request.getParameter("cantidad");
                int cantidad = cantidadParam != null && !cantidadParam.isEmpty() ? Integer.parseInt(cantidadParam) : 0;
                String precioParam = request.getParameter("precio");
                double precio = precioParam != null && !precioParam.isEmpty() ? Double.parseDouble(precioParam) : 0;
                String codCategoriaParam = request.getParameter("categoria");
                int codCategoria = codCategoriaParam != null && !codCategoriaParam.isEmpty() ? Integer.parseInt(codCategoriaParam) : 0;
                
                Producto producto = new Producto();
                producto.setNombre(nombre);
                producto.setCantidad(cantidad);
                producto.setPrecio(precio);
                producto.setCategoria(new Categoria(codCategoria));
                productoService.crearProducto(producto);

                
                response.sendRedirect(request.getContextPath() + "/producto/listado");
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        configureResponseHeaders(response);
        logger.info("Actualización de un producto...");

        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            long id = Long.parseLong(idParam);
            Producto producto = productoService.obtenerProductosId(id);
            if (producto != null) {
            	String nombre = request.getParameter("nombre");
                String cantidadParam = request.getParameter("cantidad");
                int cantidad = cantidadParam != null && !cantidadParam.isEmpty() ? Integer.parseInt(cantidadParam) : 0;
                String precioParam = request.getParameter("precio");
                double precio = precioParam != null && !precioParam.isEmpty() ? Double.parseDouble(precioParam) : 0;
                String codCategoriaParam = request.getParameter("categoria");
                int codCategoria = codCategoriaParam != null && !codCategoriaParam.isEmpty() ? Integer.parseInt(codCategoriaParam) : 0;

                producto.setNombre(nombre);
                producto.setCantidad(cantidad);
                producto.setPrecio(precio);
                producto.setCategoria(new Categoria(codCategoria));
                productoService.modificarProducto(producto);

                response.setStatus(HttpServletResponse.SC_OK);
                response.sendRedirect(request.getContextPath() + "/producto/listado");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        configureResponseHeaders(response);
        
        logger.info("Borrado de un prroducto...");

        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            long id = Long.parseLong(idParam);
            Producto producto = productoService.obtenerProductosId(id);
            if (producto != null) {
                productoService.eliminarProductos(id);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void showListado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        request.setCharacterEncoding("UTF-8");
        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        //List<Producto> allProductos = productoService.obtenerProductos();
        Set<Producto> allProductos = productoService.obtenerProductos();
        ctx.setVariable("productos", allProductos);
        TemplateEngine engine = configThymeleaf.getTemplateEngine();
        engine.process("tablaProductos", ctx, response.getWriter());
    }

    private void configureResponseHeaders(HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
    }

    @Override
    public void destroy() {
        super.destroy();
        DBConnection.getInstance().destroyConnection();
    }
}

