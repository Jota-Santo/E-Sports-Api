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

import br.com.lojaesportes.api.Dto.CategoriaRequestDTO;
import br.com.lojaesportes.api.Dto.CategoriaResponseDTO;
import br.com.lojaesportes.api.Service.CategoriaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    
    @Autowired // Injeta automaticamente uma dependência gerenciada pelo Spring, como um Repository.
    private CategoriaService service;

    @GetMapping // ConSulta dados Mapeia requisições HTTP GET, usadas para consultar dados.
    public List<CategoriaResponseDTO> listar(){
        return service.listarTodos();
    }

    @GetMapping("/{id}") // ConSulta dados Mapeia requisições HTTP GET, usadas para consultar dados.
    public CategoriaResponseDTO buscarPorId(@PathVariable Long id){  // @PathVariable serve para pegar um valor da URL e passá-lo para o método.
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> salvar(@Valid @RequestBody CategoriaRequestDTO dto){ // @RequestBody Converte o JSON recebido na requisição em um objeto Java.
        CategoriaResponseDTO categoriaSalva = service.salvar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaRequestDTO dto ){ // Mesma coisa que encima so que junto, seguindo a logica de manter o ID e atualizar o registro.
        CategoriaResponseDTO atualizada = service.atualizar(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body(atualizada);
    }

    @DeleteMapping("/{id}")  // Excluir pelo Id meio Obivio kkkkk
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}