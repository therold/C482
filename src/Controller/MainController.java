package Controller;

import Model.Inhouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import Model.Product;
import heroldinventorysystem.Main;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainController implements Initializable {
    @FXML private TableView<Part> tblParts;
    @FXML private TableView<Product> tblProducts;
    @FXML private TextField txtPartSearch;
    @FXML private TextField txtProductSearch;
    @FXML private Button btnPartModify;
    @FXML private Button btnPartDelete;
    @FXML private Button btnProductModify;
    @FXML private Button btnProductDelete;
    private static ObservableList<Part> mainPartList = FXCollections.observableArrayList();
    private static ObservableList<Product> mainProductList = FXCollections.observableArrayList();
    

    
    public ObservableList<Part> getAllParts() {
        return mainPartList;
    }
    
    public ObservableList<Product> getAllProducts() {
        return mainProductList;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    protected void handlePartsSearchButtonAction(ActionEvent event) {
        String search = txtPartSearch.getText();
        if(search.equals("")) {
            mainPartList.setAll(Inventory.getAllParts());
        } else {
            mainPartList.setAll(Inventory.searchParts(search));
        }
    }
    
    @FXML
    protected void handlePartsSearchKeyPressed(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ESCAPE)) {
            txtPartSearch.setText("");
            mainPartList.setAll(Inventory.getAllParts());
        }
    }
    
    @FXML
    protected void handlePartsAddButtonAction(ActionEvent event) throws IOException {
        UI.loadStageWithData("/View/AddModifyPart.fxml", "Add Part",  0);
        refreshPartsTable();
    }
    
    @FXML
    protected void handlePartsModifyButtonAction(ActionEvent event) throws IOException {
        UI.loadStageWithData("/View/AddModifyPart.fxml", "Modify Part", tblParts.getSelectionModel().getSelectedItem().getPartID());
        refreshPartsTable();
    }
    
    @FXML
    protected void handlePartsDeleteButtonAction(ActionEvent event) {
        Part part = tblParts.getSelectionModel().getSelectedItem();
        Optional<ButtonType> result = UI.showDialog(
                "Delete Part", 
                "Really delete this part? This action cannot be undone.", 
                "ID: " + part.getPartID() + "\t\tName: " + part.getName()
        );
        if (result.get() == ButtonType.OK) {
            Inventory.deletePart(tblParts.getSelectionModel().getSelectedItem());
            /* We could just setAll the mainPartList to Inventory.getAllParts(), but
               we don't want to reset the user's current search results. */
            mainPartList.setAll(Inventory.getAllParts().filtered(p -> mainPartList.contains(p)));
            btnPartModify.setDisable(true);
            btnPartDelete.setDisable(true);
        }
    }
    
    @FXML
    protected void handleProductsSearchButtonAction(ActionEvent event) {
        String search = txtProductSearch.getText();
        if(search.equals("")) {
            mainProductList.setAll(Inventory.getAllProducts());
        } else {
            mainProductList.setAll(Inventory.searchProducts(search));
        }
    }
    
    @FXML
    protected void handleProductsSearchKeyPressed(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ESCAPE)) {
            txtProductSearch.setText("");
            mainProductList.setAll(Inventory.getAllProducts());
        }
    }

    @FXML
    protected void handleProductsAddButtonAction(ActionEvent event) throws IOException {
        UI.loadStageWithData("/View/AddModifyProduct.fxml", "Add Product", 0);
        refreshProductsTable();
    }
    
    @FXML
    protected void handleProductsModifyButtonAction(ActionEvent event) throws IOException {
        UI.loadStageWithData("/View/AddModifyProduct.fxml", "Modify Product", tblProducts.getSelectionModel().getSelectedItem().getProductID());
        refreshProductsTable();
    }
    
    @FXML
    protected void handleProductsDeleteButtonAction(ActionEvent event) {
        Product product = tblProducts.getSelectionModel().getSelectedItem();
        Optional<ButtonType> result = UI.showDialog(
                "Delete Product", 
                "Really delete this product? This action cannot be undone.", 
                "ID: " + product.getProductID() + "\t\tName: " + product.getName()
        );
        if (result.get() == ButtonType.OK) {
            Inventory.deleteProduct(tblProducts.getSelectionModel().getSelectedItem());
            /* We could just setAll the mainProductList to Inventory.getAllProducts(), but
               we don't want to reset the user's current search results. */
            mainProductList.setAll(Inventory.getAllProducts().filtered(p -> mainProductList.contains(p)));
            btnPartModify.setDisable(true);
            btnPartDelete.setDisable(true);
        }
    }    
    
    @FXML
    protected void handleExitButtonAction(ActionEvent event) {
        Stage primaryStage = Main.getPrimaryStage();
        primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    
    @FXML
    protected void handleProductsTableSelected() {
        if (tblProducts.getSelectionModel().getSelectedItem() != null) {
           btnProductModify.setDisable(false);
           btnProductDelete.setDisable(false);
        } else {
           btnProductModify.setDisable(true);
           btnProductDelete.setDisable(true);
        }
    }
    
    @FXML
    protected void handlePartsTableSelected() {
        if (tblParts.getSelectionModel().getSelectedItem() != null) {
           btnPartModify.setDisable(false);
           btnPartDelete.setDisable(false); 
        } else {
           btnPartModify.setDisable(true);
           btnPartDelete.setDisable(true);
        }
    }
    
    private void refreshPartsTable() {
        mainPartList.setAll(Inventory.getAllParts());
        txtPartSearch.setText("");
        btnPartModify.setDisable(true);
        btnPartDelete.setDisable(true);
    }
    
    private void refreshProductsTable() {
        mainProductList.setAll(Inventory.getAllProducts());
        txtProductSearch.setText("");
        btnProductModify.setDisable(true);
        btnProductDelete.setDisable(true);
    }
}
