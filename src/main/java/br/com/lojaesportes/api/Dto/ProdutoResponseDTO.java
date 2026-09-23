package br.com.lojaesportes.api.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer estoque;
    private String marca;
    private String categoria;
    private Boolean ativo;
    
}
