package scenes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.CheckoutController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import main.Main;
import model.Cart;
import util.DatabaseConnection;

public class CheckoutView {
    private Scene scene;
    private Button confirmButton;
    public static TextField customerNameField, accountNumberField, phoneNumberField;

    
	public CheckoutView() {
        initialize();
        action();
    }
	
	@SuppressWarnings("unchecked")
    private void initialize() {
    	// TableView for items
        TableView<Cart> tableView = new TableView<>();
        tableView.setPrefWidth(400);

        // Table columns
        TableColumn<Cart, String> itemColumn = new TableColumn<>("Item");
        itemColumn.setCellValueFactory(new PropertyValueFactory<Cart, String>("name"));
        itemColumn.setMinWidth(150);

        TableColumn<Cart, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("quantity"));
        quantityColumn.setMinWidth(100);

        TableColumn<Cart, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("total"));
        priceColumn.setMinWidth(100);

        tableView.getColumns().addAll(itemColumn, quantityColumn, priceColumn);

        // Insert data
        ObservableList<Cart> items = FXCollections.observableArrayList();
        items = HomeView.cartList;
        tableView.setItems(FXCollections.observableArrayList(items));

        // Input fields
        customerNameField = new TextField();
        customerNameField.setPromptText("CustomerName");

        accountNumberField = new TextField();
        accountNumberField.setPromptText("AccountNumber");

        phoneNumberField = new TextField();
        phoneNumberField.setPromptText("PhoneNumber");

        // Total label
        int total = 0;
    	for(Cart c : items) {
    		total += c.getTotal();
    	}
        Label totalLabel = new Label("Total: " + total);
        totalLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        // Confirm button
        confirmButton = new Button("Confirm");
        confirmButton.setPrefWidth(100);
        confirmButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Purchase Confirmed!");
            alert.showAndWait();
        });

        // Layout
        VBox inputFields = new VBox(10, customerNameField, accountNumberField, phoneNumberField, totalLabel, confirmButton);
        inputFields.setAlignment(Pos.CENTER);
        inputFields.setPadding(new Insets(20));

        HBox root = new HBox(20, tableView, inputFields);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        scene = new Scene(root, 800, 600);
    }
	
	private void action() {
		confirmButton.setPrefWidth(100);
		confirmButton.setOnAction(e -> {
		    if (customerNameField.getText().isEmpty() || accountNumberField.getText().isEmpty() || phoneNumberField.getText().isEmpty()) {
		        Main.showAlert(AlertType.ERROR, "Error", "Validation Error", "Please fill all the fields!");
		        return;
		    }

		    // Generate IDs
		    String transactionID = generateTranID(); // Implement correctly
		    System.out.println(transactionID);
		    String userID = generateUserID(); // Assuming a method to get UserID
		    String staffID = "ST001"; // Replace with logged-in staff ID
		    String paymethodID = "PM001"; // Replace with actual PaymethodID
		    java.sql.Date transactionDate = new java.sql.Date(System.currentTimeMillis());
		    
		    System.out.println(transactionID+userID+staffID+paymethodID+transactionDate);

		    // Insert TransactionHeader
		    boolean isTransactionInserted = CheckoutController.addTransactionHeader(transactionID, staffID, paymethodID, userID, transactionDate);

		    System.out.println("Pass");
		    if (!isTransactionInserted) {
		        Main.showAlert(AlertType.ERROR, "Error", "Database Error", "Failed to process the transaction.");
		        return;
		    }

		    // Insert TransactionDetails and Update Inventory
		    for (Cart cartItem : HomeView.cartList) {
		        boolean isDetailInserted = CheckoutController.addTransactionDetail(transactionID, cartItem.getItemID(), cartItem.getQuantity());
		        boolean isStockUpdated = CheckoutController.updateInventory(cartItem.getItemID(), cartItem.getQuantity());

		        if (!isDetailInserted || !isStockUpdated) {
		            Main.showAlert(AlertType.ERROR, "Error", "Database Error", "Failed to update transaction details or inventory.");
		            return;
		        }
		    }

		    // Clear the cart and refresh the view
		    HomeView.cartList.clear();
		    Main.showAlert(AlertType.INFORMATION, "Success", "Transaction Completed", "Your transaction was successful!");
		    Main.showHomePage();
		});

	}
    
	private static String generateUserID(){
		try(Connection conn = DatabaseConnection.getConnection()){
			String query = "SELECT COUNT(*) FROM mscustomer";
			PreparedStatement prepState = conn.prepareStatement(query);
			ResultSet resSet = prepState.executeQuery();
			resSet.next();
			int userCount = resSet.getInt(1) + 1;
			return String.format("CU%03d", userCount);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
		
	}
	
	private static String generateTranID(){
		try(Connection conn = DatabaseConnection.getConnection()){
			String query = "SELECT COUNT(*) FROM transactionheader";
			PreparedStatement prepState = conn.prepareStatement(query);
			ResultSet resSet = prepState.executeQuery();
			resSet.next();
			int userCount = resSet.getInt(1) + 1;
			return String.format("TR%03d", userCount);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
		
	}
	

    public Scene getScene() {
    	return scene;
    }

}