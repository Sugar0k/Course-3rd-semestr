package aFiles.assistsFiles;

import aFiles.Worker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mainWindow.src.Controller;

import java.io.IOException;
import java.util.List;

import static aFiles.assistsFiles.Global.gMainTable;

public class Other {

    public static TableView initializeSecondTable(TableView tableView){
        boolean sortable = true;
        addColumnS(tableView, "Название отдела", "title", sortable);
        addColumnI(tableView, "Кол-во сотрудников", "quantity", sortable);
        addColumnI(tableView, "Средняя зарплата", "middleSalary", sortable);
        addColumnI(tableView, "Мин зарплата", "minSalary", sortable);
        addColumnI(tableView, "Макс зарплата", "maxSalary", sortable);
        return tableView;
    }

    public static TableView initializeTable(TableView tableView, boolean sortable){
        addColumnI(tableView, "ID", "id", sortable);
        addColumnS(tableView, "Фамилия", "lName", sortable);
        addColumnS(tableView, "Имя", "fName", sortable);
        addColumnS(tableView, "Отчество", "sName", sortable);
        addColumnI(tableView, "Зарплата", "salary", sortable);
        addColumnS(tableView, "Отдел", "department", sortable);
        return tableView;
    }


    public static void addColumnI(TableView tableView, String s1, String s2, boolean sortable){
        TableColumn<Worker, Integer> column = new TableColumn<>(s1);
        column.setCellValueFactory(new PropertyValueFactory<>(s2));
        column.setSortable(sortable);
        tableView.getColumns().add(column);
    }

    public static void addColumnS(TableView tableView, String s1, String s2, boolean sortable){
        TableColumn<Worker, String> column = new TableColumn<>(s1);
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

    public static int middleSalary() {
        List<Worker> list = gMainTable.getItems();
        if (list.size() == 0) return 0;
        int salary = 0;
        for (Worker wr: list) {
            salary+= wr.getSalary();
        }
        return salary / list.size();
    }
}
