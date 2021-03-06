package com.azulita.springboot.backend.gestioneducativa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

import com.azulita.springboot.backend.gestioneducativa.integration.CrmAPI;
import com.azulita.springboot.backend.gestioneducativa.integration.ReniecAPI;
import com.azulita.springboot.backend.gestioneducativa.models.entity.Alumno;
import com.azulita.springboot.backend.gestioneducativa.models.services.IAlumnoService;

/**
 * @author SebasNavarro
 *
 */
@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api/v1")
public class AlumnoRestController {

	private final IAlumnoService alumnoService;
	private final ReniecAPI reniecAPI;
	private final CrmAPI crmAPI;

	public AlumnoRestController(IAlumnoService alumnoService, ReniecAPI reniecAPI, CrmAPI crmAPI) {
		this.alumnoService = alumnoService;
		this.reniecAPI = reniecAPI;
		this.crmAPI = crmAPI;
	}

	@Secured({"ROLE_ADMIN"})	
	@PostMapping("/alumnos")
	public ResponseEntity<?> create(@Validated @RequestBody Alumno alumno, BindingResult result) {

		Map<String, Object> response = new HashMap<>();
		
		
		Alumno alumnoNew = null;
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try{
			ResponseEntity<?> resReniec = reniecAPI.send(alumno);
			System.out.print("STATUS DE LA RENIEC: "+resReniec.getStatusCode());
			if(resReniec.getStatusCode()==HttpStatus.OK) {
				Alumno alumnoReniec = (Alumno) resReniec.getBody();
				alumnoNew = alumnoService.save(alumnoReniec);
				System.out.println("RENIEC: "+ alumnoNew);

				
			}else {
				ResponseEntity<?> resCrm = crmAPI.send(alumno);
				System.out.print("STATUS DEl CRM: "+resCrm.getStatusCode());

				if(resCrm.getStatusCode()==HttpStatus.OK) {
					Alumno alumnoCrmAPI = (Alumno) resCrm.getBody();
					alumnoNew = alumnoService.save(alumnoCrmAPI);
				}else {
					response.put("mensaje", "Error: Servicio externos ca??dos");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
				
				}
				System.out.println("CRM: "+alumnoNew);
			}
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error: Se ha producido un error al registrar al alumno");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "El alumno ha sido registrado con ??xito!");
		response.put("alumno", alumnoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured({"ROLE_ADMIN","ROLE_USER"})	
	@GetMapping("/alumnos/page/{page}")
	public ResponseEntity<?> findAll(@PathVariable Integer page, @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,  @RequestParam(value = "isState", required = false, defaultValue = "false") boolean isState) {
		Page<Alumno> alumnos = null;
		Map<String, Object> response = new HashMap<>();

		try{
			alumnos = alumnoService.findAll(PageRequest.of(page, size), isState);

		}catch (DataAccessException e){
			response.put("mensaje", "Error: Se ha producido un error al intentar listar los alumnos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(alumnos, HttpStatus.OK);
	}

	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/alumnos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Alumno alumno = null;
		Map<String, Object> response = new HashMap<>();

		try {
			alumno = alumnoService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error: Se ha producido un error al intentar buscar al alumno");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (alumno == null) {
			response.put("mensaje", "Error: El ID: ".concat(id.toString().concat(" no existe en la tabla alumno!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
	}


	@Secured({"ROLE_ADMIN"})	
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
			alumnoActual.setCodigo(alumno.getCodigo());
			alumnoActual.setDni(alumno.getDni());
			ResponseEntity<?> resReniec = reniecAPI.send(alumnoActual);
			System.out.print("STATUS DE LA RENIEC: "+resReniec.getStatusCode());
			if(resReniec.getStatusCode()==HttpStatus.OK) {
				Alumno alumnoReniec = (Alumno) resReniec.getBody();
				alumnoUpdated = alumnoService.save(alumnoReniec);
				System.out.println("RENIEC: "+ alumnoUpdated);

			}else {
				ResponseEntity<?> resCrm = crmAPI.send(alumnoActual);
				System.out.print("STATUS DEl CRM: "+resCrm.getStatusCode());

				if(resCrm.getStatusCode()==HttpStatus.OK) {
					Alumno alumnoCrmAPI = (Alumno) resCrm.getBody();
					alumnoUpdated= alumnoService.save(alumnoCrmAPI);
				}else {
					response.put("mensaje", "Error: Servicio externos ca??dos");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
				
				}
				System.out.println("CRM: "+alumnoUpdated);
			}
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error: No se pudo modificar los datos del alumno.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El alumno ha sido actualizado con ??xito!");
		response.put("alumno", alumnoUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})	
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
		
		response.put("mensaje", "El alumno ha sido eliminado de la base de datos con ??xito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
