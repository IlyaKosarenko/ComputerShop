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

public class TableGoodsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ButtonAdd;

    @FXML
    private Button ButtonEdit;

    @FXML
    private Button ButtonDelete;

    @FXML
    private TableColumn<Goods, String> ColName;

    @FXML
    private TableColumn<Goods, String> ColType;

    @FXML
    private TableColumn<Goods, Double> ColCost;

    @FXML
    private TableColumn<Goods, Integer> ColQuanity;

    @FXML
    private TableView<Goods> tableView;
    Main main = new Main();
    ObservableList<String> choiseBoxList = FXCollections.observableArrayList("Персонал", "Клиенты", "Товары");

    @FXML
    ChoiceBox<String> ChoiceBox = new ChoiceBox<String>(choiseBoxList);


    @FXML
    void handleAdd(ActionEvent event) {
        Goods tempGood = new Goods();
        boolean OkClicked = main.changeSceneToAddGoods(tempGood);
        if (OkClicked) {
            main.getGoodsData().add(tempGood);
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
        Goods selectedGood = tableView.getSelectionModel().getSelectedItem();
        if (selectedGood != null) {
            boolean okClicked = main.changeSceneToAddGoods(selectedGood);
            if (okClicked) {
                tableView.getItems().remove(selectedGood);
                main.getGoodsData().add(selectedGood);
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
        ColName.setCellValueFactory(new PropertyValueFactory<Goods, String>("name"));
        ColType.setCellValueFactory(new PropertyValueFactory<Goods, String>("type"));
        ColCost.setCellValueFactory(new PropertyValueFactory<Goods, Double>("cost"));
        ColQuanity.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("quanity"));
        tableView.setItems(main.getGoodsData());
        ChoiceBox.getItems().addAll(choiseBoxList);
        ChoiceBox.setValue("Товары");
        ChoiceBox.setOnAction(event -> {
            if(ChoiceBox.getValue() == "Персонал") {
                try {
                    main.changeSceneToPersonal();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ChoiceBox.getValue() == "Клиенты") {
                try {
                    main.changeSceneToClients();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            tableView.setItems(main.getGoodsData());
        });
    }

    public void Serialization() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(main.getGoodsPath(), Charset.defaultCharset()))) {
            for(int i = 0; i < main.getGoodsData().size(); i++) {
                bw.write(String.valueOf(main.getGoodsData().get(i).getName()));
                bw.newLine();
                bw.write(String.valueOf(main.getGoodsData().get(i).getType()));
                bw.newLine();
                bw.write(String.valueOf(main.getGoodsData().get(i).getCost()));
                bw.newLine();
                bw.write(String.valueOf(main.getGoodsData().get(i).getQuanity()));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Deserealization() {
        String line, name, type;
        Double cost;
        int quanity;
        try (BufferedReader br = new BufferedReader(new FileReader(main.getGoodsPath(), Charset.defaultCharset()))) {
            while ((line = br.readLine()) != null) {
                if(line.equals("")) {
                    break;
                }
                name = line;
                type = br.readLine();
                cost = Double.parseDouble(br.readLine());
                quanity = Integer.parseInt(br.readLine());
                main.getGoodsData().add(new Goods(type, name, cost, quanity));
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
