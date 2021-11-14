package com.azulita.springboot.backend.gestioneducativa.controller;

import java.util.HashMap;

import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.azulita.springboot.backend.gestioneducativa.integration.EquifaxAPI;



/**
 * @author ErickPalacios
 *
 */
@CrossOrigin(origins = { "http://localhost:3000", "*" })
@RestController
@RequestMapping("/api/v1")
public class EquifaxRestController {
    private final EquifaxAPI equifaxAPI;

	public EquifaxRestController(EquifaxAPI equifaxAPI) {
		
		this.equifaxAPI = equifaxAPI;
	}

	@Secured({"ROLE_ADMIN","ROLE_USER"})	
	@GetMapping("/equifax/{dni}")
	public ResponseEntity<?> findAll(@PathVariable String dni) {
		Map<String, Object> response = new HashMap<>();
		
		ResponseEntity<?> respuesta = equifaxAPI.Viewinvoice(dni );
		
		try{
			
			if(respuesta.getStatusCode()==HttpStatus.NOT_FOUND) {
				
				
			
				response.put("mensaje", "Error: No se encontraron datos en la bd EQUIFAX");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error: Error al monstrar datos de Equifax");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("equifax", respuesta);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
