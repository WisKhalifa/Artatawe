/**
 *
 * @author Cormac
 */
public class Main {
        public static void main(String[] args) {
                AuctionManager am = new AuctionManager();
                
                Artwork art = new Painting("Test", "test", "photo", "creator", 1, 1, 1, 1, 1, 1);
                
                Auction a = new Auction(art);
                
                am.addAuction(a);
                
                System.out.println(am.getAuctions().get(0));
                
                Bid b = new Bid(new Profile("","","","","","",""), art, 100);
                
                System.out.println(b.getDate());
        }
}
