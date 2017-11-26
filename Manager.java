
import java.util.ArrayList;

/**
 *
 * @author Cormac Anderson
 */
abstract class Manager {
        private ArrayList<Object> listOfElements;
        
        public Manager() {
                listOfElements = new ArrayList<>();
        }
        
        public void addElement(Object elem) {
                listOfElements.add(elem);
        };
        
        public void deleteElement(Object elem) {
                listOfElements.remove(elem);
        };
        
        public ArrayList<Object> getAllElements() {
                return listOfElements;
        }

}
