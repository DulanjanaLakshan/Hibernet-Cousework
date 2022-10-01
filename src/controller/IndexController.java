package controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexController {
    public AnchorPane Context2;
    public ImageView imgStudent;
    public ImageView imgCourse;
    public ImageView imgStudentReport;
    public ImageView imgPayment;

    public void initialize(URL url, ResourceBundle rb){
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000),Context2);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    public void openloginformOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/Startup.fxml"));
        Scene scene = new Scene(load);
        Stage stage = (Stage) Context2.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void navigate(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            Parent root = null;

            switch (icon.getId()) {
                case "imgStudent":
                    root = FXMLLoader.load(this.getClass().getResource("/view/Studentform.fxml"));
                    break;
                case "imgCourse":
                    root = FXMLLoader.load(this.getClass().getResource("/view/Courseform.fxml"));
                    break;
                case "imgPayment":
                    root = FXMLLoader.load(this.getClass().getResource("/view/payment.fxml"));
                    break;
                case "imgStudentReport":
                    root = FXMLLoader.load(this.getClass().getResource("/view/report.fxml"));
                    break;
            }

            if (root != null) {
                Scene scene = new Scene(root);
                Stage primaryStage = (Stage) this.Context2.getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), scene.getRoot());
                tt.setFromX(-scene.getWidth());
                tt.setToX(0);
                tt.play();
            }
        }

    }

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView){
            ImageView icon = (ImageView) mouseEvent.getSource();

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    public void playMouseExitAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200),icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
        }
    }
}
