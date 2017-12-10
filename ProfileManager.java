import java.util.ArrayList;

/**
 * This class simulates a profile manager which handles all of the profiles
 * in the system.
 * @author Cormac Anderson 911013
 */
public class ProfileManager {
    
        // Instance values
        private ArrayList<Profile> profiles;
        
        /**
         * Creates an instance of a profile manager.
         * @param fl The file loader to load all of the profiles.
         */
        public ProfileManager(FileLoader fl) {
            profiles = new ArrayList<>();
            profiles = fl.loadProfile();
        }
        
        /**
         * @return The array list of profiles.
         */
        public ArrayList<Profile> getProfiles() {
                return profiles;
        }
        
        /**
         * Adds a profile to the profile manager.
         * @param a The profile to be added to the manager.
         */
        public void addProfile(Profile p) {
                profiles.add(p);
        }

        /**
         * Removes a profile from the profile manager.
         * @param a the profile to be removed
         */        
        public void deleteProfile(Profile p) {
                profiles.remove(p);
        }
        
        /**
         * Save all of the profiles in the profile manager to file.
         * @param fw The file writer to save all of the profiles.
         */
        public void saveProfiles(FileWriter fw) {
                fw.writeProfileToFile(profiles);
        }
        
}
