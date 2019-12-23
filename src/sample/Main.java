package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

import static aFiles.assistsFiles.Global.*;
import static aFiles.assistsFiles.Save.programSave;

public class Main extends Application {


    public static void programClose(Stage stage){
        int count = 0;
        if(gMainWindow.isShowing()) count++;
        if(gBinWindow.isShowing()) count++;

        if(count == 1 && !gIsSave.getText().equals("Сохранено")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getDialogPane().setMinWidth(400);
            alert.setTitle("Закрытие приложения");
            alert.setHeaderText("Вы хотите сохранит изменения?");
            alert.setContentText("При выборе \"Не сохранять\" вы можете потерять нужые данные");

            ButtonType buttonTypeSave = new ButtonType("Сохранить");
            ButtonType buttonTypeDoNotSave = new ButtonType("Не сохранять");
            ButtonType buttonTypeCancel = new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeSave, buttonTypeDoNotSave, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeSave){
                programSave();
            } else if (result.get() == buttonTypeDoNotSave) {
                stage.close();
            } else {
                return;
            }
        } else {
            stage.close();
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        gMainWindow = primaryStage;
        Parent root1 = FXMLLoader.load(getClass().getResource("/mainWindow/sample.fxml"));
        primaryStage.setTitle("Курсовая работа Павлюк ИВТ/б-18-2-о");
        primaryStage.setScene(new Scene(root1));
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(400);
        primaryStage.show();

        Stage newWindow1 = new Stage();
        gBinWindow = newWindow1;
        Parent root2 = FXMLLoader.load(getClass().getResource("/deletedCells/secondTable.fxml"));
        newWindow1.setTitle("Удалённые файлы");
        newWindow1.setScene(new Scene(root2));
        newWindow1.setMinWidth(700);
        newWindow1.setMinHeight(400);
        newWindow1.setX(gMainWindow.getX() + 200);
        newWindow1.setY(gMainWindow.getY() + 100);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
