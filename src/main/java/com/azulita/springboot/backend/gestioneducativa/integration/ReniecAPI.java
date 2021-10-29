package com.azulita.springboot.backend.gestioneducativa.integration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.azulita.springboot.backend.gestioneducativa.models.dto.Persona;
import com.azulita.springboot.backend.gestioneducativa.models.entity.Alumno;

@Service
public class ReniecAPI {
	
	 private static final String URL_API_RENIEC="https://reniec-api-progra.herokuapp.com/api/persona/";

	    private RestTemplate restTemplate;

	    public ReniecAPI(RestTemplate restTemplate){
	        this.restTemplate = restTemplate;
	    }
	    
       
	   
	    public ResponseEntity<?> send(Alumno alumno){
	    	Map<String, String> params = new HashMap<String, String>();
			Map<String, Object> response = new HashMap<>();

	    	try {
	    	ResponseEntity<Persona> responseEntity = restTemplate
                    .getForEntity(URL_API_RENIEC+'/'+alumno.getUsername(), Persona.class, params);
	    
	        alumno.setNombres(responseEntity.getBody().getNombre());
	        alumno.setApellidos(responseEntity.getBody().getApellido());
	        alumno.setFechaNacimiento(responseEntity.getBody().getFechaNacimiento());
	        alumno.setEmail(responseEntity.getBody().getNombre()+'_'+responseEntity.getBody().getApellido()+"@usmp.pe");
	        
	    	}catch(HttpStatusCodeException e) {
	    		response.put("mensaje", "Error: Se ha producido un error al intentar consumir el servicio de la reniec");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	    		
	    	}
	        return new ResponseEntity<>(alumno, HttpStatus.OK);
	    }

}
