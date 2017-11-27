/**
 *
 * @author Cormac
 */
public class AlreadyHighestBidderException extends Exception {

    /**
     * Creates a new instance of <code>AlreadyHighestBidderException</code>
     * without detail message.
     */
    public AlreadyHighestBidderException() {
    }

    /**
     * Constructs an instance of <code>AlreadyHighestBidderException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public AlreadyHighestBidderException(String msg) {
        super(msg);
    }
}
