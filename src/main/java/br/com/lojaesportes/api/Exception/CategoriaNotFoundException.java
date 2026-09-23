package br.com.lojaesportes.api.Exception;

public class CategoriaNotFoundException extends RuntimeException {
    
    public CategoriaNotFoundException(String message){
        super(message);
    }
}
