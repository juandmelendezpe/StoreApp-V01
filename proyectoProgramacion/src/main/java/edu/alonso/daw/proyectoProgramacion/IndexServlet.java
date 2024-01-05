package edu.alonso.daw.proyectoProgramacion;

import java.io.IOException;
import java.util.Date;
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
import edu.alonso.daw.proyectoProgramacion.service.CategoriaService;
import edu.alonso.daw.proyectoProgramacion.service.ProductoService;


public class IndexServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;
	private ProductoService productoService;
	private CategoriaService categoriaService;
	
	private static Logger logger = LogManager.getLogger(IndexServlet.class);

    
	@Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        productoService = new ProductoService();
        categoriaService = new CategoriaService();
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// Para ejecutar:
    	// http://localhost:8080/proyectoProgramacion/
    	
    	// revisar https://www.codejava.net/ides/eclipse/how-to-create-java-web-project-with-maven-in-eclipse
    	
    	
    	// Primero invoco al doGet del padre.
    	super.doGet(request, response);
    	
    	showListado(request, response);

        
    }
    
    private void showListado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        request.setCharacterEncoding("UTF-8");
        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        //List<Producto> allProductos = productoService.obtenerProductos();
        Set<Producto> allProductos = productoService.obtenerProductos();
        ctx.setVariable("productos", allProductos);
        
        Set<Categoria> allCategorias = categoriaService.obtenerCategorias();
        ctx.setVariable("categorias", allCategorias);
        
        TemplateEngine engine = configThymeleaf.getTemplateEngine();
        engine.process("principal", ctx, response.getWriter());
    }
}
