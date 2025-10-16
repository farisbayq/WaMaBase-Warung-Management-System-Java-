package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import scenes.CheckoutView;
import util.DatabaseConnection;

public class CheckoutController {
	
	public static int countCustomer() {
		int totalCustomer = 0;
		try(Connection conn = DatabaseConnection.getConnection()) {
			String query = "SELECT COUNT(CustomerID) as totalCustomer"
					+ "FROM mscustomer";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				totalCustomer = rs.getInt("totalCustomer");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return totalCustomer;
	}
	
	public static int countTransaction() {
		int totalTransaction = 0;
		try(Connection conn = DatabaseConnection.getConnection()) {
			String query = "SELECT COUNT(TransactionID) as totalTransaction"
					+ "FROM transactionheader";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				totalTransaction = rs.getInt("totalTransaction");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return totalTransaction;
	}
	
	public static boolean isNameExists(String name) {
		String query = "Select Count(*) FROM mscustomer Where CustomerName = ?";
		
		try(Connection conn = DatabaseConnection.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1) > 0;
			
		} catch(SQLException e ) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean isIDExists(String id) {
		String query = "Select Count(*) FROM mscustomer Where CustomerID = ?";
		
		try(Connection conn = DatabaseConnection.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1) > 0;
			
		} catch(SQLException e ) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean addTransactionHeader(String transactionID, String staffID, String payMethodID, String customerID, java.sql.Date transactionDate) {
	    String query = "INSERT INTO TransactionHeader VALUES (?, ?, ?, ?, ?)";
	    
	    if(!isIDExists(customerID)) {
	    	addCustomer(customerID, CheckoutView.customerNameField.getText(), CheckoutView.accountNumberField.getText(), CheckoutView.phoneNumberField.getText());
	    }
	    
    	try (Connection conn = DatabaseConnection.getConnection()) {
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setString(1, transactionID);
	        ps.setString(2, staffID);
	        ps.setString(3, payMethodID);
	        ps.setString(4, customerID);
	        ps.setDate(5, transactionDate);
	        ps.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }	
	    
	    
	    
	}

	public static boolean addTransactionDetail(String transactionID, String itemID, int quantity) {
	    String query = "INSERT INTO TransactionDetail (TransactionID, ItemID, Quantity) VALUES (?, ?, ?)";
	    try (Connection conn = DatabaseConnection.getConnection()) {
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setString(1, transactionID);
	        ps.setString(2, itemID);
	        ps.setInt(3, quantity);
	        ps.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public static boolean updateInventory(String itemID, int quantity) {
	    String query = "UPDATE Inventory SET StockQuantity = StockQuantity - ? WHERE ItemID = ?";
	    try (Connection conn = DatabaseConnection.getConnection()) {
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, quantity);
	        ps.setString(2, itemID);
	        ps.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static String getUserIDByName(String name) {
        String userID = null;
        String query = "SELECT CustomerID FROM mscustomer WHERE CustomerName = ?";
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                userID = rs.getString("CustomerID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return userID;
    }
	
	
	public static void addCustomer(String id, String name, String accountNumber, String phoneNumber) {
		
		try(Connection conn = DatabaseConnection.getConnection()) {
			String query = "INSERT INTO mscustomer VALUES (?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, accountNumber);
			ps.setString(4, phoneNumber);
			ps.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}