package rtbhouse;

public class LineDoesNotExistException extends Exception {

    public LineDoesNotExistException() {
        super();
    }

    public LineDoesNotExistException(String message) {
        super(message);
    }
}
