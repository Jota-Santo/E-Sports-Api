package br.com.lojaesportes.api.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojaesportes.api.Dto.CategoriaResponseDTO;
import br.com.lojaesportes.api.Exception.CategoriaDuplicateException;
import br.com.lojaesportes.api.Exception.CategoriaNotFoundException;
import br.com.lojaesportes.api.Model.Categoria;
import br.com.lojaesportes.api.Repository.CategoriaRepository;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository repository;

    public List<CategoriaResponseDTO> listarTodos() {

        List<Categoria> categorias = repository.findAll();
        List<CategoriaResponseDTO> response = new ArrayList<>();
        for(Categoria categoria : categorias){
            response.add(converterParaResponseDTO(categoria));
        }
        return response;
    }

    public Categoria buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new CategoriaNotFoundException ("Categoria não encontrada com o ID: " + id));
    }

    public Categoria salvar(Categoria categoria) {

        if (repository.findByNome(categoria.getNome()).isPresent()) {
            throw new CategoriaDuplicateException("Já existe uma categoria com esse nome.");
        }

        return repository.save(categoria);
    }

    public Categoria atualizar(Long id, Categoria categoria) {
        Categoria categoriaatualizar = repository.findById(id).orElseThrow(() -> new CategoriaNotFoundException("Categoria não encontrada com o ID: " + id));

        Categoria categoriaexiste =  repository.findByNome(categoria.getNome()).orElse(null);
        if (categoriaexiste != null && !categoriaexiste.getId().equals(id)) {throw new CategoriaDuplicateException("Já existe uma categoria com esse nome.");
        }
        
        categoriaatualizar.setNome(categoria.getNome());

        return repository.save(categoriaatualizar);
    }

    public void excluir(Long id) {

        Categoria categoria = repository.findById(id).orElseThrow(() -> new CategoriaNotFoundException("Categoria não encontrada com o ID: " + id));
        repository.delete(categoria);
    }

    private CategoriaResponseDTO converterParaResponseDTO(Categoria categoria){

        CategoriaResponseDTO response = new CategoriaResponseDTO();

        response.setId(categoria.getId());
        response.setNome(categoria.getNome());

        return response;
    }
}