package requests;

public class RequestException extends RuntimeException {

    public RequestException(String errorMessage) {
        super(errorMessage);
    }
}