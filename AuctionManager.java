import java.util.ArrayList;

/**
 * This class simulates a auction manager which handles all of the auctions
 * in the system.
 * @author Cormac Anderson 911013
 */
public class AuctionManager {
    
        // Instance values
        private ArrayList<Auction> auctions;
        
        /**
         * Creates an instance of an auction manager.
         * @param fl The file loader to load all of the auctions.
         */
        public AuctionManager(FileLoader fl) {
                auctions = new ArrayList<>();
                auctions = fl.loadAuction();
        }
        
        /**
         * @return The array list of auctions in the system.
         */
        public ArrayList<Auction> getAuctions() {
                return auctions;
        }
        
        /**
         * @return The array list of auctions that are completed.
         */
        public ArrayList<Auction> getCompletedAuctions() {
                ArrayList<Auction> completedAuctions = new ArrayList<>();
                // loop through each auction in the auctions arraylist
                for (Auction elem: auctions) {
                    // if the auction is complete add to completedAuctions
                    if (elem.isComplete() == true) {
                        completedAuctions.add(elem);
                    }
                } 
                return completedAuctions;
        }

        /**
         * @return The array list of auctions that are not completed.
         */
        public ArrayList<Auction> getNonCompletedAuctions() {
                ArrayList<Auction> nonCompletedAuctions = new ArrayList<>();
                // loop through each auction in the auctions arraylist
                for (Auction elem: auctions) {
                    // if the auction is  not complete add to nonCompletedAuctions
                    if (elem.isComplete() == false) {
                        nonCompletedAuctions.add(elem);
                    }
                } 
                return nonCompletedAuctions;
        }
        
        /**
         * Adds an auction to the auction manager.
         * @param a The auction to be added to the manager.
         */
        public void addAuction(Auction a) {
                auctions.add(a);
        }
        
        /**
         * Removes an auction from the auction manager.
         * @param a The auction to be removed.
         */
        public void deleteAuction(Auction a) {
                auctions.remove(a);
        }
        
        /**
         * Save all of the auctions in the auction manager to file.
         * @param fw The file writer to save all of the auctions.
         */
        public void saveAuctions(FileWriter fw) {
                fw.writeAuctionToFile(auctions);
        }
}
