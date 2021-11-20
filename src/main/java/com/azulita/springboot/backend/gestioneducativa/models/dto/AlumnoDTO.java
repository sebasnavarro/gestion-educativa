package com.azulita.springboot.backend.gestioneducativa.models.dto;

import java.util.Date;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlumnoDTO {
    public String id;
    public String dni;
    public String carrera;
    public String nombre;
    public String apellidos;
    public String sexo;
    public String email;
    public String celular;
    public Date fechaCum;
}
