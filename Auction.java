

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
        
        public void placeBid(Bid b) throws AlreadyHighestBidderException,
                LessThanReservePriceException, LessThanHighestBidException {
                Bid currenstHighestBid = bids.peek();
                if (b.getBidder() == currenstHighestBid.getBidder()) {
                    // if the bidder is already the highest bidder error
                    throw new AlreadyHighestBidderException();
                } else if (artwork.getPrice() > b.getAmount()) {
                    // if the artwork reserve price is greater than the amount then error
                    throw new LessThanReservePriceException();
                } else if (currenstHighestBid.getAmount() > b.getAmount()) {
                    // if the amount of the highest bid is more than the bid error
                    throw new LessThanHighestBidException();
                } else {
                    // accept bid
                    bids.add(b);
                }
        }

}
