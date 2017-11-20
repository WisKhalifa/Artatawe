import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Artatawe extends Application {
	
	//The scene used for the program.
	private Scene artataweScene;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		//stuff to do with the stage
		primaryStage.setTitle("Artatawe");
		primaryStage.getIcons().add(new Image("applicationIcon.png"));
		primaryStage.setResizable(false);
		
		artataweScene = makeLoginPage();
		artataweScene.getStylesheets().add("application.css");
		
		primaryStage.setScene(artataweScene);
		primaryStage.show();
	}
	
	/**
	 * Creates the Login page for Artatawe.
	 * @return A scene containing the Login page
	 */
	public Scene makeLoginPage(){
		//The root pane everything will be attached to.
		StackPane loginStackPane = new StackPane();
		
		//Editing the VBox.
		VBox loginVBox = new VBox();
		loginVBox.setAlignment(javafx.geometry.Pos.CENTER);
		
		//Editing the BorderPane.
		BorderPane loginItems = new BorderPane();
		loginItems.setMaxHeight(100);
		loginItems.setMinHeight(100);
		loginItems.setMaxWidth(300);
		loginItems.setMinWidth(300);
		loginItems.setId("loginBox");
		
		//Create background image
		Image loginBackgroundImage = new Image("swanseaBay.png");
		ImageView loginImage = new ImageView();
		loginImage.setEffect(new GaussianBlur());
		loginImage.setImage(loginBackgroundImage);
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
		
		//When you click the Login Button
		loginButton.setOnAction( e -> {
			if(usernameTextField.getText().equals("")) { 
				errorLabel.setText("Wrong Username!");
				errorLabel.setStyle("-fx-text-fill: red");
			}else if(true){
				//add check for profile Exists, method return boolean
				makeDashboard();
			}else {
				//go to dashboard.
				makeDashboard();
			}
		});
		
		//create VBox to go in borderPane bottom
		VBox borderPaneVBox = new VBox();
		borderPaneVBox.getChildren().addAll(errorLabel, loginButton);
		borderPaneVBox.setMargin(errorLabel,new Insets(5,5,5,6));
		
		//adds items to borderPane
		loginItems.setCenter(usernameTextField);
		loginItems.setBottom(borderPaneVBox);
		
		//VBox items Customisation
		loginItems.setMargin(usernameTextField,new Insets(5,5,5,5));
		
		//adds borderPane to VBox
		loginVBox.getChildren().addAll(titleLabel, loginItems);
		
		//add items to stackPane.
		loginStackPane.getChildren().add(loginVBox);
		
		Scene scene = new Scene(loginStackPane, 1000, 700);
		
		//removes focus from the text Field, has to be placed after scene implementation.
		loginVBox.requestFocus();
		
		return scene;
	}

	public void makeDashboard() {
		BorderPane mainBorderPane = new BorderPane();
		mainBorderPane.setId("mainBorderPane");
		
		//Creation of everything in the top BorderPane
		VBox mainBorderPaneTopVBox = new VBox();
		
		StackPane bannerSP = new StackPane();
		
		Image bannerImage = new Image("bannerImage.png");
		ImageView bannerImageView = new ImageView();
		bannerImageView.setImage(bannerImage);
		
		
		Label bannerTitle = new Label("Artatawe");
		bannerTitle.setId("BannerTitle");
		bannerSP.setMargin(bannerTitle,new Insets(0,800,0,0));
		
		bannerSP.getChildren().addAll(bannerImageView, bannerTitle);
		
		HBox navigationBar = new HBox();
		navigationBar.setId("navigationBar");
		Button viewAuctions = new Button("Auctions");
		Button viewProfile = new Button("My Account");
		viewAuctions.setId("viewAuctionsButton");
		viewProfile.setId("viewProfileButton");
		navigationBar.getChildren().addAll(viewAuctions, viewProfile);
		
		HBox auctionNavigationBar = new HBox();
		auctionNavigationBar.setId("auctionNavigationBar");
		auctionNavigationBar.setMaxHeight(40);
		auctionNavigationBar.setMinHeight(40);
		
		Label filterlabel = new Label("Filter:");
		CheckBox paintingCheckBox = new CheckBox("Painting");
        CheckBox sculptureCheckBox = new CheckBox("Scultpure");

		Label searchLabel = new Label("Search:");
		TextField searchUserArtwork = new TextField();
		searchUserArtwork.setPromptText("Username");
		
		filterlabel.setId("filterlabel");
		searchLabel.setId("searchLabel");
		searchUserArtwork.setId("searchUserArtwork");
		paintingCheckBox.setId("filterPaintingCheckBox");
		sculptureCheckBox.setId("filterSculptureCheckBox");

		auctionNavigationBar.getChildren().addAll(filterlabel, paintingCheckBox, sculptureCheckBox, searchLabel, searchUserArtwork);
		auctionNavigationBar.setMargin(searchUserArtwork,new Insets(7,0,0,0));
		auctionNavigationBar.setMargin(paintingCheckBox,new Insets(10,10,0,0));
		auctionNavigationBar.setMargin(sculptureCheckBox,new Insets(10,10,0,0));
		
		mainBorderPaneTopVBox.getChildren().addAll(bannerSP, navigationBar, auctionNavigationBar);
		
		//Creation of everything in the center BorderPane
		
		ScrollPane borderPaneSP = new ScrollPane();
		
		
		//Sets the main BorderPane properties
		mainBorderPane.setTop(mainBorderPaneTopVBox);
		mainBorderPane.setCenter(borderPaneSP);
		
		artataweScene.setRoot(mainBorderPane);
		mainBorderPane.requestFocus();
	}
}