import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * Main page for the Profile Picture, has two buttons you choose to enter either
 * a Custom Drawing or Avatars
 * @author Elias Nemr, 961625
 */
public class ProfilePicture {
    
    private final static int sceneWidth = 400;
    private final static int sceneHeight = 400;
    private final int gridGap = 20;

    public static void runProgram(Stage primaryStage, Profile currentProfile) {
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
                Avatars.chooseAvatar(primaryStage, currentProfile);
            }
        });
        btnCustomDrawing.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Abertawe: Custom Drawing");
                CustomDrawing.makeCustomDrawing(primaryStage, currentProfile);
            }
        });
        //End of Event Handling
       
        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        
        primaryStage.setTitle("Artatawe: Choosing a Profile Picture");
        primaryStage.setScene(scene);
        primaryStage.show();
        

    }

    /*
    public static void main(String[] args) {
        launch(args);
        
    }*/
    
}
