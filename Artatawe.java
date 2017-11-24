import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
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
import javafx.stage.Stage;

public class Artatawe extends Application {
	
	//Constant values.
	public final int SCENE_WIDTH = 1000;
	public final int SCENE_HEIGHT = 700;
	public final int LOGIN_BOX_HEIGHT = 100;
	public final int LOGIN_BOX_WIDTH = 300;
	public final int LOGIN_BUTTON_WIDTH = 300;
	public final int AUCTION_BOX_ITEM_PIC_CONTAINER_HEIGHT = 160;
	public final int AUCTION_BOX_ITEM_PIC_CONTAINER_WIDTH = 200;
	public final int AUCTION_BOX_ITEM_WIDTH = 200;
	public final int AUCTION_BOX_ITEM_HEIGHT = 200;
	public final int AUCTION_NAVIGATION_BAR_HEIGHT = 40;
	public final int AUCTION_FLOWPANE_H_GAP = 4; //to fit 4 auction boxes on a line
	public final int AUCTION_FLOWPANE_LENGTH = 1000;
	
	private Scene artataweScene; //The scene used for the program.
	private ProfileManager profileManager;
	private AuctionManager auctionManager;
	//private Profile mainProfile; //the profile that is being used on the program.
	private VBox mainBorderPaneTopVBox;
	private HBox auctionNavigationBar;
	private BorderPane mainBorderPane;
	private FlowPane AuctionFP;
	private int paintingFilter;
	private int sculptureFilter;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		//creates the profile and auction managers.
		profileManager = new ProfileManager();
		auctionManager = new AuctionManager();
		
		//stuff to do with the stage
		primaryStage.setTitle("Artatawe");
		primaryStage.getIcons().add(new Image("applicationIcon.png"));
		primaryStage.setResizable(false);
		
		artataweScene = makeLoginPage();
		artataweScene.getStylesheets().add("artataweStyleSheet.css");
		
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
		loginItems.setMaxHeight(LOGIN_BOX_HEIGHT);
		loginItems.setMinHeight(LOGIN_BOX_HEIGHT);
		loginItems.setMaxWidth(LOGIN_BOX_WIDTH);
		loginItems.setMinWidth(LOGIN_BOX_WIDTH);
		loginItems.setId("loginBox");
		
		//Create background image	
		ImageView loginImage = createImage("swanseaBay.png");
		loginImage.setEffect(new GaussianBlur());
		loginStackPane.getChildren().add(loginImage);
		
		//create title Label
		Label titleLabel = new Label("Artatawe");
		titleLabel.setId("titleLabel");
		
		//Edit textField
		TextField usernameTextField = new TextField();
		usernameTextField.setId("username-TextField");
		usernameTextField.setPromptText("Username");
		loginItems.setMargin(usernameTextField,new Insets(5,5,5,5));
		
		//creates the login box.
		VBox borderPaneVBox = createLoginButton(usernameTextField);

		//adds items to borderPane
		loginItems.setCenter(usernameTextField);
		loginItems.setBottom(borderPaneVBox);
		
		loginVBox.getChildren().addAll(titleLabel, loginItems); //adds borderPane to VBox
		loginStackPane.getChildren().add(loginVBox); //add items to stackPane.
		
		Scene scene = new Scene(loginStackPane, SCENE_WIDTH, SCENE_HEIGHT);
		//removes focus from the text Field, placed after scene implementation.
		loginVBox.requestFocus();
		return scene;
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
		//Edit errorLabel
		Label errorLabel = new Label("");
		errorLabel.setId("errorLabel");
				
		//Login Button
		Button loginButton = new Button("Connect");
		loginButton.setMinWidth(LOGIN_BUTTON_WIDTH);
		loginButton.setId("loginButton");
		
		//When you click the Login Button
		loginButton.setOnAction( e -> {
			if(usernameTextField.getText().equals("")) { 
				errorLabel.setText("Wrong Username!");
				errorLabel.setStyle("-fx-text-fill: red");
			}else{
				makeDashboard();
				//for(int i = 0; i < profileManager.getAllElements().size(); i++) {
				//	if(usernameTextField.getText().equals(profileManager.getAllElements().get(i).getUsername())) {
				//		mainProfile = profileManager.getAllElements().get(i);
				//		makeDashboard();
				//	}
				}
				errorLabel.setText("Wrong Username!");
				errorLabel.setStyle("-fx-text-fill: red");
		});
		//create VBox to go in borderPane bottom
		borderPaneVBox.getChildren().addAll(errorLabel, loginButton);
		borderPaneVBox.setMargin(errorLabel,new Insets(5,5,5,6));
		return borderPaneVBox;
	}
	
	/**
	 * Creates the dash board page, This GUI is loaded once the user logs in.
	 * The dash board page is the browse Auction page.
	 */
	public void makeDashboard() {	
		mainBorderPane = new BorderPane();
		mainBorderPane.setId("mainBorderPane");
		
		//Creation of containers in the top BorderPane
		mainBorderPaneTopVBox = new VBox();
		StackPane bannerSP = new StackPane();
		
		//Creation of containers in the center BorderPane
		ScrollPane borderPaneSP = new ScrollPane();
		borderPaneSP.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		AuctionFP = new FlowPane(Orientation.HORIZONTAL);
		AuctionFP.setHgap(AUCTION_FLOWPANE_H_GAP);
		AuctionFP.setPrefWrapLength(AUCTION_FLOWPANE_LENGTH);
		
		ImageView bannerImageView = createImage("bannerImage.png");
		Label bannerTitle = new Label("Artatawe");
		bannerTitle.setId("BannerTitle");
		bannerSP.setMargin(bannerTitle,new Insets(0,800,0,0));
		bannerSP.getChildren().addAll(bannerImageView, bannerTitle);
		
		//calls a method that creates the navigation bar.
		HBox navigationBar = createNavigationBar();
		HBox auctionNavigationBar = createAuctionNavigationBar();
		
		mainBorderPaneTopVBox.getChildren().addAll(bannerSP, navigationBar, auctionNavigationBar);
		
		//creates and displays all the Auction's into the flowPane.
		//displayAuctions(AuctionFP);
		//HERE
		BorderPane auctionItemBox = new BorderPane();
		auctionItemBox.setId("auctionItemBox");
		
		BorderPane auctionItemBoxPicContainer = new BorderPane();
		ImageView auctionBoxImageView = createImage("test.jpg");
		auctionBoxImageView.fitWidthProperty().bind(auctionItemBoxPicContainer.widthProperty());
		auctionBoxImageView.fitHeightProperty().bind(auctionItemBoxPicContainer.heightProperty());
		auctionItemBoxPicContainer.setCenter(auctionBoxImageView);
		auctionItemBoxPicContainer.setMaxHeight(AUCTION_BOX_ITEM_PIC_CONTAINER_HEIGHT);
		auctionItemBoxPicContainer.setMaxWidth(AUCTION_BOX_ITEM_PIC_CONTAINER_WIDTH);
		auctionItemBoxPicContainer.setMinHeight(AUCTION_BOX_ITEM_PIC_CONTAINER_HEIGHT);
		auctionItemBoxPicContainer.setMinWidth(AUCTION_BOX_ITEM_PIC_CONTAINER_WIDTH);
		
		VBox auctionBoxVBox = new VBox();
		Label artworkName = new Label("ARTWORK NAME");
		Label artworkOwner = new Label("USERNAME");
		artworkName.setId("artworkName");
		artworkOwner.setId("artworkOwner");
		auctionBoxVBox.getChildren().addAll(artworkName, artworkOwner);
		
		auctionItemBox.setCenter(auctionItemBoxPicContainer);
		auctionItemBox.setBottom(auctionBoxVBox);
		auctionItemBox.setMaxHeight(AUCTION_BOX_ITEM_HEIGHT);
		auctionItemBox.setMaxWidth(AUCTION_BOX_ITEM_WIDTH);
		auctionItemBox.setMinHeight(AUCTION_BOX_ITEM_HEIGHT);
		auctionItemBox.setMinWidth(AUCTION_BOX_ITEM_WIDTH);
		
		AuctionFP.getChildren().addAll(auctionItemBox);
		AuctionFP.setMargin(auctionItemBox,new Insets(20,20,20,20));
		
		auctionItemBox.setOnMouseClicked( e -> {
			System.out.println("clicked");
		});
		//HERE
		
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
		auctionNavigationBar.setId("auctionNavigationBar");
		auctionNavigationBar.setMaxHeight(AUCTION_NAVIGATION_BAR_HEIGHT);
		auctionNavigationBar.setMinHeight(AUCTION_NAVIGATION_BAR_HEIGHT);
		
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
		/*
		//creates the actions for the filter check boxes.
		paintingCheckBox.setOnAction( e -> {
			sculptureCheckBox.setSelected(false);
			sculptureFilter = 0;
			if(paintingCheckBox.isSelected()) {
				paintingFilter = 1;
				mainBorderPane.setCenter(null);
				displayAuctions(paintingFilter,sculptureFilter);
			}else {
				paintingFilter = 0;
				mainBorderPane.setCenter(null);
				displayAuctions(paintingFilter,sculptureFilter);
			}
		});
		sculptureCheckBox.setOnAction( e -> {
			paintingCheckBox.setSelected(false);
			paintingFilter = 0;
			if(sculptureCheckBox.isSelected()) {
				sculptureFilter = 1;
				mainBorderPane.setCenter(null);
				displayAuctions(paintingFilter,sculptureFilter);
			}else {
				sculptureFilter = 0;
				mainBorderPane.setCenter(null);
				displayAuctions(paintingFilter,sculptureFilter);
			}
		});*/
		
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
		viewAuctions.setId("viewAuctionsButton");
		viewProfile.setId("viewProfileButton");
		navigationBar.getChildren().addAll(viewAuctions, viewProfile);
		
		//sets the actions for the buttons on the Navigation bar.
		viewAuctions.setOnAction( e -> {
			makeDashboard();
		});
		viewProfile.setOnAction( e -> {
			
		});
		
		return navigationBar;
	}
	
	/**
	 * This checks the filters and then displays the correct auction's
	 * based off the filters
	 * @param paintingFilter Used to see if the painting filter needs to be applied.
	 * @param sculptureFilter Used to see if the sculpture filter needs to be applied.
	 */
	/*
	public void displayAuctions(int paintingFilter, int sculptureFilter) {
		for(Auction auction : AuctionManager.getAllElements()) {
		
			BorderPane auctionItemBox = new BorderPane();
			
			//0 means get it. 1 means dont get it.
			if((object instanceof Painting) && (paintingFilter == 0)){
				auctionItemBox = createAuctionItem(AuctionFP);
			}else if((object instanceof Sculpture) && (sculptureFilter == 0)){
				auctionItemBox = createAuctionItem(AuctionFP);
			}
			
			auctionItemBox.setId("auctionItemBox");
			AuctionFP.getChildren().add(auctionItemBox);
			AuctionFP.setMargin(auctionItemBox,new Insets(20,20,20,20));
			
			//opens up the Auction page 
			auctionItemBox.setOnMouseClicked( e -> {
				createAuctionPage(auction);
			});
		}
	}*/
	
	/**
	 * This finds all the auction's and creates a GUI for them and then displays them
	 * in the auction page or dash board.
	 *//*
	public void createAuctionItem() {
		BorderPane auctionItemBoxPicContainer = new BorderPane();
		ImageView auctionBoxImageView = createImage(auction.getArtwork().getMainPhoto());
		auctionBoxImageView.fitWidthProperty().bind(auctionItemBoxPicContainer.widthProperty());
		auctionBoxImageView.fitHeightProperty().bind(auctionItemBoxPicContainer.heightProperty());
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
	}*/
	
	/**
	 * Creates the Auction page for the particular auction that is clicked.
	 * This shows more information of the artwork and auction and allows the user
	 * to bid on the Auction.
	 * @param auction The auction clicked on by the user.
	 */
	/*
	public void createAuctionPage(Auction auction) {
		mainBorderPaneTopVBox.remove(auctionNavigationBar);
		VBox auctionPageVBox = new VBox();
		
		//adds the information about the artwork
		Label artworkName = new Label(auction.getArtwork().getTitle());
		Label artworkOwner = new Label("Creator: " + auction.getArtwork().getCreatorName());
		Label artworkCreationYear = new Label("Creation year: " + auction.getArtwork().getCreationYear());
		Label artworkPrice = new Label("Price: " + auction.getArtwork().getPrice());
		Label artworkBidTotal = new Label("Total bids: " + auction.getArtwork().getBidTotal());
		Label artworkDateAndTime = new Label("Time uploaded: " + auction.getArtwork().getDateAndTime());
		Label artworkDescription = new Label(auction.getArtwork().getDescription());
		
		//creates a button to view oweners profile.
		Button viewProfile = new Button("View Profile");
		
		//creates the input to make a bid.
		HBox makeBid = new HBox();
		TextField enterBid = new TextField();
		Button sendBid = new Button("Make bid");
		makeBid.getChildren().addAll(enterBid, sendBid);
		
		viewProfile.setOnAction( e -> {
			for(int i = 0; i < profileManager.getAllElements().size(); i++){
				if(auction.getArtwork().getCreatorName().equals(profileManager.getAllElements().get(i).getUsername())){
					viewProfile(profileManager.getAllElements().get(i));
				}
			}
		});
		
		sendBid.setOnAction( e -> {
			
		});
		
		auctionPageVBox.getChildren().addAll(artworkName, artworkOwner, artworkCreationYear, artworkPrice,
			artworkBidTotal, artworkDateAndTime, artworkDescription, viewProfile, makeBid);
			
		mainBorderPane.setCenter(auctionPageVBox);
	}*/
}