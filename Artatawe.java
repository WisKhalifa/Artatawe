import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
	public final int PROFILE_NAVIGATION_BAR_HEIGHT = 40;
	public final int AUCTION_FLOWPANE_H_GAP = 4; //to fit 4 auction boxes on a line
	public final int AUCTION_FLOWPANE_LENGTH = 1000;
	
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
				for(int i = 0; i < profileManager.getAllElements().size(); i++) {
					if(usernameTextField.getText().equals(profileManager.getAllElements().get(i).getUsername())) {
						currentProfile = profileManager.getAllElements().get(i);
						makeDashboard();
					}
				}
				errorLabel.setText("Wrong Username!");
				errorLabel.setStyle("-fx-text-fill: red");
			}
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
		
		//creates the actions for the filter check boxes.
		paintingCheckBox.setOnAction( e -> {
			sculptureCheckBox.setSelected(false);
			sculptureFilter = 0;
			if(paintingCheckBox.isSelected()) {
				paintingFilter = 1;
				mainBorderPane.setCenter(null);
				displayAuctions();
			}else {
				paintingFilter = 0;
				mainBorderPane.setCenter(null);
				displayAuctions();
			}
		});
		sculptureCheckBox.setOnAction( e -> {
			paintingCheckBox.setSelected(false);
			paintingFilter = 0;
			if(sculptureCheckBox.isSelected()) {
				sculptureFilter = 1;
				mainBorderPane.setCenter(null);
				displayAuctions();
			}else {
				sculptureFilter = 0;
				mainBorderPane.setCenter(null);
				displayAuctions();
			}
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
		viewAuctions.setId("viewAuctionsButton");
		viewProfile.setId("viewProfileButton");
		navigationBar.getChildren().addAll(viewAuctions, viewProfile);
		
		//sets the actions for the buttons on the Navigation bar.
		viewAuctions.setOnAction( e -> {
			makeDashboard();
		});
		viewProfile.setOnAction( e -> {
			viewCurrentProfile();
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
		for(Auction auction : AuctionManager.getAllElements()) {
		
			BorderPane auctionItemBox = new BorderPane();
			
			//0 means get it. 1 means don't get it.
			if((auction.getArtwork() instanceof Painting) && (paintingFilter == 0)){
				auctionItemBox = createAuctionItem(auctionItemBox,auction);
			}else if((auction.getArtwork() instanceof Sculpture) && (sculptureFilter == 0)){
				auctionItemBox = createAuctionItem(auctionItemBox,auction);
			}
			
			auctionItemBox.setId("auctionItemBox");
			AuctionFP.getChildren().add(auctionItemBox);
			AuctionFP.setMargin(auctionItemBox,new Insets(20,20,20,20));
			
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
	}
	
	/**
	 * Creates the Auction page for the particular auction that is clicked.
	 * This shows more information of the artwork and auction and allows the user
	 * to bid on the Auction.
	 * @param auction The auction clicked on by the user.
	 */
	public void createAuctionPage(Auction auction) {
		mainBorderPaneTopVBox.getChildren().remove(auctionNavigationBar);
		VBox auctionPageVBox = new VBox();
		
		//adds the information about the artwork
		Label artworkName = new Label(auction.getArtwork().getTitle());
		Label artworkOwner = new Label("Creator: " + auction.getArtwork().getCreatorName());
		Label artworkCreationYear = new Label("Creation year: " + auction.getArtwork().getCreationYear());
		Label artworkPrice = new Label("Price: " + auction.getArtwork().getPrice());
		Label artworkBidTotal = new Label("Total bids: " + auction.getArtwork().getBidTotal());
		Label artworkDateAndTime = new Label("Time uploaded: " + auction.getArtwork().getDateAndTime());
		Label artworkDescription = new Label(auction.getArtwork().getDescription()); //this one is Optional, wil just be empty otherwise.
		
		//creates a button to view owners profile.
		Button viewProfile = new Button("View Profile");
		
		//creates the input to make a bid.
		HBox makeBid = new HBox();
		TextField enterBid = new TextField();
		Button sendBid = new Button("Make bid");
		Label bidErrorMessage = new Label("");
		makeBid.getChildren().addAll(enterBid, sendBid, bidErrorMessage);
		
		//if the Auction is complete then the user can't add a bid.
		if(auction.isComplete()) {
			makeBid.setDisable(true);
		}
		
		//table of all bids.
		Label bidTableLabel = new Label("All Bids");
		TableView<Bid> bidTable = makeBidTable(auction);
		
		//button to view the auction owners profile.
		viewProfile.setOnAction( e -> {
			for(int i = 0; i < profileManager.getAllElements().size(); i++){
				if(auction.getArtwork().getCreatorName().equals(profileManager.getAllElements().get(i).getUsername())){
					viewProfile(profileManager.getAllElements().get(i));
				}
			}
		});
		
		//creates a bid and sends it off to be added to the Auction.
		sendBid.setOnAction( e -> {
			int newBidTotal = Integer.parseInt(enterBid.getText());
			enterBid.setText("");
			bidErrorMessage.setStyle("-fx-text-fill: red;");
			if(currentProfile == auction.getHighestBid().getBidder()) {
				bidErrorMessage.setText("You already have the highest Bid!");
			}else if(auction.getArtwork().getPrice() > newBidTotal) {
				bidErrorMessage.setText("You must enter a bid higher than initial price.");
			}else if(auction.getHighestBid().getAmount() > newBidTotal){
				bidErrorMessage.setText("Must be higher than current highest bid.");
			}else {
				Bid newbid = new Bid(currentProfile,auction.getArtwork(),newBidTotal);
				auction.placeBid(newbid);
				//updates the table.
				auctionPageVBox.getChildren().remove(bidTable);
				bidTable = makeBidTable(auction);
				auctionPageVBox.getChildren().add(bidTable);
			}
		});
		
		auctionPageVBox.getChildren().addAll(artworkName, artworkOwner, artworkCreationYear, artworkPrice,
			artworkBidTotal, artworkDateAndTime, artworkDescription, viewProfile, makeBid, bidTableLabel, bidTable);
			
		mainBorderPane.setCenter(auctionPageVBox);
	}
	
	/**
	 * This creates a table containing all the bids that have been created
	 * for an auction. The table includes the user who made the bid, the amount
	 * and the time of the bid.
	 * @param auction The auction used to get the bids.
	 * @return A table.
	 */
	public TableView makeBidTable(Auction auction){
		TableView<Bid> bidTable = new TableView<>();
		bidTable.setEditable(false);
		TableColumn<Bid, String> usernameCol = new TableColumn<>("Username");
        TableColumn<Bid, String> amountCol = new TableColumn<>("Amount (£)");
        TableColumn<Bid, String> dateAndTimeCol = new TableColumn<>("Date and Time");
        usernameCol.setMinWidth(200);
        amountCol.setMinWidth(200);
        dateAndTimeCol.setMinWidth(200);
        //gets the data from the variables in that class
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("bidder"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateAndTimeCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        //add elements to table.
        ObservableList<Bid> allBids = FXCollections.observableArrayList();
        for(int i = 0; i < auction.getAllBids().size(); i++) {
        	allBids.add(auction.getAllBids().pop());
        }
        
        bidTable.setItems(allBids);
		bidTable.getColumns().addAll(usernameCol, amountCol, dateAndTimeCol);
	}
	
	/**
	 * This method creates the GUI for viewing a Profile. This profile
	 * is not the current Users profile but another Users profile.
	 * @param profile The profile that is being viewed.
	 */
	public void viewProfile(Profile profile) {
		mainBorderPaneTopVBox.getChildren().remove(auctionNavigationBar);
		VBox viewProfileVBox = new VBox();
		
		HBox profileHeader = new HBox();
		ImageView profileAvatar = createImage(profile.getImagePath());
		Label profileUsername = new Label(profile.getUsername());
		profileHeader.getChildren().addAll(profileAvatar, profileUsername);
		
		//list of won artwork's
		Label wonArtworksLabel = new Label("All Won Artworks");
		TableView<Artwork> wonArtworksTable = getWonArtworksTable(profile);
		
		//list of artwork's sold
		Label artworksSoldLabel = new Label("All Artworks Sold");
		TableView<Artwork> artworksSoldTable = getArtworksSold(profile);
		
		//list of all bids placed
		Label allPlacedBidsLabel = new Label("All Bids Placed");
		TableView<Bid> allBidsPlacedTable = getBidsPlaced(profile);
		
		//list of bids done on their artwork's.
		Label bidsOnOwnersArtworksLabel = new Label("All bids on " + profile.getUsername() + "'s Artworks");
		TableView<Bid> bidsOnOwnersArtworkTable = getBidsOnOwnersArtwork(profile);
		
		viewProfileVBox.getChildren().addAll(profileHeader);
		
		mainBorderPane.setCenter(viewProfileVBox);
	}
	
	/**
	 * This method creates a table containing all the artwork won by a particular
	 * profile.
	 * @param profile
	 * @return A table of won artwork's.
	 */
	public TableView getWonArtworksTable(Profile profile) {
		TableView<Artwork> wonArtworksTable = new TableView<>();
		wonArtworksTable.setEditable(false);
		TableColumn<Artwork, String> artworkTitleCol = new TableColumn<>("Artwork Title");
        TableColumn<Artwork, String> priceCol = new TableColumn<>("Price (£)");
        TableColumn<Artwork, String> previousOwnerCol = new TableColumn<>("Previous Owner");
        artworkTitleCol.setMinWidth(200);
        priceCol.setMinWidth(200);
        previousOwnerCol.setMinWidth(200);
        //gets the data from the variables in that class
        artworkTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        previousOwnerCol.setCellValueFactory(new PropertyValueFactory<>("creatorsName"));
        
        //add elements to table.
        ObservableList<Artwork> allWonArtworks = FXCollections.observableArrayList();
        for(int i = 0; i < auctionManager.getAllElements().size(); i++) {
        	if(auctionManager.getAllElements().get(i).isComplete()){
        		if(auctionManager.getAllElements().get(i).getHighestBid().getBidder() == profile){
        			allWonArtworks.add(auctionManager.getAllElements().get(i).getArtwork());
        		}
        	}
        }
        
        wonArtworksTable.setItems(allWonArtworks);
        wonArtworksTable.getColumns().addAll(artworkTitleCol, priceCol, previousOwnerCol);
	}
	
	/**
	 * This method creates a table of all the artwork's sold by a profile.
	 * @param profile
	 * @return A table of all sold artwork's.
	 */
	public TableView getArtworksSold(Profile profile) {
		TableView<Artwork> artworksSoldTable = new TableView<>();
		artworksSoldTable.setEditable(false);
		TableColumn<Artwork, String> artworkTitleCol = new TableColumn<>("Artwork Title");
        TableColumn<Artwork, String> priceSoldCol = new TableColumn<>("Price Sold (£)");
        TableColumn<Artwork, String> WinningBidderCol = new TableColumn<>("Winning Bidder");
        artworkTitleCol.setMinWidth(200);
        priceSoldCol.setMinWidth(200);
        WinningBidderCol.setMinWidth(200);
        //gets the data from the variables in that class
        artworkTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        priceSoldCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        WinningBidderCol.setCellValueFactory(new PropertyValueFactory<>("creatorsName"));
        
        //add elements to table.
        ObservableList<Artwork> allWonArtworks = FXCollections.observableArrayList();
        for(int i = 0; i < auctionManager.getAllElements().size(); i++) {
        	//artwork class should hold winning bidder, as variable.
        }
        
        artworksSoldTable.setItems(allWonArtworks);
        artworksSoldTable.getColumns().addAll(artworkTitleCol, priceSoldCol, WinningBidderCol);
	}
	
	/**
	 * A method that returns a table of all the bids a profile has 
	 * ever placed.
	 * @param profile
	 * @return A table of bids placed.
	 */
	public TableView getBidsPlaced(Profile profile) {
		TableView<Bid> bidsPlacedTable = new TableView<>();
		bidsPlacedTable.setEditable(false);
		TableColumn<Bid, String> artworkTitleCol = new TableColumn<>("Artwork Title"); 
        TableColumn<Bid, String> amountCol = new TableColumn<>("Amount (£)");
        TableColumn<Bid, String> dateOfBidCol = new TableColumn<>("Date of Bid");
        artworkTitleCol.setMinWidth(200);
        amountCol.setMinWidth(200);
        dateOfBidCol.setMinWidth(200);
        //gets the data from the variables in that class
        artworkTitleCol.setCellValueFactory(new PropertyValueFactory<>("")); //make variable in bid.??
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateOfBidCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        //add elements to table.
        ObservableList<Bid> allBidsPlaced = FXCollections.observableArrayList();
        for(int i = 0; i < auctionManager.getAllElements().size(); i++) {
        	
        	for(int j = 0; j < auctionManager.getAllElements().get(i).getAllBids().size(); j++) {
        		Bid tempBid = auctionManager.getAllElements().get(j).getAllBids().pop();
        	
	        	if(tempBid.getBidder().getUsername().equals(profile.getUsername())){
	        		allBidsPlaced.add(tempBid);
	        	}
        	}
        }
        
        bidsPlacedTable.setItems(allBidsPlaced);
        bidsPlacedTable.getColumns().addAll(artworkTitleCol, amountCol, dateOfBidCol);
	}
	
	/**
	 * A method that returns a table of all the bids that have been made of a
	 * Profiles auction's.
	 * @param profile
	 * @return A table of bids placed on the profiles Auction's.
	 */
	public TableView getBidsOnOwnersArtwork(Profile profile) {
		TableView<Bid> bidsOnOwnersArtworkTable = new TableView<>();
		bidsOnOwnersArtworkTable.setEditable(false);
		TableColumn<Bid, String> artworkTitleCol = new TableColumn<>("Artwork Title"); 
        TableColumn<Bid, String> amountCol = new TableColumn<>("Amount (£)");
        TableColumn<Bid, String> bidderCol = new TableColumn<>("Bidder");
        TableColumn<Bid, String> dateOfBidCol = new TableColumn<>("Date of Bid");
        artworkTitleCol.setMinWidth(150);
        amountCol.setMinWidth(150);
        bidderCol.setMinWidth(150);
        dateOfBidCol.setMinWidth(150);
        //gets the data from the variables in that class
        artworkTitleCol.setCellValueFactory(new PropertyValueFactory<>("")); //make artwork title in bid??
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        bidderCol.setCellValueFactory(new PropertyValueFactory<>("")); //make bidder name in bid
        dateOfBidCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        //add elements to table.
        ObservableList<Bid> bidsOnOwnersArtwork = FXCollections.observableArrayList();
        for(int i = 0; i < auctionManager.getAllElements().size(); i++) {
        	if(auctionManager.getAllElements().get(i).getSeller().equals(profile.getUsername())) {
        		for(int j = 0; j < auctionManager.getAllElements().get(i).getAllBids().size(); j++) {
        			bidsOnOwnersArtwork.add(auctionManager.getAllElements().get(i).getAllBids().get(j));
        		}
        	}
        }
        
        bidsOnOwnersArtworkTable.setItems(bidsOnOwnersArtwork);
        bidsOnOwnersArtworkTable.getColumns().addAll(artworkTitleCol, amountCol, bidderCol, dateOfBidCol);
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
		
		VBox currentProfilePageVBox = new VBox();
		
		HBox profileHeader = new HBox();
		ImageView profileAvatar = createImage(currentProfile.getImagePath());
		Label profileUsername = new Label(currentProfile.getUsername());
		profileHeader.getChildren().addAll(profileAvatar, profileUsername);
		
		//adds the information about the artwork
		Label profileFirstName = new Label("First Name: " + currentProfile.getFirstName());
		Label profileLastName = new Label("Last Name: " + currentProfile.getLastName());
		Label profileTelephone = new Label("Telephone: " + currentProfile.getTelephone());
		Label profileAddress = new Label("Address: " + currentProfile.getFirstAddress());
		Label profilePostCode = new Label("PostCode: " + currentProfile.getPostcode());
		
		//list of won artwork's
		Label wonArtworksLabel = new Label("All Won Artworks");
		TableView<Artwork> wonArtworksTable = getWonArtworksTable(currentProfile);
				
		//list of artwork's sold
		Label artworksSoldLabel = new Label("All Artworks Sold");
		TableView<Artwork> artworksSoldTable = getArtworksSold(currentProfile);
				
		//list of all bids placed
		Label allPlacedBidsLabel = new Label("All Bids Placed");
		TableView<Bid> allBidsPlacedTable = getBidsPlaced(currentProfile);
				
		//list of bids done on their artwork's.
		Label bidsOnOwnersArtworksLabel = new Label("All bids on " + currentProfile.getUsername() + "'s Artworks");
		TableView<Bid> bidsOnOwnersArtworkTable = getBidsOnOwnersArtwork(currentProfile);
		
		currentProfilePageVBox.getChildren().addAll(profileHeader,profileFirstName,profileLastName,profileTelephone,profileAddress,profilePostCode,
				wonArtworksLabel,wonArtworksTable,artworksSoldLabel,artworksSoldTable,allPlacedBidsLabel,allBidsPlacedTable,bidsOnOwnersArtworksLabel,
				bidsOnOwnersArtworkTable);
		
		mainBorderPane.setCenter(currentProfilePageVBox);
	}
	
	/**
	 * Creates a profile Naviagation Bar. This is a bar that will appear underneath the main
	 * navigation bar once the user views their own profile.
	 * @return A profile Navigation bar in a HBox container.
	 */
	public HBox createProfileNavigationBar() {
		profileNavigationBar.setId("profileNavigationBar");
		profileNavigationBar.setMaxHeight(PROFILE_NAVIGATION_BAR_HEIGHT);
		profileNavigationBar.setMinHeight(PROFILE_NAVIGATION_BAR_HEIGHT);
		
		Label filterlabel = new Label("Settings");
		
		filterlabel.setId("settingslabel");
		
		profileNavigationBar.getChildren().add(filterlabel);
		
		//opens settings page on click.
		filterlabel.setOnMouseClicked( e -> {
			makeSettingsWindow();
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
        window.setTitle("Make Changes");
        window.getIcons().add(new Image("applicationIcon.png"));
        
        VBox settingsVBox = new VBox();
        
        //displays the information that can be changed.
        Label changeFirstName = new Label("Edit FirstName");
        HBox changeFirstNameHBox = new HBox();
        TextField firstNameField = new TextField();
        firstNameField.setText(currentProfile.getFirstName());
        Button makeFNChanges = new Button("Make Changes");
        changeFirstNameHBox.getChildren().addAll(firstNameField,makeFNChanges);
        
        Label changeLastName = new Label("Edit LastName");
        HBox changeLastNameHBox = new HBox();
        TextField lastNameField = new TextField();
        lastNameField.setText(currentProfile.getLastName());
        Button makeLNChanges = new Button("Make Changes");
        changeLastNameHBox.getChildren().addAll(lastNameField,makeLNChanges);
        
        Label changeTelephone = new Label("Edit Telephone Number");
        HBox changeTelephoneNameHBox = new HBox();
        TextField telephoneField = new TextField();
        telephoneField.setText(currentProfile.getTelephone());
        Button makeTChanges = new Button("Make Changes");
        changeTelephoneNameHBox.getChildren().addAll(telephoneField,makeTChanges);
        
        Label changeAddress = new Label("Edit Address");
        HBox changeAddressNameHBox = new HBox();
        TextField addressField = new TextField();
        addressField.setText(currentProfile.getFirstAddress());
        Button makeAChanges = new Button("Make Changes");
        changeAddressNameHBox.getChildren().addAll(addressField,makeAChanges);
        
        Label changePostCode = new Label("Edit PostCode");
        HBox changePostCodeNameHBox = new HBox();
        TextField postCodeField = new TextField();
        postCodeField.setText(currentProfile.getPostcode());
        Button makePCChanges = new Button("Make Changes");
        changePostCodeNameHBox.getChildren().addAll(postCodeField,makePCChanges);
        
        settingsVBox.getChildren().addAll(changeFirstName,changeFirstNameHBox,changeLastName,changeLastNameHBox,changeTelephone,changeTelephoneNameHBox,
        		changeAddress,changeAddressNameHBox,changePostCode,changePostCodeNameHBox);
        
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

		Scene scene = new Scene(settingsVBox, 600, 900);
        window.setScene(scene);
        window.showAndWait();
	}
}