package model;

public class Transaction {
	
	private String transactionID;
	private String staffID;
	private String payID;
	private String customerID;
	private String date;
	public Transaction(String transactionID, String staffID, String payID, String customerID, String date) {
		super();
		this.transactionID = transactionID;
		this.staffID = staffID;
		this.payID = payID;
		this.customerID = customerID;
		this.date = date;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getStaffID() {
		return staffID;
	}
	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}
	public String getPayID() {
		return payID;
	}
	public void setPayID(String payID) {
		this.payID = payID;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	

}
