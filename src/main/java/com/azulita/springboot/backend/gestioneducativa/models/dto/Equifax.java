package com.azulita.springboot.backend.gestioneducativa.models.dto;


import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equifax {
    public int id;
    public String dni;
    public String nombres;
    public String apellidos;
    public List<DatosConsulta> datosConsultas;
}
