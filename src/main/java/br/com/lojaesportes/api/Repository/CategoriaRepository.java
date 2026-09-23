package br.com.lojaesportes.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lojaesportes.api.Model.Categoria;
import java.util.Optional;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    Optional<Categoria> findByNome(String nome);
}