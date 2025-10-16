package model;

public class TransactionDetail {
	
	private String ItemName;
	private int quantity;
	private int total;
	public TransactionDetail(String itemName, int quantity, int total) {
		super();
		ItemName = itemName;
		this.quantity = quantity;
		this.total = total;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	

}
