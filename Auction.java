import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Cormac
 */
public class Auction {
        private final Artwork artwork;
        private ArrayList<Bid> bids;
        private Boolean isComplete;
        private int bidsLeft;
        
        final int MAX_NO_OF_BIDS = 5;
        
        /**
         * 
         * @param a
         * @param isComplete 
         */
        public Auction(Artwork a, Boolean isComplete) {
                this.artwork = a;
                this.isComplete = isComplete;
                bids = new ArrayList<>();
                Random rand = new Random();
                bidsLeft = rand.nextInt(MAX_NO_OF_BIDS) + 1;
        }
        
        /**
         * 
         * @return 
         */
        public Artwork getArtwork() {
                return artwork;
        }
        
        /**
         * 
         * @return 
         */
        public Bid getHighestBid() {
                return bids.get(bids.size() -1);
        }
        
        /**
         * 
         * @return 
         */
        public ArrayList<Bid> getAllBids() {
                return bids;
        }
        
        /**
         * 
         * @return 
         */
        public Boolean isComplete() {
                return isComplete;
        }
        
        /**
         * 
         * @return 
         */
        public String getSeller() {
                return artwork.getCreatorName();
        }
        
        /**
         * 
         * @return 
         */
        public int getBidsLeft() {
                return bidsLeft;
        }
        
        /**
         * 
         * @param b 
         */
        public void placeBid(Bid b){
                bidsLeft -= 1;
                bids.add(b);
                if (bidsLeft == 0) {
                    isComplete = true;
                }
        }
}
