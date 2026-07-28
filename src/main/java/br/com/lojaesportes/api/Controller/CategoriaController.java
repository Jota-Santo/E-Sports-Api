package br.com.lojaesportes.api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lojaesportes.api.Model.Categoria;
import br.com.lojaesportes.api.Service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    
    @Autowired // Injeta automaticamente uma dependência gerenciada pelo Spring, como um Repository.
    private CategoriaService service;

    @GetMapping // ConSulta dados Mapeia requisições HTTP GET, usadas para consultar dados.
    public List<Categoria> listar(){
        return service.listarTodos();
    }

    @GetMapping("/{id}") // ConSulta dados Mapeia requisições HTTP GET, usadas para consultar dados.
    public Categoria buscarPorId(@PathVariable Long id){  // @PathVariable serve para pegar um valor da URL e passá-lo para o método.
        return service.buscarPorId(id);
    }

    @PostMapping
    public Categoria salvar(@RequestBody Categoria categoria){ // @RequestBody Converte o JSON recebido na requisição em um objeto Java.
        return service.salvar(categoria);
    }

    @PutMapping("/{id}")
    public Categoria atualizar(@PathVariable Long id, @RequestBody Categoria categoria){ // Mesma coisa que encima so que junto, seguindo a logica de manter o ID e atualizar o registro.

        categoria.setId(id);
        return service.atualizar(id, categoria);
    }

    @DeleteMapping("/{id}")  // Excluir pelo Id meio Obivio kkkkk
    public void excluir(@PathVariable Long id){
        service.excluir(id);
    }  
}
