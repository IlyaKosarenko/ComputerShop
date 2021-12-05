package com.example.computershop;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableClientsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ButtonAdd;

    @FXML
    private Button ButtonDelete;

    @FXML
    private Button ButtonEdit;

    Main main = new Main();
    ObservableList<String> choiseBoxList = FXCollections.observableArrayList("Персонал", "Клиенты", "Товары");
    @FXML
    ChoiceBox<String> ChoiceBox = new ChoiceBox<String>(choiseBoxList);

    @FXML
    private TableColumn<Clients, String> ColBirth;

    @FXML
    private TableColumn<Clients, String> ColName;

    @FXML
    private TableColumn<Clients, String> ColPhone;

    @FXML
    private TableColumn<Clients, String> ColCoupon;

    @FXML
    private TableColumn<Clients, String> ColSurname;

    @FXML
    private TableColumn<Clients, String> ColThirdname;

    @FXML
    private TableView<Clients> tableView;

    @FXML
    void handleAdd(ActionEvent event) {
        Clients tempClient = new Clients();
        boolean OkClicked = main.changeSceneToAddClients(tempClient);
        if (OkClicked) {
            main.getClientsData().add(tempClient);
            Serialization();
        }
    }

    @FXML
    void handleDelete(ActionEvent event) {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tableView.getItems().remove(selectedIndex);
            Serialization();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Ошибка");
            alert.setHeaderText("Вы не выбрали строку, чтобы удалить");
            alert.setContentText("Для удаления строки выберите элемент в таблице");
            alert.showAndWait();
        }
    }

    @FXML
    void handleEdit(ActionEvent event) {
        Clients selectedClient = tableView.getSelectionModel().getSelectedItem();
        if (selectedClient != null) {
            boolean okClicked = main.changeSceneToAddClients(selectedClient);
            if (okClicked) {
                tableView.getItems().remove(selectedClient);
                main.getClientsData().add(selectedClient);
                Serialization();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Ошибка");
            alert.setHeaderText("Вы не выбрали строку, чтобы редактировать");
            alert.setContentText("Для редактирования строки выберите элемент в таблице");
            alert.showAndWait();
        }

    }

    @FXML
    void initialize() {
        Deserealization();
        ColBirth.setCellValueFactory(new PropertyValueFactory<Clients, String>("BirthDate"));
        ColName.setCellValueFactory(new PropertyValueFactory<Clients, String>("Name"));
        ColSurname.setCellValueFactory(new PropertyValueFactory<Clients, String>("Surname"));
        ColThirdname.setCellValueFactory(new PropertyValueFactory<Clients, String>("ThirdName"));
        ColPhone.setCellValueFactory(new PropertyValueFactory<Clients, String>("MobilePhone"));
        ColCoupon.setCellValueFactory(new PropertyValueFactory<Clients, String>("coupon"));
        tableView.setItems(main.getClientsData());
        ChoiceBox.getItems().addAll(choiseBoxList);
        ChoiceBox.setValue("Клиенты");
        ChoiceBox.setOnAction(event -> {
            if(ChoiceBox.getValue() == "Персонал") {
                try {
                    main.changeSceneToPersonal();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ChoiceBox.getValue() == "Товары") {
                try {
                    main.changeSceneToGoods();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        tableView.setItems(main.getClientsData());
    }
    public void Serialization() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(main.getClientsPath(), Charset.defaultCharset()))) {
            for(int i = 0; i < main.getClientsData().size(); i++) {
                bw.write(String.valueOf(main.getClientsData().get(i).getName()));
                bw.newLine();
                bw.write(String.valueOf(main.getClientsData().get(i).getSurname()));
                bw.newLine();
                bw.write(String.valueOf(main.getClientsData().get(i).getThirdName()));
                bw.newLine();
                bw.write(String.valueOf(main.getClientsData().get(i).getBirthDate()));
                bw.newLine();
                bw.write(String.valueOf(main.getClientsData().get(i).getMobilePhone()));
                bw.newLine();
                bw.write(String.valueOf(main.getClientsData().get(i).getCoupon()));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Deserealization() {
        String line, name, surname, thirdname, birth, phone, coupon;
        try (BufferedReader br = new BufferedReader(new FileReader(main.getClientsPath(), Charset.defaultCharset()))) {
            while ((line = br.readLine()) != null) {
                if(line.equals("")) {
                    break;
                }
                name = line;
                surname = br.readLine();
                thirdname = br.readLine();
                birth = br.readLine();
                phone = br.readLine();
                coupon = br.readLine();
                main.getClientsData().add(new Clients(name, surname, thirdname, birth, phone, coupon));
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

