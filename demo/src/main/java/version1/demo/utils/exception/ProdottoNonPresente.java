package version1.demo.utils.exception;

public class ProdottoNonPresente extends RuntimeException{
    
    public ProdottoNonPresente(String messaggio) {
        super(messaggio);
    }
}
