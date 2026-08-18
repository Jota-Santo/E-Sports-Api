package br.com.lojaesportes.api.Exception;

public class ProdutoNotFoundException extends RuntimeException {

    public ProdutoNotFoundException(String mensagem) {
        super(mensagem);
    }
}