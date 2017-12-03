import java.util.ArrayList;
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
	private final int LOGIN_BUTTON_WIDTH = 300;
	private final int AUCTION_BOX_ITEM_PIC_CONTAINER_HEIGHT = 160;
	private final int AUCTION_BOX_ITEM_PIC_CONTAINER_WIDTH = 200;
	private final int AUCTION_BOX_ITEM_WIDTH = 200;
	private final int AUCTION_BOX_ITEM_HEIGHT = 200;
	private final int AUCTION_NAVIGATION_BAR_HEIGHT = 40;
	private final int PROFILE_NAVIGATION_BAR_HEIGHT = 40;
	private final int AUCTION_FLOWPANE_H_GAP = 4; //to fit 4 auction boxes on a line
	private final int NORMAL_MARGIN = 10;
	private final int SMALL_MARGIN = 5;
	private final int LARGE_MARGIN = 20;
	private final int EXTRA_LARGE_MARGIN = 30;
	private final int SETTINGS_SCENE_WIDTH = 300;
	private final int SETTINGS_SCENE_HEIGHT = 320;
	private final int PROFILE_HEADER_HEIGHT = 200;
	private final int AVATAR_WIDTH = 200;
	private final int AUCTION_IMAGE_CONTAINER = 200;
	private final int TEXT_FIELD_MARGIN = 7;
	
	//instance variables
	private Scene artataweScene; //The scene used for the program.
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
		profileManager = new ProfileManager();
		auctionManager = new AuctionManager();
		
		//HARD CODED OBJECTS DELETE AFTER TESTING--------------------------------------------------------------------------
		Profile p1 = new Profile("redneck","Wesly","Kenny","07831432809","10 Hillary Street", "SP3 8NT", "artworks/Son_Of_A_Man.png");
		Profile p2 = new Profile("chrome","Gia","Searle","07825753891","22 Bank Street", "BS2 4NK", "artworks/Son_Of_A_Man.png");
		Profile p3 = new Profile("roker","Caroline","Whitmore","07825764382","56 Wellington Street", "WE1 5ST", "artworks/Son_Of_A_Man.png");
		Profile p4 = new Profile("some_username","John","Stone","07825786273","10 Wild Green West", "WG2 5WE", "artworks/Son_Of_A_Man.png");
		
		Painting ap1 = new Painting("Son Of A Man", "This is a painting I created in my in my while at University a couple years ago.", "artworks/Son_Of_A_Man.png", "redneck", 2015, 500.0, 2, 30, 60);
		Painting ap2 = new Painting("American Gothic", "This is a painting of a two americans.", "artworks/American_Gothic.png", "chrome", 2000, 1000.0, 3, 40, 80);
		Sculpture as1 = new Sculpture("The Thinker", "A greek man thinking.", "artworks/The_Thinker.png", "roker", 1050, 2500.99, 1, 40, 80, 20, "Marble");
		Sculpture as2 = new Sculpture("Discobolus", "A greek man with a Disc.", "artworks/Discobolus.png", "some_username", 980, 99.99, 2, 100, 200, 40, "Marble");
		
		ArrayList<String> photos = new ArrayList<>();
		photos.add("artworks/The_Thinker2.png");
		photos.add("artworks/The_Thinker3.png");
		as1.setExtraPhotos(photos);
		
		Auction a1 = new Auction(ap1, false);
		Auction a2 = new Auction(ap2, true);
		Auction a3 = new Auction(as1, false);
		Auction a4 = new Auction(as2, false);
		
		Bid b1 = new Bid(p2,ap1,600.0);
		Bid b2 = new Bid(p3,ap1,650.0);
		a1.placeBid(b1);
		a1.placeBid(b2);
		
		Bid b3 = new Bid(p1,ap2,1200.0);
		Bid b4 = new Bid(p3,ap2,1250.0);
		Bid b5 = new Bid(p4,ap2,1400.0);
		a2.placeBid(b3);
		a2.placeBid(b4);
		a2.placeBid(b5);
		
		Bid b6 = new Bid(p4,as1,3000.0);
		a3.placeBid(b6);
		
		Bid b7 = new Bid(p1,as2,110.0);
		Bid b8 = new Bid(p2,as2,120.0);
		a4.placeBid(b7);
		a4.placeBid(b8);
		
		auctionManager.addAuction(a1);
		auctionManager.addAuction(a2);
		auctionManager.addAuction(a3);
		auctionManager.addAuction(a4);
		
		profileManager.addProfile(p1);
		profileManager.addProfile(p2);
		profileManager.addProfile(p3);
		profileManager.addProfile(p4);
		//HARD CODED OBJECTS DELETE AFTER TESTING--------------------------------------------------------------------------
		
		//stuff to do with the stage
		primaryStage.setTitle("Artatawe");
		primaryStage.getIcons().add(new Image("applicationIcon.png"));
		
		artataweScene = new Scene(makeLoginPage(), SCENE_WIDTH, SCENE_HEIGHT);
		artataweScene.getStylesheets().add("artataweStyleSheet.css");
		
		//calls this method when user closes the program (red x).
		primaryStage.setOnCloseRequest(e -> closeProgram());
		
		primaryStage.setScene(artataweScene);
		primaryStage.show();
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
				
		//Login Button.
		Button loginButton = new Button("Connect");
		loginButton.setMinWidth(LOGIN_BUTTON_WIDTH);
		loginButton.setId("loginButton");
		
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
		//create VBox to go in borderPane bottom.
		borderPaneVBox.getChildren().addAll(errorLabel, loginButton);
		borderPaneVBox.setMargin(errorLabel,new Insets(SMALL_MARGIN,
				SMALL_MARGIN, SMALL_MARGIN, SMALL_MARGIN + 1));
		return borderPaneVBox;
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
		
		filterlabel.setId("filterlabel");
		searchLabel.setId("searchLabel");
		searchUserArtwork.setId("searchUserArtwork");
		paintingCheckBox.setId("filterPaintingCheckBox");
		sculptureCheckBox.setId("filterSculptureCheckBox");
		startSearchLabel.setId("startSearchLabel");
		
		auctionNavigationBar.getChildren().addAll(filterlabel,
				paintingCheckBox, sculptureCheckBox, searchLabel,
				searchUserArtwork, startSearchLabel);
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
		Button logOutButton = new Button("Log Out");
		viewAuctions.setId("viewAuctionsButton");
		viewProfile.setId("viewProfileButton");
		logOutButton.setId("logOutButton");
		navigationBar.getChildren().addAll(viewAuctions, viewProfile, logOutButton);
		
		//sets the actions for the buttons on the Navigation bar.
		viewAuctions.setOnAction( e -> {
			makeDashboard();
		});
		viewProfile.setOnAction( e -> {
			if (mainBorderPane.getCenter() == profileScrollPane) {
				System.out.println("Account already open");
			} else {
				viewCurrentProfile();
			}
		});
		//logs out of the system.
		logOutButton.setOnAction( e -> {
			artataweScene.setRoot(makeLoginPage());
			currentProfile = null;
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
		artworkName.setId("artworkName");
		artworkOwner.setId("artworkOwner");
		auctionBoxVBox.getChildren().addAll(artworkName, artworkOwner);
		
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
		
		//creates a button to view owners profile.
		Button viewProfile = new Button("View Profile");
		viewProfile.setId("auctionButtons");
		auctionPageVBox.setMargin(viewProfile,new Insets(NORMAL_MARGIN,
				NORMAL_MARGIN, NORMAL_MARGIN, NORMAL_MARGIN));
		
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
		auctionPageVBox.setMargin(bidTable,new Insets(0,30,20,30));
		
		//button to view the auction owners profile.
		viewProfile.setOnAction( e -> {
			for (int i = 0; i < profileManager.getProfiles().size(); i++){
				if (auction.getArtwork().getCreatorName().
						equals(profileManager.getProfiles().get(i).getUsername())){
					viewProfile(profileManager.getProfiles().get(i));
				}
			}
		});
		
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
			
			enterBid.setText("");
			if (currentProfile.getUsername().equals(auction.getHighestBid().
					getBidder().getUsername())) {
				bidErrorMessage.setText("You already have the highest Bid!");
			} else if (auction.getArtwork().getPrice() > newBidTotal) {
				bidErrorMessage.setText("You must enter a bid higher than initial price.");
			} else if (auction.getHighestBid().getAmount() > newBidTotal){
				bidErrorMessage.setText("Must be higher than current highest bid.");
			} else {
				Bid newbid = new Bid(currentProfile,auction.getArtwork(),newBidTotal);
				auction.placeBid(newbid);
				bidErrorMessage.setText("");
				auctionPageVBox.getChildren().remove(bidTable);
				bidTable = makeBidTable(auction);
				auctionPageVBox.getChildren().add(bidTable);
				auctionPageVBox.setMargin(bidTable,new Insets(0,30,20,30));
			}  
		});
		
		auctionPageVBox.getChildren().addAll(artworkName,
				artworkComplete, artworkOwner, artworkCreationYear,
				artworkPrice,artworkBidTotal, artworkDateAndTime,
				artworkDescription, viewProfile, makeBid, 
				bidTableLabel, bidTable);
		
		auctionScrollPane.setContent(auctionPageVBox);
		mainBorderPane.setCenter(auctionScrollPane);
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
		
		//loops backwards so the highest bid is at the top.
		for (int i = auction.getAllBids().size() - 1; i >= 0; i--) {
			Bid tempbid = auction.getAllBids().get(i);
			Label col1Label = new Label(tempbid.getBidder().getUsername());
			Label col2Label = new Label(Double.toString(tempbid.getAmount()));
			Label col3Label = new Label(tempbid.getDate());
			col1.getChildren().add(col1Label);
			col2.getChildren().add(col2Label);
			col3.getChildren().add(col3Label);
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
		
		//list of won artwork's
		Label wonArtworksLabel = new Label("All Won Artworks");
		wonArtworksLabel.setId("profileDetails");
		HBox wonArtworksTable = getWonArtworksTable(profile);
		viewProfileVBox.setMargin(wonArtworksTable,new Insets(0,
				EXTRA_LARGE_MARGIN, LARGE_MARGIN, EXTRA_LARGE_MARGIN));
		
		//list of artwork's sold
		Label artworksSoldLabel = new Label("All Artworks Sold");
		artworksSoldLabel.setId("profileDetails");
		HBox artworksSoldTable = getArtworksSold(profile);
		viewProfileVBox.setMargin(artworksSoldTable,new Insets(0,
				EXTRA_LARGE_MARGIN, LARGE_MARGIN, EXTRA_LARGE_MARGIN));
		
		//list of all bids placed
		Label allPlacedBidsLabel = new Label("All Bids Placed");
		allPlacedBidsLabel.setId("profileDetails");
		HBox allPlacedBidsTable = getBidsPlaced(profile);
		viewProfileVBox.setMargin(allPlacedBidsTable,new Insets(0,
				EXTRA_LARGE_MARGIN, LARGE_MARGIN, EXTRA_LARGE_MARGIN));
		
		//list of bids done on their artwork's.
		Label bidsOnOwnersArtworksLabel = new Label("All bids on " +
				profile.getUsername() + "'s Artworks");
		bidsOnOwnersArtworksLabel.setId("profileDetails");
		HBox bidsOnOwnersArtworksTable = getBidsOnOwnersArtwork(profile);
		viewProfileVBox.setMargin(bidsOnOwnersArtworksTable,new Insets(0,
				EXTRA_LARGE_MARGIN, LARGE_MARGIN, EXTRA_LARGE_MARGIN));
		
		viewProfileVBox.getChildren().addAll(profileHeader, wonArtworksLabel,
				wonArtworksTable, artworksSoldLabel, artworksSoldTable,
				allPlacedBidsLabel, allPlacedBidsTable, bidsOnOwnersArtworksLabel,
				bidsOnOwnersArtworksTable);
		
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
		col3.setId("finalColumn");
		Label col1Header = new Label("Artwork Title");
		Label col2Header = new Label("Price (£)");
		Label col3Header = new Label("Previous Owner");
		col1.getChildren().add(col1Header);
		col2.getChildren().add(col2Header);
		col3.getChildren().add(col3Header);
		for (int i = 0; i < auctionManager.getAuctions().size(); i++) {
        	if (auctionManager.getAuctions().get(i).isComplete()){
        		if (auctionManager.getAuctions().get(i).getHighestBid().
        				getBidder().getUsername().equals(profile.getUsername())){
        			Label col1Label = new Label(auctionManager.getAuctions().
        					get(i).getArtwork().getTitle());
        			Label col2Label = new Label(Double.toString(auctionManager.
        					getAuctions().get(i).getHighestBid().getAmount()));
        			Label col3Label = new Label(auctionManager.getAuctions().
        					get(i).getArtwork().getCreatorName());
        			col1.getChildren().add(col1Label);
        			col2.getChildren().add(col2Label);
        			col3.getChildren().add(col3Label);
        		}
        	}
        }
		wonArtworksTable.getChildren().addAll(col1, col2, col3);
        
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
		col3.setId("finalColumn");
		Label col1Header = new Label("Artwork Title");
		Label col2Header = new Label("Price (£)");
		Label col3Header = new Label("Winning Bidder");
		col1.getChildren().add(col1Header);
		col2.getChildren().add(col2Header);
		col3.getChildren().add(col3Header);
		for (int i = 0; i < auctionManager.getAuctions().size(); i++) {
			if (auctionManager.getAuctions().get(i).isComplete()) {
				if (auctionManager.getAuctions().get(i).
						getSeller().equals(profile.getUsername())) {
					Label col1Label = new Label(auctionManager.getAuctions().
							get(i).getArtwork().getTitle());
        			Label col2Label = new Label(Double.toString(auctionManager.
        					getAuctions().get(i).getHighestBid().getAmount()));
        			Label col3Label = new Label(auctionManager.getAuctions().
        					get(i).getHighestBid().getBidder().getUsername());
        			col1.getChildren().add(col1Label);
        			col2.getChildren().add(col2Label);
        			col3.getChildren().add(col3Label);
				}
			}
        }
		artworksSoldTable.getChildren().addAll(col1, col2, col3);
		
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
		for (int i = 0; i < auctionManager.getAuctions().size(); i++) {
			ArrayList<Bid> tempArray = auctionManager.getAuctions().get(i).getAllBids();
			for (int j = 0; j < tempArray.size(); j++) {
				Bid temp = tempArray.get(j);
				if (temp.getBidder().getUsername().equals(profile.getUsername())) {
					Label col1Label = new Label(auctionManager.getAuctions().
							get(i).getArtwork().getTitle());
        			Label col2Label = new Label(Double.toString(temp.getAmount()));
        			Label col3Label = new Label(temp.getDate());
        			col1.getChildren().add(col1Label);
        			col2.getChildren().add(col2Label);
        			col3.getChildren().add(col3Label);
				}
			}
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
		for (int i = 0; i < auctionManager.getAuctions().size(); i++) {
			if (auctionManager.getAuctions().get(i).getSeller().
					equals(profile.getUsername())) {
				ArrayList<Bid> tempArray = auctionManager.getAuctions().get(i).getAllBids();
				for (int j = 0; j < tempArray.size(); j++) {
					Bid temp = tempArray.get(j);
					if (temp.getBidder().getUsername().equals(profile.getUsername())) {
						Label col1Label = new Label(auctionManager.
								getAuctions().get(i).getArtwork().getTitle());
	        			Label col2Label = new Label(Double.toString(temp.getAmount()));
	        			Label col3Label = new Label(temp.getBidder().getUsername());
	        			Label col4Label = new Label(temp.getDate());
	        			col1.getChildren().add(col1Label);
	        			col2.getChildren().add(col2Label);
	        			col3.getChildren().add(col3Label);
	        			col4.getChildren().add(col4Label);
					}
				}
			}
        }
		bidsOnOwnersArtworkTable.getChildren().addAll(col1, col2, col3, col4);
        
        return bidsOnOwnersArtworkTable;
	}
	
	/** This method makes the current Profiles page. This allows
	 * the current user to view their information and make
	 * changes to their information and also make custom drawings.
	 */
	public void viewCurrentProfile() {
		mainBorderPaneTopVBox.getChildren().remove(auctionNavigationBar);
		
		//creates a profile Navigation bar
		HBox profileNavigationBar = createProfileNavigationBar();
		mainBorderPaneTopVBox.getChildren().add(profileNavigationBar);
		
		ScrollPane profileScrollPane = new ScrollPane();
		profileScrollPane.setFitToHeight(true);
		profileScrollPane.setFitToWidth(true);
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
				0, LARGE_MARGIN, 0));
		
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
				profileLastName, profileTelephone, profileAddress, profilePostCode,
				wonArtworksLabel, wonArtworksTable, artworksSoldLabel,
				artworksSoldTable, allPlacedBidsLabel, allPlacedBidsTable,
				bidsOnOwnersArtworksLabel, bidsOnOwnersArtworksTable);
		
		profileScrollPane.setContent(currentProfilePageVBox);
		mainBorderPane.setCenter(profileScrollPane);
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
		
		Label customDrawingLabel = new Label("Make Custom Drawing");
		customDrawingLabel.setId("customDrawingLabel");
		
		profileNavigationBar.getChildren().addAll(settingslabel, customDrawingLabel);
		
		//opens settings page on click.
		settingslabel.setOnMouseClicked( e -> {
			makeSettingsWindow();
		});
		
		//opens the customDrawing window.
		customDrawingLabel.setOnMouseClicked( e -> {
			
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
	 * This method is called when the user wants to close the program
	 * by clicking the red x in the top right corner. This method will close the
	 * program but first it saves all the data, so changes are not lost.
	 */
	public void closeProgram() {
		
	}
}