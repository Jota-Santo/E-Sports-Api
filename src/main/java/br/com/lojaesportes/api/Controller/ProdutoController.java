package br.com.lojaesportes.api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lojaesportes.api.Dto.ProdutoRequestDTO;
import br.com.lojaesportes.api.Dto.ProdutoResponseDTO;
import br.com.lojaesportes.api.Service.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {  // CONTROLLER RECEBE AS OPEÇÂOES E PEDIDOS E CHAMA O SERVICE
    
    @Autowired // Injeta automaticamente uma dependência gerenciada pelo Spring, como um Repository.
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscaPorId(@PathVariable Long id){
        ProdutoResponseDTO produto = service.buscarPorId(id);

        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvar(@Valid @RequestBody ProdutoRequestDTO dto){
        ProdutoResponseDTO produto = service.salvar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoRequestDTO dto){
        ProdutoResponseDTO produto = service.atualizar(id, dto);
        
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}