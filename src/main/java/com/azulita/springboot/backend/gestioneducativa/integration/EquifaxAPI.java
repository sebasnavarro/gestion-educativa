package com.azulita.springboot.backend.gestioneducativa.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.azulita.springboot.backend.gestioneducativa.models.dto.DatosConsulta;
import com.azulita.springboot.backend.gestioneducativa.models.dto.Equifax;


@Service
public class EquifaxAPI {
    private static final String URL_API_EQUISFAX="https://app-equifax.herokuapp.com/api/equifax/";

    private RestTemplate restTemplate;

    public EquifaxAPI(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    
   
   
    public ResponseEntity<?> Viewinvoice(String dni){
        Map<String, String> params = new HashMap<String, String>();
        Map<String, Object> response = new HashMap<>();
        
        Equifax equifax = new Equifax();
        
        try {
        ResponseEntity<Equifax> responseEntity = restTemplate
                .getForEntity(URL_API_EQUISFAX+'/'+dni, Equifax.class, params);
    
                equifax.setDni(responseEntity.getBody().getDni());
                
                equifax.setNombres(responseEntity.getBody().getNombres());
                equifax.setId(responseEntity.getBody().getId());
                equifax.setApellidos(responseEntity.getBody().getApellidos());
                
                List<DatosConsulta> datosConsultas = new ArrayList<DatosConsulta>();
                for(int x=0;x<responseEntity.getBody().getDatosConsultas().size();x++){
                    DatosConsulta d = new DatosConsulta();
                    d.setId(responseEntity.getBody().getDatosConsultas().get(x).getId());
                    d.setEntidad(responseEntity.getBody().getDatosConsultas().get(x).getEntidad());
                    d.setDeuda(responseEntity.getBody().getDatosConsultas().get(x).getDeuda());
                    d.setFecha(responseEntity.getBody().getDatosConsultas().get(x).getFecha());
                    d.setCalificacion(responseEntity.getBody().getDatosConsultas().get(x).getCalificacion());
                    d.setDiasVencidas(responseEntity.getBody().getDatosConsultas().get(x).getDiasVencidas());
                    System.out.println(d.toString());
                    datosConsultas.add(d);

                }
                equifax.setDatosConsultas(datosConsultas);
        
        
        }catch(HttpStatusCodeException e) {
            response.put("mensaje", "Error: Se ha producido un error al intentar consumir el servicio de la factura");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            
        }
        return new ResponseEntity<>(equifax , HttpStatus.OK);
    } 
}
