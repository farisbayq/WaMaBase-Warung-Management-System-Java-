package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import util.DatabaseConnection;

public class CustomerController {

	public static ObservableList<Customer> getAll() {
		ObservableList<Customer> customerList = FXCollections.observableArrayList();
		try (Connection conn = DatabaseConnection.getConnection()) {
			String query = "SELECT * FROM mscustomer";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				customerList.add(new Customer(rs.getString("CustomerID"), rs.getString("CustomerName"), rs.getString("AccountNumber"), 
						rs.getString("PhoneNumber")));
			}
			
		} catch (SQLException e) {

			e.printStackTrace();

		}
		return customerList;
	}
	
	public static ObservableList<Customer> searchID(String search) {
		ObservableList<Customer> customerList = FXCollections.observableArrayList();
		try (Connection conn = DatabaseConnection.getConnection()) {
			String query = "SELECT *\r\n"
					+ "FROM mscustomer\r\n"
					+ "WHERE CustomerID REGEXP ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, search);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				customerList.add(new Customer(rs.getString("CustomerID"), rs.getString("CustomerName"), rs.getString("AccountNumber"), 
						rs.getString("PhoneNumber")));
			}
			
		} catch (SQLException e) {

			e.printStackTrace();

		}
		return customerList;
	}
	
	public static ObservableList<Customer> searchName(String search) {
		ObservableList<Customer> customerList = FXCollections.observableArrayList();
		try (Connection conn = DatabaseConnection.getConnection()) {
			String query = "SELECT *\r\n"
					+ "FROM mscustomer\r\n"
					+ "WHERE CustomerName REGEXP ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, search);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				customerList.add(new Customer(rs.getString("CustomerID"), rs.getString("CustomerName"), rs.getString("AccountNumber"), 
						rs.getString("PhoneNumber")));
			}
			
		} catch (SQLException e) {

			e.printStackTrace();

		}
		return customerList;
	}
	
	public static ObservableList<Customer> searchAccountNumber(String search) {
		ObservableList<Customer> customerList = FXCollections.observableArrayList();
		try (Connection conn = DatabaseConnection.getConnection()) {
			String query = "SELECT *\r\n"
					+ "FROM mscustomer\r\n"
					+ "WHERE AccountNumber REGEXP ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, search);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				customerList.add(new Customer(rs.getString("CustomerID"), rs.getString("CustomerName"), rs.getString("AccountNumber"), 
						rs.getString("PhoneNumber")));
			}
			
		} catch (SQLException e) {

			e.printStackTrace();

		}
		return customerList;
	}
	
	public static ObservableList<Customer> searchPhoneNumber(String search) {
		ObservableList<Customer> customerList = FXCollections.observableArrayList();
		try (Connection conn = DatabaseConnection.getConnection()) {
			String query = "SELECT *\r\n"
					+ "FROM mscustomer\r\n"
					+ "WHERE PhoneNumber REGEXP ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, search);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				customerList.add(new Customer(rs.getString("CustomerID"), rs.getString("CustomerName"), rs.getString("AccountNumber"), 
						rs.getString("PhoneNumber")));
			}
			
		} catch (SQLException e) {

			e.printStackTrace();

		}
		return customerList;
	}

}
