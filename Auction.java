

import java.util.Stack;


/**
 *
 * @author Cormac
 */
public class Auction {
        private final Artwork artwork;
        private Stack<Bid> bids;
        private Boolean isComplete;
        
        public Auction(Artwork a) {
                this.artwork = a;
                bids = new Stack<>();
                isComplete = false;
        }
        
        public Artwork getArtwork() {
                return artwork;
        }
        
        public Bid getHighestBid() {
                return bids.peek();
        }
        
        public Stack<Bid> getAllBids() {
                return bids;
        }
        
        public Boolean isComplete() {
                return isComplete;
        }
        
        public String getSeller() {
                return artwork.getCreatorName();
        }
        
        public void placeBid(Bid b) {
            // place bid here
        }

}
