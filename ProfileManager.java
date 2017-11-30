import java.util.ArrayList;

/**
 *
 * @author Cormac Anderson
 */
public class ProfileManager {
        private ArrayList<Profile> profiles;
        
        /**
         * Creates an instance of a profile manager.
         */
        public ProfileManager() {
            profiles = new ArrayList<>();
        }
        
        /**
         * @return the array list of profiles 
         */
        public ArrayList<Profile> getProfiles() {
                return profiles;
        }
        
        /**
         * Adds a profile to the profile manager.
         * @param a 
         */
        public void addProfile(Profile p) {
                profiles.add(p);
        }

        /**
         * Removes a profile from the profile manager.
         * @param a the auction to be removed
         */        
        public void deleteProfile(Profile p) {
                profiles.remove(p);
        }
        
}
