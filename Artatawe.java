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
		
		//editing the BorderPane
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
		
		//adds image to StackPane
		loginStackPane.getChildren().add(loginImage);
		
		//create title Label
		Label titleLabel = new Label("Artatawe");
		titleLabel.setId("titleLabel");
		
		//Edit textField
		TextField usernameTextField = new TextField();
		usernameTextField.setId("username-TextField");
		usernameTextField.setPromptText("Username");
		
		//Edit errorLabel
		Label errorLabel = new Label("");
		errorLabel.setId("errorLabel");
		
		//Login Button
		Button loginButton = new Button("Connect");
		loginButton.setMinWidth(300);
		loginButton.setId("loginButton");
		
		loginButton.setOnAction( e -> {
			if(usernameTextField.getText().equals("")) { //checkProfileExists()
				errorLabel.setText("Wrong Username!");
				errorLabel.setStyle("-fx-text-fill: red");
			}else {
				errorLabel.setText("");
			}
		});
		
		//create VBox to go in borderPane bottom
		VBox borderPaneVBox = new VBox();
		borderPaneVBox.getChildren().addAll(errorLabel, loginButton);
		borderPaneVBox.setMargin(errorLabel,new Insets(5,5,5,6));
		
		//adds items to borderPane
		loginItems.setCenter(usernameTextField);
		loginItems.setBottom(borderPaneVBox);
		
		//vbox items Customisation
		loginItems.setMargin(usernameTextField,new Insets(5,5,5,5));
		
		//adds borderPane to vbox
		loginVBox.getChildren().addAll(titleLabel, loginItems);
		
		//add items to stackPane
		loginStackPane.getChildren().add(loginVBox);
		
		Scene LoginScene = new Scene(loginStackPane, 1000, 700);
		loginVBox.requestFocus();
		LoginScene.getStylesheets().add("application.css");
		primaryStage.setScene(LoginScene);
		primaryStage.show();
	}
}
