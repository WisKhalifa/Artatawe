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
		for (int i = 0; i < profiles.size(); i++) {
			Profile temp = profiles.get(i);
			profileWriter.println(temp.getUsername());
			profileWriter.println(temp.getFirstName());
			profileWriter.println(temp.getLastName());
			profileWriter.println(temp.getTelephone());
			profileWriter.println(temp.getFirstAddress());
			profileWriter.println(temp.getPostcode());
			profileWriter.println(temp.getImagePath());
			
			//saves all the favourites a profile has.
			if (temp.getFavourites().isEmpty()) {
				profileWriter.println("-");
			} else {
				for (int j = 0; j < temp.getFavourites().size(); j++) {
					profileWriter.println(temp.getFavourites().get(j));
				}
			}
			profileWriter.println(","); //shows the end of saving a profile.
		}
		profileWriter.close();
	}
	
	/**
	 * This method writes all the data in an Auction, including its artwork
	 * to a text file.
	 * @param auctions The list of Auction's to be saved.
	 */
	public void writeAuctionToFile(ArrayList<Auction> auctions){
		for(int i = 0; i < auctions.size(); i++) {
			
			if (auctions.get(i).getArtwork() instanceof Painting) {
				auctionWriter.print("Painting,");
				auctionWriter.print(auctions.get(i).getArtwork().getTitle() + ",");
				auctionWriter.print(auctions.get(i).getArtwork().getDescription() + ",");
				auctionWriter.print(auctions.get(i).getArtwork().getMainPhoto() + ",");
				auctionWriter.print(auctions.get(i).getArtwork().getCreatorName() + ",");
				auctionWriter.print(auctions.get(i).getArtwork().getCreationYear() + ",");
				auctionWriter.print(auctions.get(i).getArtwork().getPrice() + ",");
				auctionWriter.print(auctions.get(i).getArtwork().getDateTime() + ",");
				auctionWriter.print(((Painting) auctions.get(i).getArtwork()).getWidth() + ",");
				auctionWriter.print(((Painting) auctions.get(i).getArtwork()).getHeight() + ",");
				auctionWriter.print(auctions.get(i).isComplete() + ",");
				
				for (int j = 0; j < auctions.get(i).getAllBids().size(); j++) {
					auctionWriter.print(".,");
					auctionWriter.print(auctions.get(i).getAllBids().get(j).getAmount() + ",");
					auctionWriter.print(auctions.get(i).getAllBids().get(j).getDate() + ",");
					
					if (j == auctions.get(i).getAllBids().size() -1) {
						auctionWriter.print(auctions.get(i).getAllBids().get(j).getBidder().getUsername());
					} else {
						auctionWriter.print(auctions.get(i).getAllBids().get(j).getBidder().getUsername() + ",");
					}
				}
				//finishes the first line of data and moves onto the next line.
				auctionWriter.println(""); 
			} else {
				auctionWriter.print("Sculpture,");
				auctionWriter.print(auctions.get(i).getArtwork().getTitle() + ",");
				auctionWriter.print(auctions.get(i).getArtwork().getDescription() + ",");
				auctionWriter.print(auctions.get(i).getArtwork().getMainPhoto() + ",");
				auctionWriter.print(auctions.get(i).getArtwork().getCreatorName() + ",");
				auctionWriter.print(auctions.get(i).getArtwork().getCreationYear() + ",");
				auctionWriter.print(auctions.get(i).getArtwork().getPrice() + ",");
				auctionWriter.print(auctions.get(i).getArtwork().getDateTime() + ",");
				auctionWriter.print(((Sculpture) auctions.get(i).getArtwork()).getWidth() + ",");
				auctionWriter.print(((Sculpture) auctions.get(i).getArtwork()).getHeight() + ",");
				auctionWriter.print(((Sculpture) auctions.get(i).getArtwork()).getDepth() + ",");
				auctionWriter.print(((Sculpture) auctions.get(i).getArtwork()).getMaterial() + ",");
				
				//writes the extra photos to file.
				for (int j = 0; j < ((Sculpture) auctions.get(i).getArtwork()).getExtraPhotos().size(); j++) {
					auctionWriter.print(((Sculpture) auctions.get(i).getArtwork()).getExtraPhotos().get(j) + ",");
				}
				auctionWriter.print("!,");
				
				auctionWriter.print(auctions.get(i).isComplete() + ",");
				
				for (int j = 0;j < auctions.get(i).getAllBids().size(); j++) {
					auctionWriter.print(".,");
					auctionWriter.print(auctions.get(i).getAllBids().get(j).getAmount() + ",");
					auctionWriter.print(auctions.get(i).getAllBids().get(j).getDate() + ",");
					
					if (j == auctions.get(i).getAllBids().size() -1) {
						auctionWriter.print(auctions.get(i).getAllBids().get(j).getBidder().getUsername());
					} else {
						auctionWriter.print(auctions.get(i).getAllBids().get(j).getBidder().getUsername() + ",");
					}
					
				}
				//finishes the first line of data and moves onto the next line.
				auctionWriter.println(""); 
			}
		}
		auctionWriter.close();
	}
}
