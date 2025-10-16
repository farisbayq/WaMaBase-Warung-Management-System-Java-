package scenes;

import controller.CustomerController;
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
import model.Customer;

public class CustomerView {

    private static Scene scene;
    private MenuBar menuBar;
    private Menu menuOptions;
    private MenuItem home, inventory, customer, report, logout;
    private TableView<Customer> customerTable;
    private TableColumn<Customer, String> idColumn, nameColumn, accountNumberColumn, phoneNumberColumn;
    private ComboBox<String> searchByCB;
    private TextField searchField;
    private Button searchButton, refreshButton, showTransactionButton;
    private BorderPane root;
  
    public static Customer selectedCustomer;
    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    public CustomerView() {
        initialize();
        styling();
        populateTable();
        setTableColumns();
        setTableListener();
        action();
    }

    private void initialize() {
        // Create the MenuBar
        menuBar = new MenuBar();

        // Create Menus and MenuItems
        menuOptions = new Menu("Menu");
        home = new MenuItem("Home");
        inventory = new MenuItem("Inventory");
        customer = new MenuItem("Customer");
        customer.setDisable(true);
        report = new MenuItem("Report");
        logout = new MenuItem("Log Out");

        // Add MenuItems to the Menu
        menuOptions.getItems().addAll(home, inventory, customer, report, new SeparatorMenuItem(), logout);
        menuBar.getMenus().add(menuOptions);

        // Create Customer Table
        customerTable = new TableView<Customer>();
        customerTable.setPlaceholder(new Label("No content in table"));

        idColumn = new TableColumn<Customer, String>("ID");
        nameColumn = new TableColumn<Customer, String>("Name");
        accountNumberColumn = new TableColumn<Customer, String>("Account Number");
        phoneNumberColumn = new TableColumn<Customer, String>("Phone Number");

        customerTable.getColumns().addAll(idColumn, nameColumn, accountNumberColumn, phoneNumberColumn);
        customerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Create Search Field and Button
        searchField = new TextField();
        searchField.setPromptText("Search");
        searchButton = new Button("Search");
        refreshButton = new Button("Refresh");
        searchByCB = new ComboBox<String>();
        searchByCB.getItems().addAll("Customer ID", "Customer Name", "Account Number", "Phone Number");
        searchByCB.setValue("Customer ID");
        showTransactionButton = new Button("Transactions");

        // Layout for Search
        HBox searchLayout = new HBox(10, new Label("Customer ID:"), searchField, searchButton, searchByCB, refreshButton, showTransactionButton);
        searchLayout.setAlignment(Pos.CENTER_LEFT);
        searchLayout.setPadding(new Insets(10));
        
        Label cust = new Label("Customer");
        cust.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Main Layout
        VBox mainLayout = new VBox(10, cust, searchLayout, customerTable);
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
    
    private void populateTable() {
    	customerList = CustomerController.getAll();
    	customerTable.setItems(FXCollections.observableArrayList(customerList));
    }
    
    private void setTableColumns() {
    	idColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));
    	nameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
    	accountNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("accountNumber")); 
    	phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber")); 
    }
    private void setTableListener() {
    	customerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, selectedCustomer) -> {
    		if(selectedCustomer != null) {
    			CustomerView.selectedCustomer = selectedCustomer;
    		}
    	});
    }

    private void action() {
    	home.setOnAction(e->{
    		Main.showHomePage();
    	});
    	inventory.setOnAction(e->{
    		Main.showInventoryPage();
    	});
    	report.setOnAction(e->{
    		Main.showReportPage();
    	});
    	logout.setOnAction(e->{
    		Main.showLoginPage();
    	});
    	showTransactionButton.setOnAction(e->{
    		if(selectedCustomer == null) {
    			Main.showAlert(AlertType.ERROR, "Error", "Validation Error", "Please select a customer!");
    			return;
    		}
    		Main.showTransactionPage();
    	});
    	searchButton.setOnAction(e->{
    		String search = searchField.getText();
    		String searchBy = searchByCB.getValue();
    		
    		if(searchBy.equalsIgnoreCase("Customer ID")) {
    			customerList = CustomerController.searchID(search);
    		} else if(searchBy.equalsIgnoreCase("Customer Name")) {
    			customerList = CustomerController.searchName(search);
    		} else if(searchBy.equalsIgnoreCase("Account Number")) {
    			customerList = CustomerController.searchAccountNumber(search);
    		} else if(searchBy.equalsIgnoreCase("Phone Number")) {
    			customerList = CustomerController.searchPhoneNumber(search);
    		}
    		customerTable.setItems(FXCollections.observableArrayList(customerList));
    	});
    	refreshButton.setOnAction(e ->{
    		populateTable();
    	});
    }

    public Scene getScene() {
        return scene;
    }
    
}
