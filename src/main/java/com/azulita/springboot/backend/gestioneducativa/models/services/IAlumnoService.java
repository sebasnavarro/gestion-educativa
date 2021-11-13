package com.azulita.springboot.backend.gestioneducativa.models.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.azulita.springboot.backend.gestioneducativa.models.entity.Alumno;

/**
 * @author SebasNavarro
 *
 */
public interface IAlumnoService {

	public Page<Alumno> findAll(Pageable page, boolean isState);
	
	public Alumno findById(Long id);

	public Alumno save(Alumno alumno);

	public void softDelete(Long id);
	
}
