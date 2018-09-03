package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Part {
    private final IntegerProperty partID;
    private final SimpleStringProperty name;
    private final SimpleDoubleProperty price;
    private final SimpleIntegerProperty inStock;
    private final SimpleIntegerProperty min;
    private final SimpleIntegerProperty max;
    
    public Part(int partID, String name, double price, int inStock, int min, int max) {
        this.partID = new SimpleIntegerProperty(partID);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
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
    
    public void setPartID(int partID) {
        this.partID.set(partID);
    }
    
    public int getPartID() {
        return partID.get();
    }
}
