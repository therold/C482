package Model;

import javafx.beans.property.SimpleStringProperty;

public class Outsourced extends Part {
    private final SimpleStringProperty companyName;
    
    public Outsourced(int partID, String name, double price, int inStock,
        int min, int max, String companyName) {
            super(partID, name, price, inStock, min, max);
            this.companyName = new SimpleStringProperty(companyName);
    }
    
    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }
    
    public String getCompanyName() {
        return companyName.get();
    }
    
        public void setAll(Outsourced part) {
        setPartID(part.getPartID());
        setName(part.getName());
        setPrice(part.getPrice());
        setInStock(part.getInStock());
        setMin(part.getMin());
        setMax(part.getMax());
        setCompanyName(part.getCompanyName());
    }
}
