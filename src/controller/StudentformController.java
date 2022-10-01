package controller;

import business.BOFactory;
import business.BOType;
import business.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
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
import view.tm.StudentTm;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class StudentformController {
    public AnchorPane Context3;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtNIC;
    public JFXTextField txtNumber;
    public JFXTextField txtEmail;
    public TableView <StudentTm> tblStudent;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colNIC;
    public TableColumn colEmail;
    public TableColumn colAddress;
    public TableColumn colNumber;

    StudentBOImpl studentBO = BOFactory.getInstance().getBO(BOType.STUDENT);

    public void initialize(){
        findAll();
        tableListner();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    private void tableListner() {
       tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           setData(newValue);
       });
    }

    private void setData(StudentTm tm) {
        try{
            txtId.setText(tm.getId());
            txtName.setText(tm.getName());
            txtNIC.setText(tm.getNIC());
            txtNumber.setText(tm.getContact());
            txtEmail.setText(tm.getEmail());
            txtAddress.setText(tm.getAddress());

        } catch (Exception e) {

        }
    }

    private void findAll() {
        try {
            ObservableList<StudentTm> tmlist = FXCollections.observableArrayList();
            List<StudentDTO> all = studentBO.findAll();
            for (StudentDTO dto : all){
                Button btn = new Button("Delete");
                StudentTm tm = new StudentTm(
                        dto.getId(),
                        dto.getName(),
                        dto.getNIC(),
                        dto.getContact(),
                        dto.getEmail(),
                        dto.getAddress()
                );
                tmlist.add(tm);
                btn.setOnAction((e) -> {
                    try {
                        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);

                        ButtonType no = new ButtonType("NO",ButtonBar.ButtonData.CANCEL_CLOSE);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Is this ok ",ok,no);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.orElse(no) == ok) {
                            if (studentBO.delete(tm.getId())) {
                                new Alert(Alert.AlertType.CONFIRMATION,"Deleted",ButtonType.OK).show();
                                txtId.setText(null);
                                txtName.setText(null);
                                txtNIC.setText(null);
                                txtEmail.setText(null);
                                txtAddress.setText(null);
                                findAll();
                                return;
                            }
                            new Alert(Alert.AlertType.WARNING,"Try Again",ButtonType.OK).show();
                        } else {

                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                });
            }
            tblStudent.setItems(tmlist);
        } catch (Exception e) {

        }
    }


    public void BacktoIndexPageOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/index.fxml"));
        Scene scene = new Scene(load);
        Stage stage = (Stage) Context3.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void SaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String NIC = txtNIC.getText();
        String Contact = txtNumber.getText();
        String Email = txtEmail.getText();
        String address = txtAddress.getText();

        try {
            if(studentBO.add(new StudentDTO(
                    id,
                    name,
                    NIC,
                    Contact,
                    Email,
                    address
            ))){
                txtId.setText(null);
                txtName.setText(null);
                txtNIC.setText(null);
                txtNumber.setText(null);
                txtEmail.setText(null);
                txtAddress.setText(null);
                new Alert(Alert.AlertType.INFORMATION, "Added...!").show();
                findAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "This is a Problem").showAndWait();
        }
    }

    public void UpdateOnAction(ActionEvent actionEvent) {

        String id = txtId.getText();
        String name = txtName.getText();
        String NIC = txtNIC.getText();
        String Contact = txtNumber.getText();
        String Email = txtEmail.getText();
        String address = txtAddress.getText();
        try {
            if (studentBO.update(new StudentDTO(
                    id,
                    name,
                    NIC,
                    Contact,
                    Email,
                    address
            ))){
                findAll();
                txtId.setText(null);
                txtName.setText(null);
                txtNIC.setText(null);
                txtNumber.setText(null);
                txtEmail.setText(null);
                txtAddress.setText(null);
                findAll();
            } else {
                new Alert(Alert.AlertType.ERROR,"Something rong").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something rong").show();
        }
    }

    public void DeleteOnAction(ActionEvent actionEvent) throws Exception {
        boolean Studentdelete = studentBO.delete(txtId.getText());

        if (Studentdelete) {
            new Alert(Alert.AlertType.CONFIRMATION,"Delete Student",ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.WARNING,"Delete Error",ButtonType.OK).show();
        }
        txtId.clear();
        txtName.clear();
        txtNIC.clear();
        txtNumber.clear();
        txtEmail.clear();
        txtAddress.clear();
       }
    }

