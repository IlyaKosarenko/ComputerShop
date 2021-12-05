package com.example.computershop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddGoodsController {

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
    private TextField tfCost;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfType;

    @FXML
    private TextField tfQuanity;

    @FXML
    private Label title;

    private boolean isClickedOk = false;
    private Stage dialogStage;
    private Goods good;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setGood(Goods good) {
        this.good = good;
        tfName.setText(good.getName());
        tfType.setText(good.getType());
        tfCost.setText(Double.toString(good.getCost()));
        tfQuanity.setText(Integer.toString(good.getQuanity()));
    }


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
            good.setName(tfName.getText());
            good.setType(tfType.getText());
            good.setCost(Double.parseDouble(tfCost.getText()));
            good.setQuanity(Integer.parseInt(tfQuanity.getText()));
            isClickedOk = true;
            dialogStage.close();
        } else {
            error.setVisible(true);
        }
    }

    public boolean isInvalidInput() {
        String regex = "-?\\d+(\\.\\d+)?";
        String regex1 = "\\d+";
        if (tfName.getText() == null || tfName.getText().length() == 0) {
            return false;
        }
        if (tfType.getText() == null || tfType.getText().length() == 0) {
            return false;
        }
        if (tfCost.getText() == null || tfCost.getText().length() == 0 || !tfCost.getText().matches(regex) || Double.parseDouble(tfCost.getText()) < 0) {
            return false;
        }
        if (tfQuanity.getText() == null || tfQuanity.getText().length() == 0 || !tfQuanity.getText().matches(regex1) || Integer.parseInt(tfQuanity.getText()) < 0) {
            return false;
        }
        return true;
    }

    @FXML
    void initialize() {


    }

}
