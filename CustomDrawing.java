import java.io.File;
import javafx.scene.canvas.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * Custom Draw your profile picture with a few functionalities of paint.
 * @author Elias Nemr, 961625
 */
public class CustomDrawing implements EventHandler {

    private final int sceneWidth = 700;
    private final int sceneHeight = 400;
    private final int canvasWidth = 700;
    private final int canvasHeight = 400;
    private final int slider_MIN = 1;
    private final int slider_MAX = 100;
    private final int borderStrokeXY = 1;
    private String imageDirectory;

    /**
     * Creates the GUI for allowing the user to create a custom drawing
     * which they could then use as their profile picture.
     * @param primaryStage
     * @param currentProfile The user currently logged in.
     */
    public void makeCustomDrawing(Stage primaryStage, Profile currentProfile) {
    	primaryStage.getIcons().add(new Image("applicationIcon.png"));
        BorderPane root = new BorderPane();
        StackPane holdCanvas = new StackPane();
        TilePane holdFunctions = new TilePane();
        Button backButton = new Button();
        CheckBox eraserCB = new CheckBox("Eraser");
        VBox backButtonBox = new VBox();
        Image backButtonI = new Image("back2.png");
        backButton.setGraphic(new ImageView(backButtonI));
        backButtonBox.getChildren().addAll(backButton);
        Button saveBtn = new Button("Set as Profile");
        
        Canvas canvas = new Canvas(canvasWidth,canvasHeight);
        GraphicsContext brush = canvas.getGraphicsContext2D();
        GraphicsContext border = canvas.getGraphicsContext2D();
        ColorPicker cp = new ColorPicker(); 
        Slider slider = new Slider();
        cp.setValue(Color.BLACK);
        brush.setStroke(Color.BLACK);
        brush.setLineWidth(1);
        holdCanvas.setPadding(new Insets(0,0,0,0));
        holdCanvas.setStyle("-fx-background-color: white");
        holdCanvas.setAlignment(Pos.CENTER);
        holdCanvas.getChildren().add(canvas);
        holdFunctions.getChildren().addAll(backButtonBox, cp, eraserCB, slider, saveBtn);
        holdFunctions.setAlignment(Pos.CENTER);
        
        root.setTop(holdFunctions);
        root.setCenter(holdCanvas);
            
        cp.setOnAction(e-> { 
            brush.setStroke(cp.getValue());
        });
        
        slider.setMin(slider_MIN);
        slider.setMax(slider_MAX);
        slider.valueProperty().addListener(e->{ 
            brush.setLineWidth(slider.getValue());
        });
        /*
         * If eraser is off and mouse is clicked then we can particle trace
         *  circles,
         * If eraser is On then we can erase.
         * Resizeable by the slider.
         */
        canvas.setOnMousePressed(e -> {
            if(eraserCB.isSelected()){
                brush.clearRect(e.getX(), e.getY(), 
                        slider.getValue(),slider.getValue());
            } else {
                brush.setStroke(cp.getValue());
                brush.fillOval(e.getX(), e.getY(), slider.getValue(), slider.getValue());
            }
        });   
        /*
         * If mouse is dragged when eraserCB we will just have a particle trace
         * otherwise we will erase, resizeable with the Slider for both
         * drawing and erasing.
         */
        canvas.setOnMouseDragged(e-> { 
            if(eraserCB.isSelected()) {
                brush.clearRect(e.getX(), e.getY(), 
                        slider.getValue(), slider.getValue());
            } else {
                brush.setStroke(cp.getValue());
                brush.setFill(cp.getValue());
                brush.fillOval(e.getX(), e.getY(), 
                        slider.getValue(), slider.getValue());
            }
        });
        /*
         * Back button which returns us back to the main profile picture GUI
         */
        backButton.setOnAction((ActionEvent event) -> {
            System.out.println("Abertawe: Back To Homepage");
            ProfilePicture run = new ProfilePicture();
            Stage backStage = new Stage();
            run.runProgram(backStage, currentProfile);
            primaryStage.close();
        });           
        /*
        * This EventHandler allows that when a user clicks on the saveBtn
        * then it converts the JavaFX Canvas to a .PNG and replaces users 
        * ImagePATH
        */
        saveBtn.setOnAction((ActionEvent event)-> {
            try {
                System.out.println("Abertawe: Saving...");
                String endExtension = "_CustomDrawing.png";
                imageDirectory = currentProfile.getUsername() + endExtension;
                File file = new File(imageDirectory);
                System.out.println("Directory saved at: " + imageDirectory);
                //currentProfile.setImagePath(imageDirectory);
                Image snapshot = canvas.snapshot(null, null);
                ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png"
                        , file);
                currentProfile.setImagePath(imageDirectory);
            } catch(Exception e) {
            System.out.println("Failed to save image: " + e);
          }
            setImageAlert();

        });
        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        primaryStage.setTitle("Artatawe: Drawing a custom profile");
        primaryStage.setScene(scene);
        primaryStage.show();    
    }
    
    /**
     * Keeping this empty as we have to have all methods of a interface Class
     * EventHandler
     * @author Elias Nemr, 961625
     */
    @Override
    public void handle(Event event) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    /**
    * This method displays an alert notifying the user that their profile picture
    * has been set and saved.
    * @author Elias Nemr, 961625
    */
    public void setImageAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Profile Picture.");
        alert.setHeaderText("Profile Picture saved and set!");
        alert.setContentText("Enjoy your new Profile picture!");

        alert.showAndWait();
    }  
}