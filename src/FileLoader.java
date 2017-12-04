import java.util.*;
import java.io.*;
/**
 * Loads and creates objects
 * @author Jay Markey
 *
 */
public class FileLoader {
	private final String profilePath = "profile.txt";
	private final String auctionPath = "auction.txt";
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
				
				//used to skip over the line with , 
				if (in.nextLine() == ",") {
					continue; 
				}
				
				Profile p = new Profile(username, firstName, lastName, 
						                telephone, address, postcode, imagePath);
				profiles.add(p);
				
			}
			in.close();
		} 	catch (FileNotFoundException e) {
			System.out.println(profilePath + "not found");
			System.exit(0);
		}
		return this.profiles;
	}
	
	public void loadPainting(Scanner in) {
		Profile bidder = null;
		
		String curLine = in.nextLine();
		Scanner line = new Scanner(curLine);
		line.useDelimiter(",");
		
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
		auctions.add(au);
			
		while (line.next() != null) {
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
			line.close();
	}
	
	public void loadSculpture(Scanner in) {
		Profile bidder = null;
		
		String curLine = in.nextLine();
		Scanner line = new Scanner(curLine);
		line.useDelimiter(",");
			
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
			
		while (line.next() != "£") {
			scu.getExtraPhotos().add(line.next());
		}
			
			
		boolean complete = line.nextBoolean();
			
		Auction au = new Auction(scu, complete);
		auctions.add(au);
			
		while (line.next() != null) {
			double amount = line.nextInt();
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
	line.close();
	}
	
	
	public ArrayList<Auction> loadAuction(){
		try { 
			File file = new File(auctionPath);
			
			Scanner in = new Scanner(file);
			
			while (in.hasNextLine()) {
				if (in.next().contains("Painting")) {
					this.loadPainting(in);
					System.out.println("yo yo");
				} else if (in.next().contains("Sculpture")) {
					this.loadSculpture(in);
					System.out.println("yo im here");
				}else {
					break;
				}
				
			}
			
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println(auctionPath + "not found");
			System.exit(0);
		}
		
		return this.auctions;
	}
}
