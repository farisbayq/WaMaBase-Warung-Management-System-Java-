package scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import main.Main;
import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportView {

    private static Scene scene;
    private MenuBar menuBar;
    private Menu menuOptions;
    private MenuItem home, inventory, customer, report, logout;
    private TextField yearField;
    private Button searchButton;
    private LineChart<String, Number> salesChart;
    private BorderPane root;

    public ReportView() {
        initialize();
        styling();
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
        report = new MenuItem("Report");
        report.setDisable(true);
        logout = new MenuItem("Log Out");

        // Add MenuItems to the Menu
        menuOptions.getItems().addAll(home, inventory, customer, report, new SeparatorMenuItem(), logout);
        menuBar.getMenus().add(menuOptions);

        // Create Search Controls
        yearField = new TextField();
        yearField.setPromptText("Enter Year");

        searchButton = new Button("Search");

        // Layout for Search
        HBox searchLayout = new HBox(10, new Label("Year:"), yearField, searchButton);
        searchLayout.setAlignment(Pos.CENTER_LEFT);
        searchLayout.setPadding(new Insets(10));

        // Create Sales Chart
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Month");
        xAxis.setTickLabelRotation(45); // Rotasi 45 derajat untuk mengurangi tumpang tindih
        xAxis.setTickLabelGap(10);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Total Transactions");

        salesChart = new LineChart<>(xAxis, yAxis);

        Label titleLabel = new Label("Annual Sales Report");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Main Layout
        VBox mainLayout = new VBox(10, titleLabel, searchLayout, salesChart);
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

    private void action() {
        home.setOnAction(e -> {
            Main.showHomePage();
        });
        inventory.setOnAction(e -> {
            Main.showInventoryPage();
        });
        customer.setOnAction(e -> {
            Main.showCustomerPage();
        });
        logout.setOnAction(e -> {
            Main.showLoginPage();
        });

        searchButton.setOnAction(e -> {
            String year = yearField.getText();
            if (!year.isEmpty()) {
                updateChart(year);
            } else {
                System.out.println("Please enter a valid year.");
            }
        });
    }

    private void updateChart(String year) {
        ObservableList<XYChart.Data<String, Number>> chartData = fetchTransactionData(year);
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        
        if (chartData.isEmpty()) {
            System.out.println("No data available for the selected year.");
            showAlert("Error", "Tidak ada data untuk tahun ini");
            return;
        } 
        
        series.setName("Monthly Transactions");

        series.getData().addAll(chartData);

        salesChart.getData().clear();
        salesChart.getData().add(series);

        // Force layout refresh
        salesChart.layout();
    }


    private ObservableList<XYChart.Data<String, Number>> fetchTransactionData(String year) {
        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();

        String query = "SELECT MONTH(TransactionDate) as month, COUNT(TransactionID) as count " +
                       "FROM transactionHeader " +
                       "WHERE YEAR(TransactionDate) = ? " +
                       "GROUP BY MONTH(TransactionDate)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, year);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int month = rs.getInt("month");
                    int count = rs.getInt("count");
                    String monthName = getMonthName(month); // Convert month number to name
                    data.add(new XYChart.Data<>(monthName, count));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    private String getMonthName(int month) {
        String[] months = {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        };
        return months[month - 1];
    }
    
    private static void showAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.show();
	}

    public Scene getScene() {
        return scene;
    }
}