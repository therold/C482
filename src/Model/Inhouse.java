package Model;

import javafx.beans.property.SimpleIntegerProperty;

public class Inhouse extends Part {
    private final SimpleIntegerProperty machineID;
    
    public Inhouse(int partID, String name, double price, int inStock,
        int min, int max, int machineID) {
            super(partID, name, price, inStock, min, max);
            this.machineID = new SimpleIntegerProperty(machineID);
    }
    
    public void setMachineID(int machineID) {
        this.machineID.set(machineID);
    }
    
    public int getMachineID() {
        return machineID.get();
    }
    
    public void setAll(Inhouse part) {
        setPartID(part.getPartID());
        setName(part.getName());
        setPrice(part.getPrice());
        setInStock(part.getInStock());
        setMin(part.getMin());
        setMax(part.getMax());
        setMachineID(part.getMachineID());
    }
}
