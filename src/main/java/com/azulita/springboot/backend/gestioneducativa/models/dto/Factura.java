package com.azulita.springboot.backend.gestioneducativa.models.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura {

    private int idFactura;
    private BigInteger numeroFactura;
    private BigInteger numRUCEmisor;
    private int dniReceptor;
    private Date fechaEmision;
    private BigDecimal montoTotal;
    
}
