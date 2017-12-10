import java.util.*;
import java.io.*;
/**
 * Loads and creates objects
 * @author Jay Markey
 * @version 1.4
 */
public class FileLoader {
	private final String PROFILE_PATH = "profile.txt"; //Path to profile text file
	private final String AUCTION_PATH = "auction.txt"; //Path to auction text file
	private ArrayList<Auction> auctions = new ArrayList<>(); //ArrayList to store auction objects
	private ArrayList<Profile> profiles = new ArrayList<>(); //ArrayList to store profile objects

	/**
	* Constructor for this class
	* initiates the ArrayLists
	*/	
	public FileLoader() {
		profiles = new ArrayList<>();
		auctions = new ArrayList<>();
	}
	
	/**
	 * Method to load the profiles from a text file
	 * @throws FileNotFoundException if profilePath not found
	 * @return ArrayList of profile objects 
	 */
	public ArrayList<Profile> loadProfile() {
		try {
			File file = new File(PROFILE_PATH);
			
			Scanner in = new Scanner(file);
			
			while(in.hasNextLine()) {
				String username = in.nextLine();
				String firstName = in.nextLine();
				String lastName = in.nextLine();
				String telephone = in.nextLine();
				String address = in.nextLine();
				String postcode = in.nextLine();
				String imagePath = in.nextLine();
				
				Profile p = new Profile(username, firstName, lastName, 
		                telephone, address, postcode, imagePath);
				
				String nextElem = in.nextLine(); //nextElem used compare the next element
				
				/**
				 * while the next element isnt ","
				 * use the addFavourite method in profile
				 * to add favourite users
				 */
				while(!(nextElem.equals(","))) {
					p.addFavourite(nextElem);
					nextElem = in.nextLine();
				}
				
				profiles.add(p);
			}
			in.close();
		} 	catch (FileNotFoundException e) {
			System.out.println(profilePath + "not found");
			System.exit(0);
		}
		return profiles;
	}
	
	/**
	 * Method for loading painting objects
	 * adds them to the auctions ArrayList
	 * @param line
	 */
	public void loadPainting(Scanner line) {
		Profile bidder = new Profile();
		
		String title = line.next();
		String description = line.next();
		String photo = line.next();
		String creatorsName = line.next();
		int creationYear = line.nextInt();
		double price = line.nextDouble();
		String dateTime = line.next();
		int width = line.nextInt();
		int height = line.nextInt();
			
		boolean complete = line.nextBoolean();
			
		Painting pnt = new Painting(title, description, photo, creatorsName, creationYear, 
				price, dateTime, width, height);
			
		Auction au = new Auction(pnt, complete);
		
		/**
		 * while there is a next element
		 * set the bid related variables
		 */
		while (line.hasNext()) {
			if (line.next().equals(".")) {
				double amount = line.nextDouble();
				String dateBid = line.next();
				String usernameBid = line.next();
				
				/**
				 * search through the ArrayList profiles
				 * find the profile that matches username in the bid
				 * then set bidder to this 
				 */	
				for (Profile p : this.profiles) {	
					if (p.getUsername().equals(usernameBid)) {
						bidder = p;
						
					}	
				}
				
				/**
				*Create bid and use placeBid method in auction
				*to add the bid to the arrayList
				*/				
				Bid b = new Bid(bidder, pnt, amount, dateBid);
				au.placeBid(b);
				
			}	
		}
		auctions.add(au);
	}	
	
	/**
	 * Method for loading Sculpture objects
	 * adds them to auction ArrayList
	 * @param line
	 */
	public void loadSculpture(Scanner line) {
		ArrayList<String> extraPhotos = new ArrayList<>();
		Profile bidder = new Profile();
			
		String title = line.next();
		String description = line.next();
		String photo = line.next();
		String creatorsName = line.next();
		int creationYear = line.nextInt();
		double price = line.nextDouble();
		String dateTime = line.next();
		int width = line.nextInt();
		int height = line.nextInt();
		int depth = line.nextInt();
		String material = line.next();
			
		Sculpture scu = new Sculpture(title, description, photo, creatorsName, creationYear, price,
				dateTime, width, height, depth, material);
		
		String nextPhoto = line.next();
		
		while(!(nextPhoto.equals("!"))) {
			extraPhotos.add(nextPhoto);
			nextPhoto = line.next();
		}
		
		scu.setExtraPhotos(extraPhotos);
			
		boolean complete = line.nextBoolean();
			
		Auction au = new Auction(scu, complete);
		

		while (line.hasNext()) {
			if (line.next().equals(".")) {
				double amount = line.nextDouble();
				String dateBid = line.next();
				String usernameBid = line.next();
				
				/**
				 * search through the ArrayList profiles
				 * find the profile that matches username in the bid
				 * then set bidder to this 
				 */				
				for (Profile p : this.profiles) {
					if (p.getUsername().equals(usernameBid)) {
						bidder = p;
					}	
				}
				
				/**
				*Create bid and use placeBid method in auction
				*to add the bid to the arrayList
				*/			
				Bid b = new Bid(bidder, scu, amount, dateBid);
				au.placeBid(b);
			}	
		}
		auctions.add(au);
	}
	
	/**
	 * Method that reads in text file
	 * and decides whether to call loadPainting()
	 * or LoadSculpture()
	 * @throws FileNotFoundException if auctionPath cannot be found
	 * @return ArrayList of Auction objects
	 */
	public ArrayList<Auction> loadAuction() {
		try { 
			File file = new File(AUCTION_PATH);
			
			Scanner in = new Scanner(file);
			
			
			while (in.hasNextLine()) {
				/**
				*curLine is used to read the currentline in the 
				*text file
				*/
				String curLine = in.nextLine();
				Scanner line = new Scanner(curLine);
				line.useDelimiter(","); // "," is used to split the line
				
				/**
				* no need to check case or check for "Sculpture"
				* as we only have control over this part of the text file 
				* and the only two bits will be "Painting" and "Sculpture"
				*/
				if (line.next().equals("Painting")) {
					loadPainting(line);
				} else { 
					loadSculpture(line);
				}
				line.close();
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println(auctionPath + "not found");
			System.exit(0);
		}
		return auctions;
	}
}
