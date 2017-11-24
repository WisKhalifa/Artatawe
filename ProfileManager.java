/**
 *
 * @author Cormac
 */
public class ProfileManager extends Manager {
                
        public ProfileManager() {
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
