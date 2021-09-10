package com.azulita.springboot.backend.gestioneducativa.models.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name="cursos")

public class Cursos implements Serializable{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Long id;
	
	@Column(length = 12, nullable = false)
	private String codigo;
	
	@Column(length = 64, nullable = false)
	private String nombre;
	
    @Column(length = 64, nullable = false)
	private int idhorario;

    @Column(length = 64, nullable = false)
	private int iddocente;

	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean estado;
	
	@ManyToOne
    @JoinColumn(name="iddocente", nullable=false)
    private Docente docente;

    public Cursos() {}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
