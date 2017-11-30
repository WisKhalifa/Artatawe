/**
 * @author Liam Cooper - 916153
 */
import java.util.Arrays;
public class Sculpture extends Artwork {

  private int width;
  private int height;
  private int depth;
  private String material;
  private String[] extraPhotos;

  public Sculpture(String title, String description, String photo, String creatorsName,
    int creationYear, double price, int bidTotal, int width, int height,
      int depth, String material, String[] extraPhotos) {

    super(title, description, photo, creatorsName, creationYear, price, bidTotal);
    this.width = width;
    this.height = height;
    this.depth = depth;
    this.material = material;
    this.extraPhotos = extraPhotos;

  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int getDepth() {
    return depth;
  }

  public String getMaterial() {
    return material;
  }

  public String[] getExtraPhotos() {
    return extraPhotos;
  }

  public void setWidth (int width) {
    this.width = width;
  }

  public void setHeight (int height) {
    this.height = height;
  }

  public void setDepth (int depth) {
    this.depth = depth;
  }

  public void setMaterial (String material) {
    this.material = material;
  }

  public void setExtraPhotos (String[] extraPhotos) {
    this.extraPhotos = extraPhotos;
  }

  /*public String toString() {
    return "\nSculpture -" + "\nTitle: " + getTitle() + "\nDescription: " + getDescription() + "\nPhoto: " + getMainPhoto() +
      "\nCreators Name: " + getCreatorName() + "\nYear: " + getCreationYear() + "\nPrice: £" + getPrice() +
        "\nbid total: " + getBidTotal() + "\nWidth: " + getWidth() +
          "\nHeight: " + getHeight() + "\nDepth: " + getDepth() + "\nMaterial: " + getMaterial() + "\nPhotos: " +
            getExtraPhotos();
  }*/

}
