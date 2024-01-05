package edu.alonso.daw.proyectoProgramacion.dao;
import java.util.Set;

import edu.alonso.daw.proyectoProgramacion.model.Valoraciones;

public interface ValoracionDAO {

    Set<Valoraciones> getAll();
    
    
    boolean crearValoracion(Valoraciones valoraciones,int idProducto);


	Set<Valoraciones> getValoracionId(int idProducto);
}