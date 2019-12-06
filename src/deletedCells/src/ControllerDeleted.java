package deletedCells.src;

import java.net.URL;
import java.util.ResourceBundle;

import Change.src.ControllerChange;
import aFiles.assistsFiles.TCell;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mainWindow.src.Controller;
import sample.Main;

import javax.swing.table.TableColumn;

import static aFiles.assistsFiles.Global.*;
import static aFiles.assistsFiles.Other.*;

public class ControllerDeleted {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<TCell> tableView;

    @FXML
    private Button openMainWindow;

    @FXML
    private Button addToMain;

    @FXML
    private Button deleteFinal;

    @FXML
    void initialize() {
        gTrashTable = initializeTable(tableView, false);

        gBinWindow.setOnCloseRequest(actionEvent -> {
            Main.programClose();
        });

        gTrashTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int selectedIndex = gTrashTable.getSelectionModel().getSelectedIndex();
                if (mouseEvent.getClickCount() == 2 && (selectedIndex >= 0)){
                    TCell cell = (TCell) gTrashTable.getItems().get(selectedIndex);
                    if (cell != null){
                        FXMLLoader loader = new FXMLLoader();
                        Stage dialogStage = createCW(gBinWindow, loader);
                        ControllerChange controller = loader.getController();
                        controller.setDialogStage(dialogStage);
                        controller.settCell((TCell) gTrashTable.getItems().get(selectedIndex), false);
                        dialogStage.showAndWait();
                        gTrashTable.refresh();
                    }
                }
            }
        });

        openMainWindow.setOnAction(actionEvent -> {
            if(gMainWindow.isShowing()){
                gMainWindow.setIconified(false);
                gMainWindow.requestFocus();
            } else gMainWindow.show();
        });

        addToMain.setOnAction(actionEvent -> {
            int selectedIndex = gTrashTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                TCell temp = (TCell) gTrashTable.getItems().get(selectedIndex);
                if (gCompany.add(temp)){
                    gIsSave.setText("Не сохранено");
                    gMainTable.getItems().add(new TCell(1, temp));
                    gTrashList.remove(temp);
                    gTrashTable.getItems().remove(selectedIndex);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.initOwner(gBinWindow);
                    alert.setTitle("Не уникальный ID");
                    alert.setHeaderText("Выбраный рабочий имеет не уникальный ID");
                    alert.setContentText("Пожалуйста смените ID");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(gBinWindow);
                alert.setTitle("No Selection");
                alert.setHeaderText("Ничего не выбрано");
                alert.setContentText("Пожалуйста выберите нужного рабочего в таблице.");
                alert.showAndWait();
            }
        });

        deleteFinal.setOnAction(actionEvent -> {
            int selectedIndex = gTrashTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                TCell temp = (TCell) gTrashTable.getItems().get(selectedIndex);
                gTrashList.remove(temp);
                gTrashTable.getItems().remove(selectedIndex);

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(gBinWindow);
                alert.setTitle("No Selection");
                alert.setHeaderText("Ничего не выбрано");
                alert.setContentText("Пожалуйста выберите нужного рабочего в таблице.");
                alert.showAndWait();
            }
        });
    }


}
