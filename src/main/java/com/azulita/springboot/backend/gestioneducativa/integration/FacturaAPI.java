package com.azulita.springboot.backend.gestioneducativa.integration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.azulita.springboot.backend.gestioneducativa.models.dto.Factura;

/*import com.azulita.springboot.backend.gestioneducativa.models.entity.Alumno;*/

@Service
public class FacturaAPI {
    private static final String URL_API_FACTURA="http://app-sunat.herokuapp.com/api/factura/";

    private RestTemplate restTemplate;

    public FacturaAPI(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    
   
   
    public ResponseEntity<?> Viewinvoice(Long numeroFactura){
        Map<String, String> params = new HashMap<String, String>();
        Map<String, Object> response = new HashMap<>();
        
        Factura factura = new Factura();
        
        try {
        ResponseEntity<Factura> responseEntity = restTemplate
                .getForEntity(URL_API_FACTURA+'/'+numeroFactura, Factura.class, params);
    
                factura.setNumeroFactura(responseEntity.getBody().getNumeroFactura());
                factura.setFechaEmision(responseEntity.getBody().getFechaEmision());
                factura.setDniReceptor(responseEntity.getBody().getDniReceptor());
                factura.setMontoTotal(responseEntity.getBody().getMontoTotal());
                factura.setNumRUCEmisor(responseEntity.getBody().getNumRUCEmisor());
                factura.setIdFactura(responseEntity.getBody().getIdFactura());
        
        
        }catch(HttpStatusCodeException e) {
            response.put("mensaje", "Error: Se ha producido un error al intentar consumir el servicio de la factura");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            
        }
        return new ResponseEntity<>(factura , HttpStatus.OK);
    } 
}
