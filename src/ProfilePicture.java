/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package profilepicture;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 *
 * @author Elias Nemr, 961625
 * Avatar pictures GUI, profile picture and a custom drawing GUI
 */
public class ProfilePicture extends Application {
    
    private final int sceneWidth = 400;
    private final int sceneHeight = 400;
    private final int gridGap = 20;
    public static String imageDirectory = "ImageDefault.png";
    
    

    public static String getImageDirectory() {
        return imageDirectory;
    }

    public static void setImageDirectory(String imageDirectory) {
        ProfilePicture.imageDirectory = imageDirectory;
    }
    
    
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        TilePane tileButtons = new TilePane();
        TilePane tileLabel = new TilePane();
        
        Label intro = new Label("Please choose a profile picture.");
        Button btnSetAvatar = new Button("Choose an Avatar for your Profile");
        Button btnCustomDrawing = new Button("Draw your Profile Picture");
        tileButtons.getChildren().addAll(btnSetAvatar, btnCustomDrawing);
        tileButtons.setAlignment(Pos.CENTER);
        tileLabel.getChildren().add(intro);
        tileLabel.setAlignment(Pos.TOP_CENTER);
        
        
        root.setTop(tileLabel);
        root.setCenter(tileButtons);
        

        //Event handling the buttons
        btnSetAvatar.setOnAction(new EventHandler<ActionEvent>() {     
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Abertawe: Profile Picture");
                Avatars run = new Avatars();
                run.start(primaryStage);
            }
        });
        btnCustomDrawing.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Abertawe: Custom Drawing");
                CustomDrawing run = new CustomDrawing();
                run.start(primaryStage);
            }
        });
        //End of Event Handling
       
        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        
        primaryStage.setTitle("Artatawe: Choosing a Profile Picture");
        primaryStage.setScene(scene);
        primaryStage.show();
        

    }
        

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
