import java.util.ArrayList;

/**
 * This class simulates an auction.
 * @author Cormac Anderson 911013
 */
public class Auction {
    
        // Contant values
        final int MAX_NO_OF_BIDS = 10;
        
        // Instance values
        private final Artwork artwork;
        private ArrayList<Bid> bids;
        private Boolean isComplete;
        private int bidsLeft;
        
        /**
         * Constructs an instance of an auction.
         * @param a The artwork to be sold on the auction.
         * @param isComplete The state of the auction.
         */
        public Auction(Artwork a, Boolean isComplete) {
                this.artwork = a;
                this.isComplete = isComplete;
                bids = new ArrayList<>();
                bidsLeft = MAX_NO_OF_BIDS;
        }
        
        /**
         * @return The artwork being sold on the auction.
         */
        public Artwork getArtwork() {
                return artwork;
        }
        
        /**
         * @return The last placed bid on the auction.
         */
        public Bid getHighestBid() {
                return bids.get(bids.size() -1);
        }
        
        /**
         * @return All of the bids made on the auction.
         */
        public ArrayList<Bid> getAllBids() {
                return bids;
        }
        
        /**
         * @return The state of the auction.
         */
        public Boolean isComplete() {
                return isComplete;
        }
        
        /**
         * @return The name of the seller of the auction.
         */
        public String getSeller() {
                return artwork.getCreatorName();
        }
        
        /**
         * @return The number of bids left on the auction.
         */
        public int getBidsLeft() {
                return bidsLeft;
        }
        
        /**
         * Places a bid on the auction.
         * @param b The bid to be placed.
         */
        public void placeBid(Bid b){
                bidsLeft -= 1;
                artwork.setBidTotal(artwork.getBidTotal() + 1);
                bids.add(b);
                if (bidsLeft == 0) {
                    isComplete = true;
                }
        }
}
