import java.util.ArrayList;

/**
 * Profile - creates profile objects
 * @author Wisam Halawi - 903666
 */
public class Profile{
  private String username; //stores username
  private String firstName; //stores first name
  private String lastName; //stores last name
  private String telephone; //stores telephone number
  private String address; //stores first line of address
  private String postcode; //stores postcode
  private String imagePath = "ImageDefault.png"; //stores profile image path, test.jpg is default
  private ArrayList<String> favourites;
  
  /**
   * creates a profile object by taking in a:
   * @param username A string containing the username 
   * @param firstName A string containing the first name 
   * @param lastName A string containing the last name
   * @param telephone A string containing the telephone number 
   * @param address A string containing the address 
   * @param postcode A string containing the postcode 
   * @param imagepath A string containing the image path for the profile picture
   * @exception Any exception
   * @return A profile object
   */
  public Profile (String username, String firstName, String lastName,
		  String telephone, String address, String postcode, String imagePath){
	  this.username = username;
	  this.firstName = firstName;
	  this.lastName = lastName;
	  this.telephone = telephone;
	  this.address = address;
	  this.postcode = postcode;
	  this.imagePath = imagePath;
	  favourites = new ArrayList<String>();
  }
  
  /**
   * Gets the profile username.
   * @return username
   */
  public String getUsername(){
	  return username;
  }
  
  /**
   * Gets the profile first name.
   * @return firstname
   */
  public String getFirstName(){
	  return firstName;
  }
  
  /**
   * Gets the profile's last name.
   * @return lastname
   */
  public String getLastName(){
	  return lastName;
  }
  
  /**
   * Gets the profiles telephone number.
   * @return telephone
   */
  public String getTelephone(){
	  return telephone;
  }
  
  /**
   * Gets the profiles first address.
   * @return address
   */
  public String getFirstAddress(){
	  return address;
  }
  
  /**
   * Gets the profiles post code
   * @return postcode
   */
  public String getPostcode(){
	  return postcode;
  }
  
  /**
   * Gets the image path of the profile.
   * @return imagePath
   */
  public String getImagePath(){
	  return imagePath;
  }
  
  /**
   * This gets the list of favourites from a profile.
   * @return
   */
  public ArrayList<String> getFavourites(){
	  return favourites;
  }
  
  /**
   * This sets the profiles username.
   * @param username The username.
   */
  public void setUsername(String username){
	  this.username = username;
  }
  
  /**
   * This sets the profile first name.
   * @param firstName The firstname.
   */
  public void setFirstName(String firstName){
	  this.firstName = firstName;
  }
  
  /**
   * This sets the profile last name.
   * @param lastName The lastname.
   */
  public void setLastName(String lastName){
	  this.lastName = lastName;
  }
  
  /**
   * This sets the profiles telephone number.
   * @param telephone The telephone number.
   */
  public void setTelephone(String telephone){
	  this.telephone = telephone;
  }
  
  /**
   * This sets the first address.
   * @param address First address.
   */
  public void setFirstAddress(String address){
	  this.address = address;
  }
  
  /**
   * This sets the postcode of the profile.
   * @param postcode The postcode.
   */
  public void setPostcode(String postcode){
	  this.postcode = postcode;
  }
  
  /**
   * This sets the image path of the profile.
   * @param imagePath The imagePath.
   */
  public void setImagePath(String imagePath){
	  this.imagePath = imagePath;
  }
  
  /**
   * This adds a single profile username to the profile's
   * favourites.
   * @param username The username being added to the list.
   */
  public void addFavourite(String username) {
	  favourites.add(username);
  }
  
  /**
   * Removes a username from the profiles favourites.
   * @param username
   */
  public void removeFavourite(String username) {
	  favourites.remove(username);
  }
}
