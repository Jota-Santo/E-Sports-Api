package br.com.lojaesportes.api.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojaesportes.api.Dto.CategoriaRequestDTO;
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

    public CategoriaResponseDTO buscarPorId(Long id) {
        Categoria categoria = repository.findById(id).orElseThrow(() -> new CategoriaNotFoundException ("Categoria não encontrada com o ID: " + id));

        return converterParaResponseDTO(categoria);
    }

    public CategoriaResponseDTO salvar(CategoriaRequestDTO dto) {

        if (repository.findByNome(dto.getNome()).isPresent()) {
            throw new CategoriaDuplicateException("Já existe uma categoria com esse nome.");
        }

        Categoria categoria = new Categoria();

        categoria.setNome(dto.getNome());

        Categoria categoriaSalva = repository.save(categoria);

        return converterParaResponseDTO(categoriaSalva);
    }

    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO dto) {
        
        Categoria categoria = repository.findById(id).orElseThrow(() -> new CategoriaNotFoundException("Categoria não encontrada com o ID: " + id));

        Categoria categoriaExiste =  repository.findByNome(dto.getNome()).orElse(null);
        if (categoriaExiste != null && !categoriaExiste.getId().equals(id)) {throw new CategoriaDuplicateException("Já existe uma categoria com esse nome.");
        }

        categoria.setNome(dto.getNome());

        Categoria categoriaAtualizada = repository.save(categoria);
        return converterParaResponseDTO(categoriaAtualizada);
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