package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.net.URL;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class AddModifyProductController implements Initializable, ControllerWithData {
    @FXML private AnchorPane apnRoot;
    @FXML private Label lblTitle;
    @FXML private TextField txtProductID;
    @FXML private TextField txtName;
    @FXML private TextField txtPrice;
    @FXML private TextField txtInStock;
    @FXML private TextField txtMin;
    @FXML private TextField txtMax;
    @FXML private TextField txtSearch;
    @FXML private TableView tblAssociatedParts;
    @FXML private TableView tblUnusedParts;
    @FXML private Button btnAdd;
    @FXML private Button btnDelete;
    @FXML private Button btnSave;
    private ObservableList<Part> unusedParts = FXCollections.observableArrayList();;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtName.setPromptText("Product Name");
        TextFormatter<Integer> inStockFormatter = new TextFormatter<>(new IntegerStringConverter(), 0, Input.INTEGER_ONLY_FILTER);
        TextFormatter<Integer> minFormatter = new TextFormatter<>(new IntegerStringConverter(), 0, Input.INTEGER_ONLY_FILTER);
        TextFormatter<Integer> maxFormatter = new TextFormatter<>(new IntegerStringConverter(), 0, Input.INTEGER_ONLY_FILTER);
        TextFormatter<String> priceFormatter = new TextFormatter<String>(Input.DECIMAL_ONLY_FILTER);
        txtInStock.setTextFormatter(inStockFormatter);
        txtMin.setTextFormatter(minFormatter);
        txtMax.setTextFormatter(maxFormatter);
        txtPrice.setTextFormatter(priceFormatter);
        txtName.focusedProperty().addListener((o, ov, nv) -> lostFocus(nv, isNameValid(), txtName,
            "Invalid Part Name", "WARNING: Please enter a Part Name."));
        txtInStock.focusedProperty().addListener((o, ov, nv) -> lostFocus(nv, isInStockValid(), txtInStock,
            "Invalid Inventory Level", getInStockErrorBody()));
        txtMin.focusedProperty().addListener((o, ov, nv) -> lostFocus(nv, isMinMaxValid(), txtMin,
            "Invalid Minimum Inventory Level", "WARNING: Minimum inventory level must be less than the minimum inventory level."));
        txtMax.focusedProperty().addListener((o, ov, nv) -> lostFocus(nv, isMinMaxValid(), txtMax,
            "Invalid Maximum Inventory Level", "WARNING: Maximum inventory level must be greater than the minimum inventory level."));
        txtPrice.focusedProperty().addListener((o, ov, nv) -> lostFocus(nv, isPriceValid(), txtPrice,
            "Invalid Product Price", "WARNING: Product price must be greater than cost of all associated parts."));
        
    }    
        public void initData(String title, int data) {
        lblTitle.setText(title);
        if(data == 0) {
            // Adding new product
            txtProductID.setPromptText("Auto Gen - Disabled");
            txtPrice.setText("0.00");
            unusedParts.setAll(Inventory.getAllParts());
        } else {
            // Modifying existing product
            Product product = Inventory.lookupProduct(data);
            txtProductID.setText(Integer.toString(product.getProductID()));
            txtName.setText(product.getName());
            txtPrice.setText(Double.toString(product.getPrice()));
            txtInStock.setText(Integer.toString(product.getInStock()));
            txtMin.setText(Integer.toString(product.getMin()));
            txtMax.setText(Integer.toString(product.getMax()));
            associatedParts.setAll(product.getAssociatedParts());
            unusedParts.setAll(filterUnusedParts());
        }
        apnRoot.requestFocus();
    }
        
    public ObservableList<Part> getUnusedParts() {
        return unusedParts;
    }
    
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
        
    @FXML
    protected void handleSearch(ActionEvent event) {
        String search = txtSearch.getText();
        if(search.equals("")) {
            unusedParts.setAll(filterUnusedParts());
        } else {
            unusedParts.setAll(filterUnusedParts().filtered(p -> p.getName().contains(search) ||
                    Integer.toString(p.getPartID()).equals(search)));
        }
    }
    
    @FXML
    protected void handleSearchKeyPressed(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ESCAPE)) {
            txtSearch.setText("");
            unusedParts.setAll(filterUnusedParts());
        }
    }
    
    @FXML
    protected void handleAdd(ActionEvent event) {
        Part part = (Part) tblUnusedParts.getSelectionModel().getSelectedItem();
        associatedParts.add(part);
        unusedParts.remove(part);
        btnAdd.setDisable(true);
        lostFocus(false, isPriceValid(), txtPrice, "Invalid Product Price", "WARNING: Product price must be greater than cost of all associated parts.");
        handleUnusedPartTableSelected();
        checkCanSave();
    }
    
    @FXML
    protected void handleDelete(ActionEvent event) {
        Part part = (Part) tblAssociatedParts.getSelectionModel().getSelectedItem();
                Optional<ButtonType> result = UI.showDialog(
                "Remove Part", 
                "Really remove this part? This action cannot be undone.", 
                "ID: " + part.getPartID() + "\t\tName: " + part.getName()
        );
        if (result.get() == ButtonType.OK) {
            associatedParts.remove(part);
            unusedParts.setAll(filterUnusedParts());
            lostFocus(false, isAssociatedPartsValid(), tblAssociatedParts, "Invalid Parts", "WARNING: Product must have at least one part selected.");
            handleAssociatedPartTableSelected();
        }
    }
    
    @FXML
    protected void handleSave(ActionEvent event) {
        String name = txtName.getText();
        double price = Double.parseDouble(txtPrice.getText()); 
        int inStock = Integer.parseInt(txtInStock.getText()); 
        int min = Integer.parseInt(txtMin.getText()); 
        int max = Integer.parseInt(txtMax.getText());
        if(txtProductID.getText().equals("")) {
            // Adding new Product
            int productID = getNextProductID();
            Product product = new Product(productID, name, price, inStock, min, max, associatedParts);
            Inventory.addProduct(product);
        } else {
            // Modifying existing product
            int productID = Integer.parseInt(txtProductID.getText());
            Product product = new Product(productID, name, price, inStock, min, max, associatedParts);
            Inventory.lookupProduct(productID).setAll(product);
        }
        closeWindow();
    }
    
    @FXML
    protected void handleCancel(ActionEvent event) {
        Optional<ButtonType> result = UI.showCancelDialog(lblTitle.getText());
        if(result.get() == (ButtonType.OK)) {
            closeWindow();
        }
    }
    
    @FXML
    protected void handleUnusedPartTableSelected() {
        if (tblUnusedParts.getSelectionModel().getSelectedItem() != null) {
           btnAdd.setDisable(false);
        } else {
           btnAdd.setDisable(true);
        }
    }
    
    @FXML
    protected void handleAssociatedPartTableSelected() {
        if (tblAssociatedParts.getSelectionModel().getSelectedItem() != null) {
           btnDelete.setDisable(false);
        } else {
           btnDelete.setDisable(true);
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) apnRoot.getScene().getWindow();
        stage.close();
    }
    
    private ObservableList<Part> filterUnusedParts() {
        return Inventory.getAllParts().filtered(p -> !associatedParts.contains(p));
    }
    
    private int getNextProductID() {
        int nextProductID;
        if(Inventory.getAllProducts().size() == 0) {
            nextProductID = 1;
        } else {
            nextProductID = Inventory.getAllProducts().stream()
                .max((Comparator.comparingInt(Product::getProductID))).get().getProductID() + 1;
        }
        return nextProductID;
    }
    
    public boolean canSave() {
        int inStock, min, max;
        String name = txtName.getText();
        inStock = Integer.parseInt(txtInStock.getText());
        min = Integer.parseInt(txtMin.getText());
        max = Integer.parseInt(txtMax.getText());
    
        return (!name.equals("") && min <= max && inStock >= min && inStock <= max);
    }
    
    public void checkCanSave(KeyEvent k) {
        checkCanSave();
    }
    
    public void checkCanSave() {
        String name = txtName.getText();
        int inStock = Integer.parseInt(txtInStock.getText());
        int min = Integer.parseInt(txtMin.getText());
        int max = Integer.parseInt(txtMax.getText());
        double price = Double.parseDouble(txtPrice.getText());
        double totalCost = 0.0;
        if(associatedParts.size() > 0) {
            totalCost = associatedParts.stream().mapToDouble(Part::getPrice).sum();
        }
        if (!name.equals("") && min <= max && inStock >= min && inStock <= max && associatedParts.size() > 0 && price >= totalCost) {
            btnSave.setDisable(false);
        } else {
            btnSave.setDisable(true);
        }
    }

    public boolean isPriceValid() {
        double price = Double.parseDouble(txtPrice.getText());
        double totalCost = 0.0;
        if(associatedParts.size() > 0) {
            totalCost = associatedParts.stream().mapToDouble(Part::getPrice).sum();
        }
        return (price >= totalCost);
    }
    
    public boolean isInStockValid() {
        int inStock = Integer.parseInt(txtInStock.getText());
        int min = Integer.parseInt(txtMin.getText());
        int max = Integer.parseInt(txtMax.getText());
        return (inStock >= min && inStock <= max);
    }
    
    public boolean isMinMaxValid() {
        int min = Integer.parseInt(txtMin.getText());
        int max = Integer.parseInt(txtMax.getText());
        return (max >= min);
    }
    
    private boolean isNameValid() {
        return !txtName.getText().equals("");
    }
    
    private boolean isAssociatedPartsValid() {
        return (associatedParts.size() > 0);
    }
    
    private String getInStockErrorBody() {
        int min = Integer.parseInt(txtMin.getText());
        int max = Integer.parseInt(txtMax.getText());
        int inStock = Integer.parseInt(txtInStock.getText());
        if (inStock < min) {
            return "WARNING: Inventory level must be greater than minimum level.";
        } else if (inStock > max) {
            return "WARNING: Inventory level must be less than maximum inventory level.";
        } else {
            return "";
        }
    }
    
    private void lostFocus(boolean isFocused, boolean isValid, TextField txt, String header, String body) {
        if (!isFocused) {
            if(!isValid) {
                /* Focus away before showing error dialog. Found bug when user clicks on 
                 * window controls with invalid input. If we don't focus first away, 
                 * we'll get a loop of txtField selected -> windowControl selected -> error dialog -> 
                 * txtField selected -> windowControl selected -> error dialog. */
                
                apnRoot.requestFocus();
                UI.showError(header, body);
                txt.setStyle(UI.ERROR_STYLE);
                checkCanSave();
            } else {
                txt.setStyle("");
            }
        }
    }
    
    private void lostFocus(boolean isFocused, boolean isValid, TableView tbl, String header, String body) {
        if (!isFocused) {
            if(!isValid) {
                /* Focus away before showing error dialog. Found bug when user clicks on 
                 * window controls with invalid input. If we don't focus first away, 
                 * we'll get a loop of txtField selected -> windowControl selected -> error dialog -> 
                 * txtField selected -> windowControl selected -> error dialog. */
                
                apnRoot.requestFocus();
                UI.showError(header, body);
                tbl.setStyle(UI.ERROR_STYLE);
                checkCanSave();
            } else {
                tbl.setStyle("");
            }
        }
    }
}
