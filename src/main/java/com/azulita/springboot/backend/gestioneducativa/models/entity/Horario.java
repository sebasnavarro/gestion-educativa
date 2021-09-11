package com.azulita.springboot.backend.gestioneducativa.models.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


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
@Table(name="horario")

public class Horario implements Serializable{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Long id;
	
	@Column(length = 13, nullable = false)
	private String hora;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean estado;
	
	@OneToMany(mappedBy="horario")
    private Set<Cursos> cursos;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
