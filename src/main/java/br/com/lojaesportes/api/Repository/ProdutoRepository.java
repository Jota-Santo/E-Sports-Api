package br.com.lojaesportes.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import br.com.lojaesportes.api.Model.Produto;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByNome(String nome);
}
