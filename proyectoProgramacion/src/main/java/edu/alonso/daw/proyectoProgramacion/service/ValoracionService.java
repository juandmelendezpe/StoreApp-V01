package edu.alonso.daw.proyectoProgramacion.service;
import java.util.Set;


import edu.alonso.daw.proyectoProgramacion.repository.ValoracionRepo;
import edu.alonso.daw.proyectoProgramacion.model.Valoraciones;

public class ValoracionService {
	
	/**
	 * 
	 * @return
	 */
	public Set<Valoraciones>listadoValoraciones(){
		
		return ValoracionRepo.getInstance().getAll();
		
	}
	
	/**
	 * 
	 * @param idProducto
	 * @return
	 */
	public Set<Valoraciones>listadoValoracionesProducto(int idProducto){
		
		return ValoracionRepo.getInstance().getValoracionId(idProducto);
		
	}

	public boolean crearValoracion(Valoraciones v, int idProducto) {
		
		Set<Valoraciones> valoraciones = ValoracionRepo.getInstance().getAll();
		long idV = 1;
		for (Valoraciones valoration : valoraciones) {
			if(valoration.getIdValoracion() > idV) {
				idV = valoration.getIdValoracion();
			}
		}
		v.setIdValoracion(++idV);
		return ValoracionRepo.getInstance().crearValoracion(v,idProducto);
	}
	
		
	}
