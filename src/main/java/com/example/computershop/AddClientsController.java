package com.example.computershop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddClientsController  {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonOk;

    @FXML
    private Label error;

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
    private TextField tfBirth;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfPhone;

    @FXML
    private TextField tfCoupon;

    @FXML
    private TextField tfSurname;

    private Personal tmp;
    private boolean isClickedOk = false;
    private Stage dialogStage;
    private Clients client;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setClient(Clients client) {
        this.client = client;
        tfName.setText(client.getName());
        tfSurname.setText(client.getSurname());
        tfThirdname.setText(client.getThirdName());
        tfBirth.setText(client.getBirthDate());
        tfBirth.setPromptText("dd/mm/yyyy");
        tfPhone.setText(client.getMobilePhone());
        tfCoupon.setText(client.getCoupon());
    }

    @FXML
    private TextField tfThirdname;

    @FXML
    private Label title;

    @FXML
    void handleCancel(ActionEvent event) {
        dialogStage.close();
    }

    public boolean isClickedOk() {
        return isClickedOk;
    }

    @FXML
    void handleOk(ActionEvent event) {
        if (isInvalidInput()) {
            client.setName(tfName.getText());
            client.setSurname(tfSurname.getText());
            client.setThirdName(tfThirdname.getText());
            client.setMobilePhone(tfPhone.getText());
            client.setBirthDate(tfBirth.getText());
            client.setCoupon(tfCoupon.getText());
            isClickedOk = true;
            dialogStage.close();
        } else {
            error.setVisible(true);
        }
    }

    public boolean isInvalidInput() {
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
        return true;
    }

    @FXML
    void initialize() {


    }

}
