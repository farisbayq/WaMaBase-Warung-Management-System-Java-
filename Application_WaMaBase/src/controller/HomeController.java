package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MsItem;
import util.DatabaseConnection;

public class HomeController {

	public static ObservableList<MsItem> populateTable() {
		ObservableList<MsItem> itemList = FXCollections.observableArrayList();
		try (Connection conn = DatabaseConnection.getConnection()) {
			String query = "SELECT * FROM msitem";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				itemList.add(new MsItem(rs.getString("ItemID"), rs.getString("ItemName"), rs.getInt("ItemPrice")));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return itemList;
	}

	public static ObservableList<MsItem> search(String search) {
		ObservableList<MsItem> itemList = FXCollections.observableArrayList();
		try (Connection conn = DatabaseConnection.getConnection()) {
			String query = "SELECT *\r\n"
					+ "FROM msitem\r\n"
					+ "WHERE ItemName REGEXP ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, search);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				itemList.add(new MsItem(rs.getString("ItemID"), rs.getString("ItemName"), rs.getInt("ItemPrice")));
			}
			
		} catch (SQLException e) {

			e.printStackTrace();

		}
		return itemList;
	}

}
