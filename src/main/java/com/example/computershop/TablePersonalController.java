package com.example.computershop;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


public class TablePersonalController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Personal, String> ColBirth;

    @FXML
    private TableColumn<Personal, String> ColName;

    @FXML
    private TableColumn<Personal, String> ColPhone;

    @FXML
    private TableColumn<Personal, String> ColPost;

    @FXML
    private TableColumn<Personal, Double> ColSalary;

    @FXML
    private TableColumn<Personal, String> ColThirdname;

    @FXML
    private TableColumn<Personal, String> ColSurname;

    @FXML
    public TableView<Personal> tableView;

    @FXML
    private Button ButtonAdd;

    @FXML
    private Button ButtonEdit;

    @FXML
    private Button ButtonDelete;



    Main main = new Main();
    ObservableList<String> choiseBoxList = FXCollections.observableArrayList("Персонал", "Клиенты", "Товары");
    @FXML
    ChoiceBox<String> ChoiceBox = new ChoiceBox<String>(choiseBoxList);

    @FXML
    void initialize() {
        Deserealization();
        ColBirth.setCellValueFactory(new PropertyValueFactory<Personal, String>("BirthDate"));
        ColName.setCellValueFactory(new PropertyValueFactory<Personal, String>("Name"));
        ColSurname.setCellValueFactory(new PropertyValueFactory<Personal, String>("Surname"));
        ColThirdname.setCellValueFactory(new PropertyValueFactory<Personal, String>("ThirdName"));
        ColPhone.setCellValueFactory(new PropertyValueFactory<Personal, String>("MobilePhone"));
        ColPost.setCellValueFactory(new PropertyValueFactory<Personal, String>("post"));
        ColSalary.setCellValueFactory(new PropertyValueFactory<Personal, Double>("salary"));
        tableView.setItems(main.getPersonalData());
        ChoiceBox.getItems().addAll(choiseBoxList);
        ChoiceBox.setValue("Персонал");
        ChoiceBox.setOnAction(event -> {
            if(ChoiceBox.getValue() == "Клиенты") {
                try {
                    main.changeSceneToClients();
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
        tableView.setItems(main.getPersonalData());
    }

    @FXML
    public void handleAdd() throws IOException {
        Personal tempPerson = new Personal();
        boolean OkClicked = main.changeSceneToAddPersonal(tempPerson);
        if (OkClicked) {
            main.getPersonalData().add(tempPerson);
            Serialization();
        }
    }

    @FXML
    public void handleDelete() {
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
    public void handleEdit() {
        Personal selectedPerson = tableView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = main.changeSceneToAddPersonal(selectedPerson);
            if (okClicked) {
                tableView.getItems().remove(selectedPerson);
                main.getPersonalData().add(selectedPerson);
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
    public void Serialization() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(main.getPersonalPath(), Charset.defaultCharset()))) {
            for(int i = 0; i < main.getPersonalData().size(); i++) {
                bw.write(String.valueOf(main.getPersonalData().get(i).getName()));
                bw.newLine();
                bw.write(String.valueOf(main.getPersonalData().get(i).getSurname()));
                bw.newLine();
                bw.write(String.valueOf(main.getPersonalData().get(i).getThirdName()));
                bw.newLine();
                bw.write(String.valueOf(main.getPersonalData().get(i).getBirthDate()));
                bw.newLine();
                bw.write(String.valueOf(main.getPersonalData().get(i).getMobilePhone()));
                bw.newLine();
                bw.write(String.valueOf(main.getPersonalData().get(i).getPost()));
                bw.newLine();
                bw.write(String.valueOf(main.getPersonalData().get(i).getSalary()));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Deserealization() {
        String line, name, surname, thirdname, birth, phone, post;
        double salary;
        try (BufferedReader br = new BufferedReader(new FileReader(main.getPersonalPath(), Charset.defaultCharset()))) {
            while ((line = br.readLine()) != null) {
                if(line.equals("")) {
                    break;
                }
                name = line;
                surname = br.readLine();
                thirdname = br.readLine();
                birth = br.readLine();
                phone = br.readLine();
                post = br.readLine();
                salary = Double.parseDouble(br.readLine());
                main.getPersonalData().add(new Personal(name, surname, thirdname, birth, phone, post, salary));
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}