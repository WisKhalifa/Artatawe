/**
 * @author Liam Cooper - 916153
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Artwork {

  private String title;
  private String description;
  private String photo;
  private String creatorsName;
  private int creationYear;
  private double price;
  private int bidTotal;
  private Date dateTime;

  public Artwork(String title, String description, String photo, String creatorsName,
    int creationYear, double price, int bidTotal) {

      this.title = title;
      this.description = description;
      this.photo = photo;
      this.creatorsName = creatorsName;
      this.creationYear = creationYear;
      this.price = price;
      this.bidTotal = bidTotal;
      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      dateTime = new Date();
      dateFormat.format(dateTime);

  }

  public Date getDateTime() {
          return dateTime;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getMainPhoto() {
    return photo;
  }

  public String getCreatorName() {
    return creatorsName;
  }

  public int getCreationYear() {
    return creationYear;
  }

  public double getPrice() {
    return price;
  }

  public int getBidTotal() {
    return bidTotal;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setMainPhoto(String photo) {
    this.photo = photo;
  }

  public void setCreatorName(String creatorsName) {
    this.creatorsName = creatorsName;
  }

  public void setCreationYear(int creationYear) {
    this.creationYear = creationYear;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setBidTotal (int bidTotal) {
    this.bidTotal = bidTotal;
  }

  /*public String toString() {
    return "\nTitle: " + getTitle() + "\nDescription: " + getDescription() + "\nPhoto: " + getMainPhoto() +
      "\nCreators Name: " + getCreatorName() + "\nYear: " + getCreationYear() + "\nPrice: £" + getPrice() +
        "\nbid total: " + getBidTotal();
  }*/
}
