/**
 * this class provides the basic data for all artwork's 
 * @author Liam Cooper - 916153
 * @version 1.2
 */

public abstract class Artwork {

  private String title;
  private String description;
  private String photo;
  private String creatorsName;
  private int creationYear;
  private double price;
  private int bidTotal;
  private String dateTime;
  
  /**
   * constructs artwork
   * @param title
   * @param description
   * @param photo
   * @param creatorsName
   * @param creationYear
   * @param price
   * @param dateTime
   */
  public Artwork(String title, String description, String photo, String creatorsName,
    int creationYear, double price, String dateTime) {

      this.title = title;
      this.description = description;
      this.photo = photo;
      this.creatorsName = creatorsName;
      this.creationYear = creationYear;
      this.price = price;
      this.bidTotal = 0;
      this.dateTime = dateTime;

  }
  
  /**
   * gets the date and time of when auction was placed
   * @return dateTime
   */
  public String getDateTime() {
    return dateTime;
  }
  
  /**
   * gets the title of the artwork
   * @return title
   */
  public String getTitle() {
    return title;
  }
  
  /**
   * gets the description of the artwork
   * @return description
   */
  public String getDescription() {
    return description;
  }

  /**
   * gets the main photo of the artwork
   * @return photo
   */
  public String getMainPhoto() {
    return photo;
  }

  /**
   * gets the name of the artworks creator
   * @return creatorsName
   */
  public String getCreatorName() {
    return creatorsName;
  }

  /**
   * gets the year the artwork was created
   * @return creationYear
   */
  public int getCreationYear() {
    return creationYear;
  }

  /**
   * gets the price of the artwork
   * @return price
   */
  public double getPrice() {
    return price;
  }

  /**
   * gets the maximum amount of bids for the artwork 
   * @return bidTotal 
   */
  public int getBidTotal() {
    return bidTotal;
  }

  /**
   * sets the date and time of when the artwork was placed 
   * @param dateTime
   */
  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
  }

  /**
   * sets the title of the artwork 
   * @param title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * sets the description of the artwork 
   * @param description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * sets the photo of the artwork
   * @param photo
   */
  public void setMainPhoto(String photo) {
    this.photo = photo;
  }

  /**
   * sets the name of the user that created the artwork 
   * @param creatorsName
   */
  public void setCreatorName(String creatorsName) {
    this.creatorsName = creatorsName;
  }

  /**
   * sets the year of which the artwork was created 
   * @param creationYear
   */
  public void setCreationYear(int creationYear) {
    this.creationYear = creationYear;
  }

  /**
   * sets the price of the artwork 
   * @param price
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * sets the maximum amount of bids allowed on the artwork
   * @param bidTotal
   */
  public void setBidTotal (int bidTotal) {
    this.bidTotal = bidTotal;
  }

}
