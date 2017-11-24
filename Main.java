/**
 *
 * @author Cormac
 */
public class Main {
        public static void main(String[] args) {
                AuctionManager am = new AuctionManager();
                
                Auction a = new Auction();
                
                am.addElement(a);
                
                System.out.println(am.getAllElements().get(0));
                     
        }
}
