import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * This is a class that allows a user to save Auction's and Profiles to a text file.
 * The two methods allow you to save the two different types of data. However the text
 * file location must be known when creating the FileWriter object.
 * @author Bradley Tenuta
 */
public class FileWriter {
	
	private String profileFilePath;
	private String auctionFilePath;
	private PrintWriter profileWriter;
	private PrintWriter auctionWriter;
	
	/**
	 * This constructor allows the user to save profiles and Auction's to a text file.
	 * @param profileFilePath The text file location where you want data to be saved.
	 * @param auctionFilePath The text file location where you want data to be saved.
	 * @throws FileNotFoundException Error thrown if file is not found.
	 * @throws UnsupportedEncodingException Error thrown if wrong character encoding is used.
	 */
	public FileWriter(String profileFilePath, String auctionFilePath) throws FileNotFoundException, UnsupportedEncodingException {
		
		this.profileFilePath = profileFilePath;
		this.auctionFilePath = auctionFilePath;
		
		profileWriter = new PrintWriter(profileFilePath, "UTF-8");
		auctionWriter = new PrintWriter(auctionFilePath, "UTF-8");
	}
	
	/**
	 * This method saves all the information of a profile to a text file.
	 * @param profiles The list of profiles being saved.
	 */
	public void writeProfileToFile(ArrayList<Profile> profiles) {
		for(int i = 0; i < profiles.size(); i++) {
			profileWriter.println(profiles.get(i).getUsername());
			profileWriter.println(profiles.get(i).getFirstName());
			profileWriter.println(profiles.get(i).getLastName());
			profileWriter.println(profiles.get(i).getTelephone());
			profileWriter.println(profiles.get(i).getFirstAddress());
			profileWriter.println(profiles.get(i).getPostcode());
			profileWriter.println(profiles.get(i).getImagePath());
			profileWriter.println(","); //shows the end of saving a profile.
		}
	}
	
	/**
	 * This method writes all the data in an Auction, including its artwork
	 * to a text file.
	 * @param auctions The list of Auction's to be saved.
	 */
	public void writeAuctionToFile(ArrayList<Auction> auctions){
		for(int i = 0; i < auctions.size(); i++) {
			//saves all the information about the artwork in the auction
			auctionWriter.println(auctions.get(i).getArtwork().getTitle());
			auctionWriter.println(auctions.get(i).getArtwork().getDescription());
			auctionWriter.println(auctions.get(i).getArtwork().getMainPhoto());
			auctionWriter.println(auctions.get(i).getArtwork().getCreatorName());
			auctionWriter.println(auctions.get(i).getArtwork().getCreationYear());
			auctionWriter.println(auctions.get(i).getArtwork().getPrice());
			auctionWriter.println(auctions.get(i).getArtwork().getBidTotal());
			auctionWriter.println(auctions.get(i).getArtwork().getDateAndTime());
			
			//saves details if it is a Painting.
			if(auctions.get(i).getArtwork() instanceof Painting) {
				auctionWriter.println(auctions.get(i).getArtwork().getWidth());
				auctionWriter.println(auctions.get(i).getArtwork().getHeight());
				auctionWriter.println(auctions.get(i).getArtwork().getDepth());
				auctionWriter.println(auctions.get(i).getArtwork().getMaterial());
				for(int j = 0; j < auctions.get(i).getArtwork().getAdditionalPhotos.length; j++){
					auctionWriter.println(auctions.get(i).getArtwork().getAdditionalPhotos[i]);
				}
				profileWriter.println("."); //shows the end of saving additional photos
			}else {
				auctionWriter.println(auctions.get(i).getArtwork().getWidth());
				auctionWriter.println(auctions.get(i).getArtwork().getHeight());
			}
			
			//saves all the information about the Auction
			auctionWriter.println(auctions.get(i).getSeller());
			auctionWriter.println(auctions.get(i).isComplete());
			
			for(int j = 0; j < auctions.get(i).getAllBids().size(); j++){
				auctionWriter.println(auctions.get(i).getAllBids().get(i));	
			}
			profileWriter.println(","); //shows the end of an Auction save
		}
	}
}
