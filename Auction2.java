

import java.util.Stack;

import javafx.scene.control.Label;


/**
 *
 * @author Cormac
 */
public class Auction2 {
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
        
        public void placeBid(Bid b, Label errorMessage) {
                Bid currenstHighestBid = bids.peek();
                // if the bidder is already the highest bidder error
                if (b.getBidder() == currenstHighestBid.getBidder()) {
                	errorMessage.setText("You already have the highest bid!");
                } else if (artwork.getPrice() > b.getAmount()) {
                	errorMessage.setText("You need to set a higher bid");
                } else if (currenstHighestBid.getAmount() > b.getAmount()) {
                	errorMessage.setText("Your bid must be higher than the current highest bid");
                } else {
                    // accept bid
                    bids.add(b);
                }
                
        }

}
