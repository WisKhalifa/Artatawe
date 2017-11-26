
import java.util.ArrayList;

/**
 *
 * @author Anderson
 */
public class AuctionManager extends Manager {
        private ArrayList<Auction> auctions;
        
        /**
         * Creates an instance of an auction manager.
         */
        public AuctionManager() {
                auctions = new ArrayList<>();
        }
        
        /**
         * Adds an auction to the auction manager.
         * @param a 
         */
        public void addAuction(Auction a) {
                auctions.add(a);
        }
        
        /**
         * Removes an auction from the auction manager.
         * @param a the auction to be removed
         */
        public void deleteAuction(Auction a) {
                super.getAllElements().remove(a);
        }
        
}
