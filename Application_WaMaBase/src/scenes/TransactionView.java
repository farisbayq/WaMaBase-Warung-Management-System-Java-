package scenes;

import controller.TransactionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import main.Main;
import model.Transaction;
import model.TransactionDetail;

public class TransactionView {

    private static Scene scene;
    private MenuBar menuBar;
    private Menu menuOptions;
    private MenuItem back, logout;
    private TableView<Transaction> transactionTable;
    private TableColumn<Transaction, String> transactionIdColumn, staffIdColumn, payIdColumn, customerIdColumn, dateColumn;
    private TableView<TransactionDetail> transactionDetailTable;
    private TableColumn<TransactionDetail, String> itemNameColumn;
    private TableColumn<TransactionDetail, Integer> quantityColumn, totalColumn;
    private TextField transactionIdField;
    private Button searchButton;
    private BorderPane root;
    private HBox detailLayout;
    private Label transactionIdLabel, staffNameLabel, customerNameLabel, dateLabel;
    
    private Transaction selectedTransaction;
    private ObservableList<Transaction> transactionList = FXCollections.observableArrayList();
    private ObservableList<TransactionDetail> tDetailList = FXCollections.observableArrayList();

    public TransactionView() {
        initialize();
        styling();
        populateTable();
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
        back = new MenuItem("Back");
        logout = new MenuItem("Log Out");

        // Add MenuItems to the Menu
        menuOptions.getItems().addAll(back, logout);
        menuBar.getMenus().add(menuOptions);

        // Create Transaction Tables
        transactionTable = new TableView<Transaction>();
        transactionTable.setPlaceholder(new Label("No content in table"));
        transactionTable.setPrefHeight(200);

        transactionIdColumn = new TableColumn<Transaction, String>("Transaction ID");
        staffIdColumn = new TableColumn<Transaction, String>("Staff ID");
        payIdColumn = new TableColumn<Transaction, String>("Pay Method ID");
        customerIdColumn = new TableColumn<Transaction, String>("Customer ID");
        dateColumn = new TableColumn<Transaction, String>("Date");
        
        transactionTable.getColumns().addAll(transactionIdColumn, staffIdColumn, payIdColumn, customerIdColumn, dateColumn);
        transactionTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        transactionDetailTable = new TableView<TransactionDetail>();
        transactionDetailTable.setPlaceholder(new Label("No content in table"));
        transactionDetailTable.setPrefHeight(150);
        
        itemNameColumn = new TableColumn<TransactionDetail, String>("Item Name");
        quantityColumn = new TableColumn<TransactionDetail, Integer>("Quantity");
        totalColumn = new TableColumn<TransactionDetail, Integer>("Total");
        
        transactionDetailTable.getColumns().addAll(itemNameColumn, quantityColumn, totalColumn);
        transactionDetailTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        // Set Transaction Detail Labels
        transactionIdLabel = new Label("Transaction ID	:");
        staffNameLabel = new Label("Staff Name		:");
        customerNameLabel = new Label("Customer Name 	:");
        dateLabel = new Label("Date				:");

        // Create Search Field and Button
        transactionIdField = new TextField();
        transactionIdField.setPromptText("Insert TransactionID here");
        searchButton = new Button("Search");

        // Layout for Search
//        HBox searchLayout = new HBox(10, new Label("Transaction ID:"), transactionIdField, searchButton);
//        searchLayout.setAlignment(Pos.CENTER_LEFT);
//        searchLayout.setPadding(new Insets(10));
        
        // Transaction Detail
        VBox leftDetailLayout = new VBox(10, transactionIdLabel, staffNameLabel, customerNameLabel, dateLabel);
        leftDetailLayout.setPadding(new Insets(20));
        leftDetailLayout.setAlignment(Pos.TOP_LEFT);
        
        detailLayout = new HBox(30, leftDetailLayout, transactionDetailTable);
        detailLayout.setAlignment(Pos.TOP_CENTER);
        detailLayout.setPadding(new Insets(40));
        
        Label trans = new Label("Transaction");
        trans.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Main Layout
        VBox mainLayout = new VBox(10, trans, transactionTable);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);

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
    	transactionList = TransactionController.getAll(CustomerView.selectedCustomer.getId());
    	transactionTable.setItems(FXCollections.observableArrayList(transactionList));
    }
    
    private void setTableColumns() {
    	transactionIdColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("transactionID"));
    	staffIdColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("staffID"));
    	payIdColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("payID"));
    	customerIdColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("customerID"));
    	dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("date"));
    	
    	itemNameColumn.setCellValueFactory(new PropertyValueFactory<TransactionDetail, String>("ItemName"));
    	quantityColumn.setCellValueFactory(new PropertyValueFactory<TransactionDetail, Integer>("quantity"));
    	totalColumn.setCellValueFactory(new PropertyValueFactory<TransactionDetail, Integer>("total"));
    }
    
    private void setTableListener() {
    	transactionTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, selectedTransaction) -> {
    		this.selectedTransaction = selectedTransaction;
        	tDetailList = TransactionController.getDetail(selectedTransaction.getTransactionID());
        	transactionDetailTable.setItems(FXCollections.observableArrayList(tDetailList));
    		transactionIdLabel.setText("Transaction ID	:" + selectedTransaction.getTransactionID());
    		staffNameLabel.setText("Staff Name		:" + TransactionController.getStaffName(selectedTransaction.getTransactionID()));
    		customerNameLabel.setText("Customer Name 	:" + TransactionController.getCustomerName(selectedTransaction.getTransactionID()));
    		dateLabel.setText("Date				:" + selectedTransaction.getDate());
    		root.setBottom(detailLayout);
    	});
    }

    private void action() {
    	back.setOnAction(e->{
    		Main.showCustomerPage();
    	});
    	logout.setOnAction(e->{
    		Main.showLoginPage();
    	});
    }

    public Scene getScene() {
        return scene;
    }
}
