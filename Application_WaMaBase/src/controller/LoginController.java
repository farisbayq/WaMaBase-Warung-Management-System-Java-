package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import main.Main;
import util.DatabaseConnection;

public class LoginController {

	public static boolean login(String email, String password) {
		
		try(Connection conn = DatabaseConnection.getConnection()) {
			String query = "SELECT * FROM msstaff WHERE StaffEmail = ? AND StaffPassword = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				return true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			Main.showAlert(AlertType.ERROR, "Error","Database Error", "An error has ocurred in the database error");
		}
		
		return false;
	}
	
}
