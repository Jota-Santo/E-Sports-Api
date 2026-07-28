package br.com.lojaesportes.api.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // A anotação @Entity informa ao Spring que uma classe representa uma tabela do banco de dados.
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    
    @Id // @ID define a chave primaria
    @GeneratedValue( strategy = GenerationType.IDENTITY) // @Generated Value Faz o banco gerar o ID automaticamente.
    private Long id;

    @Column(nullable = false, unique = true) // Unique Usada para não permitir Cadastro de Categorias Iguais.
    private String nome;

    @JsonManagedReference // Impede que ocorra uma repetição infinita (Classe pai, passa para classe filho) ou "Este lado será enviado no JSON."
    @OneToMany(mappedBy = "categoria") // Uma Categoria pertence a varios produtos
    private List<Produto> produtos;
}
