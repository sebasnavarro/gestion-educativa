package com.azulita.springboot.backend.gestioneducativa.models.services;

import com.azulita.springboot.backend.gestioneducativa.models.entity.Alumno;

public interface IUserService {
	public Alumno findByUsername(String username);

}
