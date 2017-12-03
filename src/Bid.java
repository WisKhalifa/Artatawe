
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Cormac
 */
public class Bid {
        private Profile bidder;
        private Artwork artwork;
        private double amount;
        private Date date;
        
        public Bid(Profile p, Artwork a, double amount) {
                this.bidder = p;
                this.artwork = a;
                this.amount = amount;
            
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                date = new Date();
                dateFormat.format(date);
        }
        
        public Profile getBidder() {
                return bidder;
        }
        
        public Artwork getArtwork() {
                return artwork;
        }
        
        public double getAmount() {
                return amount;
        }
        
        public String getDate() {
        		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        		String strDate = dateFormat.format(date);
                return strDate;
        }     
}
