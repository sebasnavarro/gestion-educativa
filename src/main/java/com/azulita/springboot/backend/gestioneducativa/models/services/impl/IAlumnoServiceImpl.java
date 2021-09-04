package com.azulita.springboot.backend.gestioneducativa.models.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.azulita.springboot.backend.gestioneducativa.models.dao.IAlumnoDao;
import com.azulita.springboot.backend.gestioneducativa.models.entity.Alumno;
import com.azulita.springboot.backend.gestioneducativa.models.services.IAlumnoService;

/**
 * @author SebasNavarro
 *
 */
@Service
public class IAlumnoServiceImpl implements IAlumnoService{

	private final IAlumnoDao alumnoDao;
	
	public IAlumnoServiceImpl(IAlumnoDao alumnoDao){
		this.alumnoDao = alumnoDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Alumno> findAll(boolean isState) {
		return alumnoDao.findByEstadoIs(isState);
	}

	@Override
	@Transactional(readOnly=true)
	public Alumno findById(Long id) {
		return alumnoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Alumno save(Alumno alumno) {
		return alumnoDao.save(alumno);
	}

	@Override
	@Transactional
	public void softDelete(Long id) {
		alumnoDao.softDelete(id);
	}

}
