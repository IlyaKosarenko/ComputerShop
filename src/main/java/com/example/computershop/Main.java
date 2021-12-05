package com.example.computershop;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.*;

public class Main extends Application {
    private static Stage stg;
    ObservableList<Personal> personal = FXCollections.observableArrayList();
    ObservableList<Clients> clients = FXCollections.observableArrayList();
    ObservableList<Goods> goods = FXCollections.observableArrayList();

    public ObservableList<Personal> getPersonalData() {
        return personal;
    }

    public ObservableList<Clients> getClientsData() {
        return clients;
    }

    public ObservableList<Goods> getGoodsData() { return goods; }


    File file1 = new File("personal.txt");
    File file2 = new File("clients.txt");
    File file3 = new File("goods.txt");

    @Override
    public void start(Stage primaryStage) throws IOException {
        file1.createNewFile();
        file2.createNewFile();
        file3.createNewFile();
        stg = primaryStage;
        primaryStage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("tablepersonal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Компьютерный магазин");
        primaryStage.getIcons().add(new Image("shop icon.png"));
        primaryStage.show();
    }

    public String getPersonalPath() {
        return file1.getAbsolutePath();
    }

    public String getClientsPath() {
        return file2.getAbsolutePath();
    }

    public String getGoodsPath() {
        return file3.getAbsolutePath();
    }

    public boolean changeSceneToAddPersonal(Personal person)  {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("addpersonal.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактировать персонал");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stg);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            AddPersonalController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);
            dialogStage.showAndWait();
            
            return controller.isClickedOk();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changeSceneToAddClients(Clients client)  {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("addclients.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактировать клиентов");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stg);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            AddClientsController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setClient(client);
            dialogStage.showAndWait();
            return controller.isClickedOk();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changeSceneToAddGoods(Goods good)  {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("addgoods.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактировать товары");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stg);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            AddGoodsController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setGood(good);
            dialogStage.showAndWait();
            return controller.isClickedOk();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Stage getPrimaryStage() {
        return stg;
    }

    public void changeSceneToPersonal() throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("tablepersonal.fxml"));
        stg.getScene().setRoot(pane);
    }

    public void changeSceneToClients() throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("tableclients.fxml"));
        stg.getScene().setRoot(pane);
    }

    public void changeSceneToGoods() throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("tablegoods.fxml"));
        stg.getScene().setRoot(pane);
    }


    public static void main(String[] args) {
        launch();
    }
}