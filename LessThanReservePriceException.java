/**
 *
 * @author Cormac
 */
public class LessThanReservePriceException extends Exception {

    /**
     * Creates a new instance of <code>LessThanReservePriceException</code>
     * without detail message.
     */
    public LessThanReservePriceException() {
    }

    /**
     * Constructs an instance of <code>LessThanReservePriceException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public LessThanReservePriceException(String msg) {
        super(msg);
    }
}
