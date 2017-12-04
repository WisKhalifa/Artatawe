import java.awt.image.RenderedImage;
import java.io.File;
import javafx.scene.canvas.*;
import javafx.application.Application;
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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * Custom Draw your profile picture with a few functionalities of paint.
 * @author Elias Nemr, 961625
 */
public class CustomDrawing implements EventHandler {

    private final static int sceneWidth = 700;
    private final static int sceneHeight = 400;
    private final static int canvasWidth = 700;
    private final static int canvasHeight = 400;
    private static String imageDirectory;
    Profile currentProfile = new Profile();
    
    public static void makeCustomDrawing(Stage primaryStage, Profile currentProfile) {
        BorderPane root = new BorderPane();
        StackPane holdCanvas = new StackPane();
        TilePane holdFunctions = new TilePane();
        Button backButton = new Button();
        Button eraser = new Button("Erase");
        Button fillButton = new Button("Draw N Fill");
        Button drawLine = new Button("Line");
        VBox backButtonBox = new VBox();
        Image backButtonI = new Image("back2.png");
        backButton.setGraphic(new ImageView(backButtonI));
        backButtonBox.getChildren().addAll(backButton);
        Button saveBtn = new Button("Set as Profile");
        
        Canvas canvas = new Canvas(canvasWidth,canvasHeight);
        GraphicsContext brush = canvas.getGraphicsContext2D();
        ColorPicker cp = new ColorPicker(); 
        Slider slider = new Slider();
        cp.setValue(Color.BLACK);
        brush.setStroke(Color.BLACK);
        brush.setLineWidth(1);
        Line line = new Line();
        
        holdCanvas.setPadding(new Insets(0,0,0,0));
        holdCanvas.setStyle("-fx-background-color: white");
        holdCanvas.setAlignment(Pos.CENTER);
        holdCanvas.getChildren().add(canvas);
        holdFunctions.getChildren().addAll(cp, fillButton,
                eraser, slider, saveBtn);
        holdFunctions.setAlignment(Pos.CENTER);
        
        root.setTop(holdFunctions);
        root.setLeft(backButtonBox);
        root.setCenter(holdCanvas);
            
        cp.setOnAction(e-> { 
            brush.setStroke(cp.getValue());
        });
        
        slider.setMin(1);
        slider.setMax(100);
        slider.valueProperty().addListener(e->{ 
            brush.setLineWidth(slider.getValue());
        });
        
        canvas.setOnMousePressed(e -> {  
            brush.beginPath();
            brush.lineTo(e.getSceneX(), e.getSceneY());
            brush.stroke();
            
        });

        canvas.setOnMouseDragged(e-> { 
            brush.lineTo(e.getSceneX(), e.getSceneY());
            brush.stroke();  
        });
        backButton.setOnAction((ActionEvent event) -> {
            System.out.println("Abertawe: Back To Homepage");
            ProfilePicture.runProgram(primaryStage, currentProfile);
        });   
        eraser.setOnAction((ActionEvent event) -> { 
            brush.setStroke(Color.WHITE);
            brush.setLineWidth(50);
        });
        
        fillButton.setOnAction((ActionEvent event) -> { 
            fillDraw(brush,cp);

        });

        saveBtn.setOnAction((ActionEvent event)-> {
            System.out.println("Abertawe: Saving...");
            File file = new File("Artatawe\\src\\artworks\\CustomDrawing.png");
            imageDirectory = currentProfile.getFirstName() + file.toString();
            System.out.println("Directory saved at: "+ imageDirectory);
            currentProfile.setImagePath(imageDirectory);
            try {
                
                WritableImage wim = new WritableImage(canvasWidth, canvasHeight);
                canvas.snapshot(null, wim);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(wim, null);
                ImageIO.write(renderedImage, "png", file);
                
            } catch(Exception e){
            
          }
            setImageAlert();
        });
         

        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        primaryStage.setTitle("Artatawe: Drawing a custom profile");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    /*    
    public static void main(String[] args) {
        launch(args);
    }*/

    @Override
    public void handle(Event event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
    * This method displays an alert notifying the user that their profile picture
    * has been set and saved.
    * @author Elias Nemr, 961625
    */
    public static void setImageAlert() {
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Profile Picture.");
        alert.setHeaderText("Profile Picture saved and set!");
        alert.setContentText("Enjoy your new Profile picture!");

        alert.showAndWait();
 
    }  

    /**
     * 
     * This class represents filling up an already brush stroked drawing
     * With Color which is set by the default Color chooser/
     * @author Elias Nemr, 961625
     */
    public static void fillDraw(GraphicsContext gc, ColorPicker cp) {
        
        gc.setFill(cp.getValue());//text color
        gc.setStroke(cp.getValue());//text color
       
        gc.fill();
        gc.stroke();
    }
}