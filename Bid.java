

/**
 *
 * @author Cormac Anderson
 */
public class Bid {
        private Profile bidder;
        private Artwork artwork;
        private double amount;
        private String date;
        
        /**
         * 
         * @param p
         * @param a
         * @param amount
         * @param date 
         */
        public Bid(Profile p, Artwork a, double amount, String date) {
                this.bidder = p;
                this.artwork = a;
                this.amount = amount;
                this.date = date;
        }
        
        /**
         * 
         * @return 
         */
        public Profile getBidder() {
                return bidder;
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
        public double getAmount() {
                return amount;
        }
        
        /**
         * 
         * @return 
         */
        public String getDate() {
                return date;
        }     
}
