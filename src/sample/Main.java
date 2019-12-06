package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static aFiles.assistsFiles.Global.*;

public class Main extends Application {

    //make same test asdasds

    public static void programClose(){
      //  if()
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        gMainWindow = primaryStage;
        Parent root1 = FXMLLoader.load(getClass().getResource("/mainWindow/sample.fxml"));
        primaryStage.setTitle("MINE LIBER PROGRAM");
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

        Parent root3 = FXMLLoader.load(getClass().getResource("/Change/ChangeScene.fxml"));
        Stage newWindow2 = new Stage();
        newWindow2.setTitle("Изменить данные");
        newWindow2.setScene(new Scene(root3));
        newWindow2.setResizable(false);
        newWindow2.setX(gMainWindow.getX() + 200);
        newWindow2.setY(gMainWindow.getY() + 100);
        //gChangeWindow = newWindow2;
    }



    public static void main(String[] args) {
        launch(args);
    }
}
