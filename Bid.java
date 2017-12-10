/**
 * This class simulates a bid for an auction.
 * @author Cormac Anderson 911013
 */
public class Bid {
    
        // Instance values
        private Profile bidder;
        private Artwork artwork;
        private double amount;
        private String date;
        
        /**
         * Constructs an instance of a bid.
         * @param p The profile of the user placing the bid.
         * @param a The artwork the bid is being placed upon.
         * @param amount The amount of the bid.
         * @param date The date the bid was placed.
         */
        public Bid(Profile p, Artwork a, double amount, String date) {
                this.bidder = p;
                this.artwork = a;
                this.amount = amount;
                this.date = date;
        }
        
        /**
         * @return The profile of the user who placed the bid. 
         */
        public Profile getBidder() {
                return bidder;
        }
        
        /**
         * @return The artwork the bid is being placed upon.
         */
        public Artwork getArtwork() {
                return artwork;
        }
        
        /**
         * @return The amount of the bid.
         */
        public double getAmount() {
                return amount;
        }
        
        /**
         * @return The date the bid was placed.
         */
        public String getDate() {
                return date;
        }     
}
