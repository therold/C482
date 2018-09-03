package Controller;

import Model.Inhouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import java.net.URL;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class AddModifyPartController implements Initializable, ControllerWithData {
    @FXML private AnchorPane apnRoot;
    @FXML private Button btnSave;
    @FXML private Label lblTitle;
//    @FXML private Label lblCompanyName;
//    @FXML private Label lblMachineID;
//    @FXML private TextField txtPartID;
//    @FXML private TextField txtName;
//    @FXML private TextField txtInStock;
//    @FXML private TextField txtPrice;
//    @FXML private TextField txtMax;
//    @FXML private TextField txtMin;
//    @FXML private TextField txtCompanyName;
//    @FXML private TextField txtMachineID;
//    @FXML private RadioButton rbInhouse;
//    @FXML private RadioButton rbOutsourced;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
////        txtName.setPromptText("Part Name");
////        txtCompanyName.setPromptText("Company Name");
//        TextFormatter<Integer> inStockFormatter = new TextFormatter<>(new IntegerStringConverter(), 0, Input.INTEGER_ONLY_FILTER);
//        TextFormatter<Integer> minFormatter = new TextFormatter<>(new IntegerStringConverter(), 0, Input.INTEGER_ONLY_FILTER);
//        TextFormatter<Integer> maxFormatter = new TextFormatter<>(new IntegerStringConverter(), 0, Input.INTEGER_ONLY_FILTER);
//        TextFormatter<Integer> machineIDFormatter = new TextFormatter<>(new IntegerStringConverter(), 0, Input.INTEGER_ONLY_FILTER);
//        TextFormatter<String> priceFormatter = new TextFormatter<String>(Input.DECIMAL_ONLY_FILTER);
//        txtInStock.setTextFormatter(inStockFormatter);
//        txtMin.setTextFormatter(minFormatter);
//        txtMax.setTextFormatter(maxFormatter);
//        txtMachineID.setTextFormatter(machineIDFormatter);
//        txtPrice.setTextFormatter(priceFormatter);
//        
//        txtName.focusedProperty().addListener((o, ov, nv) -> lostFocus(nv, isNameValid(), txtName,
//            "Invalid Part Name", "WARNING: Please enter a Part Name."));
//        txtInStock.focusedProperty().addListener((o, ov, nv) -> lostFocus(nv, isInStockValid(), txtInStock,
//            "Invalid Inventory Level", getInStockErrorBody()));
//        txtMin.focusedProperty().addListener((o, ov, nv) -> lostFocus(nv, isMinMaxValid(), txtMin,
//            "Invalid Minimum Inventory Level", "WARNING: Minimum inventory level must be less than the minimum inventory level."));
//        txtMax.focusedProperty().addListener((o, ov, nv) -> lostFocus(nv, isMinMaxValid(), txtMax,
//            "Invalid Maximum Inventory Level", "WARNING: Maximum inventory level must be greater than the minimum inventory level."));
//        txtCompanyName.focusedProperty().addListener((o, ov, nv) -> lostFocus(nv, isCompanyNameValid(), txtCompanyName,
//            "Invalid Company Name", "WARNING: Please enter a Company Name."));
    }
    
    public void initData(String title, int partID) {
        lblTitle.setText("Add Batch");
        if(partID == 0) {
            // Adding New part
//            txtPartID.setPromptText("Auto Gen - Disabled");
//            
//            txtPrice.setText("0.00");
        } else {
            // Modifying existing part
//            Part part = Inventory.lookupPart(partID);
//            txtPartID.setText(Integer.toString(part.getPartID()));
//            txtName.setText(part.getName());
//            txtInStock.setText(Integer.toString(part.getInStock()));
//            txtPrice.setText(Double.toString(part.getPrice()));
//            txtMax.setText(Integer.toString(part.getMax()));
//            txtMin.setText(Integer.toString(part.getMin()));
            
//            if(part instanceof Inhouse) {
//                rbInhouse.setSelected(true);
//                
//                lblMachineID.setVisible(true);
//                txtMachineID.setVisible(true);
//                lblCompanyName.setVisible(false);
//                txtCompanyName.setVisible(false);
//                
//                txtMachineID.setText(Integer.toString(((Inhouse)part).getMachineID()));
//            } else if (part instanceof Outsourced) {
//                rbOutsourced.setSelected(true);
//                
//                lblMachineID.setVisible(false);
//                txtMachineID.setVisible(false);
//                lblCompanyName.setVisible(true);
//                txtCompanyName.setVisible(true);
//                
//                txtCompanyName.setText(((Outsourced)part).getCompanyName());
//            }
        }
        checkCanSave();
    }
    
    @FXML
    protected void handleInHouseRadioButtonSelected() {
//        lblMachineID.setVisible(true);
//        txtMachineID.setVisible(true);
//        lblCompanyName.setVisible(false);
//        txtCompanyName.setVisible(false);
        checkCanSave();
                
    }
    
    @FXML
    protected void handleOutsourcedRadioButtonSelected() {
//        lblMachineID.setVisible(false);
//        txtMachineID.setVisible(false);
//        lblCompanyName.setVisible(true);
//        txtCompanyName.setVisible(true);
        checkCanSave();
    }
    
    @FXML
    protected void handleSave(ActionEvent event) {        
//        if (txtPartID.getText().equals("") && rbInhouse.isSelected()) {
//            // Adding new Inhouse part.
//            int nextPartID = getNextPartID();
//            Inhouse part = parseInhouse(nextPartID);
//            Inventory.addPart(part);
//        } else if (txtPartID.getText().equals("") && rbOutsourced.isSelected()) {
//            // Adding new Outsourced part.
//            int nextPartID = getNextPartID();
//            Outsourced part = parseOutsourced(nextPartID);
//            Inventory.addPart(part);
//        } else if (rbInhouse.isSelected()) {
//            // Updating existing Inhouse part.
//            int partID = Integer.parseInt(txtPartID.getText());
//            Part part = Inventory.lookupPart(partID);
//            if (part instanceof Outsourced) {
//                /* Per TaskStream H.2.pt1 we need to allow the user to change
//                   a part from Outsourced -> Inhouse on Modify. */
//                Inhouse partConverted = parseInhouse(partID);
//                Inventory.deletePart(part);
//                Inventory.addPart(partConverted);
//                Inventory.getAllParts().sort((p1, p2) -> Integer.compare(p1.getPartID(), p2.getPartID()));
//            } else {
//                // Just updating an Inhouse part.
//                ((Inhouse)part).setAll(parseInhouse(partID));
//            }
//        } else if (rbOutsourced.isSelected()) {
//            // Updating existing Outsourced part;
//            int partID = Integer.parseInt(txtPartID.getText());
//            Part part = Inventory.lookupPart(partID);
//             if (part instanceof Inhouse) {
//                /* Per TaskStream H.2.pt1 we need to allow the user to change
//                   a part from Inhouse -> Outsourced on Modify. */
//                Outsourced partConverted = parseOutsourced(partID);
//                Inventory.deletePart(part);
//                Inventory.addPart(partConverted);
//                Inventory.getAllParts().sort((p1, p2) -> Integer.compare(p1.getPartID(), p2.getPartID()));
//             } else {
//                // Just updating an Outsourced part.
//                ((Outsourced)part).setAll(parseOutsourced(partID));
//             }
//         }
        closeWindow();
    }
    
    @FXML
    protected void handleCancel(ActionEvent event) {
        Optional<ButtonType> result = UI.showCancelDialog(lblTitle.getText());
        if(result.get() == (ButtonType.OK)) {
            closeWindow();
        }
    }
    
    private int getNextPartID() {
        int nextPartID;
        if(Inventory.getAllParts().size() == 0) {
            nextPartID = 1;
        } else {
            nextPartID = Inventory.getAllParts().stream()
                .max((Comparator.comparingInt(Part::getPartID))).get().getPartID() + 1;
        }
        return nextPartID;
    }
    
    private void closeWindow() {
        Stage stage = (Stage) apnRoot.getScene().getWindow();
        stage.close();
    }
    
    public void checkCanSave(KeyEvent k) {
        checkCanSave();
    }

    public void checkCanSave() {
//        String name = txtName.getText();
//        int inStock = Integer.parseInt(txtInStock.getText());
//        int min = Integer.parseInt(txtMin.getText());
//        int max = Integer.parseInt(txtMax.getText());
//        String companyName = txtCompanyName.getText();
//        if (rbOutsourced.isSelected() && (!name.equals("") && min <= max && inStock >= min && inStock <= max && !companyName.equals(""))) {
//            btnSave.setDisable(false);
//        } else if (!name.equals("") && min <= max && inStock >= min && inStock <= max) {
//            btnSave.setDisable(false);
//        } else {
//            btnSave.setDisable(true);
//        }
    }
    
//    private Inhouse parseInhouse(int partID) {
//        String name = txtName.getText();
//        double price = Double.parseDouble(txtPrice.getText());
//        int min = Integer.parseInt(txtMin.getText());
//        int max = Integer.parseInt(txtMax.getText());
//        int inStock = Integer.parseInt(txtInStock.getText());
//        int machineID = Integer.parseInt(txtMachineID.getText());
//        return new Inhouse(partID, name, price, inStock, min, max, machineID);
//    }
    
//    private Outsourced parseOutsourced(int partID) {
//        String name = txtName.getText();
//        double price = Double.parseDouble(txtPrice.getText());
//        int inStock = Integer.parseInt(txtInStock.getText());
//        int min = Integer.parseInt(txtMin.getText());
//        int max = Integer.parseInt(txtMax.getText());
//        String companyName = txtCompanyName.getText();
//        return new Outsourced(partID, name, price, inStock, min, max, companyName);
//    }
    
//    public boolean isInStockValid() {
//        int inStock = Integer.parseInt(txtInStock.getText());
//        int min = Integer.parseInt(txtMin.getText());
//        int max = Integer.parseInt(txtMax.getText());
//        return (inStock >= min && inStock <= max);
//    }
//    
//    public boolean isMinMaxValid() {
//        int min = Integer.parseInt(txtMin.getText());
//        int max = Integer.parseInt(txtMax.getText());
//        return (max >= min);
//    }
//    
//    private boolean isNameValid() {
//        return !txtName.getText().equals("");
//    }
//    
//    private boolean isCompanyNameValid() {
//        return !txtCompanyName.getText().equals("");
//    }
//    
//    private String getInStockErrorBody() {
//        int min = Integer.parseInt(txtMin.getText());
//        int max = Integer.parseInt(txtMax.getText());
//        int inStock = Integer.parseInt(txtInStock.getText());
//        if (inStock < min) {
//            return "WARNING: Inventory level must be greater than minimum level.";
//        } else if (inStock > max) {
//            return "WARNING: Inventory level must be less than maximum inventory level.";
//        } else {
//            return "";
//        }
//    }
    
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
}
