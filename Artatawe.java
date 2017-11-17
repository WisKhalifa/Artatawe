import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Artatawe extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		//stuff to do with the stage
		primaryStage.setTitle("Artatawe");
		//primaryStage.getIcons().add(new Image("sureLogoIcon.png"));
		
		StackPane loginStackPane = new StackPane();
		
		//Editing the VBox
		VBox loginVBox = new VBox();
		loginVBox.setAlignment(javafx.geometry.Pos.CENTER);
		
		//editing the borderpane
		BorderPane loginItems = new BorderPane();
		loginItems.setMaxHeight(100);
		loginItems.setMinHeight(100);
		loginItems.setMaxWidth(300);
		loginItems.setMinWidth(300);
		loginItems.setId("loginBox");
		
		//Create Image
		Image loginBackgroundImage = new Image("swanseaBay.png");
		ImageView loginImage = new ImageView();
		loginImage.setEffect(new GaussianBlur());
		loginImage.setImage(loginBackgroundImage);
		
		//adds image to Stackpane
		loginStackPane.getChildren().add(loginImage);
		
		//create title Label
		Label titleLabel = new Label("Artatawe");
		titleLabel.setId("titleLabel");
		
		//Edit textField
		TextField usernameTextField = new TextField();
		usernameTextField.setId("username-TextField");
		usernameTextField.setPromptText("Username");
		
		//Login Button
		Button loginButton = new Button("Connect");
		loginButton.setMinWidth(300);
		loginButton.setId("loginButton");
		
		//adds items to borderpane
		loginItems.setCenter(usernameTextField);
		loginItems.setBottom(loginButton);
		
		//vbox items Customization
		loginItems.setMargin(usernameTextField,new Insets(5,5,5,5));
		
		//adds borderpane to vbox
		loginVBox.getChildren().addAll(titleLabel, loginItems);
		
		//add items to stackpane
		loginStackPane.getChildren().add(loginVBox);
		
		Scene LoginScene = new Scene(loginStackPane, 1000, 700);
		LoginScene.getStylesheets().add("application.css");
		primaryStage.setScene(LoginScene);
		primaryStage.show();
	}
}
