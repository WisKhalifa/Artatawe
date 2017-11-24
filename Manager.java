
import java.util.ArrayList;

/**
 *
 * @author 911013
 */
abstract class Manager {
        private ArrayList<Object> listOfElements;
        
        public Manager() {
                listOfElements = new ArrayList<>();
        }
        
        abstract void addElement(Object elem);
        
        abstract void deleteElement(Object elem);
        
        public ArrayList<Object> getAllElements() {
                return listOfElements;
        }

}
