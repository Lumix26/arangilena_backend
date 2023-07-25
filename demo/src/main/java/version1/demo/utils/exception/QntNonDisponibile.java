package version1.demo.utils.exception;


public class QntNonDisponibile extends RuntimeException{
    public QntNonDisponibile() {
        super("Non sono presenti abbastanza scorte!");
    }

}
