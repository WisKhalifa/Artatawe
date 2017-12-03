
import java.util.ArrayList;

/**
 *
 * @author Anderson
 */
public class AuctionManager {
        private ArrayList<Auction> auctions;
        
        /**
         * Creates an instance of an auction manager.
         */
        public AuctionManager() {
                auctions = new ArrayList<>();
        }
        
        /**
         * @return the array list of auctions 
         */
        public ArrayList<Auction> getAuctions() {
                return auctions;
        }
        
        /**
         * @return the array list of auctions that are completed 
         */
        public ArrayList<Auction> getCompletedAuctions() {
                ArrayList<Auction> completedAuctions = new ArrayList<>();
                for (Auction elem: auctions) {
                    if (elem.isComplete() == true) {
                        completedAuctions.add(elem);
                    }
                } 
                return completedAuctions;
        }

        /**
         * @return the array list of auctions that are not completed
         */
        public ArrayList<Auction> getNonCompletedAuctions() {
                ArrayList<Auction> nonCompletedAuctions = new ArrayList<>();
                for (Auction elem: auctions) {
                    if (elem.isComplete() == false) {
                        nonCompletedAuctions.add(elem);
                    }
                } 
                return nonCompletedAuctions;
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
                auctions.remove(a);
        }
        
}
