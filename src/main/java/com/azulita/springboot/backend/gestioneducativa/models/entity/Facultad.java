package com.azulita.springboot.backend.gestioneducativa.models.entity;

import java.io.Serializable;


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
 * @author ErickPalacios
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="facultad")

public class Facultad implements Serializable{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Long id;
	
	@Column(length = 5, nullable = false)
	private String idfacultad;
	
	@Column(length = 12, nullable = false)
	private String nombre;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean estado;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
