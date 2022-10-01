package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class StartupController {
    public AnchorPane Context1;


    public void OpenindexFormOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/index.fxml"));
        Scene scene = new Scene(load);
        Stage stage = (Stage) Context1.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
}
