package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Inventory;
import model.MsItem;
import util.DatabaseConnection;

public class InventoryControl {
	
	public static ObservableList<Inventory> getAll() {
		ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();
		
		try (Connection conn = DatabaseConnection.getConnection()){
			String query = "SELECT mi.ItemID, ItemName, ItemPrice, StockQuantity "
					+ "FROM msitem mi "
					+ "JOIN inventory i ON mi.ItemID = i.ItemID";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				inventoryList.add(new Inventory(rs.getString("ItemID"), rs.getString("ItemName"), rs.getInt("ItemPrice"), rs.getInt("StockQuantity")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inventoryList;
	}
	
	public static ObservableList<Inventory> searchID(String search) {
		ObservableList<Inventory> itemList = FXCollections.observableArrayList();
		try (Connection conn = DatabaseConnection.getConnection()) {
			String query = "SELECT *\r\n"
					+ "FROM msitem mi\r\n"
					+ "JOIN inventory i ON mi.ItemID = i.ItemID\r\n"
					+ "WHERE mi.ItemID REGEXP ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, search);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				itemList.add(new Inventory(rs.getString("ItemID"), rs.getString("ItemName"), rs.getInt("ItemPrice"), rs.getInt("StockQuantity")));
			}
			
		} catch (SQLException e) {

			e.printStackTrace();

		}
		return itemList;
	}
	
	public static ObservableList<Inventory> searchName(String search) {
		ObservableList<Inventory> itemList = FXCollections.observableArrayList();
		try (Connection conn = DatabaseConnection.getConnection()) {
			String query = "SELECT *\r\n"
					+ "FROM msitem mi\r\n"
					+ "JOIN inventory i ON mi.ItemID = i.ItemID\r\n"
					+ "WHERE ItemName REGEXP ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, search);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				itemList.add(new Inventory(rs.getString("ItemID"), rs.getString("ItemName"), rs.getInt("ItemPrice"), rs.getInt("StockQuantity")));
			}
			
		} catch (SQLException e) {

			e.printStackTrace();

		}
		return itemList;
	}

}
