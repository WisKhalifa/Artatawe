/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package profilepicture;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 *
 * @author elias
 */
public class Avatars extends Application implements EventHandler {
    
    private final int sceneWidth = 500;
    private final int sceneHeight = 400;  
    private final int buttonWidth = 40;
    private final int buttonHeight = 200;
    private final int gridGap = 20;
        
    public static String imageDirectory = "ImageDefault.png";

    
    public static Button currentProfilePic = new Button(); //To be implemented in the Profile GUI?
   
    
    
    @Override
    public void start(Stage primaryStage) {
        currentProfilePic.setGraphic(new ImageView(imageDirectory));
        //Creating some buttons so i can put the Avatars in there
        Button imageButtonB = new Button(); // Back Button
        Button imageButton1 = new Button();
        Button imageButton2 = new Button();
        Button imageButton3 = new Button();
        Button imageButton4 = new Button();
        Button imageButton5 = new Button();
        Button imageButtonD = new Button(); // Default Image Button
        Button setAvatar = new Button("Set Avatar");
        //Create Images

        Image image1 = new Image(getClass().getResourceAsStream("ImageOne."
                + "png"));
        Image image2 = new Image(getClass().getResourceAsStream("Image2.png"));
        Image image3 = new Image(getClass().getResourceAsStream("Image3.png"));
        Image image4 = new Image(getClass().getResourceAsStream("Image4.png"));
        Image image5 = new Image(getClass().getResourceAsStream("Image5.png"));
        Image imageBack = new Image(getClass().getResourceAsStream("back2.png"));
        //Inserting the images as a node into my buttons
        imageButton1.setGraphic(new ImageView(image1));
        imageButton2.setGraphic(new ImageView(image2));
        imageButton3.setGraphic(new ImageView(image3));
        imageButton4.setGraphic(new ImageView(image4));
        imageButton5.setGraphic(new ImageView(image5));
        imageButtonD.setGraphic(new ImageView(imageDirectory));
        imageButtonB.setGraphic(new ImageView(imageBack));
        
        
        //Creating an HBox, VBox, BorderPane to put all my objects inside
        BorderPane primaryPane = new BorderPane();
        TilePane tileCurrent = new TilePane();
        TilePane avatarsTile = new TilePane();
        TilePane backButtonTile = new TilePane();
        Label label = new Label("Current Profile Picture set:");     
        
        avatarsTile.setAlignment(Pos.CENTER);
        avatarsTile.getChildren().addAll(imageButton1, imageButton2,
                imageButton3, imageButton4, imageButton5, imageButtonD, setAvatar);    
        
        backButtonTile.setAlignment(Pos.TOP_LEFT);
        backButtonTile.setPadding(new Insets(10,0,0,0));
        backButtonTile.getChildren().add(imageButtonB);
        
        tileCurrent.setAlignment(Pos.CENTER);
        tileCurrent.getChildren().addAll(label, currentProfilePic);
        
        
        primaryPane.setTop(backButtonTile);
        primaryPane.setCenter(tileCurrent);
        primaryPane.setBottom(avatarsTile);

        //EventHandling   
        imageButton1.setOnAction((ActionEvent event) -> {
            
            System.out.println("Abertawe: Choosing an Avatar");
            Image image = 
                    new Image(getClass().getResourceAsStream("ImageOne.png"));
            
            currentProfilePic.setGraphic(new ImageView(image)); 
            imageDirectory = "ImageOne.png";
            
        });
        imageButton2.setOnAction((ActionEvent event) -> {
            System.out.println("Abertawe: Choosing an Avatar");
            Image image = 
                    new Image(getClass().getResourceAsStream("Image2.png"));
            
            currentProfilePic.setGraphic(new ImageView(image));
            imageDirectory = "Image2.png";
            
        });       
        imageButton3.setOnAction((ActionEvent event) -> {
            System.out.println("Abertawe: Choosing an Avatar");
            Image image = 
                    new Image(getClass().getResourceAsStream("Image3.png"));
            
            currentProfilePic.setGraphic(new ImageView(image)); 
            imageDirectory = "Image3.png";
        });        
        imageButton4.setOnAction((ActionEvent event) -> {
            System.out.println("Abertawe: Choosing an Avatar");
            Image image = 
                    new Image(getClass().getResourceAsStream("Image4.png"));
            
            currentProfilePic.setGraphic(new ImageView(image)); 
            imageDirectory = "Image4.png";
        });        
        imageButton5.setOnAction((ActionEvent event) -> {
            System.out.println("Abertawe: Choosing an Avatar");
            Image image = 
                    new Image(getClass().getResourceAsStream("Image5.png"));
            
            currentProfilePic.setGraphic(new ImageView(image)); 
            imageDirectory = "Image5.png";
        });              
        imageButtonD.setOnAction((ActionEvent event) -> {
            System.out.println("Abertawe: Choosing an Avatar");
            Image image = 
                    new Image(getClass().getResourceAsStream("ImageDefault.png"));
            
            currentProfilePic.setGraphic(new ImageView(image)); 
            imageDirectory = "ImageDefault.png";
        });   
        imageButtonB.setOnAction((ActionEvent event) -> {
            System.out.println("Abertawe: Back To Homepage");
            ProfilePicture run = new ProfilePicture();
            run.start(primaryStage);
        });   
        
        setAvatar.setOnAction((ActionEvent event) -> {
            
            System.out.println("Abertawe: Avatar set as profile picture.");
            currentProfilePic.setGraphic(currentProfilePic.getGraphic());
            
            ProfilePicture.imageDirectory = imageDirectory;
            setImageAlert();
        });
        
       
       
        
        //End of EventHandling
        
        
        Scene scene = new Scene(primaryPane, sceneWidth, sceneHeight);
        primaryStage.setTitle("Abertawe: Choosing an Avatar");
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

    
    public void setImageAlert() {
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Profile Picture.");
        alert.setHeaderText("Profile Picture saved and set!");
        alert.setContentText("Enjoy your new Profile picture!");

        alert.showAndWait();
 
    }    
        
}
