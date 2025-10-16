package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Transaction;
import model.TransactionDetail;
import util.DatabaseConnection;

public class TransactionController {
	
	public static ObservableList<Transaction> getAll(String id) {
		ObservableList<Transaction> transactionList = FXCollections.observableArrayList();
		
		try(Connection conn = DatabaseConnection.getConnection()) {
			String query = "SELECT * FROM transactiondetail td\r\n"
					+ "JOIN transactionheader th ON td.TransactionID = th.TransactionID\r\n"
					+ "WHERE CustomerID = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				transactionList.add(new Transaction(rs.getString("TransactionID"), rs.getString("StaffID"), rs.getString("PayMethodID"),
						rs.getString("CustomerID"), String.valueOf(rs.getString("TransactionDate"))));
			}
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		return transactionList;
	}
	
	public static ObservableList<TransactionDetail> getDetail(String id){
		ObservableList<TransactionDetail> tDetailList = FXCollections.observableArrayList();
		try(Connection conn = DatabaseConnection.getConnection()) {
			String query = "SELECT ItemName, Quantity, SUM(Quantity * ItemPrice) as Total\r\n"
					+ "FROM msitem mi\r\n"
					+ "JOIN transactiondetail td ON td.ItemID = mi.ItemID\r\n"
					+ "WHERE TransactionID = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				tDetailList.add(new TransactionDetail(rs.getString("ItemName"), rs.getInt("Quantity"), rs.getInt("Total")));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return tDetailList;
	}
	
	public static String getStaffName(String id) {
		String staffName = "";
		try(Connection conn = DatabaseConnection.getConnection()) {
			String query = "SELECT StaffName FROM msstaff ms\r\n"
					+ "JOIN transactionheader th ON ms.StaffID = th.StaffID\r\n"
					+ "WHERE TransactionID = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				staffName = rs.getString("StaffName");
			}
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return staffName;
	}
	
	public static String getCustomerName(String id) {
		String customerName = "";
		try(Connection conn = DatabaseConnection.getConnection()) {
			String query = "SELECT CustomerName FROM mscustomer ms\r\n"
					+ "JOIN transactionheader th ON ms.CustomerID = th.CustomerID\r\n"
					+ "WHERE TransactionID = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				customerName = rs.getString("CustomerName");
			}
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return customerName;
	}

}
