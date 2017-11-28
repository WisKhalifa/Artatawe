import java.util.*;
import java.io.*;
/**
 * 
 * @author Jay Markey
 *
 */
public class FileLoader {
	private final String profilePath = "C:\\Users\\Jay\\eclipse-workspace\\A3\\Profiles";
	private final String auctionPath = "C:\\Users\\Jay\\eclipse-workspace\\A3\\Auctions";
	
	
	public void loadProfile(){
		ProfileManager pm = new ProfileManager();
		try {
			File file = new File(profilePath);
			
			Scanner in = new Scanner(file);
			
			while(in.hasNextLine()) {
				String username = in.nextLine();
				String firstName = in.nextLine();
				String lastName = in.nextLine();
				String telephone = in.nextLine();
				String address = in.nextLine();
				String postcode = in.nextLine();
				String imagePath = in.nextLine();
				//used to skip over the line with , 
				if (in.nextLine() == ",") {
					continue; 
				}
				
				Profile p = new Profile(username, firstName, lastName, 
						                telephone, address, postcode, imagePath);
				pm.addProfile(p);
				
			}
			in.close();
		} 	catch (FileNotFoundException e) {
			System.out.println(profilePath + "not found");
			System.exit(0);
		}
		
	}
	
	public void loadAuction(){
		try { 
			File file = new File(auctionPath);
			
			Scanner in = new Scanner(file);
			
			while (in.hasNextLine()) {
				String title = in.nextLine();
				String description = in.nextLine();
				String image = in.nextLine();
				String creator = in.nextLine();
				
			}
			
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println(auctionPath + "not found");
			System.exit(0);
		}
		
	}
}
