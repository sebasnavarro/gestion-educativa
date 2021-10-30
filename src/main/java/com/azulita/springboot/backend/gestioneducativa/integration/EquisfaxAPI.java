package com.azulita.springboot.backend.gestioneducativa.integration;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.azulita.springboot.backend.gestioneducativa.models.dto.Cliente;
import com.azulita.springboot.backend.gestioneducativa.models.entity.Alumno;

@Service
public class EquisfaxAPI {

    private static final String URL_API_EQUISFAX="https://app-equifax.herokuapp.com/cliente/registro";

	    private RestTemplate restTemplate;

	    public EquisfaxAPI(RestTemplate restTemplate){
	        this.restTemplate = restTemplate;
	    }
	    
       
	   
	    public ResponseEntity<?> send(Alumno alumno){
	    	Map<String, String> params = new HashMap<String, String>();
			Map<String, Object> response = new HashMap<>();

	    	try {
	    	ResponseEntity<Cliente> responseEntity = restTemplate
                    .getForEntity(URL_API_EQUISFAX+'/'+alumno.getUsername(), Cliente.class, params);
	    
					alumno.setNombres(responseEntity.getBody().getFirstName());
					alumno.setApellidos(responseEntity.getBody().getLastName());
					alumno.setPhone(responseEntity.getBody().getPhone());
					alumno.setEmail(responseEntity.getBody().getEmail());
	        
	    	}catch(HttpStatusCodeException e) {
	    		response.put("mensaje", "Error: Se ha producido un error al intentar consumir el servicio de la equisfax");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	    		
	    	}
	        return new ResponseEntity<>(alumno, HttpStatus.OK);
	    }
    
}
