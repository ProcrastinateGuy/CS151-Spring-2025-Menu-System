
// this exception is thrown when trying to create an object with
// invalid arguments such as creating a deal object with
// the end date earlier than the start date

public class  InvalidArgumentException extends RuntimeException {

    // String message;

    //no argument constructor
    public InvalidArgumentException(String message, Throwable err) {
        super(message, err);
    }

}
