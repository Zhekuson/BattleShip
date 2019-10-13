package battleship.exceptions;

public class WrongCoordsException extends Exception {
    public WrongCoordsException(){}
    public WrongCoordsException(String message)
    {
        super(message);
    }
}
