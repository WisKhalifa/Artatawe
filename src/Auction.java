import java.util.ArrayList;

/**
 *
 * @author Cormac
 */
public class Auction {
        private final Artwork artwork;
        private ArrayList<Bid> bids;
        private Boolean isComplete;
        
        public Auction(Artwork a, Boolean isComplete) {
                this.artwork = a;
                this.isComplete = isComplete;
                bids = new ArrayList<>();
        }
        
        public Artwork getArtwork() {
                return artwork;
        }
        
        public Bid getHighestBid() {
                return bids.get(bids.size() -1);
        }
        
        public ArrayList<Bid> getAllBids() {
                return bids;
        }
        
        public Boolean isComplete() {
                return isComplete;
        }
        
        public String getSeller() {
                return artwork.getCreatorName();
        }
        
        public void placeBid(Bid b){
                bids.add(b);
        }
}
