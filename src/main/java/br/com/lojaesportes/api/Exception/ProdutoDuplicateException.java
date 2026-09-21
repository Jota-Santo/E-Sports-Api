package br.com.lojaesportes.api.Exception;

public class ProdutoDuplicateException extends RuntimeException {
    
    public ProdutoDuplicateException (String mensagem) {
        super(mensagem);
    }
}