package version1.demo.utils.exception;

public class CategoriaNonPresente extends RuntimeException{
    
    public CategoriaNonPresente(String messaggio) {
        super(messaggio);
    }
}
