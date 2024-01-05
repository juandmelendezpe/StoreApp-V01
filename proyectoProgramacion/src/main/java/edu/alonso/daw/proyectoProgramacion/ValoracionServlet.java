package edu.alonso.daw.proyectoProgramacion;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import edu.alonso.daw.proyectoProgramacion.model.Producto;
import edu.alonso.daw.proyectoProgramacion.model.Valoraciones;
import edu.alonso.daw.proyectoProgramacion.service.ProductoService;
import edu.alonso.daw.proyectoProgramacion.service.ValoracionService;
import edu.alonso.daw.proyectoProgramacion.utils.DBConnection;


public class ValoracionServlet extends GenericServlet {
    private static final long serialVersionUID = 1L;
    private ProductoService productoService;
    private ValoracionService valoracionService;

    
	private static Logger logger = LogManager.getLogger(ValoracionServlet.class);
	
	
	// Problema: navegadores web no soportan nativamente los métodos HTTP PUT y DELETE en los formularios, solo los métodos GET y POST son soportados.
	// Solucuión, redirigir todas las peticinoes desde el doPost.
	

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        productoService = new ProductoService();
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

        if ("/valorar".equals(cleanUri)) {
            String idParam = request.getParameter("id");
            
            long id = Long.parseLong(idParam);
            Producto producto = productoService.obtenerProductosId(id);
            request.setAttribute("producto", producto);
            WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
            TemplateEngine engine = configThymeleaf.getTemplateEngine();
            engine.process("valoraciones", ctx, response.getWriter());
            
            
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
        if ("/crear".equals(cleanUri)) {
        		String idParam = request.getParameter("id");
        		int id = idParam != null && !idParam.isEmpty() ? Integer.parseInt(idParam) : 0;
                String nombre = request.getParameter("nombreCliente");
                String comentario = request.getParameter("comentario");
        		String estrellasParam = request.getParameter("id");
        		int estrellas = estrellasParam != null && !idParam.isEmpty() ? Integer.parseInt(estrellasParam) : 0;
                
                Valoraciones valoracion = new Valoraciones();
                
                valoracion.setCodProducto(id);
                valoracion.setNombre(nombre);
                valoracion.setComentario(comentario);
                valoracion.setValoracion(estrellas);
                
                valoracionService.crearValoracion(valoracion,id);
                
               response.sendRedirect(request.getContextPath() + "/producto/mostrar?id=" + id);

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

