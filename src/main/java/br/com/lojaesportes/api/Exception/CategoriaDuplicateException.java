package br.com.lojaesportes.api.Exception;

public class CategoriaDuplicateException extends RuntimeException {
    
    public CategoriaDuplicateException (String mensagem) {
        super(mensagem);
    }
}