import java.util.ArrayList;

/**
 *
 * @author Cormac Anderson
 */
public class ProfileManager extends Manager {
        private ArrayList<Profile> profiles;
        
        /**
         * Creates an instance of a profile manager.
         */
        public ProfileManager() {
            profiles = new ArrayList<>();
        }
        
        /**
         * Adds a profile to the profile manager.
         * @param a 
         */
        public void addProfile(Profile a) {
                profiles.getAllElements().add(a);
        }
        
        /**
         * Removes a profile from the profile manager.
         * @param a the auction to be removed
         */        public void deleteProfile(Profile a) {
                profiles.getAllElements().remove(a);
        }
        
}
