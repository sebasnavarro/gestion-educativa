package com.azulita.springboot.backend.gestioneducativa.models.dto;



import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;   
     
    
}
