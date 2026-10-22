package br.com.lojaesportes.api.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produto")
@Getter
@Setter
@NoArgsConstructor
public class Produto {
    
    // FUNÇÕES DOS @
    @Id // Define a chave primária.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o ID automaticamente.
    private Long id;

    @NotBlank (message = "O nome é obrigatório.")
    @Size(max = 100, message = "O maximo de caracteres é 100.")
    @Column(nullable = false, length = 100) //Configura uma coluna (nome, tamanho, obrigatório escrever e etc.).
    private String nome;

    @Column(length = 500)
    @Size(max = 500 , message = "A descrição pode ter um máximo de 500 caracteres.")
    private String descricao;

    @jakarta.validation.constraints.NotNull(message = "O preço é obrigatório.")
    @Positive(message = "O preço deve ser maior que zero.")
    @Column(nullable = false)
    private Double preco;

    @jakarta.validation.constraints.NotNull(message = "O estoque é obrigatorio")
    @PositiveOrZero(message = "O estoque não pode ser negativo.")
    @Column(nullable = false)
    private Integer estoque;

    @Size(max = 80, message = "A marca pode ter no maximo 80 caracteres.")
    @Column(length = 80)
    private String marca;

    @jakarta.validation.constraints.NotNull(message = "A categoria é obrigatoria.")
    @JsonBackReference // Impede de ocorrer uma repetição infinita (Filho da Relação impedi de retorna a classe Pai) ou "Quando chegar aqui, pare."
    @ManyToOne // Muitos produtos pertencem a uma categoria. ///// e Temos o @OneToMany = Um registro possui vários outros.
    @JoinColumn(name = "categoria_id")  // Define a chave estrangeira. @JoinColumn
    private Categoria categoria;

    @jakarta.validation.constraints.NotNull(message = "Informe se o produto está ativo.")
    @Column(nullable = false)
    private Boolean ativo;
}