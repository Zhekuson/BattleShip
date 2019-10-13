package battleship.exceptions;

/**
 * custom exception for wrong row & column on input
 */
public class WrongCoordsException extends Exception {
    public WrongCoordsException(){}

    /**
     *  constructor for functionality
     * @param message
     */
    public WrongCoordsException(String message)
    {
        super(message);
    }
}
