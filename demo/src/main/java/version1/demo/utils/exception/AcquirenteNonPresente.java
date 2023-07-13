package version1.demo.utils.exception;

public class AcquirenteNonPresente extends RuntimeException{
    
    public AcquirenteNonPresente(String messaggio) {
        super(messaggio);
    }
}
