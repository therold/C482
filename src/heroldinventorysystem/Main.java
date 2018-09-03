package heroldinventorysystem;

import Controller.UI;
import java.util.Optional;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStage;
    
    private void setPrimaryStage(Stage stage) {
        Main.primaryStage = stage;
    }
    
    static public Stage getPrimaryStage() {
        return Main.primaryStage;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
        Scene scene = new Scene(root);
        setPrimaryStage(stage);
        
        stage.setTitle("Inventory Management System");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> {
            Optional<ButtonType> result = UI.showDialog(
                    "Exit Inventory Manager", 
                    "Are you sure you want to exit?"
            );
            if (result.get() == ButtonType.CANCEL) {
                e.consume();
            }
        });
        stage.show();
    }
    
    

    public static void main(String[] args) {
        launch(args);
    }
    
}
