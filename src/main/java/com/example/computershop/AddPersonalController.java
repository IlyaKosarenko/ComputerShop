package com.example.computershop;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;


public class AddPersonalController {

    @FXML
    private Label title;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label label6;
    @FXML
    private Label label7;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfSurname;
    @FXML
    private TextField tfThirdname;
    @FXML
    private TextField tfBirth;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfPost;
    @FXML
    private TextField tfSalary;
    @FXML
    private Button buttonOk;
    @FXML
    private Button buttonCancel;
    @FXML
    private Label error;

    private Personal tmp;

    private boolean isClickedOk = false;

    private Stage dialogStage;

    private Personal person;

    Main main = new Main();

    @FXML
    void initalize() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setPerson(Personal person) {
        this.person = person;
        tfName.setText(person.getName());
        tfSurname.setText(person.getSurname());
        tfThirdname.setText(person.getThirdName());
        tfBirth.setText(person.getBirthDate());
        tfBirth.setPromptText("dd/mm/yyyy");
        tfPhone.setText(person.getMobilePhone());
        tfPost.setText(person.getPost());
        tfSalary.setText(Double.toString(person.getSalary()));
    }

    @FXML
    public void handleOk(ActionEvent event) throws IOException {
        if (isInvalidInput()) {
            person.setName(tfName.getText());
            person.setSurname(tfSurname.getText());
            person.setThirdName(tfThirdname.getText());
            person.setMobilePhone(tfPhone.getText());
            person.setBirthDate(tfBirth.getText());
            person.setPost(tfPost.getText());
            person.setSalary(Double.parseDouble(tfSalary.getText()));
            isClickedOk = true;
            dialogStage.close();
        } else {
            error.setVisible(true);
        }
    }

    @FXML
    public void handleCancel() {
        dialogStage.close();
    }

    public Personal getTmp() {
        return tmp;
    }

    public boolean isClickedOk() {
        return isClickedOk;
    }




    public boolean isInvalidInput() {
        String regex = "-?\\d+(\\.\\d+)?";
        if (tfName.getText() == null || tfName.getText().length() == 0) {
            return false;
        }
        if (tfSurname.getText() == null || tfSurname.getText().length() == 0) {
            return false;
        }
        if (tfThirdname.getText() == null || tfThirdname.getText().length() == 0) {
            return false;
        }
        if (tfPhone.getText() == null || tfPhone.getText().length() == 0) {
            return false;
        }
        if (tfBirth.getText() == null || tfBirth.getText().length() == 0) {
            return false;
        }
        if (tfPost.getText() == null || tfPost.getText().length() == 0) {
            return false;
        }
        if (tfSalary.getText() == null || tfSalary.getText().length() == 0 || !tfSalary.getText().matches(regex) || Double.parseDouble(tfSalary.getText()) < 0) {
            return false;
        }
        return true;
    }

}
