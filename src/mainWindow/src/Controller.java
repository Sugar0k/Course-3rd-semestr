package mainWindow.src;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import Change.src.ControllerChange;
import aFiles.Company;
import aFiles.Department;
import aFiles.Worker;
import aFiles.assistsFiles.Filters;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;


import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.WindowEvent;
import sample.Main;
import javafx.stage.Stage;

import static aFiles.assistsFiles.Global.*;
import static aFiles.assistsFiles.Other.*;
import static aFiles.assistsFiles.Save.programSave;
import static aFiles.assistsFiles.Save.programSaveAs;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem programOpen;

    @FXML
    private MenuItem programSave;

    @FXML
    private MenuItem programSaveAs;

    @FXML
    private MenuItem programClose;

    @FXML
    private TextField addLName;

    @FXML
    private TextField addFName;

    @FXML
    private TextField addSName;

    @FXML
    private TextField addID;

    @FXML
    private TextField addSalary;

    @FXML
    private TextField addDepartment;

    @FXML
    private Button addWorker;

    @FXML
    private Label forError;

    @FXML
    private TextField bottomSalary;

    @FXML
    private TextField topSalary;

    @FXML
    private CheckBox salaryCheck;

    @FXML
    private TextField bottomID;

    @FXML
    private TextField topID;

    @FXML
    private CheckBox iDCheck;

    @FXML
    private TextField filterFName;

    @FXML
    private CheckBox fNameCheck;

    @FXML
    private TextField filterLName;

    @FXML
    private CheckBox lNameCheck;

    @FXML
    private TextField filterSName;

    @FXML
    private CheckBox sNameCheck;

    @FXML
    private TextField filterDepartment;

    @FXML
    private CheckBox departmentCheck;

    @FXML
    private Button useFilters;

    @FXML
    private Button useDefault;

    @FXML
    private Button clearFields;

    @FXML
    private TableView<Worker> tableView;

    @FXML
    private TableView<Department> secondTable;

    @FXML
    private Label isSave;

    @FXML
    private Label fileLocation;

    @FXML
    private Button riseUp;

    @FXML
    private Button deleteWorker;

    @FXML
    private Button deleteAll;

    @FXML
    private Label statistic;


    @FXML
    void initialize() {

        gMainTable = initializeTable(tableView, true);
        gSecondTable = initializeSecondTable(secondTable);
        gIsSave = isSave;
        gFileLocation = fileLocation;
        gStatistic = statistic;

        useFilters.setOnAction(actionEvent -> {
            Filters filter = gFilter;
            filter.setMap(gCompany.getMap());
                    //new Filters(gCompany.getMap());

            int bS = 0, tS = 0, bID = 0, tID = 0;
            String fName = "", sName = "", lName = "", department = "";
            try {
                bS = Integer.parseInt(bottomSalary.getText());
                tS = Integer.parseInt(topSalary.getText());
            } catch (Exception e) {}
            try {
                bID = Integer.parseInt(bottomID.getText());
                tID = Integer.parseInt(topID.getText());
            } catch (Exception e) {}

            fName = filterFName.getText();
            lName = filterLName.getText();
            sName = filterSName.getText();
            department = filterDepartment.getText();

            filter.setSalary(bS, tS)
                    .setID(bID, tID)
                    .setFName(fName)
                    .setLName(lName)
                    .setSName(sName)
                    .setDepartment(department);

            if (salaryCheck.isSelected()) filter.addSalary();
            if (iDCheck.isSelected()) filter.addSelectedID();
            if (fNameCheck.isSelected()) filter.addFName();
            if (lNameCheck.isSelected()) filter.addLName();
            if (sNameCheck.isSelected()) filter.addSName();
            if (departmentCheck.isSelected()) filter.addDepartment();
            if (filter.isFiltered()) {
                Set<Worker> set = filter.getSet();
                filter.clearFilters();
                //           System.out.println(set);
                tableView.getItems().clear();
                tableView.getItems().setAll(set);
                tableView.refresh();
                for (Worker wr : set) System.out.println(wr);
            } else {
                tableView.getItems().clear();
                tableView.getItems().setAll(gCompany.getMap().values());
                tableView.refresh();
            }
            statistic.setText(Integer.toString(middleSalary()));
        });

        useDefault.setOnAction(actionEvent -> {
            tableView.getItems().clear();
            tableView.getItems().setAll(gCompany.getMap().values());
            tableView.refresh();
            statistic.setText(Integer.toString(middleSalary()));
        });

        clearFields.setOnAction(actionEvent -> {
            bottomSalary.clear();
            topSalary.clear();
            bottomID.clear();
            topID.clear();
            filterFName.clear();
            filterLName.clear();
            filterSName.clear();
            filterDepartment.clear();
            salaryCheck.setSelected(false);
            iDCheck.setSelected(false);
            fNameCheck.setSelected(false);
            lNameCheck.setSelected(false);
            sNameCheck.setSelected(false);
            departmentCheck.setSelected(false);
        });

        programClose.setOnAction(actionEvent -> {
            Main.programClose(gMainWindow);
        });

        gMainWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
                Main.programClose(gMainWindow);
            }
        });

        gMainTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int selectedIndex = gMainTable.getSelectionModel().getSelectedIndex();
                if (mouseEvent.getClickCount() == 2 && (selectedIndex >= 0)){
                    Worker cell = (Worker) gMainTable.getItems().get(selectedIndex);
                    if (cell != null){
                        FXMLLoader loader = new FXMLLoader();
                        Stage dialogStage = createCW(gMainWindow, loader);
                        //gChangeWindow = newWindow2;
                        ControllerChange controller = loader.getController();
                        controller.setDialogStage(dialogStage);
                        controller.setWorker((Worker) gMainTable.getItems().get(selectedIndex), true);
                        dialogStage.showAndWait();
                        statistic.setText(Integer.toString(middleSalary()));
                        gMainTable.refresh();
                    }
                }
            }

        });


        riseUp.setOnAction(actionEvent -> {
            if(gBinWindow.isShowing()){
                gBinWindow.setIconified(false);
                gBinWindow.requestFocus();
            } else gBinWindow.show();
        });

        addWorker.setOnAction(actionEvent -> {
            try {
                Worker temp = new Worker(Integer.parseInt(addID.getText()),
                        addLName.getText(),
                        addFName.getText(),
                        addSName.getText(),
                        Integer.parseInt(addSalary.getText()),
                        addDepartment.getText());
                if (gCompany.add(temp)){
                    isSave.setText("Не сохранено");
                    if (gCompany.getDep(temp.department).getQuantity() == 1) {
                        gSecondTable.getItems().add(gCompany.getDep(temp.department));
                    }
                    else {
                        gSecondTable.refresh();
                    }
                    gMainTable.getItems().add(temp);
                    forError.setText("");
                    statistic.setText(Integer.toString(middleSalary()));

                } else {
                    forError.setText("ID должен быть уникальным");
                }
            } catch (Exception e) {
                forError.setText("Некорректные данные");
            }
        });

        deleteWorker.setOnAction(actionEvent -> {
            int selectedIndex = gMainTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                isSave.setText("Не сохранено");
                Worker temp = (Worker) gMainTable.getItems().get(selectedIndex);
                gTrashList.add(temp);
                gTrashTable.getItems().add(temp);
                Department dep = gCompany.getDep(temp.department);
                gCompany.del(temp);
                gMainTable.getItems().remove(selectedIndex);
                if (dep.isEmpty()){
                    gSecondTable.getItems().remove(dep);
                } else {
                    gSecondTable.refresh();
                }
                statistic.setText(Integer.toString(middleSalary()));

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(gMainWindow);
                alert.setTitle("No Selection");
                alert.setHeaderText("Ничего не выбрано");
                alert.setContentText("Пожалуйста выберите нужного рабочего в таблице.");
                alert.showAndWait();
            }
        });

        deleteAll.setOnAction(actionEvent -> {
            List<Worker> list = gMainTable.getItems();
            if (list.isEmpty()) return;
            for (Worker wr: list) {
                Department dep = gCompany.getDep(wr.department);
                gTrashList.add(wr);
                gTrashTable.getItems().add(wr);
                gCompany.del(wr);
                if (dep.isEmpty()) gSecondTable.getItems().remove(dep);
            }
            gMainTable.getItems().clear();
            gSecondTable.refresh();
            statistic.setText(Integer.toString(middleSalary()));
        });

        programOpen.setOnAction(actionEvent -> {
            File sFile = chooseFile().showOpenDialog(gMainWindow);

            if (sFile != null){
                try {
                    gCurrentFile = sFile;
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(sFile.getAbsolutePath()));
                    gCompany = (Company) inputStream.readObject();
                    gMainTable.getItems().clear();
                    for (Worker wr : gCompany.getMap().values()) {
                        gSecondTable.getItems().add(gCompany.getDep(wr.department));
                        gMainTable.getItems().add(wr);
                    }
                    isSave.setText("Сохранено");
                    statistic.setText(Integer.toString(middleSalary()));

                } catch (StreamCorruptedException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(gMainWindow);
                    alert.setTitle("Ошибка при открытии файла");
                    alert.setHeaderText("Файл повреждён");
                    alert.setContentText("Пожалуйста выберите другой файл\n");
                    alert.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                fileLocation.setText(sFile.getAbsolutePath());

            } else {
                fileLocation.setText("Файл не выбран");
            }
        });

        // Save methods
        programSave.setOnAction(actionEvent -> {
            programSave();
        });

        programSaveAs.setOnAction(actionEvent -> {
            programSaveAs();
        });

    }

}