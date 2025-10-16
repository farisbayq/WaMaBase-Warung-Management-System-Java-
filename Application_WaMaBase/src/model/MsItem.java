package model;

public class MsItem {
	
	private String ItemID;
	private String ItemName;
	private int ItemPrice;
	public MsItem(String itemID, String itemName, int itemPrice) {
		super();
		ItemID = itemID;
		ItemName = itemName;
		ItemPrice = itemPrice;
	}
	public String getItemID() {
		return ItemID;
	}
	public void setItemID(String itemID) {
		ItemID = itemID;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public int getItemPrice() {
		return ItemPrice;
	}
	public void setItemPrice(int itemPrice) {
		ItemPrice = itemPrice;
	}
	
	

}
