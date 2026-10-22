package br.com.lojaesportes.api.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter 
@NoArgsConstructor
public class CategoriaRequestDTO {

    @NotBlank(message = "O nome não da categoria deve ser preenchido.")
    @Size(max = 100, message = "Maximo de caracteres para nome é 100.")
    private String nome;
}