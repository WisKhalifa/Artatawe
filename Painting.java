/**
 * this class inherits data from artwork and creates paintings 
 * @author Liam Cooper - 916153
 * @version 1.2
 */
public class Painting extends Artwork {

  private int width;
  private int height;

  /**
   * constructs a painting
   * @param title
   * @param description
   * @param photo
   * @param creatorsName
   * @param creationYear
   * @param price
   * @param dateTime
   * @param width
   * @param height
   */
  public Painting(String title, String description, String photo, String creatorsName,
    int creationYear, double price, String dateTime, int width, int height) {

    super(title, description, photo, creatorsName, creationYear, price, dateTime);
    this.width = width;
    this.height = height;
  }

  /**
   * gets the width of the painting 
   * @return width 
   */
  public int getWidth() {
    return width;
  }

  /**
   * gets the height of the painting 
   * @return height
   */
  public int getHeight() {
    return height;
  }

  /**
   * sets the width of the painting 
   * @param width
   */
  public void setWidth (int width) {
    this.width = width;
  }

  /**
   * sets the height of the painting 
   * @param height
   */
  public void setHeight (int height) {
    this.height = height;
  }

}
