package com.azulita.springboot.backend.gestioneducativa.integration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.azulita.springboot.backend.gestioneducativa.models.dto.AlumnoDTO;
import com.azulita.springboot.backend.gestioneducativa.models.entity.Alumno;

@Service
public class CrmAPI {
    private static final String URL_API_CRM="https://appi-crm.herokuapp.com/api/alumnos/";

    private RestTemplate restTemplate;
    
    public CrmAPI(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    
    public ResponseEntity<?> send(Alumno alumno){
    	Map<String, String> params = new HashMap<String, String>();
		Map<String, Object> response = new HashMap<>();

    	try {
    	ResponseEntity<AlumnoDTO> responseEntity = restTemplate
                .getForEntity(URL_API_CRM+'/'+alumno.getDni(), AlumnoDTO.class, params);
    
        alumno.setNombres(responseEntity.getBody().getNombre());
        alumno.setApellidos(responseEntity.getBody().getApellidos());
        alumno.setFechaNacimiento(responseEntity.getBody().getFechaCum());
        alumno.setEmail(responseEntity.getBody().getEmail());
        alumno.setSexo(responseEntity.getBody().getSexo());
        
    	}catch(HttpStatusCodeException e) {
    		response.put("mensaje", "Error: Se ha producido un error al intentar consumir el servicio de la reniec");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    		
    	}
        return new ResponseEntity<>(alumno, HttpStatus.OK);
    }

}
