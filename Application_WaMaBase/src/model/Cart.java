package model;

public class Cart {
    
    private String itemID; // Add itemID attribute
    private String name;
    private int quantity;
    private int total;

    public Cart(String itemID, String name, int quantity, int total) {
        super();
        this.itemID = itemID; // Initialize itemID
        this.name = name;
        this.quantity = quantity;
        this.total = total;
    }

    // Getter and Setter for itemID
    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    // Existing getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
