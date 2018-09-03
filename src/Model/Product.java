package Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private SimpleIntegerProperty productID;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty inStock;
    private SimpleIntegerProperty min;
    private SimpleIntegerProperty max;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    
    
    public Product(int productID, String name, double price, int inStock, int min, int max, 
            ObservableList<Part> associatedParts) {
        this.productID = new SimpleIntegerProperty(productID);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
        this.associatedParts.setAll(associatedParts);
    }
    
    public void setName(String name) {
        this.name.set(name);
    }
    
    public String getName() {
        return name.get();
    }
    
    public void setPrice(double price) {
        this.price.set(price);
    }
    
    public double getPrice() {
        return price.get();
    }
    
    public void setInStock(int inStock) {
        this.inStock.set(inStock);
    }
    
    public int getInStock() {
        return inStock.get();
    }
    
    public void setMin(int min) {
        this.min.set(min);
    }
    
    public int getMin() {
        return min.get();
    }
    
    public void setMax(int max) {
        this.max.set(max);
    }
    
    public int getMax() {
        return max.get();
    }
    
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }
    
    public boolean removeAssociatedPart(int partID) {
        Part part = lookupAssociatedPart(partID);
        return associatedParts.remove(part);
    }
    
    public Part lookupAssociatedPart(int partID) {
        return associatedParts.filtered(p -> (p.getPartID() == partID)).get(0);
    }
    
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
    
    public void setProductID(int productID) {
        this.productID.set(productID);
    }
    
    public int getProductID() {
        return productID.get();
    }
    
    public void setAll(Product product) {
        this.productID.set(product.getProductID());
        this.name.set(product.getName());
        this.price.set(product.getPrice());
        this.inStock.set(product.getInStock());
        this.min.set(product.getMin());
        this.max.set(product.getMax());
        this.associatedParts.setAll(product.getAssociatedParts());
    }
}
