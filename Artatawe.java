import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class contains all the GUI for the Artatawe program.
 * @author Bradley Tenuta
 */
public class Artatawe extends Application {
	
	//Constant values.
	private final int SCENE_WIDTH = 1200;
	private final int SCENE_HEIGHT = 700;
	private final int LOGIN_BOX_HEIGHT = 100;
	private final int LOGIN_BOX_WIDTH = 300;
	private final int LOGIN_BUTTON_WIDTH = 150;
	private final int AUCTION_BOX_ITEM_PIC_CONTAINER_HEIGHT = 160;
	private final int AUCTION_BOX_ITEM_PIC_CONTAINER_WIDTH = 200;
	private final int AUCTION_BOX_ITEM_WIDTH = 200;
	private final int AUCTION_BOX_ITEM_HEIGHT = 220;
	private final int AUCTION_NAVIGATION_BAR_HEIGHT = 40;
	private final int PROFILE_NAVIGATION_BAR_HEIGHT = 40;
	private final int AUCTION_FLOWPANE_H_GAP = 4; //to fit 4 auction boxes on a line
	private final int NORMAL_MARGIN = 10;
	private final int SMALL_MARGIN = 5;
	private final int LARGE_MARGIN = 20;
	private final int EXTRA_LARGE_MARGIN = 30;
	private final int SETTINGS_SCENE_WIDTH = 300;
	private final int SETTINGS_SCENE_HEIGHT = 320;
	private final int SIGNUP_SCENE_WIDTH = 300;
	private final int SIGNUP_SCENE_HEIGHT = 500;
	private final int MAKE_AUCTION_SCENE_WIDTH = 300;
	private final int MAKE_AUCTION_SCENE_HEIGHT = 650;
	private final int MAKE_AUCTION_SCULPTURE_SCENE_WIDTH = 350;
	private final int MAKE_AUCTION_SCULPTURE_SCENE_HEIGHT = 750;
	private final int PROFILE_HEADER_HEIGHT = 200;
	private final int AVATAR_WIDTH = 200;
	private final int AUCTION_IMAGE_CONTAINER = 200;
	private final int TEXT_FIELD_MARGIN = 7;
	private final int WIDE_MARGIN = 100;
	private final int PROFILE_lIST_HBOX_HEIGHT = 50;
	private final int BID_TABLE_HEIGHT_MARGIN = 30;
	private final int BID_TABLE_WIDTH_MARGIN = 20;
	
	//instance variables
	private Scene artataweScene; //The scene used for the program.
	private Stage mainStage;
	private ProfileManager profileManager;
	private AuctionManager auctionManager;
	private Profile currentProfile; //the profile that is being used on the program.
	private VBox mainBorderPaneTopVBox;
	private HBox auctionNavigationBar;
	private HBox profileNavigationBar;
	private BorderPane mainBorderPane;
	private FlowPane AuctionFP;
	private int paintingFilter;
	private int sculptureFilter;
	private HBox bidTable;
	private ScrollPane profileScrollPane;
	private ScrollPane currentProfileScrollPane;
	private ScrollPane viewAllProfilesScrollPane;
	private File selectedDirectory = null;
	
	/**
	 * This is the method that is called when the program launches.
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * This method is how the java GUI loads the program and
	 * displays all the data.
	 * @param Stage This is the main stage that the java FX will use
	 * for the program.
	 */
	@Override
	public void start(Stage primaryStage) {
		//creates the profile and auction managers.
		FileLoader f1 = new FileLoader();
		profileManager = new ProfileManager(f1);
		auctionManager = new AuctionManager(f1);
		
		mainStage = primaryStage;
		//stuff to do with the stage
		mainStage.setTitle("Artatawe");
		mainStage.getIcons().add(new Image("applicationIcon.png"));
		
		artataweScene = new Scene(makeLoginPage(), SCENE_WIDTH, SCENE_HEIGHT);
		artataweScene.getStylesheets().add("artataweStyleSheet.css");
		
		//calls this method when user closes the program (red x).
		mainStage.setOnCloseRequest(e -> closeProgram());
		
		mainStage.setScene(artataweScene);
		mainStage.show();
	}
	
	/**
	 * Creates the Login page for Artatawe.
	 * @return A Container containing the login page.
	 */
	public StackPane makeLoginPage(){
		//The root pane everything will be attached to.
		StackPane loginStackPane = new StackPane();
		
		//Editing the VBox.
		VBox loginVBox = new VBox();
		loginVBox.setAlignment(javafx.geometry.Pos.CENTER);
		
		//Editing the BorderPane.
		BorderPane loginItems = new BorderPane();
		loginItems.setMaxHeight(LOGIN_BOX_HEIGHT);
		loginItems.setMinHeight(LOGIN_BOX_HEIGHT);
		loginItems.setMaxWidth(LOGIN_BOX_WIDTH);
		loginItems.setMinWidth(LOGIN_BOX_WIDTH);
		loginItems.setId("loginBox");
		
		//Create background image	
		ImageView loginImage = createImage("swanseaBay.png");
		loginImage.setEffect(new GaussianBlur());
		loginImage.fitWidthProperty().bind(loginStackPane.widthProperty()); 
		loginStackPane.getChildren().add(loginImage);
		
		//create title Label
		Label titleLabel = new Label("Artatawe");
		titleLabel.setId("titleLabel");
		
		//Edit textField
		TextField usernameTextField = new TextField();
		usernameTextField.setId("username-TextField");
		usernameTextField.setPromptText("Username");
		loginItems.setMargin(usernameTextField,new Insets(SMALL_MARGIN,
				SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
		
		//creates the login box.
		VBox borderPaneVBox = createLoginButton(usernameTextField);

		//adds items to borderPane
		loginItems.setCenter(usernameTextField);
		loginItems.setBottom(borderPaneVBox);
		
		//adds borderPane to VBox
		loginVBox.getChildren().addAll(titleLabel, loginItems); 
		//add items to stackPane.
		loginStackPane.getChildren().add(loginVBox); 
		
		//removes focus, place after scene implementation.
		loginVBox.requestFocus();
		return loginStackPane;
	}
	
	/**
	 * Creates an Image that can be displayed in the application.
	 * @param url This is the string location of the source image.
	 * @return An ImageView object.
	 */
	public ImageView createImage(String url) {
		Image loginBackgroundImage = new Image(url);
		ImageView loginImage = new ImageView();
		loginImage.setImage(loginBackgroundImage);
		return loginImage;
	}
	
	/**
	 * This creates the login box button used on the login page.
	 * It also includes the logic for when the button is pressed.
	 * @param usernameTextField Required when running the logic to log the user in.
	 * @return returns the a container with the login button inside.
	 */
	public VBox createLoginButton(TextField usernameTextField) {
		VBox borderPaneVBox = new VBox();
		//Edit errorLabel.
		Label errorLabel = new Label("");
		errorLabel.setId("errorLabel");
		
		//HBox
		HBox loginButtonsHBox = new HBox();
		
		//Login Button.
		Button loginButton = new Button("Connect");
		loginButton.setMinWidth(LOGIN_BUTTON_WIDTH);
		loginButton.setId("loginButton");
		
		//sign up button
		Button signUp = new Button("Sign Up");
		signUp.setMinWidth(LOGIN_BUTTON_WIDTH);
		signUp.setId("signUpButton");
		
		loginButtonsHBox.getChildren().addAll(signUp, loginButton);
		
		//When you click the Login Button.
		loginButton.setOnAction( e -> {
			if (usernameTextField.getText().equals("")) { 
				errorLabel.setText("Wrong Username!");
				errorLabel.setStyle("-fx-text-fill: red");
			}else{
				for (int i = 0; i < profileManager.getProfiles().size(); i++) {
					if (usernameTextField.getText().
							equals(profileManager.getProfiles().get(i).getUsername())) {
						currentProfile = profileManager.getProfiles().get(i);
						makeDashboard();
					}
				}
				errorLabel.setText("Wrong Username!");
				errorLabel.setStyle("-fx-text-fill: red");
			}
		});
		
		//the action for the sign up button.
		signUp.setOnAction( e -> {
			openSignUpWindow();
		});
		
		//create VBox to go in borderPane bottom.
		borderPaneVBox.getChildren().addAll(errorLabel, loginButtonsHBox);
		borderPaneVBox.setMargin(errorLabel,new Insets(SMALL_MARGIN,
				SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN + 1));
		return borderPaneVBox;
	}
	
	/**
	 * Allows the user to make an account if they haven't got one.
	 * This method creates a GUI in a new window which then allows a user
	 * to create a profile.
	 */
	public void openSignUpWindow() {
		Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setTitle("Sign Up");
        window.getIcons().add(new Image("applicationIcon.png"));
        
        VBox signUpVBox = new VBox();
        
        //displays the information that can be added.
        Label addUsername = new Label("Add Username");
        TextField addUsernameField = new TextField();
        Label errorMessageUsername = new Label("");
        
        Label addFirstName = new Label("Add FirstName");
        TextField firstNameField = new TextField();
        
        Label addLastName = new Label("Add LastName");
        TextField lastNameField = new TextField();
        
        Label addTelephone = new Label("Add Telephone Number");
        TextField telephoneField = new TextField();
        Label errorMessageTelephone = new Label("");
        
        Label addAddress = new Label("Add Address");
        TextField addressField = new TextField();
        
        Label addPostCode = new Label("Add PostCode");
        TextField postCodeField = new TextField();
        
        Button addProfileButton = new Button("Make Profile");
        Label mainErrorLabel = new Label("");
        
        signUpVBox.getChildren().addAll(addUsername, addUsernameField,
        		errorMessageUsername, addFirstName, firstNameField, addLastName,
        		lastNameField, addTelephone, telephoneField, errorMessageTelephone,
        		addAddress, addressField, addPostCode, postCodeField,
        		addProfileButton, mainErrorLabel);
        
        //The action for creating a profile.
        addProfileButton.setOnAction(e -> {
        	if (addUsernameField.getText().isEmpty() || 
        			firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || 
        			telephoneField.getText().isEmpty() || addressField.getText().isEmpty() || postCodeField.getText().isEmpty()) {
        		mainErrorLabel.setText("Must complete all Fields!");
        		mainErrorLabel.setStyle("-fx-text-fill: red");
        		return;
        	}
        	
        	for (int i = 0; i < profileManager.getProfiles().size(); i++) {
        		if (profileManager.getProfiles().get(i).getUsername().equalsIgnoreCase(addUsernameField.getText())) {
        			errorMessageUsername.setText("Username already used!");
        			errorMessageUsername.setStyle("-fx-text-fill: red");
        			return;
        		}
        	}
        	
	        String username = addUsernameField.getText();
	        String firstName = firstNameField.getText();
	        String lastName = lastNameField.getText();
	        	
	        if (telephoneField.getText().length() > 10) {
	        	errorMessageTelephone.setText("Telepone Number too Long!");
	        	errorMessageTelephone.setStyle("-fx-text-fill: red");
	        	return;
	        }
	        
	        String telephone = telephoneField.getText();
	        try {
	        	int tel = Integer.parseInt(telephone);
	        }catch(NumberFormatException e1) {
	        	errorMessageTelephone.setText("Can only contain digits!");
	        	errorMessageTelephone.setStyle("-fx-text-fill: red");
	        	return;
	        }
	        
		    String Address = addressField.getText();
			String postCode = postCodeField.getText();
			String imagePath = "ImageDefault.png";
			        	
			Profile newProfile = new Profile(username, firstName, lastName, telephone,
					Address, postCode, imagePath);
			profileManager.addProfile(newProfile);
			window.close();
        });
        
        signUpVBox.setMargin(addUsername,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        signUpVBox.setMargin(addUsernameField,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        signUpVBox.setMargin(addFirstName,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        signUpVBox.setMargin(firstNameField,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        signUpVBox.setMargin(addLastName,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        signUpVBox.setMargin(lastNameField,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        signUpVBox.setMargin(addTelephone,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        signUpVBox.setMargin(telephoneField,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        signUpVBox.setMargin(addAddress,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        signUpVBox.setMargin(addressField,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        signUpVBox.setMargin(addPostCode,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        signUpVBox.setMargin(postCodeField,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        signUpVBox.setMargin(addProfileButton,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        signUpVBox.setMargin(errorMessageUsername,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        signUpVBox.setMargin(errorMessageTelephone,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        signUpVBox.setMargin(mainErrorLabel,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));

		Scene scene = new Scene(signUpVBox, SIGNUP_SCENE_WIDTH, SIGNUP_SCENE_HEIGHT);
        window.setScene(scene);
        window.showAndWait();
	}
	
	/**
	 * Creates the dash board page, This GUI is loaded once the user logs in.
	 * The dash board page is the browse Auction page.
	 */
	public void makeDashboard() {	
		mainBorderPane = new BorderPane();
		mainBorderPane.setId("mainBorderPane");
		
		//Creation of containers in the top BorderPane.
		mainBorderPaneTopVBox = new VBox();
		StackPane bannerSP = new StackPane();
		
		//Creation of containers in the centre BorderPane.
		ScrollPane borderPaneSP = new ScrollPane();
		borderPaneSP.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		AuctionFP = new FlowPane(Orientation.HORIZONTAL);
		AuctionFP.setHgap(AUCTION_FLOWPANE_H_GAP);
		AuctionFP.prefWidthProperty().bind(borderPaneSP.widthProperty());
		AuctionFP.prefHeightProperty().bind(borderPaneSP.heightProperty());
		
		ImageView bannerImageView = createImage("bannerImage.png");
		bannerImageView.fitWidthProperty().bind(bannerSP.widthProperty()); 
		bannerSP.getChildren().add(bannerImageView);
		
		//calls a method that creates the navigation bar.
		HBox navigationBar = createNavigationBar();
		HBox auctionNavigationBar = createAuctionNavigationBar();
		
		mainBorderPaneTopVBox.getChildren().addAll(bannerSP,
				navigationBar, auctionNavigationBar);
		
		//creates and displays all the Auction's into the flowPane.
		displayAuctions();
		
		borderPaneSP.setContent(AuctionFP);
		
		//Sets the main BorderPane properties
		mainBorderPane.setTop(mainBorderPaneTopVBox);
		mainBorderPane.setCenter(borderPaneSP);
		
		artataweScene.setRoot(mainBorderPane);
		mainBorderPane.requestFocus();
	}
	
	/**
	 * Creates the Auction Navigation bar that appears when you are 
	 * viewing all the auction's.
	 * @param AuctionFP
	 * @return A container containing the navigation bar.
	 */
	public HBox createAuctionNavigationBar() {
		auctionNavigationBar = new HBox();
		auctionNavigationBar.setId("auctionNavigationBar");
		auctionNavigationBar.setMaxHeight(AUCTION_NAVIGATION_BAR_HEIGHT);
		auctionNavigationBar.setMinHeight(AUCTION_NAVIGATION_BAR_HEIGHT);
		
		Label filterlabel = new Label("Filter:");
		CheckBox paintingCheckBox = new CheckBox("Painting");
        CheckBox sculptureCheckBox = new CheckBox("Scultpure");

		Label searchLabel = new Label("Search:");
		TextField searchUserArtwork = new TextField();
		searchUserArtwork.setId("inputField");
		searchUserArtwork.setPromptText("Username");
		
		Label startSearchLabel = new Label("Start Search");
		
		Label viewCompletedAuctionsLabel = new Label("View Completed Auctions");
		
		Label showFavouritesAuctions = new Label("Show Auctions by Favourites");
		
		filterlabel.setId("filterlabel");
		searchLabel.setId("searchLabel");
		searchUserArtwork.setId("searchUserArtwork");
		paintingCheckBox.setId("filterPaintingCheckBox");
		sculptureCheckBox.setId("filterSculptureCheckBox");
		startSearchLabel.setId("startSearchLabel");
		viewCompletedAuctionsLabel.setId("viewCompletedAuctionsLabel");
		showFavouritesAuctions.setId("showFavouritesAuctions");
		
		auctionNavigationBar.getChildren().addAll(filterlabel,
				paintingCheckBox, sculptureCheckBox, searchLabel,
				searchUserArtwork, startSearchLabel, viewCompletedAuctionsLabel,
				showFavouritesAuctions);
		
		auctionNavigationBar.setMargin(searchUserArtwork,
				new Insets(TEXT_FIELD_MARGIN,0,0,0));
		auctionNavigationBar.setMargin(paintingCheckBox,
				new Insets(NORMAL_MARGIN,NORMAL_MARGIN,0,0));
		auctionNavigationBar.setMargin(sculptureCheckBox,
				new Insets(NORMAL_MARGIN,NORMAL_MARGIN,0,0));
		
		//creates the actions for the filter check boxes.
		paintingCheckBox.setOnAction( e -> {
			sculptureCheckBox.setSelected(false);
			paintingFilter = 0;
			if (paintingCheckBox.isSelected()) {
				sculptureFilter = 1;
				AuctionFP.getChildren().clear();
				displayAuctions();
			} else {
				sculptureFilter = 0;
				AuctionFP.getChildren().clear();
				displayAuctions();
			}
		});
		sculptureCheckBox.setOnAction( e -> {
			paintingCheckBox.setSelected(false);
			sculptureFilter = 0;
			if (sculptureCheckBox.isSelected()) {
				paintingFilter = 1;
				AuctionFP.getChildren().clear();
				displayAuctions();
			} else {
				paintingFilter = 0;
				AuctionFP.getChildren().clear();
				displayAuctions();
			}
		});
		
		//searches for auction based on the word.
		startSearchLabel.setOnMouseClicked( e -> {
			sculptureCheckBox.setSelected(false);
			paintingCheckBox.setSelected(false);
			sculptureFilter = 0;
			paintingFilter = 0;
			AuctionFP.getChildren().clear();
			searchAuction(searchUserArtwork.getText());
		});
		
		//views just completed Auction's
		viewCompletedAuctionsLabel.setOnMouseClicked( e -> {
			sculptureCheckBox.setSelected(false);
			paintingCheckBox.setSelected(false);
			sculptureFilter = 0;
			paintingFilter = 0;
			AuctionFP.getChildren().clear();
			showCompletedAuctions();
		});
		
		//when clicked it displays favourites auction's.
		showFavouritesAuctions.setOnMouseClicked( e -> {
			sculptureCheckBox.setSelected(false);
			paintingCheckBox.setSelected(false);
			sculptureFilter = 0;
			paintingFilter = 0;
			AuctionFP.getChildren().clear();
			showFavourites();
		});
		
		return auctionNavigationBar;
	}
	
	/**
	 * Creates the Navigation bar used for in the application.
	 * @return A container containing the Navigation bar.
	 */
	public HBox createNavigationBar() {
		HBox navigationBar = new HBox();
		navigationBar.setId("navigationBar");
		
		Button viewAuctions = new Button("Auctions");
		Button viewProfile = new Button("My Account");
		Button viewAllProfiles = new Button("View All Profiles");
		Button logOutButton = new Button("Log Out");
		viewAuctions.setId("viewAuctionsButton");
		viewProfile.setId("viewProfileButton");
		viewAllProfiles.setId("viewAllProfiles");
		logOutButton.setId("logOutButton");
		navigationBar.getChildren().addAll(viewAuctions, viewProfile,
				viewAllProfiles, logOutButton);
		
		//sets the actions for the buttons on the Navigation bar.
		viewAuctions.setOnAction( e -> {
			makeDashboard();
		});
		viewProfile.setOnAction( e -> {
			if (mainBorderPane.getCenter() == currentProfileScrollPane) {
				System.out.println("Account already open");
			} else {
				viewCurrentProfile();
			}
		});
		
		viewAllProfiles.setOnAction( e -> {
			if (mainBorderPane.getCenter() == viewAllProfilesScrollPane) {
				System.out.println("Account already open");
			} else {
				viewAllCurrentProfiles();
			}
		});
		
		//logs out of the system.
		logOutButton.setOnAction( e -> {
			artataweScene.setRoot(makeLoginPage());
			currentProfile = null;
			saveData(); //saves data before log out
		});
		
		return navigationBar;
	}
	
	/**
	 * This checks the filters and then displays the correct auction's
	 * based off the filters
	 * @param paintingFilter Used to see if the painting filter needs to be applied.
	 * @param sculptureFilter Used to see if the sculpture filter needs to be applied.
	 */
	public void displayAuctions() {
		for(Auction auction : auctionManager.getAuctions()) {
		
			BorderPane auctionItemBox = new BorderPane();
			
			//0 means get it. 1 means don't get it.
			if ((auction.getArtwork() instanceof Painting) && (paintingFilter == 0)){
				auctionItemBox = createAuctionItem(auctionItemBox, auction);
			} else if ((auction.getArtwork() instanceof Sculpture) && (sculptureFilter == 0)){
				auctionItemBox = createAuctionItem(auctionItemBox, auction);
			}
			
			auctionItemBox.setId("auctionItemBox");
			AuctionFP.getChildren().add(auctionItemBox);
			AuctionFP.setMargin(auctionItemBox,new Insets(LARGE_MARGIN,
					LARGE_MARGIN, LARGE_MARGIN, LARGE_MARGIN));
			
			//opens up the Auction page 
			auctionItemBox.setOnMouseClicked( e -> {
				createAuctionPage(auction);
			});
		}
	}
	
	/**
	 * This method takes in a parameter of a user input which is user name.
	 * All Auction's that are being sold by this user name will be returned and
	 * shown on the dash board.
	 * @param username The input from the user.
	 */
	public void searchAuction(String username) {
		for (Auction auction : auctionManager.getAuctions()) {
			
			BorderPane auctionItemBox = new BorderPane();
			
			if (auction.getSeller().equalsIgnoreCase(username)) {
				auctionItemBox = createAuctionItem(auctionItemBox, auction);
			}
			
			auctionItemBox.setId("auctionItemBox");
			AuctionFP.getChildren().add(auctionItemBox);
			AuctionFP.setMargin(auctionItemBox,new Insets(LARGE_MARGIN,
					LARGE_MARGIN, LARGE_MARGIN, LARGE_MARGIN));
			
			//opens up the Auction page 
			auctionItemBox.setOnMouseClicked( e -> {
				createAuctionPage(auction);
			});
		}
	}
	
	/**
	 * This method finds and displays all the completed auction's.
	 */
	public void showCompletedAuctions() {
		for (Auction auction : auctionManager.getAuctions()) {
			
			BorderPane auctionItemBox = new BorderPane();
			
			if (auction.isComplete()) {
				auctionItemBox = createAuctionItem(auctionItemBox, auction);
			}
			
			auctionItemBox.setId("auctionItemBox");
			AuctionFP.getChildren().add(auctionItemBox);
			AuctionFP.setMargin(auctionItemBox,new Insets(LARGE_MARGIN,
					LARGE_MARGIN, LARGE_MARGIN, LARGE_MARGIN));
			
			//opens up the Auction page 
			auctionItemBox.setOnMouseClicked( e -> {
				createAuctionPage(auction);
			});
		}
	}
	
	/**
	 * This method finds and displays all the favourites auction's of the user.
	 */
	public void showFavourites() {
		for (Auction auction : auctionManager.getAuctions()) {
			
			BorderPane auctionItemBox = new BorderPane();
			
			//if the seller is in the fav list then display.
			for (int i = 0; i < currentProfile.getFavourites().size(); i++) {
				if (auction.getSeller().equals(currentProfile.getFavourites().get(i))) {
					auctionItemBox = createAuctionItem(auctionItemBox, auction);
				}
			}
			
			auctionItemBox.setId("auctionItemBox");
			AuctionFP.getChildren().add(auctionItemBox);
			AuctionFP.setMargin(auctionItemBox,new Insets(LARGE_MARGIN,
					LARGE_MARGIN, LARGE_MARGIN, LARGE_MARGIN));
			
			//opens up the Auction page 
			auctionItemBox.setOnMouseClicked( e -> {
				createAuctionPage(auction);
			});
		}
	}
	
	/**
	 * This finds all the auction's and creates a GUI for them and then displays them
	 * in the auction page or dash board.
	 */
	public BorderPane createAuctionItem(BorderPane auctionItemBox, Auction auction) {
		BorderPane auctionItemBoxPicContainer = new BorderPane();
		ImageView auctionBoxImageView = createImage(auction.getArtwork().getMainPhoto());
		
		//sets width and height of the image to fit the container.
		auctionBoxImageView.fitWidthProperty().
			bind(auctionItemBoxPicContainer.widthProperty());
		auctionBoxImageView.fitHeightProperty().
			bind(auctionItemBoxPicContainer.heightProperty());
		
		auctionItemBoxPicContainer.setCenter(auctionBoxImageView);
		auctionItemBoxPicContainer.setMaxHeight(AUCTION_BOX_ITEM_PIC_CONTAINER_HEIGHT);
		auctionItemBoxPicContainer.setMaxWidth(AUCTION_BOX_ITEM_PIC_CONTAINER_WIDTH);
		auctionItemBoxPicContainer.setMinHeight(AUCTION_BOX_ITEM_PIC_CONTAINER_HEIGHT);
		auctionItemBoxPicContainer.setMinWidth(AUCTION_BOX_ITEM_PIC_CONTAINER_WIDTH);
		
		VBox auctionBoxVBox = new VBox();
		Label artworkName = new Label(auction.getArtwork().getTitle());
		Label artworkOwner = new Label(auction.getArtwork().getCreatorName());
		
		//this takes the description and makes a substring and only shows a part of it.
		String description = auction.getArtwork().getDescription();
		if (description.length() >= 20) {
			description = description.substring(0, 20);
		}
		description += "...";
		Label artworkDescription = new Label(description);
		
		
		artworkName.setId("artworkName");
		artworkOwner.setId("artworkOwner");
		artworkDescription.setId("artworkDescription");
		auctionBoxVBox.getChildren().addAll(artworkName, artworkOwner, artworkDescription);
		
		auctionItemBox.setCenter(auctionItemBoxPicContainer);
		auctionItemBox.setBottom(auctionBoxVBox);
		auctionItemBox.setMaxHeight(AUCTION_BOX_ITEM_HEIGHT);
		auctionItemBox.setMaxWidth(AUCTION_BOX_ITEM_WIDTH);
		auctionItemBox.setMinHeight(AUCTION_BOX_ITEM_HEIGHT);
		auctionItemBox.setMinWidth(AUCTION_BOX_ITEM_WIDTH);
		
		return auctionItemBox;
	}
	
	/**
	 * Creates the Auction page for the particular auction that is clicked.
	 * This shows more information of the artwork and auction and allows the user
	 * to bid on the Auction.
	 * @param auction The auction clicked on by the user.
	 */
	public void createAuctionPage(Auction auction) {
		mainBorderPaneTopVBox.getChildren().remove(auctionNavigationBar);
		
		ScrollPane auctionScrollPane = new ScrollPane();
		auctionScrollPane.setFitToHeight(true);
		auctionScrollPane.setFitToWidth(true);
		VBox auctionPageVBox = new VBox();
		
		HBox imageContainer = new HBox();
		imageContainer.setId("imageContainer");
		ImageView artworkImage = createImage(auction.getArtwork().getMainPhoto());
		imageContainer.getChildren().add(artworkImage);
		imageContainer.setAlignment(Pos.CENTER);
		imageContainer.setMaxHeight(AUCTION_IMAGE_CONTAINER);
		imageContainer.setMinHeight(AUCTION_IMAGE_CONTAINER);
		artworkImage.fitHeightProperty().bind(imageContainer.heightProperty());
		artworkImage.setFitWidth(AUCTION_IMAGE_CONTAINER);
		auctionPageVBox.getChildren().add(imageContainer);
		
		//add buttons for more pictures if artwork is a sculpture.
		if (auction.getArtwork() instanceof Sculpture) {
			HBox containerOfExtraPhotos = createExtraPhotoButton(auction,artworkImage);
			auctionPageVBox.getChildren().add(containerOfExtraPhotos);
		}
		
		//adds the information about the artwork
		Label artworkName = new Label(auction.getArtwork().getTitle());
		artworkName.setId("auctionImportantDetails");
		
		Label artworkComplete = new Label("");
		artworkComplete.setId("auctionImportantDetails");
		if (auction.isComplete()) {
			artworkComplete.setText("Auction Complete");
		} else {
			artworkComplete.setText("Auction Open");
		}
		
		//creates labels to show information about the auction.
		Label artworkOwner = new Label("Creator: " +
				auction.getArtwork().getCreatorName());
		artworkOwner.setId("auctionDetails");
		Label artworkCreationYear = new Label("Creation year: " +
				auction.getArtwork().getCreationYear());
		artworkCreationYear.setId("auctionDetails");
		Label artworkPrice = new Label("Price: £" +
				auction.getArtwork().getPrice());
		artworkPrice.setId("auctionDetails");
		Label artworkBidTotal = new Label("Total bids: " +
				auction.getArtwork().getBidTotal());
		artworkBidTotal.setId("auctionDetails");
		Label artworkDateAndTime = new Label("Time uploaded: " +
				auction.getArtwork().getDateTime());
		artworkDateAndTime.setId("auctionDetails");
		
		//this one is Optional, will just be empty otherwise.
		Label artworkDescription = new Label(auction.getArtwork().getDescription());
		artworkDescription.setId("auctionDetails");
		
		//VBox containing all the buttons.
		VBox buttonContainer = createButtonContainer(auction, auctionPageVBox);
		
		//A VBox containing the bid table and the bid input.
		VBox auctionBidContainer = makeAuctionBidContainer(auction, auctionPageVBox,
				artworkBidTotal);
		
		auctionPageVBox.getChildren().addAll(artworkName,
				artworkComplete, artworkOwner, artworkCreationYear,
				artworkPrice,artworkBidTotal, artworkDateAndTime,
				artworkDescription, buttonContainer, auctionBidContainer);
		
		auctionScrollPane.setContent(auctionPageVBox);
		mainBorderPane.setCenter(auctionScrollPane);
	}
	
	/**
	 * This method makes a VBox container than hold the bid input and the bid
	 * table for an Auction page.
	 * @param auction The Auction being worked upon.
	 * @param auctionPageVBox The Main VBox of the auction page.
	 * @param artworkBidTotal A label that needs to be updated when a bid is made.
	 * @return A container of the bid input GUI and the bid table.
	 */
	public VBox makeAuctionBidContainer(Auction auction, VBox auctionPageVBox,
			Label artworkBidTotal) {
		
		VBox auctionBidContainer = new VBox();
		
		//creates the input to make a bid.
		HBox makeBid = new HBox();
		TextField enterBid = new TextField();
		Button sendBid = new Button("Make bid");
		sendBid.setId("auctionButtons");
		Label bidErrorMessage = new Label("");
		enterBid.setId("inputField");
		bidErrorMessage.setId("auctionDetails");
		makeBid.setMargin(enterBid,new Insets(NORMAL_MARGIN,
				NORMAL_MARGIN, NORMAL_MARGIN, NORMAL_MARGIN));
		makeBid.setMargin(sendBid,new Insets(NORMAL_MARGIN,
				NORMAL_MARGIN, NORMAL_MARGIN, NORMAL_MARGIN));
		makeBid.setMargin(bidErrorMessage,new Insets(NORMAL_MARGIN,
				NORMAL_MARGIN, NORMAL_MARGIN, NORMAL_MARGIN));
		makeBid.getChildren().addAll(enterBid, sendBid, bidErrorMessage);
		
		//if the Auction is complete then the user can't add a bid.
		if (auction.isComplete()) {
			makeBid.setDisable(true);
		}
		
		//Creates a table of all the bids for an auction using containers and labels.
		Label bidTableLabel = new Label("All Bids");
		bidTableLabel.setId("auctionDetails");
		bidTable = makeBidTable(auction);
		auctionPageVBox.setMargin(bidTable,new Insets(0, BID_TABLE_HEIGHT_MARGIN,
				BID_TABLE_WIDTH_MARGIN, BID_TABLE_HEIGHT_MARGIN));
		
		//creates a bid and sends it off to be added to the Auction.
		sendBid.setOnAction( e -> {
			bidErrorMessage.setStyle("-fx-text-fill: red;");
			double newBidTotal = 0;
			//checks to make sure no characters are entered.
			try {
				newBidTotal = Double.parseDouble(enterBid.getText());
			} catch(NumberFormatException e1) {
				bidErrorMessage.setText("You Must Enter a Number");
				return; //breaks from the button click.
			}
			
			if (currentProfile.getUsername().equals(auction.getHighestBid().
					getBidder().getUsername())) {
				bidErrorMessage.setText("You already have the highest Bid!");
			} else if (auction.getArtwork().getPrice() > newBidTotal) {
				bidErrorMessage.setText("You must enter a bid higher than initial price.");
			} else if (auction.getHighestBid().getAmount() > newBidTotal){
				bidErrorMessage.setText("Must be higher than current highest bid.");
			} else if (auction.getSeller().equals(currentProfile.getUsername())){
				bidErrorMessage.setText("You can't bid on your own Auction!");
			} else {
				//creates the time and date of the bid.
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				String dateStr = sdf.format(date);
				
				Bid newbid = new Bid(currentProfile,auction.getArtwork(),newBidTotal,dateStr);
				auction.placeBid(newbid);
				enterBid.setText("");
				bidErrorMessage.setText("");
				auctionBidContainer.getChildren().remove(bidTable);
				bidTable = makeBidTable(auction);
				artworkBidTotal.setText("Total bids: " + auction.getArtwork().getBidTotal());
				auctionBidContainer.getChildren().add(bidTable);
				auctionBidContainer.setMargin(bidTable,new Insets(0 , BID_TABLE_HEIGHT_MARGIN,
						BID_TABLE_WIDTH_MARGIN ,BID_TABLE_HEIGHT_MARGIN));
			}  
		});
		auctionBidContainer.getChildren().addAll(makeBid, bidTableLabel, bidTable);
		
		return auctionBidContainer;
	}
	
	/**
	 * This method creates a container full of buttons that will be displayed on
	 * the Auction page.
	 * @param auction The auction that the buttons will be on.
	 * @param auctionPageVBox The main Container in the Auction Page.
	 * @return A VBox that contains the buttons.
	 */
	public VBox createButtonContainer(Auction auction, VBox auctionPageVBox){
		VBox buttonContainer = new VBox();
		
		//creates a button to view owners profile.
		Button viewProfile = new Button("View Profile");
		viewProfile.setId("auctionButtons");
		auctionPageVBox.setMargin(viewProfile,new Insets(NORMAL_MARGIN,
				NORMAL_MARGIN, NORMAL_MARGIN, NORMAL_MARGIN));
				
		//creates a button to add the owner to the profile favourite list.
		Button addFav = new Button("Add to Favourites");
		addFav.setId("auctionButtons");
		auctionPageVBox.setMargin(addFav,new Insets(NORMAL_MARGIN,
				NORMAL_MARGIN, NORMAL_MARGIN, NORMAL_MARGIN));
				
		//creates a button to remove a profile as a favourite.
		Button removeFav = new Button("Remove from Favourites");
		addFav.setId("auctionButtons");
		auctionPageVBox.setMargin(removeFav,new Insets(NORMAL_MARGIN,
				NORMAL_MARGIN, NORMAL_MARGIN, NORMAL_MARGIN));
				
		//if the seller is already in the favourites list then disable add to fav button.
		for (int i = 0; i < profileManager.getProfiles().size(); i++) {
			if (profileManager.getProfiles().get(i).getUsername().equals(auction.getSeller())) {
				for (int j = 0; j < currentProfile.getFavourites().size(); j++) {
					if (auction.getSeller().equals(currentProfile.getFavourites().get(j))) {
						addFav.setDisable(true);
					}
				}
			}
		}
		
		//if the user is the owner of the auction they can't add 
		//them selevs to favourites.
		if (currentProfile.getUsername().equals(auction.getSeller())) {
			addFav.setDisable(true);
		}
		
		//button to view the auction owners profile.
		viewProfile.setOnAction( e -> {
			for (int i = 0; i < profileManager.getProfiles().size(); i++){
				if (auction.getArtwork().getCreatorName().
						equals(profileManager.getProfiles().get(i).getUsername())){
					viewProfile(profileManager.getProfiles().get(i));
				}
			}
		});
		
		//this adds the seller to the users favourites.
		addFav.setOnAction( e -> {
			currentProfile.addFavourite(auction.getSeller());
			addFav.setDisable(true);
		});
		removeFav.setOnAction( e -> {
			currentProfile.getFavourites().remove(auction.getSeller());
			addFav.setDisable(false);
		});
		
		buttonContainer.getChildren().addAll(viewProfile, addFav, removeFav);
		
		return buttonContainer;
	}
	
	/**
	 * This method returns a HBox that contains buttons that
	 * change the auction image.
	 * @param auction The auction the images belong to.
	 * @param artworkImage The Image container that changes.
	 * @return A HBox that contains the buttons.
	 */
	public HBox createExtraPhotoButton(Auction auction, ImageView artworkImage) {
		HBox containerOfExtraPhotos = new HBox();
		Button mainPicButton = new Button("Picture 1");
		mainPicButton.setId("auctionButtons");
		containerOfExtraPhotos.getChildren().add(mainPicButton);
		containerOfExtraPhotos.setMargin(mainPicButton,
				new Insets(NORMAL_MARGIN, NORMAL_MARGIN,
						NORMAL_MARGIN, NORMAL_MARGIN));
		
		mainPicButton.setOnAction( e -> {
			Image newPic = new Image(auction.getArtwork().getMainPhoto());
			artworkImage.setImage(newPic);
		});
		
		for (int i = 0; i < ((Sculpture) auction.getArtwork()).getExtraPhotos().size(); i++) {
			Button picButton = new Button("Picture " + (i + 2));
			picButton.setId("auctionButtons");
			containerOfExtraPhotos.getChildren().add(picButton);
			containerOfExtraPhotos.setMargin(picButton,
					new Insets(NORMAL_MARGIN, NORMAL_MARGIN,
							NORMAL_MARGIN, NORMAL_MARGIN));
			String source = ((Sculpture) auction.getArtwork()).getExtraPhotos().get(i);
			
			picButton.setOnAction( e -> {
				Image newPic = new Image(source);
				artworkImage.setImage(newPic);
			});
		}
		return containerOfExtraPhotos;
	}
	
	/**
	 * Creates a table that contains all the bids and details about
	 * those bids for a certain auction.
	 * @param auction
	 * @return A table containing the bids of an Auction.
	 */
	public HBox makeBidTable(Auction auction) {
		HBox bidTable = new HBox();
		bidTable.setId("Table");
		VBox col1 = new VBox();
		col1.setId("Column");
		VBox col2 = new VBox();
		col2.setId("Column");
		VBox col3 = new VBox(); 
		col3.setId("finalColumn");
		Label col1Header = new Label("Username");
		Label col2Header = new Label("Amount (£)");
		Label col3Header = new Label("Date");
		col1.getChildren().add(col1Header);
		col2.getChildren().add(col2Header);
		col3.getChildren().add(col3Header);
		
		//checks if there is any bids in an auction.
		ArrayList<Bid> tempAllBids = auction.getAllBids();
		if (tempAllBids.isEmpty()) {
			Label col1Label = new Label("No bids found");
			col1.getChildren().add(col1Label);
		} else {
			//loops backwards so the highest bid is at the top.
			for (int i = tempAllBids.size() - 1; i >= 0; i--) {
				Bid tempbid = tempAllBids.get(i);
				Label col1Label = new Label(tempbid.getBidder().getUsername());
				Label col2Label = new Label(Double.toString(tempbid.getAmount()));
				Label col3Label = new Label(tempbid.getDate());
				col1.getChildren().add(col1Label);
				col2.getChildren().add(col2Label);
				col3.getChildren().add(col3Label);
			}
		}
		
		
		bidTable.getChildren().addAll(col1, col2, col3);
		
		return bidTable;
	}
	
	/**
	 * This method creates the GUI for viewing a Profile. This profile
	 * is not the current Users profile but another Users profile.
	 * @param profile The profile that is being viewed.
	 */
	public void viewProfile(Profile profile) {
		mainBorderPaneTopVBox.getChildren().remove(auctionNavigationBar);
		
		profileScrollPane = new ScrollPane();
		profileScrollPane.setFitToHeight(true);
		profileScrollPane.setFitToWidth(true);
		VBox viewProfileVBox = new VBox();
		
		//creates the header for the profile.
		HBox profileHeader = makeProfileHeader(profile);
		
		//creates a button to add the owner to the profile favourite list.
		Button addFav = new Button("Add to Favourites");
		addFav.setId("auctionButtons");
		viewProfileVBox.setMargin(addFav,new Insets(NORMAL_MARGIN,
				NORMAL_MARGIN, NORMAL_MARGIN, NORMAL_MARGIN));
		
		//creates a button to remove a profile as a favourite.
		Button removeFav = new Button("Remove from Favourites");
		addFav.setId("auctionButtons");
		viewProfileVBox.setMargin(removeFav,new Insets(NORMAL_MARGIN,
				NORMAL_MARGIN, NORMAL_MARGIN, NORMAL_MARGIN));
				
		//if the seller is already in the favourites list then disable add to fav button.
		for (int i = 0; i < profileManager.getProfiles().size(); i++) {
			if (profileManager.getProfiles().get(i).getUsername().equals(profile.getUsername())) {
				for (int j = 0; j < currentProfile.getFavourites().size(); j++) {
					if (profile.getUsername().equals(currentProfile.getFavourites().get(j))) {
						addFav.setDisable(true);
					}
				}
			}
		}
		
		//this adds the seller to the users favourites.
		addFav.setOnAction( e -> {
			currentProfile.addFavourite(profile.getUsername());
			addFav.setDisable(true);
		});
		removeFav.setOnAction( e -> {
			currentProfile.getFavourites().remove(profile.getUsername());
			addFav.setDisable(false);
		});
		
		viewProfileVBox.getChildren().addAll(profileHeader, addFav, removeFav);
		
		profileScrollPane.setContent(viewProfileVBox);
		mainBorderPane.setCenter(profileScrollPane);
	}
	
	/**
	 * This method creates a table containing all the artwork won by a particular
	 * profile.
	 * @param profile
	 * @return A table of won artwork's.
	 */
	public HBox getWonArtworksTable(Profile profile) {
		HBox wonArtworksTable = new HBox();
		wonArtworksTable.setId("Table");
		VBox col1 = new VBox();
		col1.setId("Column");
		VBox col2 = new VBox();
		col2.setId("Column");
		VBox col3 = new VBox();
		col3.setId("Column");
		VBox col4 = new VBox();
		col4.setId("finalColumn");
		Label col1Header = new Label("Artwork Title");
		Label col2Header = new Label("Price (£)");
		Label col3Header = new Label("Previous Owner");
		Label col4Header = new Label("Time Sold");
		col1.getChildren().add(col1Header);
		col2.getChildren().add(col2Header);
		col3.getChildren().add(col3Header);
		col4.getChildren().add(col4Header);
		
		//gets an Array of all Artwork's sold
		ArrayList<Auction> tempArray = new ArrayList<>();
		for (int i = 0; i < auctionManager.getAuctions().size(); i++) {
        	if (auctionManager.getAuctions().get(i).isComplete()){
        		if (auctionManager.getAuctions().get(i).getHighestBid().
        				getBidder().getUsername().equals(profile.getUsername())){
        			tempArray.add(auctionManager.getAuctions().get(i));
        		}
        	}
		}
		
		//sorts the Array on date
		tempArray = sortAuction(tempArray);
		
		//makes the table of won artwork's.
		for (int i = tempArray.size() - 1; i >= 0; i--) {
			Label col1Label = new Label(tempArray.
        		get(i).getArtwork().getTitle());
			Label col2Label = new Label(Double.toString(tempArray.get(i).
					getHighestBid().getAmount()));
        	Label col3Label = new Label(tempArray.
        		get(i).getArtwork().getCreatorName());
        	Label col4Label = new Label(tempArray.
            		get(i).getHighestBid().getDate());
        	col1.getChildren().add(col1Label);
        	col2.getChildren().add(col2Label);
        	col3.getChildren().add(col3Label);
        	col4.getChildren().add(col4Label);
		}
		wonArtworksTable.getChildren().addAll(col1, col2, col3, col4);
        
        return wonArtworksTable;
	}
	
	/**
	 * This method creates a table of all the artwork's sold by a profile.
	 * @param profile
	 * @return A table of all sold artwork's.
	 */
	public HBox getArtworksSold(Profile profile) {
		HBox artworksSoldTable = new HBox();
		artworksSoldTable.setId("Table");
		VBox col1 = new VBox();
		col1.setId("Column");
		VBox col2 = new VBox();
		col2.setId("Column");
		VBox col3 = new VBox();
		col3.setId("Column");
		VBox col4 = new VBox();
		col4.setId("finalColumn");
		Label col1Header = new Label("Artwork Title");
		Label col2Header = new Label("Price (£)");
		Label col3Header = new Label("Winning Bidder");
		Label col4Header = new Label("Time of Win");
		col1.getChildren().add(col1Header);
		col2.getChildren().add(col2Header);
		col3.getChildren().add(col3Header);
		col4.getChildren().add(col4Header);
		
		//gets an Array of all Artwork's sold
		ArrayList<Auction> tempArray = new ArrayList<>();
		for (int i = 0; i < auctionManager.getAuctions().size(); i++) {
			if (auctionManager.getAuctions().get(i).isComplete()) {
				if (auctionManager.getAuctions().get(i).
						getSeller().equals(profile.getUsername())) {
					tempArray.add(auctionManager.getAuctions().get(i));
				}
			}
		}
		
		//sorts the Array on date
		tempArray = sortAuction(tempArray);
		
		for (int i = tempArray.size() - 1; i >= 0; i--) {
			Label col1Label = new Label(tempArray.
				get(i).getArtwork().getTitle());
        	Label col2Label = new Label(Double.toString(tempArray.get(i).
        			getHighestBid().getAmount()));
        	Label col3Label = new Label(tempArray.
        		get(i).getHighestBid().getBidder().getUsername());
        	Label col4Label = new Label(tempArray.
            		get(i).getHighestBid().getDate());
        	col1.getChildren().add(col1Label);
        	col2.getChildren().add(col2Label);
        	col3.getChildren().add(col3Label);
        	col4.getChildren().add(col4Label);
		}
		artworksSoldTable.getChildren().addAll(col1, col2, col3, col4);
		
        return artworksSoldTable;
	}
	
	/**
	 * A method that returns a table of all the bids a profile has 
	 * ever placed.
	 * @param profile
	 * @return A table of bids placed.
	 */
	public HBox getBidsPlaced(Profile profile) {
		HBox bidsPlacedTable = new HBox();
		bidsPlacedTable.setId("Table");
		VBox col1 = new VBox();
		col1.setId("Column");
		VBox col2 = new VBox();
		col2.setId("Column");
		VBox col3 = new VBox();
		col3.setId("finalColumn");
		Label col1Header = new Label("Artwork Title");
		Label col2Header = new Label("Price (£)");
		Label col3Header = new Label("Date of Bid");
		col1.getChildren().add(col1Header);
		col2.getChildren().add(col2Header);
		col3.getChildren().add(col3Header);
		
		//makes an array of bids ever placed by the user.
		ArrayList<Bid> tempArray = new ArrayList<>();
		for (int i = 0; i < auctionManager.getAuctions().size(); i++) {
			for (int j = 0; j < auctionManager.getAuctions().get(i).getAllBids().size(); j++) {
				Bid temp = auctionManager.getAuctions().get(i).getAllBids().get(j);
				if (temp.getBidder().getUsername().equals(profile.getUsername())) {
					tempArray.add(temp);
				}
			}
        }
		
		//sorts the Array on date.
		tempArray = sortBid(tempArray);
		
		//adds the bids to a table.
		for(int i = tempArray.size() -1; i >= 0 ; i--) {
			Bid temp = tempArray.get(i);
			Label col1Label = new Label(temp.getArtwork().getTitle());
			Label col2Label = new Label(Double.toString(temp.getAmount()));
			Label col3Label = new Label(temp.getDate());
			col1.getChildren().add(col1Label);
			col2.getChildren().add(col2Label);
			col3.getChildren().add(col3Label);
		}
		bidsPlacedTable.getChildren().addAll(col1, col2, col3);
        
        return bidsPlacedTable;
	}
	
	/**
	 * A method that returns a table of all the bids that have been made of a
	 * Profiles auction's.
	 * @param profile
	 * @return A table of bids placed on the profiles Auction's.
	 */
	public HBox getBidsOnOwnersArtwork(Profile profile) {
		HBox bidsOnOwnersArtworkTable = new HBox();
		bidsOnOwnersArtworkTable.setId("Table");
		VBox col1 = new VBox();
		col1.setId("Column");
		VBox col2 = new VBox();
		col2.setId("Column");
		VBox col3 = new VBox();
		col3.setId("Column");
		VBox col4 = new VBox();
		col4.setId("finalColumn");
		Label col1Header = new Label("Artwork Title");
		Label col2Header = new Label("Price (£)");
		Label col3Header = new Label("Bidder");
		Label col4Header = new Label("Date of Bid");
		col1.getChildren().add(col1Header);
		col2.getChildren().add(col2Header);
		col3.getChildren().add(col3Header);
		col4.getChildren().add(col4Header);
		
		//gets an array of bids.
		ArrayList<Bid> tempArray = new ArrayList<>();
		for (int i = 0; i < auctionManager.getAuctions().size(); i++) {
			if (auctionManager.getAuctions().get(i).getSeller().
					equals(profile.getUsername())) {
				tempArray = auctionManager.getAuctions().get(i).getAllBids();
			}
		}
		
		//sorts the Array on date.
		tempArray = sortBid(tempArray);
		
		//adds it all to a table.
		for (int j = tempArray.size() - 1; j >= 0; j--) {
			Bid temp = tempArray.get(j);
			Label col1Label = new Label(tempArray.get(j).getArtwork().getTitle());
	        Label col2Label = new Label(Double.toString(temp.getAmount()));
	        Label col3Label = new Label(temp.getBidder().getUsername());
	        Label col4Label = new Label(temp.getDate());
	        col1.getChildren().add(col1Label);
	        col2.getChildren().add(col2Label);
	        col3.getChildren().add(col3Label);
	        col4.getChildren().add(col4Label);
		}

		bidsOnOwnersArtworkTable.getChildren().addAll(col1, col2, col3, col4);
        
        return bidsOnOwnersArtworkTable;
	}
	
	/**
	 * This method's sorts a list of Auction's in order of date. useful when you want
	 * to sort through artwork's.
	 * @param tempArray The array of Auction's.
	 * @return tempArray An array of Auction's but sorted by date.
	 */
	public ArrayList<Auction> sortAuction(ArrayList<Auction> tempArray) {
		//sorts the Array on date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Collections.sort(tempArray, new Comparator<Auction>() {
			  public int compare(Auction o1, Auction o2) {
				  Date date = new Date();
				  Date date2 = new Date();
				  String sd = o1.getHighestBid().getDate();
				  String sd2 = o2.getHighestBid().getDate();
				  try {
					  date = sdf.parse(sd);
				  } catch (ParseException e1) {
					  e1.printStackTrace();
				  }
				  try {  
					date2 = sdf.parse(sd2);
				  } catch (ParseException e) {
					e.printStackTrace();
				  }
				  
				  if (date == null || date2 == null) {
					  return 0;
				  } else {
					 return date.compareTo(date2); 
				  }
			 }
		});
		return tempArray;
	}
	
	/**
	 * This method's sorts a list of Bids in order of date. useful when you want
	 * to sort through Bids.
	 * @param tempArray The array of Bids.
	 * @return An array of Bids but sorted by date.
	 */
	public ArrayList<Bid> sortBid(ArrayList<Bid> tempArray) {
		//sorts the Array on date.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Collections.sort(tempArray, new Comparator<Bid>() {
			public int compare(Bid o1, Bid o2) {
				Date date = new Date();
				Date date2 = new Date();
				String sd = o1.getDate();
				String sd2 = o2.getDate();
				try {
					date = sdf.parse(sd);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				try {  
					date2 = sdf.parse(sd2);
				} catch (ParseException e) {
					e.printStackTrace();
				}
						  
				if (date == null || date2 == null) {
					return 0;
				} else {
					return date.compareTo(date2);
				}
			}
		});
		return tempArray;
	}
	
	/** This method makes the current Profiles page. This allows
	 * the current user to view their information and make
	 * changes to their information and also make custom drawings.
	 */
	public void viewCurrentProfile() {
		//checks if the scene already has a 
		mainBorderPaneTopVBox.getChildren().remove(auctionNavigationBar);
		
		//creates a profile Navigation bar
		profileNavigationBar = createProfileNavigationBar();
		mainBorderPaneTopVBox.getChildren().add(profileNavigationBar);
		
		currentProfileScrollPane = new ScrollPane();
		currentProfileScrollPane.setFitToHeight(true);
		currentProfileScrollPane.setFitToWidth(true);
		VBox currentProfilePageVBox = new VBox();
		
		//creates the header for the profile.
		HBox profileHeader = makeProfileHeader(currentProfile);
		
		//adds the information about the artwork
		Label profileFirstName = new Label("First Name: " + currentProfile.getFirstName());
		profileFirstName.setId("profileDetails");
		Label profileLastName = new Label("Last Name: " + currentProfile.getLastName());
		profileLastName.setId("profileDetails");
		Label profileTelephone = new Label("Telephone: " + currentProfile.getTelephone());
		profileTelephone.setId("profileDetails");
		Label profileAddress = new Label("Address: " + currentProfile.getFirstAddress());
		profileAddress.setId("profileDetails");
		Label profilePostCode = new Label("PostCode: " + currentProfile.getPostcode());
		profilePostCode.setId("profileDetails");
		//adds some margin to the last label.
		currentProfilePageVBox.setMargin(profilePostCode,new Insets(0,
				0, 0, 0));
		
		//Make auction Buttons
		HBox makeAuctionHBox = new HBox();
		Button makePAuciton = new Button("Make new Auction (Painting)");
		makeAuctionHBox.setMargin(makePAuciton,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, LARGE_MARGIN, NORMAL_MARGIN));
		makePAuciton.setOnAction(e -> {
			makeAuctionPWindow(currentProfile);
		});
		Button makeSAuciton = new Button("Make new Auction (Sculpture)");
		makeAuctionHBox.setMargin(makeSAuciton,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, LARGE_MARGIN, NORMAL_MARGIN));
		makeSAuciton.setOnAction(e -> {
			makeAuctionSWindow(currentProfile);
		});
		makeAuctionHBox.getChildren().addAll(makePAuciton, makeSAuciton);
		
		//list of won artwork's
		Label wonArtworksLabel = new Label("All Won Artworks");
		wonArtworksLabel.setId("profileDetails");
		HBox wonArtworksTable = getWonArtworksTable(currentProfile);
		currentProfilePageVBox.setMargin(wonArtworksTable,new Insets(0,
				EXTRA_LARGE_MARGIN, LARGE_MARGIN, EXTRA_LARGE_MARGIN));
		
		//list of artwork's sold
		Label artworksSoldLabel = new Label("All Artworks Sold");
		artworksSoldLabel.setId("profileDetails");
		HBox artworksSoldTable = getArtworksSold(currentProfile);
		currentProfilePageVBox.setMargin(artworksSoldTable,new Insets(0,
				EXTRA_LARGE_MARGIN, LARGE_MARGIN, EXTRA_LARGE_MARGIN));
		
		//list of all bids placed
		Label allPlacedBidsLabel = new Label("All Bids Placed");
		allPlacedBidsLabel.setId("profileDetails");
		HBox allPlacedBidsTable = getBidsPlaced(currentProfile);
		currentProfilePageVBox.setMargin(allPlacedBidsTable,new Insets(0,
				EXTRA_LARGE_MARGIN, LARGE_MARGIN, EXTRA_LARGE_MARGIN));
		
		//list of bids done on their artwork's.
		Label bidsOnOwnersArtworksLabel = new Label("All bids on " +
				currentProfile.getUsername() + "'s Artworks");
		bidsOnOwnersArtworksLabel.setId("profileDetails");
		HBox bidsOnOwnersArtworksTable = getBidsOnOwnersArtwork(currentProfile);
		currentProfilePageVBox.setMargin(bidsOnOwnersArtworksTable,new Insets(0,
				EXTRA_LARGE_MARGIN, LARGE_MARGIN, EXTRA_LARGE_MARGIN));
		
		currentProfilePageVBox.getChildren().addAll(profileHeader, profileFirstName,
				profileLastName, profileTelephone, profileAddress, profilePostCode, makeAuctionHBox,
				wonArtworksLabel, wonArtworksTable, artworksSoldLabel,
				artworksSoldTable, allPlacedBidsLabel, allPlacedBidsTable,
				bidsOnOwnersArtworksLabel, bidsOnOwnersArtworksTable);
		
		currentProfileScrollPane.setContent(currentProfilePageVBox);
		mainBorderPane.setCenter(currentProfileScrollPane);
	}
	
	/**
	 * This method creates a new instance of an Auction for the user.
	 * This opens up a window where the user can enter in information
	 * in order to create a new auction. This method is only called if the 
	 * users wants to add a painting to an Auciton.
	 */
	public void makeAuctionPWindow(Profile profile) {
		Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setTitle("Make Auction");
        window.getIcons().add(new Image("applicationIcon.png"));
        
        VBox makeAuctionVBox = new VBox();
        
        //displays the information that can be added.
        Label addArtworkTitle = new Label("Name of Artwork");
        TextField addArtworkTitleField = new TextField();
        
        Label addDescription = new Label("Add a Description");
        TextField addDescriptionField = new TextField();
        
        Label addMainPhoto = new Label("Add Main Photo");
        HBox browseSource = new HBox();
        TextField addMainPhotoField = new TextField();
        addMainPhotoField.setEditable(false); //can't edit this text field.
        Button browse = new Button("Browse");
        browseSource.getChildren().addAll(addMainPhotoField, browse);
        
        Label addYearCreated = new Label("Year Artwork Created");
        TextField addYearCreatedField = new TextField();
        Label errorMessageYearCreated = new Label("");
        errorMessageYearCreated.setStyle("-fx-text-fill: red");
        
        Label addPrice = new Label("Add Price");
        TextField addPriceField = new TextField();
        Label errorMessagePrice = new Label("");
        errorMessagePrice.setStyle("-fx-text-fill: red");
        
        Label addWidth = new Label("Add Width");
        TextField addWidthField = new TextField();
        Label errorMessageWidth = new Label("");
        errorMessageWidth.setStyle("-fx-text-fill: red");
        
        Label addHeight = new Label("Add Height");
        TextField addHeightField = new TextField();
        Label errorMessageHeight = new Label("");
        errorMessageHeight.setStyle("-fx-text-fill: red");
        
        Button addAuctionButton = new Button("Make Auction");
        Label errorMessageMain = new Label("");
        errorMessageMain.setStyle("-fx-text-fill: red");
        
        makeAuctionVBox.getChildren().addAll(addArtworkTitle, addArtworkTitleField,
        		addDescription, addDescriptionField, addMainPhoto, browseSource,
        		addYearCreated, addYearCreatedField, errorMessageYearCreated, addPrice,
        		addPriceField, errorMessagePrice, addWidth, addWidthField, errorMessageWidth, addHeight,
        		addHeightField, errorMessageHeight, addAuctionButton, errorMessageMain);
        
        //when you click the browse button.
        browse.setOnAction(e -> {
        	//creates a directory chooser and saves the choice to a file.
        	try {
	        	FileChooser newFileChooser = new FileChooser();
	    		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png");
	    		newFileChooser.getExtensionFilters().add(extFilter);
	    		selectedDirectory = newFileChooser.showOpenDialog(mainStage);
	    		addMainPhotoField.setText(selectedDirectory.getPath());
        	} catch(NullPointerException e1) {
        		errorMessageMain.setText("File not Found!");
        		errorMessageMain.setStyle("-fx-text-fill: red");
        	}
        });
        
        //The action for creating an Auction.
        addAuctionButton.setOnAction(e -> {
        	if (addArtworkTitleField.getText().isEmpty() || 
        			addMainPhotoField.getText().isEmpty() || addYearCreatedField.getText().isEmpty() || 
        			addPriceField.getText().isEmpty() || addWidthField.getText().isEmpty() || addHeightField.getText().isEmpty()) {
        		errorMessageMain.setText("Must complete all Fields!");
        		errorMessageMain.setStyle("-fx-text-fill: red");
        		return;
        	}

        	String title = addArtworkTitleField.getText();
        	String description = addDescriptionField.getText();
        	
        	int year = 0;
        	try {
        		year = Integer.parseInt(addYearCreatedField.getText());
        	} catch(NumberFormatException e1) {
        		errorMessageYearCreated.setText("Can only contain digits!");
        		errorMessageYearCreated.setStyle("-fx-text-fill: red");
        		return;
        	}
        	
        	double price = 0;
        	try {
        		price = Double.parseDouble(addPriceField.getText());
        	} catch(NumberFormatException e1) {
        		errorMessagePrice.setText("Can only contain digits!");
        		errorMessagePrice.setStyle("-fx-text-fill: red");
        		return;
        	}
        	
        	int width = 0;
        	try {
        		width = Integer.parseInt(addWidthField.getText());
        	} catch(NumberFormatException e1) {
        		errorMessageWidth.setText("Can only contain digits!");
            	errorMessageWidth.setStyle("-fx-text-fill: red");
            	return;
        	}

  			int height = 0;
        	try {
        		height = Integer.parseInt(addHeightField.getText());
        	} catch(NumberFormatException e1) {
        		errorMessageHeight.setText("Can only contain digits!");
               	errorMessageHeight.setStyle("-fx-text-fill: red");
               	return;
        	}
        	//makes the date and time of the auction being uploaded.
	        Date date = new Date();
	  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        String dateStr = sdf.format(date);
	        
	        //This copies the image that the user has chosen and saves it into the artwork's folder.
	        String newDir = saveImage(selectedDirectory, title, profile, errorMessageMain);
	        if (newDir.isEmpty()) {
	        	return;
	        }
	    				
	        Painting newArtwork = new Painting(title, description, newDir, 
	        		profile.getUsername(), year, price, dateStr, width, height);
	        Auction newAuction = new Auction(newArtwork, false);
	        auctionManager.getAuctions().add(newAuction);
	        window.close();
        });
        
        makeAuctionVBox.setMargin(addArtworkTitle,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addArtworkTitleField,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addDescription,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addDescriptionField,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addMainPhoto,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(browseSource,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addYearCreated,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addYearCreatedField,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(errorMessageYearCreated,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addPrice,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addPriceField,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(errorMessagePrice,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addWidth,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addWidthField,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addHeight,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addHeightField,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addAuctionButton,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(errorMessageMain,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));

		Scene scene = new Scene(makeAuctionVBox, MAKE_AUCTION_SCENE_WIDTH, MAKE_AUCTION_SCENE_HEIGHT);
        window.setScene(scene);
        window.showAndWait();
	}
	
	/**
	 * This method saves an image into the artwork's folder. This is so the program can access the images
	 * when a user creates an Auction.
	 * @param selectedDirectory The directory.
	 * @param title The title of artwork.
	 * @param profile The profile creating the artwork.
	 * @param errorMessageMain The error message if something goes wrong.
	 * @return A string of the new Directory.
	 */
	public String saveImage(File selectedDirectory, String title, Profile profile, Label errorMessageMain) {
		String newDir = "";
        try {
        	BufferedImage image = ImageIO.read(selectedDirectory);
        	newDir = "artworks/" + title + "_" + profile.getUsername() + ".png";
        	File newFile = new File(newDir);
        	newFile.getParentFile().mkdirs();
        	ImageIO.write(image, "png", newFile);   	
        } catch (IOException e1) {
        	errorMessageMain.setText("Error Saving File!");
    		errorMessageMain.setStyle("-fx-text-fill: red");
    		return "";
        }
        return newDir;
	}
	
	/**
	 * this method is called when the user wants to create a new auction
	 * for a sculpture. This will allow the user to create an auction by
	 * adding the relevant data.
	 * @param profile The user who will own the Auction.
	 */
	public void makeAuctionSWindow(Profile profile) {
		Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setTitle("Make Auction");
        window.getIcons().add(new Image("applicationIcon.png"));
        
        VBox makeAuctionVBox = new VBox();
        
        //displays the information that can be added.
        Label addArtworkTitle = new Label("Name of Artwork");
        TextField addArtworkTitleField = new TextField();
        
        Label addDescription = new Label("Add a Description");
        TextField addDescriptionField = new TextField();
        
        Label addMainPhoto = new Label("Add Main Photo");
        HBox browseSource = new HBox();
        TextField addMainPhotoField = new TextField();
        addMainPhotoField.setEditable(false); //can't edit this text field.
        Button browse = new Button("Browse");
        browseSource.getChildren().addAll(addMainPhotoField, browse);
        
        Label addYearCreated = new Label("Year Artwork Created");
        TextField addYearCreatedField = new TextField();
        Label errorMessageYearCreated = new Label("");
        errorMessageYearCreated.setStyle("-fx-text-fill: red");
        
        Label addPrice = new Label("Add Price");
        TextField addPriceField = new TextField();
        Label errorMessagePrice = new Label("");
        errorMessagePrice.setStyle("-fx-text-fill: red");
        
        Label addWidth = new Label("Add Width");
        TextField addWidthField = new TextField();
        Label errorMessageWidth = new Label("");
        errorMessageWidth.setStyle("-fx-text-fill: red");
        
        Label addHeight = new Label("Add Height");
        TextField addHeightField = new TextField();
        Label errorMessageHeight = new Label("");
        errorMessageHeight.setStyle("-fx-text-fill: red");
        
        Label addDepth = new Label("Add Depth");
        TextField addDepthField = new TextField();
        Label errorMessageDepth = new Label("");
        errorMessageDepth.setStyle("-fx-text-fill: red");
        
        Label addMaterial = new Label("Add Material");
        TextField addMaterialField = new TextField();
        
        Button addAuctionButton = new Button("Make Auction");
        Label errorMessageMain = new Label("");
        errorMessageMain.setStyle("-fx-text-fill: red");
        
        makeAuctionVBox.getChildren().addAll(addArtworkTitle, addArtworkTitleField,
        		addDescription, addDescriptionField, addMainPhoto, browseSource,
        		addYearCreated, addYearCreatedField, errorMessageYearCreated, addPrice,
        		addPriceField, errorMessagePrice, addWidth, addWidthField, errorMessageWidth, addHeight,
        		addHeightField, errorMessageHeight, addDepth, addDepthField, errorMessageDepth, 
        		addMaterial, addMaterialField, addAuctionButton, errorMessageMain);
        
        //when you click the browse button.
        browse.setOnAction(e -> {
        	//creates a directory chooser and saves the choice to a file.
        	try {
	        	FileChooser newFileChooser = new FileChooser();
	    		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png");
	    		newFileChooser.getExtensionFilters().add(extFilter);
	    		selectedDirectory = newFileChooser.showOpenDialog(mainStage);
	    		addMainPhotoField.setText(selectedDirectory.getPath());
        	} catch(NullPointerException e1) {
        		errorMessageMain.setText("File not Found!");
        		errorMessageMain.setStyle("-fx-text-fill: red");
        	}
        });
        
        //The action for creating an Auction.
        addAuctionButton.setOnAction(e -> {
        	if (addArtworkTitleField.getText().isEmpty() || 
        			addMainPhotoField.getText().isEmpty() || addYearCreatedField.getText().isEmpty() || 
        			addPriceField.getText().isEmpty() || addWidthField.getText().isEmpty() || addHeightField.getText().isEmpty() ||
        			addMaterialField.getText().isEmpty() || addMaterialField.getText().isEmpty()) {
        		errorMessageMain.setText("Must complete all Fields!");
        		errorMessageMain.setStyle("-fx-text-fill: red");
        		return;
        	}

        	String title = addArtworkTitleField.getText();
        	String description = addDescriptionField.getText();
        	
        	int year = 0;
        	try {
        		year = Integer.parseInt(addYearCreatedField.getText());
        	} catch(NumberFormatException e1) {
        		errorMessageYearCreated.setText("Can only contain digits!");
        		errorMessageYearCreated.setStyle("-fx-text-fill: red");
        		return;
        	}
        	
        	double price = 0;
        	try {
        		price = Double.parseDouble(addPriceField.getText());
        	} catch(NumberFormatException e1) {
        		errorMessagePrice.setText("Can only contain digits!");
        		errorMessagePrice.setStyle("-fx-text-fill: red");
        		return;
        	}
        	
        	int width = 0;
        	try {
        		width = Integer.parseInt(addWidthField.getText());
        	} catch(NumberFormatException e1) {
        		errorMessageWidth.setText("Can only contain digits!");
            	errorMessageWidth.setStyle("-fx-text-fill: red");
            	return;
        	}

  			int height = 0;
        	try {
        		height = Integer.parseInt(addHeightField.getText());
        	} catch(NumberFormatException e1) {
        		errorMessageHeight.setText("Can only contain digits!");
               	errorMessageHeight.setStyle("-fx-text-fill: red");
               	return;
        	}
        	
        	int depth = 0;
        	try {
        		depth = Integer.parseInt(addDepthField.getText());
        	} catch(NumberFormatException e1) {
        		errorMessageDepth.setText("Can only contain digits!");
               	errorMessageDepth.setStyle("-fx-text-fill: red");
               	return;
        	}
        	
        	String material = addMaterialField.getText();
        	
        	//makes the date and time of the auction being uploaded.
	        Date date = new Date();
	  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        String dateStr = sdf.format(date);
	        				
	        //This copies the image that the user has chosen and saves it into the artwork's folder.
	        String newDir = saveImage(selectedDirectory, title, profile, errorMessageMain);
	        if (newDir.isEmpty()) {
	        	return;
	        }
	    				
	        Sculpture newArtwork = new Sculpture(title, description, newDir, 
	        		profile.getUsername(), year, price, dateStr, width, height, depth, material);
	        Auction newAuction = new Auction(newArtwork, false);
	        auctionManager.getAuctions().add(newAuction);
	        window.close();
        });
        
        makeAuctionVBox.setMargin(addArtworkTitle,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addArtworkTitleField,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addDescription,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addDescriptionField,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addMainPhoto,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(browseSource,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addYearCreated,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addYearCreatedField,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(errorMessageYearCreated,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addPrice,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addPriceField,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(errorMessagePrice,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addWidth,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addWidthField,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addHeight,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addHeightField,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addAuctionButton,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(errorMessageMain,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addDepth,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addDepthField,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addMaterial,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        makeAuctionVBox.setMargin(addMaterialField,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));

		Scene scene = new Scene(makeAuctionVBox, MAKE_AUCTION_SCULPTURE_SCENE_WIDTH, MAKE_AUCTION_SCULPTURE_SCENE_HEIGHT);
        window.setScene(scene);
        window.showAndWait();
	}
		
	/**
	 * This creates the GUI that will be the profile header.
	 * This is found at the top of any profile page and includes the profiles name
	 * and their profile picture.
	 * @return A container containing the profile Header.
	 */
	public HBox makeProfileHeader(Profile profile) {
		HBox profileHeader = new HBox();
		
		profileHeader.setId("profileHeader");
		ImageView profileAvatar = createImage(profile.getImagePath());
		profileAvatar.fitHeightProperty().bind(profileHeader.heightProperty());
		profileAvatar.setFitWidth(AVATAR_WIDTH);
		
		Label profileUsername = new Label(profile.getUsername());
		profileUsername.setId("profileUserName");
		profileHeader.getChildren().addAll(profileAvatar, profileUsername);
		profileHeader.setMaxHeight(PROFILE_HEADER_HEIGHT);
		profileHeader.setMinHeight(PROFILE_HEADER_HEIGHT);
		
		return profileHeader;
	}
	
	
	/**
	 * Creates a profile Navigation Bar. This is a bar that will appear underneath the main
	 * navigation bar once the user views their own profile.
	 * @return A profile Navigation bar in a HBox container.
	 */
	public HBox createProfileNavigationBar() {
		profileNavigationBar = new HBox();
		profileNavigationBar.setId("profileNavigationBar");
		profileNavigationBar.setMaxHeight(PROFILE_NAVIGATION_BAR_HEIGHT);
		profileNavigationBar.setMinHeight(PROFILE_NAVIGATION_BAR_HEIGHT);
		
		Label settingslabel = new Label("Settings");
		settingslabel.setId("settingslabel");
		
		Label customDrawingLabel = new Label("Choose Profile Picture");
		customDrawingLabel.setId("customDrawingLabel");
		
		profileNavigationBar.getChildren().addAll(settingslabel, customDrawingLabel);
		
		//opens settings page on click.
		settingslabel.setOnMouseClicked( e -> {
			makeSettingsWindow();
		});
		
		//opens the customDrawing window.
		customDrawingLabel.setOnMouseClicked( e -> {
			ProfilePicture run = new ProfilePicture();
            Stage primaryStage = new Stage();
            run.runProgram(primaryStage, currentProfile);
		});
		
		return profileNavigationBar;
	}
	
	
	/**
	 * This method creates a settings window which allows the user to edit
	 * their information such as name, address or telephone number.
	 */
	public void makeSettingsWindow() {
		Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.resizableProperty().setValue(Boolean.FALSE);
        window.setTitle("Make Changes");
        window.getIcons().add(new Image("applicationIcon.png"));
        
        VBox settingsVBox = new VBox();
        
        //displays the information that can be changed.
        Label changeFirstName = new Label("Edit FirstName");
        HBox changeFirstNameHBox = new HBox();
        TextField firstNameField = new TextField();
        firstNameField.setText(currentProfile.getFirstName());
        Button makeFNChanges = new Button("Make Changes");
        changeFirstNameHBox.getChildren().addAll(firstNameField, makeFNChanges);
        
        Label changeLastName = new Label("Edit LastName");
        HBox changeLastNameHBox = new HBox();
        TextField lastNameField = new TextField();
        lastNameField.setText(currentProfile.getLastName());
        Button makeLNChanges = new Button("Make Changes");
        changeLastNameHBox.getChildren().addAll(lastNameField, makeLNChanges);
        
        Label changeTelephone = new Label("Edit Telephone Number");
        HBox changeTelephoneNameHBox = new HBox();
        TextField telephoneField = new TextField();
        telephoneField.setText(currentProfile.getTelephone());
        Button makeTChanges = new Button("Make Changes");
        changeTelephoneNameHBox.getChildren().addAll(telephoneField, makeTChanges);
        
        Label changeAddress = new Label("Edit Address");
        HBox changeAddressNameHBox = new HBox();
        TextField addressField = new TextField();
        addressField.setText(currentProfile.getFirstAddress());
        Button makeAChanges = new Button("Make Changes");
        changeAddressNameHBox.getChildren().addAll(addressField, makeAChanges);
        
        Label changePostCode = new Label("Edit PostCode");
        HBox changePostCodeNameHBox = new HBox();
        TextField postCodeField = new TextField();
        postCodeField.setText(currentProfile.getPostcode());
        Button makePCChanges = new Button("Make Changes");
        changePostCodeNameHBox.getChildren().addAll(postCodeField, makePCChanges);
        
        settingsVBox.getChildren().addAll(changeFirstName, changeFirstNameHBox,
        		changeLastName, changeLastNameHBox, changeTelephone,
        		changeTelephoneNameHBox, changeAddress, changeAddressNameHBox,
        		changePostCode, changePostCodeNameHBox);
        
        settingsVBox.setMargin(changeFirstName,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        settingsVBox.setMargin(changeFirstNameHBox,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        settingsVBox.setMargin(changeLastName,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        settingsVBox.setMargin(changeLastNameHBox,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        settingsVBox.setMargin(changeTelephone,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        settingsVBox.setMargin(changeTelephoneNameHBox,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        settingsVBox.setMargin(changeAddress,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        settingsVBox.setMargin(changeAddressNameHBox,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        settingsVBox.setMargin(changePostCode,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        settingsVBox.setMargin(changePostCodeNameHBox,new Insets(SMALL_MARGIN,
        		SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN));
        
        //adds logic to the buttons.
        makeFNChanges.setOnAction( e -> {
			currentProfile.setFirstName(firstNameField.getText());
		});
        makeLNChanges.setOnAction( e -> {
        	currentProfile.setLastName(lastNameField.getText());
		});
        makeTChanges.setOnAction( e -> {
        	currentProfile.setTelephone(telephoneField.getText());
		});
        makeAChanges.setOnAction( e -> {
        	currentProfile.setFirstAddress(addressField.getText());
		});
        makePCChanges.setOnAction( e -> {
        	currentProfile.setPostcode(postCodeField.getText());
		});

		Scene scene = new Scene(settingsVBox, SETTINGS_SCENE_WIDTH, SETTINGS_SCENE_HEIGHT);
        window.setScene(scene);
        window.showAndWait();
	}
	
	
	/**
	 * This method creates the GUI that shows all the profiles that have
	 * an account on Artatawe and allows you to visit their Account page.
	 */
	public void viewAllCurrentProfiles() {
		mainBorderPaneTopVBox.getChildren().remove(auctionNavigationBar);
		mainBorderPaneTopVBox.getChildren().remove(profileNavigationBar);
		
		viewAllProfilesScrollPane = new ScrollPane();
		viewAllProfilesScrollPane.setFitToHeight(true);
		viewAllProfilesScrollPane.setFitToWidth(true);
		
		VBox profileList = new VBox();
		
		for (int i = 0; i < profileManager.getProfiles().size(); i++) {
			//makes sure you don't add the current profile to the list of profiles.
			if (!(currentProfile.getUsername().equals(profileManager.getProfiles().get(i).getUsername()))) {
				Profile tempProfile = profileManager.getProfiles().get(i);
				HBox profileHBox = new HBox();
				profileHBox.setId("profileHBox");
				profileHBox.setMaxHeight(PROFILE_lIST_HBOX_HEIGHT);
				profileHBox.setMinHeight(PROFILE_lIST_HBOX_HEIGHT);
				
				//creates profile image
				ImageView profileImage = createImage(tempProfile.getImagePath());
				profileImage.fitHeightProperty().bind(profileHBox.heightProperty());
				profileImage.setFitWidth(PROFILE_lIST_HBOX_HEIGHT);
				
				//creates label
				Label profileUsername = new Label(tempProfile.getUsername());
				profileUsername.setId("profileUsername");
				
				profileList.setMargin(profileHBox,new Insets(0, WIDE_MARGIN, 0, WIDE_MARGIN));
				
				//creates the add to fav button
				Button addFav = new Button("Add to Favourites");
				addFav.setId("auctionButtons");
				profileHBox.setMargin(addFav,new Insets(LARGE_MARGIN,
						NORMAL_MARGIN, NORMAL_MARGIN, NORMAL_MARGIN));
				
				//creates a button to remove a profile as a favourite.
				Button removeFav = new Button("Remove from Favourites");
				addFav.setId("auctionButtons");
				profileHBox.setMargin(removeFav,new Insets(LARGE_MARGIN,
						NORMAL_MARGIN, NORMAL_MARGIN, NORMAL_MARGIN));
						
				//if the seller is already in the favourites list then disable add to fav button.
				for (int j = 0; j < profileManager.getProfiles().size(); j++) {
					if (profileManager.getProfiles().get(j).getUsername().equals(tempProfile.getUsername())) {
						for (int k = 0; k < currentProfile.getFavourites().size(); k++) {
							if (tempProfile.getUsername().equals(currentProfile.getFavourites().get(k))) {
								addFav.setDisable(true);
							}
						}
					}
				}
				
				//this adds the seller to the users favourites.
				addFav.setOnAction( e -> {
					currentProfile.addFavourite(tempProfile.getUsername());
					addFav.setDisable(true);
				});
				removeFav.setOnAction( e -> {
					currentProfile.getFavourites().remove(tempProfile.getUsername());
					addFav.setDisable(false);
				});
				
				profileHBox.getChildren().addAll(profileImage, profileUsername, addFav, removeFav);
				profileList.getChildren().add(profileHBox);
				
				//opens up the profile page for that particular profile.
				profileHBox.setOnMouseClicked( e -> {
					viewProfile(tempProfile);
				});
			}
		}
		viewAllProfilesScrollPane.setContent(profileList);
		mainBorderPane.setCenter(viewAllProfilesScrollPane);
	}
	
	
	/**
	 * This method is called when the user wants to close the program
	 * by clicking the red x in the top right corner. This method will close the
	 * program but first it saves all the data, so changes are not lost.
	 */
	public void closeProgram() {
		saveData();
	}
	
	
	/**
	 * This method saves the data.
	 */
	public void saveData() {
		FileWriter filewriter1 = null;
		try {
			filewriter1 = new FileWriter("profile.txt", "auction.txt");
		} catch (FileNotFoundException e) {
			System.out.println("File not Found.");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Wrong encoding used.");
		}
		auctionManager.saveAuctions(filewriter1);
		profileManager.saveProfiles(filewriter1);
	}
}