package com.azulita.springboot.backend.gestioneducativa.models.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatosConsulta {
    public int id;
    public String entidad;
    public int deuda;
    public String fecha;
    public int calificacion;
    public int diasVencidas;
    
}
