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
  private String imagePath = "test.jpg"; //stores profile image path, test.jpg is default
  
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
  }
  
  public String getUsername(){
	  return username;
  }
  public String getFirstName(){
	  return firstName;
  }
  public String getLastName(){
	  return lastName;
  }
  public String getTelephone(){
	  return telephone;
  }
  public String getFirstAddress(){
	  return address;
  }
  public String getPostcode(){
	  return postcode;
  }
  public String getImagePath(){
	  return imagePath;
  }
  
  public void setUsername(String username){
	  this.username = username;
  }
  public void setFirstName(String firstName){
	  this.firstName = firstName;
  }
  public void setLastName(String lastName){
	  this.lastName = lastName;
  }
  public void setTelephone(String telephone){
	  this.telephone = telephone;
  }
  public void setFirstAddress(String address){
	  this.address = address;
  }
  public void setPostcode(String postcode){
	  this.postcode = postcode;
  }
  public void setImagePath(String imagePath){
	  this.imagePath = imagePath;
  }
  
  /**
   * prints out the details of a profile object
   */
  public String toString(){
	  String result = "";
	  result += "\n Username: " + username +
			  "\n First name: " + firstName +
			  "\n Last name: " + lastName +
			  "\n Telephone number: " + telephone +
			  "\n Address: " + address +
			  "\n Postcode: " + postcode;
	  return result;
  }
  
}
