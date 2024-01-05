package edu.alonso.daw.proyectoProgramacion.service;

import java.util.Set;

import edu.alonso.daw.proyectoProgramacion.model.Categoria;
import edu.alonso.daw.proyectoProgramacion.repository.CategoriaRepository;

public class CategoriaService {

	public Set<Categoria> obtenerCategorias() {
		return CategoriaRepository.getInstace().getCategorias();
	}
	
}
