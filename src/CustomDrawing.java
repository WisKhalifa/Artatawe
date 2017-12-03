/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package profilepicture;


import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.*;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author elias
 */
public class CustomDrawing extends Application implements EventHandler{

    private final int sceneWidth = 700;
    private final int sceneHeight = 400;
    private final int canvasWidth = 700;
    private final int canvasHeight = 400;
    public static String imageDirectory2;
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        StackPane holdCanvas = new StackPane();
        TilePane holdFunctions = new TilePane();
        Button backButton = new Button();
        Button eraser = new Button("Erase");
        Button fillButton = new Button("Draw N Fill");
        Button drawLine = new Button("Line");
        VBox backButtonBox = new VBox();
        Image backButtonI = new Image(getClass().getResourceAsStream("back2.png"));
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
            ProfilePicture run = new ProfilePicture();
            run.start(primaryStage);
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
            String directoryOfDrawing = file.toString();
            System.out.println("Directory saved at: "+directoryOfDrawing);
            
            ProfilePicture.imageDirectory = "Artatawe\\src\\artworks\\CustomDrawing.png";
            
            //+Profile.username 
            try {
                
                WritableImage wim = new WritableImage(canvasWidth, canvasHeight);
                canvas.snapshot(null, wim);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(wim, null);
                
                ImageIO.write(renderedImage, "png", file);
                
                  
                
            } catch(Exception e){
                
            }
        });
         

        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        primaryStage.setTitle("Artatawe: Drawing a custom profile");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
        
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(Event event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    public void fillDraw(GraphicsContext gc, ColorPicker cp) {
        
        gc.setFill(cp.getValue());//text color
        gc.setStroke(cp.getValue());//text color
       
        gc.fill();
        gc.stroke();
    }  

    
}
    
    
    
    
    
    
    
    
    
    
    

