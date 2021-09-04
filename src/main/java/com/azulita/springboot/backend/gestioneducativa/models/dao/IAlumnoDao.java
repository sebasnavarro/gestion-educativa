package com.azulita.springboot.backend.gestioneducativa.models.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.azulita.springboot.backend.gestioneducativa.models.entity.Alumno;

/**
 * @author SebasNavarro
 *
 */
@Repository
public interface IAlumnoDao extends JpaRepository<Alumno, Long>{

	public List<Alumno> findByEstadoIs(boolean isState);
	
	@Modifying
	@Query("update Alumno a set a.estado=true where a.id=?1")
	public void softDelete(Long id);
}
