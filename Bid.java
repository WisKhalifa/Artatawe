
import java.util.Date;

/**
 *
 * @author Cormac
 */
public class Bid {
        private Profile bidder;
        private Artwork artwork;
        private int amount;
        private Date date;
        
        public Bid(Profile p, Artwork a, int amount) {
                this.bidder = p;
                this.artwork = a;
                this.amount = amount;
                // need to add date
        }
        
        public Profile getBidder() {
                return bidder;
        }
        
        public Artwork getArtwork() {
                return artwork;
        }
        
        public int getAmount() {
                return amount;
        }
        
        public Date getDate() {
                return date;
        }
        
}
