package model;

public class Customer {
	
	private String id;
	private String name;
	private String accountNumber;
	private String phoneNumber;
	public Customer(String id, String name, String accountNumber, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.accountNumber = accountNumber;
		this.phoneNumber = phoneNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	

}
