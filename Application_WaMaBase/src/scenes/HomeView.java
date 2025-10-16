package scenes;

import controller.HomeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import main.Main;
import model.Cart;
import model.MsItem;

public class HomeView {

    private static Scene scene;
    private MenuBar menuBar;
    private Menu menuOptions;
    private MenuItem home, inventory, customer, report, logout;
    private TableView<MsItem> leftTable;
	private TableView<Cart> rightTable;
    private TableColumn<MsItem, String> itemColumn;
	private TableColumn<Cart, String> cartItemColumn;
	private TableColumn<Cart, Integer> cartQuantityColumn;
	private TableColumn<Cart, Integer> cartPriceColumn;
	private TableColumn<MsItem, Integer> priceColumn;
    private TextField searchField;
    private Button searchButton, addButton, checkoutButton;
    private Spinner<Integer> quantitySpinner;
    private Label totalLabel, priceLabel;
    private HBox searchLayout, quantityLayout, checkoutLayout;
    private VBox leftTableLayout, rightTableLayout;
    private HBox tableLayout;
    private BorderPane root;
    
    private ObservableList<MsItem> itemList = FXCollections.observableArrayList();
    public static ObservableList<Cart> cartList = FXCollections.observableArrayList();
    private MsItem selectedItem;

    public HomeView() {
        initialize();
        populateTables();
        setTableColumns();
        setTableListener();
        action();
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
	private void initialize() {
        // Create the MenuBar
        menuBar = new MenuBar();

        // Create Menus and MenuItems
        menuOptions = new Menu("Menu");
        home = new MenuItem("Home");
        home.setDisable(true);
        inventory = new MenuItem("Inventory");
        customer = new MenuItem("Customer");
        report = new MenuItem("Report");
        logout = new MenuItem("Log Out");

        // Add MenuItems to the Menu
        menuOptions.getItems().addAll(home, inventory, customer, report, new SeparatorMenuItem(), logout);
        menuBar.getMenus().add(menuOptions);

        // Create Left Table (Items and Prices)
        leftTable = new TableView<MsItem>();
        leftTable.setPlaceholder(new Label("No content in table"));
        itemColumn = new TableColumn<MsItem, String>("Item");
        priceColumn = new TableColumn<MsItem, Integer>("Price");
        leftTable.getColumns().addAll(itemColumn, priceColumn);
        leftTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        leftTable.setPrefWidth(350);
        leftTable.setPrefHeight(400);

        // Create Right Table (Cart: Item, Quantity, Price)
        rightTable = new TableView<Cart>();
        rightTable.setPlaceholder(new Label("No content in table"));
        cartItemColumn = new TableColumn<Cart, String>("Item");
        cartQuantityColumn = new TableColumn<Cart, Integer>("Quantity");
        cartPriceColumn = new TableColumn<Cart, Integer>("Price");
        rightTable.getColumns().addAll(cartItemColumn, cartQuantityColumn, cartPriceColumn);
        rightTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        cartItemColumn.setPrefWidth(200);
        rightTable.setPrefWidth(350);
        rightTable.setPrefHeight(400);

        // Add Cart Label
        Label cartLabel = new Label("Cart");
        cartLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Add WaMaBase Label
        Label titleLabel = new Label("WaMaBase");
        titleLabel.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");
        titleLabel.setAlignment(Pos.CENTER);

        // Search and Quantity Inputs
        searchField = new TextField();
        searchField.setPromptText("Search");
        searchButton = new Button("Search");
        quantitySpinner = new Spinner<>(1, 100, 1);
        addButton = new Button("Add");

        // Layout for Search
        searchLayout = new HBox(10, new Label("Search"), searchField, searchButton);
        searchLayout.setAlignment(Pos.CENTER);
        searchLayout.setPadding(new Insets(0, 20, 0, 20));

        // Layout for Quantity
        quantityLayout = new HBox(10, new Label("Quantity"), quantitySpinner, addButton);
        quantityLayout.setAlignment(Pos.CENTER_LEFT);

        // Cart Total and Checkout
        totalLabel = new Label("Total");
        priceLabel = new Label("[Price]");
        checkoutButton = new Button("Checkout");

        checkoutLayout = new HBox(10, totalLabel, priceLabel, checkoutButton);
        checkoutLayout.setAlignment(Pos.CENTER_RIGHT);

        // Layout for Tables
        leftTableLayout = new VBox(10, leftTable, quantityLayout);
        rightTableLayout = new VBox(10, cartLabel, rightTable, checkoutLayout);
        rightTableLayout.setAlignment(Pos.CENTER);

        // Main layout for the tables
        tableLayout = new HBox(30, leftTableLayout, rightTableLayout);
        tableLayout.setPadding(new Insets(20, 20, 20, 20));
        tableLayout.setAlignment(Pos.CENTER);

        VBox mainLayout = new VBox(20, titleLabel, searchLayout, tableLayout);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.CENTER);

        // Main container
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(mainLayout);
        root.setTop(new VBox(menuBar));

        // Set the scene
        scene = new Scene(root, 800, 600);
    }
    
    private void populateTables() {
    	itemList = HomeController.populateTable();
    	leftTable.setItems(FXCollections.observableArrayList(itemList));	
    	
    	rightTable.setItems(FXCollections.observableArrayList(cartList));
    }
    
    private void setTableColumns() {
    	itemColumn.setCellValueFactory(new PropertyValueFactory<MsItem, String>("ItemName"));
    	priceColumn.setCellValueFactory(new PropertyValueFactory<MsItem, Integer>("ItemPrice")); 
    	
    	cartItemColumn.setCellValueFactory(new PropertyValueFactory<Cart, String>("name"));
    	cartQuantityColumn.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("quantity"));
    	cartPriceColumn.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("total"));
    }
    
    private void setTableListener() {
		leftTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, selectedDonut) -> {
			if (selectedDonut != null) {
				this.selectedItem = selectedDonut;
			}
		});
	}
    
    private void updateCartTotal() {
    	int total = 0;
    	for(Cart c : cartList) {
    		total += c.getTotal();
    	}
    	priceLabel.setText(String.valueOf(total));
    }

    private void action() {
        inventory.setOnAction(e -> {
            Main.showInventoryPage();
        });
        customer.setOnAction(e -> {
            Main.showCustomerPage();
        });
        report.setOnAction(e -> {
            Main.showReportPage();
        });
        logout.setOnAction(e -> {
            Main.showLoginPage();
        });
        
        addButton.setOnAction(e -> {
            if (selectedItem == null) {
                Main.showAlert(AlertType.ERROR, "Error", "Selection Error", "Please select an item first.");
                return;
            }

            // Get the item details from the selected MsItem
            String itemID = selectedItem.getItemID(); // Use getItemID() from MsItem
            String name = selectedItem.getItemName();
            int quantity = quantitySpinner.getValue();
            int price = quantity * selectedItem.getItemPrice();

            // Add the item to the cart
            cartList.add(new Cart(itemID, name, quantity, price));

            // Reset spinner, update total, and refresh tables
            quantitySpinner.getValueFactory().setValue(1);
            updateCartTotal();
            populateTables();
        });

        
        searchButton.setOnAction(e -> {
        	String search = searchField.getText();
        	if(search.isEmpty()) {
        		Main.showAlert(AlertType.ERROR, "Error", "Validation Error", "Type in something before searching!");
        		return;
        	}
        	itemList = HomeController.search(search);
        	leftTable.setItems(FXCollections.observableArrayList(itemList));
        	searchField.clear();
        });
        
        checkoutButton.setOnAction(e->{
        	if(cartList.isEmpty()) {
        		Main.showAlert(AlertType.ERROR, "Error", "Validation Error", "Cart is empty!");
        		return;
        	}
        	Main.showCheckoutPage();
        });
    }

    public Scene getScene() {
        return scene;
    }
}

