package br.com.lojaesportes.api.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutoRequestDTO {

    @NotBlank(message = "Nome deve ser Preenchido.")
    @Size(max = 100, message = "Maximo de caracteres para nome é 100.")
    private String nome;

    @Size(max = 500)
    private String descricao;

    @NotNull(message = "Preço deve ser preenchido")
    @Positive
    private Double preco;

    @NotNull(message = "Estoque deve ser preenchido.")
    @PositiveOrZero
    private Integer estoque;

    @Size(max = 80)
    private String marca;

    @NotNull
    private Long categoriaId;

    @NotNull
    private Boolean ativo;
    
}
