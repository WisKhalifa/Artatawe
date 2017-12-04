import java.util.*;
import java.io.*;
/**
 * Loads and creates objects
 * @author Jay Markey
 *
 */
public class FileLoader {
	private final String profilePath = "C:\\Users\\Jay\\eclipse-workspace\\A3Test\\Profiles";
	private final String auctionPath = "C:\\Users\\Jay\\eclipse-workspace\\A3Test\\Auctions";
	private ArrayList<Auction> auctions = new ArrayList<>();
	private ArrayList<Profile> profiles = new ArrayList<>();
	
	
	public ArrayList<Profile> loadProfile(){
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
				
				Profile p = new Profile(username, firstName, lastName, 
		                telephone, address, postcode, imagePath);
				
				String nextElem = in.nextLine();
				
				while(!(nextElem.equals(","))) {
					p.addFavourite(nextElem);
					System.out.println(nextElem);
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
	
	public void loadPainting(Scanner line) {
		Profile bidder = null;
		
		String title = line.next();
		String description = line.next();
		String photo = line.next();
		String creatorsName = line.next();
		int creationYear = line.nextInt();
		double price = line.nextDouble();
		int bidTotal = line.nextInt();
		String dateTime = line.next();
		int width = line.nextInt();
		int height = line.nextInt();
			
		boolean complete = line.nextBoolean();
			
		Painting pnt = new Painting(title, description, photo, creatorsName, creationYear, 
				price, bidTotal, dateTime, width, height);
			
		Auction au = new Auction(pnt, complete);
		
		while (line.hasNext()) {
			if (line.next().equals(".")) {
				double amount = line.nextDouble();
				String dateBid = line.next();
				String usernameBid = line.next();
							
				for (Profile p : this.profiles) {
					if (p.getUsername().equals(usernameBid)) {
						bidder = p;
					}	
				}
							
				Bid b = new Bid(bidder, pnt, amount, dateBid);
				au.placeBid(b);
			}	
		}
		auctions.add(au);
	}	
	
	public void loadSculpture(Scanner line) {
		ArrayList<String> extraPhotos = new ArrayList<>();
		Profile bidder = null;
			
		String title = line.next();
		String description = line.next();
		String photo = line.next();
		String creatorsName = line.next();
		int creationYear = line.nextInt();
		double price = line.nextDouble();
		int bidTotal = line.nextInt();
		String dateTime = line.next();
		int width = line.nextInt();
		int height = line.nextInt();
		int depth = line.nextInt();
		String material = line.next();
			
		Sculpture scu = new Sculpture(title, description, photo, creatorsName, creationYear, price, bidTotal,
				dateTime, width, height, depth, material);
		
		String nextPhoto = line.next();
		
		while(!(nextPhoto.equals("!"))) {
			extraPhotos.add(nextPhoto);
			System.out.println(nextPhoto);
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
							
				for (Profile p : this.profiles) {
					if (p.getUsername().equals(usernameBid)) {
						bidder = p;
					}	
				}
							
				Bid b = new Bid(bidder, scu, amount, dateBid);
				au.placeBid(b);
			}	
		}
		auctions.add(au);
	}
	
	
	public ArrayList<Auction> loadAuction(){
		try { 
			File file = new File(auctionPath);
			
			Scanner in = new Scanner(file);
			
			
			while (in.hasNextLine()) {
				String curLine = in.nextLine();
				Scanner line = new Scanner(curLine);
				line.useDelimiter(",");
				
				if (line.next().equalsIgnoreCase("Painting")) {
					loadPainting(line);
					System.out.println("yo yo");
					
				} else if (line.next().equalsIgnoreCase("Sculpture")) { 
					loadSculpture(line);
					System.out.println("yo im here");
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
