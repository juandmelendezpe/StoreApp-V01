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
import edu.alonso.daw.proyectoProgramacion.model.Venta;
import edu.alonso.daw.proyectoProgramacion.service.CategoriaService;
import edu.alonso.daw.proyectoProgramacion.service.ProductoService;
import edu.alonso.daw.proyectoProgramacion.service.VentaService;
import edu.alonso.daw.proyectoProgramacion.utils.DBConnection;


public class VentaServlet extends GenericServlet {
    private static final long serialVersionUID = 1L;
    private ProductoService productoService;
    private VentaService ventaService;
    private CategoriaService categoriaService;
    
	private static Logger logger = LogManager.getLogger(VentaServlet.class);
	
	
	// Problema: navegadores web no soportan nativamente los métodos HTTP PUT y DELETE en los formularios, solo los métodos GET y POST son soportados.
	// Solucuión, redirigir todas las peticinoes desde el doPost.
	

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        productoService = new ProductoService();
        ventaService = new VentaService();
        categoriaService = new CategoriaService();
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
        	
            request.setCharacterEncoding("UTF-8");
            WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
            Set<Venta> allVentas = ventaService.listadoVentas();
            ctx.setVariable("ventas", allVentas);
            TemplateEngine engine = configThymeleaf.getTemplateEngine();
            engine.process("tablaVentas", ctx, response.getWriter());
                
        
        
        } else if ("/compra".equals(cleanUri)) {
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
            
            engine.process("compraProducto", ctx, response.getWriter());
            
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
        if ("/compra".equals(cleanUri)) {
        		String idParam = request.getParameter("id");
        		int id = idParam != null && !idParam.isEmpty() ? Integer.parseInt(idParam) : 0;
                String nombre = request.getParameter("nombre");
                String cantidadParam = request.getParameter("cantidad");
                int cantidad = cantidadParam != null && !cantidadParam.isEmpty() ? Integer.parseInt(cantidadParam) : 0;

              
                
                Venta venta = new Venta();

                venta.setCodProducto(id);
                venta.setNombreComprador(nombre);
                venta.setCantidad(cantidad);
                
                if(ventaService.nuevaVenta(venta)) {
                	response.sendRedirect(request.getContextPath() + "/");
                } else {
                	response.sendRedirect(request.getContextPath() + "/venta/compra?id=" + id);
                }
                
            
        }
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

