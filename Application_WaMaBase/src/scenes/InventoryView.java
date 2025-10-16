package scenes;

import controller.InventoryControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import main.Main;
import model.Inventory;

public class InventoryView {

    private static Scene scene;
    private MenuBar menuBar;
    private Menu menuOptions;
    private MenuItem home, inventory, customer, report, logout;
    private Label inv;
    private TableView<Inventory> inventoryTable;
    private TableColumn<Inventory, String> idColumn, nameColumn;
	private TableColumn<Inventory, Integer> priceColumn, stockColumn;
	private ComboBox<String> searchByCB;
    private TextField searchField;
    private Button searchButton, refreshButton;
    private BorderPane root;
    
    private ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();

    public InventoryView() {
        initialize();
        styling();
        populateTables();
        setTableColumns();
        action();
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
	private void initialize() {
        // Create the MenuBar
        menuBar = new MenuBar();

        // Create Menus and MenuItems
        menuOptions = new Menu("Menu");
        home = new MenuItem("Home");
        inventory = new MenuItem("Inventory");
        inventory.setDisable(true);
        customer = new MenuItem("Customer");
        report = new MenuItem("Report");
        logout = new MenuItem("Log Out");

        // Add MenuItems to the Menu
        menuOptions.getItems().addAll(home, inventory, customer, report, new SeparatorMenuItem(), logout);
        menuBar.getMenus().add(menuOptions);

        // Create Inventory Table
        inventoryTable = new TableView<>();
        inventoryTable.setPlaceholder(new Label("No content in table"));

        idColumn = new TableColumn<Inventory, String>("ID");
        nameColumn = new TableColumn<>("Name");
        priceColumn = new TableColumn<>("Price");
        stockColumn = new TableColumn<>("Stock");

        inventoryTable.getColumns().addAll(idColumn, nameColumn, priceColumn, stockColumn);
        inventoryTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Create Search Field and Button
        searchField = new TextField();
        searchField.setPromptText("Insert itemID here");
        searchButton = new Button("Search");
        refreshButton = new Button("Refresh");
        searchByCB = new ComboBox<String>();
        searchByCB.getItems().addAll("ItemID", "ItemName");
        searchByCB.setValue("ItemID");

        // Layout for Search
        HBox searchLayout = new HBox(10, new Label("Item ID:"), searchField, searchButton, searchByCB,  refreshButton);
        searchLayout.setAlignment(Pos.CENTER_LEFT);
        searchLayout.setPadding(new Insets(10));

        inv = new Label("Inventory");
        inv.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        
        // Main Layout
        VBox mainLayout = new VBox(10, inv, searchLayout, inventoryTable);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.CENTER);

        // Main container
        root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(mainLayout);

        // Set the scene
        scene = new Scene(root, 800, 600);
    }

    private void styling() {
        // Add styling if needed (CSS or inline styles)
    }
    
    private void populateTables() {
    	inventoryList = InventoryControl.getAll();
    	inventoryTable.setItems(FXCollections.observableArrayList(inventoryList));
    }
    
    private void setTableColumns() {
    	idColumn.setCellValueFactory(new PropertyValueFactory<Inventory, String>("id"));
    	nameColumn.setCellValueFactory(new PropertyValueFactory<Inventory, String>("name"));
    	priceColumn.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("price")); 
    	stockColumn.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("stock")); 
    }

    private void action() {
    	home.setOnAction(e->{
    		Main.showHomePage();
    	});
    	customer.setOnAction(e->{
    		Main.showCustomerPage();
    	});
    	report.setOnAction(e->{
    		Main.showReportPage();
    	});
    	logout.setOnAction(e->{
    		Main.showLoginPage();
    	});
    	searchButton.setOnAction(e -> {
    		String search = searchField.getText();
    		String searchBy = searchByCB.getValue();
    		if(searchBy.equalsIgnoreCase("ItemID")) {
    			inventoryList = InventoryControl.searchID(search);
    			
    		} else if (searchBy.equalsIgnoreCase("ItemName")) {
    			inventoryList = InventoryControl.searchName(search);
    			
    		}
    		inventoryTable.setItems(FXCollections.observableArrayList(inventoryList));
    	});
    	refreshButton.setOnAction(e -> {
    		populateTables();
    	});
    }

    public Scene getScene() {
        return scene;
    }
}
