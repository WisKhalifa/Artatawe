
import java.util.ArrayList;

/**
 *
 * @author Cormac
 */
public class Auction {
        //private final Artwork artwork;
        // bids possibly in a stack structure?
        private ArrayList<Bid> bids;
        private Bid currentHighestBid;
        private Boolean isComplete;
        
        public Auction() {
                //this.artwork = a;
                bids = new ArrayList<>();
                isComplete = false;
        }
        
        public Artwork getArtwork() {
                return artwork;
        }
        
        public Bid getHighestBid() {
                return currentHighestBid;
        }
        
        public ArrayList<Bid> getAllBids() {
                return bids;
        }
        
        public Boolean isComplete() {
                return isComplete;
        }
        
        public Profile getSeller() {
                return artwork.getSeller();
        }
        
        public void placeBid(Bid b) {
            // place bid here
        }

}
