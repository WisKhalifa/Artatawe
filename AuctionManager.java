
import java.util.ArrayList;

/**
 *
 * @author 911013
 */
public class AuctionManager extends Manager {
        
        public AuctionManager() {
            super();
        }
        
        @Override
        public void addElement(Object a) {
                super.getAllElements().add(a);
        }
        
        @Override
        public void deleteElement(Object a) {
                super.getAllElements().remove(a);
        }
        
}
