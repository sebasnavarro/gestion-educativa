package com.azulita.springboot.backend.gestioneducativa.models.services;

import java.util.List;

import com.azulita.springboot.backend.gestioneducativa.models.entity.Alumno;

/**
 * @author SebasNavarro
 *
 */
public interface IAlumnoService {

	public List<Alumno> findAll(boolean isState);
	
	public Alumno findById(Long id);

	public Alumno save(Alumno alumno);

	public void softDelete(Long id);
	
}
