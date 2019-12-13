package mainWindow.src;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import Change.src.ControllerChange;
import aFiles.Company;
import aFiles.Worker;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;


import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
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
    private TableView<Worker> tableView;

    @FXML
    private Label isSave;

    @FXML
    private Label fileLocation;

    @FXML
    private Button riseUp;

    @FXML
    private Button deleteWorker;

    @FXML
    void initialize() {

        gMainTable = initializeTable(tableView, true);
        gIsSave = isSave;
        gFileLocation = fileLocation;
        programClose.setOnAction(actionEvent -> Main.programClose());

        gMainWindow.setOnCloseRequest(actionEvent -> {
            Main.programClose();
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
                        controller.settCell((Worker) gMainTable.getItems().get(selectedIndex), true);
                        dialogStage.showAndWait();
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
                    gMainTable.getItems().add(temp);
                    forError.setText("");
                } else {
                    forError.setText("ID должен быть уникальным");
                }
            } catch (Exception e) {
                forError.setText("Не корректные данные");
            }
        });

        deleteWorker.setOnAction(actionEvent -> {
            int selectedIndex = gMainTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                isSave.setText("Не сохранено");
                Worker temp = (Worker) gMainTable.getItems().get(selectedIndex);
                gTrashList.add(temp);
                gTrashTable.getItems().add(temp);
                gCompany.del(temp.id);
                gMainTable.getItems().remove(selectedIndex);

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(gMainWindow);
                alert.setTitle("No Selection");
                alert.setHeaderText("Ничего не выбрано");
                alert.setContentText("Пожалуйста выберите нужного рабочего в таблице.");
                alert.showAndWait();
            }
        });

        programOpen.setOnAction(actionEvent -> {
            File sFile = chooseFile().showOpenDialog(gMainWindow);

            if (sFile != null){
                try {
                    gCurrentFile = sFile;
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(sFile.getAbsolutePath()));
                    gCompany = (Company) inputStream.readObject();
                    gMainTable.getItems().clear();
                    for (Worker wr : gCompany.getMap().values())
                        gMainTable.getItems().add(wr);
                    isSave.setText("Сохранено");
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