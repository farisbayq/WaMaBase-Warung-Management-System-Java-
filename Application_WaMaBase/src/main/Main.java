package main;

import scenes.*;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Main extends Application{
	private static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		showLoginPage();
		primaryStage.setTitle("WaMaBase");
		primaryStage.show();
	}
	
	
	public static void showLoginPage() {
		LoginView loginView = new LoginView();
		primaryStage.setScene(loginView.getScene());
	}
	
	public static void showHomePage() {
		HomeView homeView = new HomeView();
		primaryStage.setScene(homeView.getScene());
	}
	
	public static void showCheckoutPage() {
		CheckoutView checkoutView = new CheckoutView();
		primaryStage.setScene(checkoutView.getScene());
	}
	
	public static void showInventoryPage() {
		InventoryView inventoryView = new InventoryView();
		primaryStage.setScene(inventoryView.getScene());
		
	}
	
	public static void showCustomerPage() {
		CustomerView custView = new CustomerView();
		primaryStage.setScene(custView.getScene());
	}
	
	public static void showTransactionPage() {
		TransactionView tranView = new TransactionView();
		primaryStage.setScene(tranView.getScene());
		
	}
	
	public static void showReportPage() {
		ReportView repView = new ReportView();
		primaryStage.setScene(repView.getScene());
		
	}
	
	public static void showAlert(AlertType alertType, String title, String header, String content) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
