package controller;

import business.BOFactory;
import business.BOType;
import business.custom.impl.CourseBOImpl;
import com.jfoenix.controls.JFXTextField;
import dto.CourseDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.CourseTm;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CourseformController {
    public AnchorPane Context4;
    public JFXTextField txtID;
    public JFXTextField txtTitle;
    public JFXTextField txtDuration;
    public JFXTextField txtfee;
    public TableColumn colId;
    public TableColumn colTitle;
    public TableColumn colDuration;
    public TableColumn colFee;
    public TableView <CourseTm> tblCourse;


    CourseBOImpl courseBO = BOFactory.getInstance().getBO(BOType.COURSE);

    public void initialize(){
        findAll();
        tableListener();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("Duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("salary"));

    }

    private void tableListener() {
        tblCourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(CourseTm tm) {
        try{
            txtID.setText(tm.getId());
            txtTitle.setText(tm.getTitle());
            txtDuration.setText(tm.getDuration());
            txtfee.setText(String.valueOf(tm.getSalary()));
        }catch (Exception e){

        }
    }

    private void findAll() {
        try {
            ObservableList<CourseTm> tmList = FXCollections.observableArrayList();
            List<CourseDTO> all = courseBO.findAll();
            for (CourseDTO dto : all) {
                Button btn = new Button("Delete");
                CourseTm tm = new CourseTm(
                        dto.getId(),
                        dto.getTitle(),
                        dto.getDuration(),
                        dto.getSalary()
                );
                tmList.add(tm);
                btn.setOnAction(e ->{
                    try {
                        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("NO",ButtonBar.ButtonData.CANCEL_CLOSE);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Is this ok ",ok ,no);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.orElse(no) == ok) {
                            if (courseBO.delete(tm.getId())) {
                                new Alert(Alert.AlertType.CONFIRMATION,"Deleted",ButtonType.OK).show();
                                txtID.setText(null);
                                txtTitle.setText(null);
                                txtDuration.setText(null);
                                txtfee.setText(null);
                                findAll();
                                return;
                            }
                            new Alert(Alert.AlertType.WARNING,"Try Again", ButtonType.OK).show();
                        } else {

                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } );
            }
            tblCourse.setItems(tmList);
        } catch (Exception e) {

        }
    }
public void BacktoIndexOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/index.fxml"));
        Scene scene = new Scene(load);
        Stage stage = (Stage) Context4.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void SaveBtnOnAction(ActionEvent actionEvent) {
        String id = txtID.getText();
        String title = txtTitle.getText();
        String duration = txtDuration.getText();
        double salary = Double.parseDouble(txtfee.getText());
        try{
            if (courseBO.add(new CourseDTO(
                    id,
                    title,
                    duration,
                    salary
            ))){
                txtID.setText(null);
                txtTitle.setText(null);
                txtDuration.setText(null);
                txtfee.setText(null);
                new Alert(Alert.AlertType.INFORMATION, "Added...!").show();
                findAll();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UpdateBtnOnAction(ActionEvent actionEvent) {
        String id = txtID.getText();
        String title = txtTitle.getText();
        String duration = txtDuration.getText();
        double salary = Double.parseDouble(txtfee.getText());
        try{
            if (courseBO.update(new CourseDTO(
                    id,
                    title,
                    duration,
                    salary
            ))){
                findAll();
                txtID.setText(null);
                txtTitle.setText(null);
                txtDuration.setText(null);
                txtfee.setText(null);
                findAll();

            } else {
                new Alert(Alert.AlertType.ERROR,"Something rong").show();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something rong").show();
        }
    }

    public void DeleteOnAction(ActionEvent actionEvent) throws Exception {
        boolean Coursedelete = courseBO.delete(txtID.getText());

        if (Coursedelete){
            new Alert(Alert.AlertType.CONFIRMATION,"Delete Course Program",ButtonType.OK).show();
        } else {
            new Alert(Alert.AlertType.WARNING,"Delete Error",ButtonType.OK).show();
        }
        txtID.clear();
        txtTitle.clear();
        txtDuration.clear();
        txtfee.clear();
    }
}
