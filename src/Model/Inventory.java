package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Product> products = FXCollections.observableArrayList();
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    
    public static void addProduct(Product product) {
        products.add(product);
    }
    
    public static boolean removeProduct(int productID) {
        return products.remove(lookupProduct(productID));
    }
    
    public static Product lookupProduct(int productID) {
        return products.filtered(p -> p.getProductID() == productID).get(0);
    }
    
    public static void addPart(Part part) {
        allParts.add(part);
    }
    
    public static boolean deletePart(Part part) {
        return allParts.remove(part);
    }
    
    public static boolean deleteProduct(Product product) {
        return products.remove(product);
    }
    
    public static Part lookupPart(int partID) {
        /* Included for compatibility with UML spec. Not used in UI. */
        return allParts.filtered(p -> p.getPartID() == partID).get(0);
    }
    
    public void updatePart(int partID) {
        /* Parts are updated automatically by FXML binding. updatePart is unnecessary. */
    }
    
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    
    public static ObservableList<Product> getAllProducts() {
        return products;
    }
    
    public static ObservableList<Part> searchParts(String search) {
        /* Search function used in UI. Using String-based search to 
           enable searching by name or ID instead of just ID. */
        return allParts.filtered(p -> (p.getName().toUpperCase().contains(search.toUpperCase()) || 
                                       Integer.toString(p.getPartID()).equals(search)));
    }
    
    public static ObservableList<Product> searchProducts(String search) {
        return products.filtered(p -> p.getName().toUpperCase().contains(search.toUpperCase()) ||
                                      Integer.toString(p.getProductID()).equals(search));
    }
}
