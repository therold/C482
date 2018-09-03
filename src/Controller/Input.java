package Controller;

import java.util.function.UnaryOperator;
import javafx.beans.property.ReadOnlyBooleanPropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class Input {
    public static UnaryOperator<TextFormatter.Change> INTEGER_ONLY_FILTER = change -> {
        // accept only integers
        if (!(change.getControlNewText()).matches("\\d{0,7}?")) {
            return null;
        }
        // default to 0 if input is empty
        if (change.getControlNewText().isEmpty()) {
            change.setRange(0, change.getControlText().length());
            change.setText("0");
        }
        return change;
    };
    
    public static UnaryOperator<TextFormatter.Change> DECIMAL_ONLY_FILTER = change -> {
        // accept only 2 decimal places
        if (!(change.getControlNewText()).matches("\\d{0,7}([\\.]\\d{0,2})?")) {
            return null;
        }
        // default to 0.00 if input is empty
        if (change.getControlNewText().isEmpty()) {
            change.setRange(0, change.getControlText().length());
            change.setText("0.00");
        }
        return change;
    };
    
    public static ChangeListener focusListener = (o, ov, nv) -> {
        if(!(boolean)nv) {
            System.out.println("lost focus");
            TextField tf = (TextField)((ReadOnlyBooleanPropertyBase)o).getBean();
            System.out.println(tf.getText());
        }
//        if(!nv) {
//            TextField tf = (TextField)((ReadOnlyBooleanPropertyBase)o).getBean();
//            System.out.println(o);
//        }
    };
}

