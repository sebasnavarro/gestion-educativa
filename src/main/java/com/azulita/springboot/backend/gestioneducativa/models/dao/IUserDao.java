package com.azulita.springboot.backend.gestioneducativa.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.azulita.springboot.backend.gestioneducativa.models.entity.Alumno;


public interface IUserDao extends CrudRepository<Alumno, Long>{

	public Alumno findByUsername(String username);

}
