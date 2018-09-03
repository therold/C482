package Controller;

import heroldinventorysystem.Main;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UI {
    final static String ERROR_STYLE = "    -fx-border-color: red; " +
        "-fx-border-width: 1; " +
        "-fx-border-style: solid; ";
    
    public static void loadStageWithData(String file, String title, int data) throws IOException {
        FXMLLoader loader = new FXMLLoader(UI.class.getResource(file));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setScene(new Scene((Pane) loader.load()));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.getPrimaryStage());

        stage.setOnCloseRequest(e -> {
            Optional<ButtonType> result = UI.showCancelDialog(title);
            if (result.get() == ButtonType.CANCEL) {
                e.consume();
            }
        });

        ControllerWithData controller = loader.getController();
        controller.initData(title, data);
        stage.showAndWait();
    }
        
    public static Optional<ButtonType> showDialog(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert.showAndWait();
    }
        
    public static Optional<ButtonType> showDialog(String title, String header) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        return alert.showAndWait();
    }
    
    public static Optional<ButtonType> showCancelDialog(String title) {
        return UI.showDialog(
            "Cancel " + title, 
            "Are you sure you want cancel " + title + "?"
        );
    }
    
    public static void showError(String title, String header) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        Optional<ButtonType> result = alert.showAndWait();
    }
}
