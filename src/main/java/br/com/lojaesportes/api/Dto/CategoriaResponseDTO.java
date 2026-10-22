package br.com.lojaesportes.api.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategoriaResponseDTO {
    
    private Long id;
    private String nome;

}