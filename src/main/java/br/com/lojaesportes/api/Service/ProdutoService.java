package br.com.lojaesportes.api.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lojaesportes.api.Dto.ProdutoRequestDTO;
import br.com.lojaesportes.api.Dto.ProdutoResponseDTO;
import br.com.lojaesportes.api.Exception.CategoriaNotFoundException;
import br.com.lojaesportes.api.Exception.ProdutoDuplicateException;
import br.com.lojaesportes.api.Exception.ProdutoNotFoundException;
import br.com.lojaesportes.api.Model.Categoria;
import br.com.lojaesportes.api.Model.Produto;
import br.com.lojaesportes.api.Repository.CategoriaRepository;
import br.com.lojaesportes.api.Repository.ProdutoRepository;

@Service // Service recebe chamadas do Controller e executa as regras de negócio; quem recebe as requisições HTTP é o Controller.
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;


// METODOS PUBLICOS PARA O CONTROLLER CHAMAR

    // Metodo Listar Todos os Produtos, retorna uma lista de ProdutoResponseDTO
    public List<ProdutoResponseDTO> listarTodos() {

        List<Produto> produtos = repository.findAll();
        List<ProdutoResponseDTO> response =  new ArrayList<>();
        for (Produto produto : produtos){
            response.add(converterParaResponseDTO(produto));
        }
        return response;
    }

    // Metodo Buscar Produto por ID, retorna um ProdutoResponseDTO;
    public ProdutoResponseDTO buscarPorId(Long id){

        Produto produto = repository.findById(id).orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado com o ID: " + id));

        return converterParaResponseDTO(produto);
    }

    // Metodo Salvar Produto, recebe um ProdutoRequestDTO e retorna um ProdutoResponseDTO
    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {

        // Verifica se o Produto já existe.
        if (repository.findByNome(dto.getNome()).isPresent()) {
            throw new ProdutoDuplicateException("Já existe um produto com esse nome.");
        }
        
        // Cria o Produto Se ele não existe:
        Produto produto = new Produto();

        preencherProduto(produto, dto);

        Produto produtoSalvo = repository.save(produto); // Salva o produto gerado acima.

        return converterParaResponseDTO(produtoSalvo);
    }

    // Metodo Excluir Produto, recebe um ID e retorna void
    public void excluir(Long id) {

        Produto produto = repository.findById(id).orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado com o ID: " + id));

        repository.delete(produto);
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto){
        
        Produto produto = repository.findById(id).orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado com o ID: " + id));

        Produto produtoExistente = repository.findByNome(dto.getNome()).orElse(null);
        if(produtoExistente != null && !produtoExistente.getId().equals(id)){
            throw new ProdutoDuplicateException("Já existe um produto com esse nome.");
        }

        preencherProduto(produto, dto);

        Produto produtoAtualizado = repository.save(produto);
        return converterParaResponseDTO(produtoAtualizado);
    }


    private void preencherProduto(Produto produto, ProdutoRequestDTO dto){
        
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setEstoque(dto.getEstoque());
        produto.setMarca(dto.getMarca());
        produto.setAtivo(dto.getAtivo());

        Categoria categoria = categoriaRepository
                .findById(dto.getCategoriaId())
                .orElseThrow(() -> new CategoriaNotFoundException("Categoria não encontrada com o ID: " + dto.getCategoriaId()));

        produto.setCategoria(categoria);
    }

    // Metodo Privado pois apenas a classe Produto Service ira usalo, poupar tempo e espaço metodo conversor
    private ProdutoResponseDTO converterParaResponseDTO(Produto produto) {

        ProdutoResponseDTO response = new ProdutoResponseDTO();

        response.setId(produto.getId());
        response.setNome(produto.getNome());
        response.setDescricao(produto.getDescricao());
        response.setPreco(produto.getPreco());
        response.setEstoque(produto.getEstoque());
        response.setMarca(produto.getMarca());
        response.setAtivo(produto.getAtivo());

        // para caso de algum motivo, um produto for salvo sem categoria. o Java lançaria uma NullPointerException, porque estaria tentando acessar o nome de uma categoria que não existe.
        if (produto.getCategoria() != null) {
            response.setCategoria(produto.getCategoria().getNome());
        }

        return response;
    }
}