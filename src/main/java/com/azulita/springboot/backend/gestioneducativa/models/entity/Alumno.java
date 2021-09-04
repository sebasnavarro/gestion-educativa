package com.azulita.springboot.backend.gestioneducativa.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author SebasNavarro
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="alumnos")
public class Alumno implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Long id;
	
	@Column(length = 12, nullable = false)
	private String codigo;
	
	@Column(length = 64, nullable = false)
	private String nombres;
	
	@Column(length = 64, nullable = false)
	private String apellidos;
	
	@Column(nullable = false)
	private char sexo;
	
	@Column(name = "fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	@Column(name = "alumno_imagen")
	private String url;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean estado;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
