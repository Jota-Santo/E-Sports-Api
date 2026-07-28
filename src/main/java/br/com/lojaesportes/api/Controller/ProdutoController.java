package br.com.lojaesportes.api.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.lojaesportes.api.Dto.ProdutoRequestDTO;
import br.com.lojaesportes.api.Dto.ProdutoResponseDTO;
import br.com.lojaesportes.api.Model.Produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.lojaesportes.api.Service.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {  // CONTROLLER RECEBE AS OPEÇÂOES E PEDIDOS E CHAMA O SERVICE
    
    @Autowired // Injeta automaticamente uma dependência gerenciada pelo Spring, como um Repository.
    private ProdutoService service;

    @GetMapping
    public List<ProdutoResponseDTO> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO buscaPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PostMapping
    public ProdutoResponseDTO salvar(@Valid @RequestBody ProdutoRequestDTO dto){
        return service.salvar(dto);
    }

    @PutMapping("/{id}")
    public ProdutoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoRequestDTO dto){
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
