package com.azulita.springboot.backend.gestioneducativa.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
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
	private String dni;
	
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
	
	@Column(unique = true)
	private String email;

	@Column(unique = true, length = 20)
	private String username;
	
	@Column(length = 60)
	private String password;
		
	@Column(name = "alumno_imagen")
	private String url;
	
	private Boolean enabled;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean estado;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="alumnos_roles", joinColumns= @JoinColumn(name="alumnos_id")
	, inverseJoinColumns= @JoinColumn(name="roles_id"),
	uniqueConstraints= {@UniqueConstraint(columnNames = {"alumnos_id","roles_id"})})
	private List<Rol> roles;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
