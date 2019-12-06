package aFiles.assistsFiles;

import aFiles.Worker;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import mainWindow.src.Controller;

import java.io.IOException;

import static aFiles.assistsFiles.Global.gBinWindow;

public class Other {

    public static TableView initializeTable(TableView tableView, boolean sortable){
        TableColumn<Integer, Worker> column = new TableColumn<>("№");
        column.setSortable(false);
        column.setVisible(false);
        column.setCellValueFactory(new PropertyValueFactory<>("index"));
        tableView.getColumns().add(column);
        //addColumnI(tableView, "№", "index");
        addColumnI(tableView, "ID", "id", sortable);
        addColumnS(tableView, "Фамилия", "lName", sortable);
        addColumnS(tableView, "Имя", "fName", sortable);
        addColumnS(tableView, "Отчество", "sName", sortable);
        addColumnI(tableView, "Зарплата", "salary", sortable);
        addColumnS(tableView, "Отдел", "department", sortable);
        return tableView;
    }

    public static void addColumnTest(TableView tableView, String s1, String s2, boolean sortable){
        Callback<TableColumn, TableCell> cellFactory =
                new Callback<TableColumn, TableCell>() {
                    public TableCell call(TableColumn p) {
                        EditingCell a = new EditingCell();
                        //a.setMinHeight(32);
                        a.setAlignment(Pos.CENTER_LEFT);
                        return a;
                    }
                };

        TableColumn column = new TableColumn<>(s1);
        column.setCellValueFactory(new PropertyValueFactory<>(s2));
        column.setCellFactory(cellFactory);

        column.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<TCell, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<TCell, String> t) {
                        ((TCell) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setfName(t.getNewValue());
                    }
                }
        );
        column.setSortable(sortable);
        tableView.getColumns().add(column);
    }

    public static void addColumnI(TableView tableView, String s1, String s2, boolean sortable){
        TableColumn<TCell, Integer> column = new TableColumn<>(s1);
        column.setCellValueFactory(new PropertyValueFactory<>(s2));
        column.setSortable(sortable);
        tableView.getColumns().add(column);
    }

    public static void addColumnS(TableView tableView, String s1, String s2, boolean sortable){
        TableColumn<TCell, String> column = new TableColumn<>(s1);
        column.setCellValueFactory(new PropertyValueFactory<>(s2));
        column.setSortable(sortable);
        tableView.getColumns().add(column);
    }

    public static FileChooser chooseFile() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Files", "*.txt", "*.bd"));
        return fc;
    }

    public static Stage createCW(Stage stage, FXMLLoader loader){
        Parent page = null;
        loader.setLocation(Controller.class.getResource("/Change/ChangeScene.fxml"));
        try {
            page = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Изменить данные");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(stage);
        dialogStage.setScene(new Scene(page));
        dialogStage.setResizable(false);
        dialogStage.setX(stage.getX() + 200);
        dialogStage.setY(stage.getY() + 100);
        return dialogStage;
    }
}
