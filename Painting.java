/**
 * @author Liam Cooper - 916153
 */
public class Painting extends Artwork {

  private int width;
  private int height;

  public Painting(String title, String description, String photo, String creatorsName,
    int creationYear, double price, int bidTotal, int width, int height) {

    super(title, description, photo, creatorsName, creationYear, price, bidTotal);
    this.width = width;
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public void setWidth (int width) {
    this.width = width;
  }

  public void setHeight (int height) {
    this.height = height;
  }

  /*public String toString() {
    return "\nPainting-" + "\nTitle: " + getTitle() + "\nDescription: " + getDescription() + "\nPhoto: " + getMainPhoto() +
      "\nCreators Name: " + getCreatorName() + "\nYear: " + getCreationYear() + "\nPrice: £" + getPrice() +
        "\nbid total: " + getBidTotal() + "\nWidth: " + getWidth() +
          "\nHeight: " + getHeight();
  }*/

}
