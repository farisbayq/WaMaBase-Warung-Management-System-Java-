package scenes;

import controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.Main;

public class LoginView {

	private Scene scene;
    private Label loginLbl, emailLbl, passwordLbl;
    private TextField emailFld;
    private PasswordField passwordFld;
    private Button loginBtn;
    private VBox mainContainer, buttonContainer;
    private GridPane formContainer;
    private BorderPane baseContainer;

    public LoginView() {
        initialize();
        styling();
        action();
    }

    private void initialize() {
        baseContainer = new BorderPane();
        scene = new Scene(baseContainer, 800, 600);

        loginLbl = new Label("LOGIN");

        // Form elements
        formContainer = new GridPane();
        emailLbl = new Label("Email\t:");
        emailFld = new TextField();
        passwordLbl = new Label("Password\t:");
        passwordFld = new PasswordField();

        formContainer.addRow(0, emailLbl, emailFld);
        formContainer.addRow(1, passwordLbl, passwordFld);

        // Login button
        buttonContainer = new VBox();
        loginBtn = new Button("Login");
        buttonContainer.getChildren().add(loginBtn);

        formContainer.add(buttonContainer, 1, 2);

        // Main container
        mainContainer = new VBox();
        mainContainer.getChildren().addAll(loginLbl, formContainer);
        baseContainer.setCenter(mainContainer);
    }

    private void styling() {
        loginLbl.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        emailFld.setPromptText("Email");
        passwordFld.setPromptText("Password");
        loginBtn.setPrefWidth(100);

        formContainer.setVgap(15);
        formContainer.setHgap(10);
        formContainer.setPadding(new Insets(20));
        formContainer.setAlignment(Pos.CENTER);

        buttonContainer.setAlignment(Pos.CENTER_RIGHT);
        buttonContainer.setPadding(new Insets(10, 0, 0, 0));
        
        mainContainer.setPadding(new Insets(30));
        mainContainer.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(mainContainer, Pos.CENTER);
    }
    
    private void action() {
    	loginBtn.setOnAction(e->{
    		String email = emailFld.getText();
    		String password = passwordFld.getText();
    		if(email.isEmpty() || password.isEmpty()) {
    			Main.showAlert(AlertType.ERROR, "Error", "Validation Error", "Email or Password can't be empty!");
    			return ;
    		}
    		if(LoginController.login(emailFld.getText(),passwordFld.getText())) {
    			Main.showHomePage();
    		} else {
    			Main.showAlert(AlertType.ERROR, "Error", "Validation Error", "Email and/or Password does not match");
    		}
    	});
    }
    
    public Scene getScene() {
    	return scene;
    }
	
}
