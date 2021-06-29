package Exceptions;

/**
 * The type Model exception. It is thrown by the model functions when there is an error. The message indicates the
 * problem to the player
 */
public class ModelException extends Exception{
    /**
     * Instantiates a new Model exception.
     *
     * @param message the message
     */
    public ModelException(String message){
        super(message);
    }
}
