package com.azulita.springboot.backend.gestioneducativa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.azulita.springboot.backend.gestioneducativa.models.entity.Alumno;
import com.azulita.springboot.backend.gestioneducativa.models.services.IAlumnoService;

/**
 * @author SebasNavarro
 *
 */
@CrossOrigin(origins = { "http://localhost:3000", "*" })
@RestController
@RequestMapping("/api/v1")
public class AlumnoRestController {

	private final IAlumnoService alumnoService;

	public AlumnoRestController(IAlumnoService alumnoService) {
		this.alumnoService = alumnoService;
	}

	@PostMapping("/alumnos")
	public ResponseEntity<?> create(@Validated @RequestBody Alumno alumno, BindingResult result) {

		Map<String, Object> response = new HashMap<>();
		Alumno alumnoNew = null;
		if (result.hasErrors()) 
		{
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}try{
			alumnoNew = alumnoService.save(alumno);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error: Se ha producido un error al intentar registrar al alumno");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El alumno ha sido registrado con éxito!");
		response.put("alumno", alumnoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/alumnos")
	public ResponseEntity<?> findAll(
			@RequestParam(value = "isState", required = false, defaultValue = "false") boolean isState) {
		List<Alumno> alumnos = null;
		Map<String, Object> response = new HashMap<>();

		try{
			alumnos = alumnoService.findAll(isState);

		}catch (DataAccessException e){
			response.put("mensaje", "Error: Se ha producido un error al intentar listar los alumnos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(alumnos, HttpStatus.OK);
	}

	@PutMapping("/alumnos/{id}")
	public ResponseEntity<?> update(@Validated @RequestBody Alumno alumno, BindingResult result,
			@PathVariable Long id) {

		Alumno alumnoActual = alumnoService.findById(id);
		Alumno alumnoUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (alumnoActual == null) {
			response.put("mensaje", "Error: Al intentar modificar al alumno con el ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			alumnoActual.setNombres(alumno.getNombres());
			alumnoActual.setApellidos(alumno.getApellidos());
			alumnoActual.setCodigo(alumno.getCodigo());
			alumnoActual.setSexo(alumno.getSexo());
			alumnoActual.setFechaNacimiento(alumno.getFechaNacimiento());
			alumnoUpdated = alumnoService.save(alumnoActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error: No se pudo modificar los datos del alumno.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El alumno ha sido actualizado con éxito!");
		response.put("alumno", alumnoUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/alumnos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {			
		    alumnoService.softDelete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error: Al momento de eliminar al alumno de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El alumno ha sido eliminado de la base de datos con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
