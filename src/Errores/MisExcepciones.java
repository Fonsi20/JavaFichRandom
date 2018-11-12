package Errores;

public class MisExcepciones extends Exception {

    private String error;

    public MisExcepciones(String err) {
        super();
        this.error = err;
    }

    public String getError() {
        return error;
    }
}
