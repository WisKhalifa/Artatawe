/**
 * this class inherits data from artwork and creates a sculpture
 * @author Liam Cooper - 916153
 * @version 1.2
 */
import java.util.ArrayList;
public class Sculpture extends Artwork {

  private int width;
  private int height;
  private int depth;
  private String material;
  private ArrayList<String> extraPhotos;
  
  /**
   * Constructs a Sculpture 
   * @param title
   * @param description
   * @param photo
   * @param creatorsName
   * @param creationYear
   * @param price
   * @param dateTime
   * @param width
   * @param height
   * @param depth
   * @param material
   */
  public Sculpture(String title, String description, String photo, String creatorsName,
    int creationYear, double price, String dateTime, int width, int height,
      int depth, String material) {

    super(title, description, photo, creatorsName, creationYear, price, dateTime);
    this.width = width;
    this.height = height;
    this.depth = depth;
    this.material = material;
    extraPhotos = new ArrayList<>();
  }

  /**
   * gets the width of the sculpture
   * @return width
   */
  public int getWidth() {
    return width;
  }

  /**
   * gets the height of the sculpture
   * @return height
   */
  public int getHeight() {
    return height;
  }

  /**
   * gets the depth of the sculpture
   * @return depth
   */
  public int getDepth() {
    return depth;
  }
  
  /**
   * gets the material the sculpture is made of
   * @return material
   */
  public String getMaterial() {
    return material;
  }

  /**
   * gets the extra photos of the sculpture
   * @return extraPhotos
   */
  public ArrayList<String> getExtraPhotos() {
    return extraPhotos;
  }

  /**
   * sets the width of the sculpture
   * @param width
   */
  public void setWidth (int width) {
    this.width = width;
  }
  
  /**
   * sets the height of the sculpture
   * @param height
   */
  public void setHeight (int height) {
    this.height = height;
  }
  
  /**
   * sets the depth of the sculpture
   * @param depth
   */
  public void setDepth (int depth) {
    this.depth = depth;
  }

  /**
   * sets the material ths sculpture is made of
   * @param material
   */
  public void setMaterial (String material) {
    this.material = material;
  }

  /**
   * sets the extra photos of the sculpture
   * @param extraPhotos
   */
  public void setExtraPhotos (ArrayList<String> extraPhotos) {
    this.extraPhotos = extraPhotos;
  }
  
  /**
   * Adds an extra Photo to the already existing ExtraPhotos
   * @param extraPhotos
   */
  public void addExtraPhotos (String extraPhoto) {
    extraPhotos.add(extraPhoto);
  }

}
