package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportController {
    public AnchorPane Context6;


    public void BacktoindexOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/index.fxml"));
        Scene scene = new Scene(load);
        Stage stage = (Stage) Context6.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
