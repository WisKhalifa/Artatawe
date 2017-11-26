/**
 *
 * @author Cormac
 */
public class Main {
        public static void main(String[] args) {
                AuctionManager am = new AuctionManager();
                
                Artwork art = new Painting("Test", "test", "photo", "creator", 1, 1, 1, 1, 1, 1);
                
                Auction a = new Auction(art);
                
                am.addElement(a);
                
                System.out.println(am.getAllElements().get(0));
                     
        }
}
