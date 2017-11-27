/**
 *
 * @author Cormac
 */
public class LessThanHighestBidException extends Exception {

    /**
     * Creates a new instance of <code>LessThanHighestBidException</code>
     * without detail message.
     */
    public LessThanHighestBidException() {
    }

    /**
     * Constructs an instance of <code>LessThanHighestBidException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public LessThanHighestBidException(String msg) {
        super(msg);
    }
}
