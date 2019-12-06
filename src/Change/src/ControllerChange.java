package Change.src;

import java.net.URL;
import java.util.ResourceBundle;

import aFiles.assistsFiles.TCell;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static aFiles.assistsFiles.Global.*;

public class ControllerChange {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ID;

    @FXML
    private TextField lName;

    @FXML
    private TextField fName;

    @FXML
    private TextField sName;

    @FXML
    private TextField salary;

    @FXML
    private TextField department;

    @FXML
    private Button changeT;

    @FXML
    private Button changeF;

    private Stage dialogStage;
    private TCell tCell;
    private boolean okClicked = false;
    private boolean chek = false;

    @FXML
    private void initialize() {
        changeT.setOnAction(actionEvent -> {
            if (isInputValid()){
                handleOk();
            }
        });

        changeF.setOnAction(actionEvent -> {
            handleCancel();
        });
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void settCell(TCell tCell, boolean b) {
        chek = b;
        this.tCell = tCell;
        fName.setText(tCell.getFName());
        lName.setText(tCell.getLName());
        sName.setText(tCell.getSName());
        ID.setText(Integer.toString(tCell.getId()));
        salary.setText(Integer.toString(tCell.getSalary()));
        department.setText(tCell.getDepartment());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        tCell.setfName(fName.getText());
        tCell.setlName(lName.getText());
        tCell.setsName(sName.getText());
        tCell.setSalary(Integer.parseInt(salary.getText()));
        tCell.setDepartment(department.getText());

        if (chek && !(tCell.id == Integer.parseInt(ID.getText()))) {
            gCompany.del(tCell.id);
            tCell.setId(Integer.parseInt(ID.getText()));
            gCompany.add(tCell);
        } else {
            tCell.setId(Integer.parseInt(ID.getText()));
        }


        okClicked = true;
        dialogStage.close();
    }



    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if ((fName.getText() == null || fName.getText().length() == 0) ||
                (lName.getText() == null || lName.getText().length() == 0) ||
                (sName.getText() == null || sName.getText().length() == 0) ||
                (department.getText() == null || department.getText().length() == 0)){
            errorMessage = "Не корректные данные!";
        }

        if ((ID.getText() == null || ID.getText().length() == 0) ||
                (salary.getText() == null || salary.getText().length() == 0)){
            errorMessage = "Не корректные данные!";
        } else {
            try {
                Integer.parseInt(ID.getText());
                Integer.parseInt(salary.getText());
            } catch (NumberFormatException e) {
                errorMessage = "Не корректные данные!";
            }
        }



        try {
            if (!(tCell.id == Integer.parseInt(ID.getText())))
                if (chek && gCompany.contains(Integer.parseInt(ID.getText()))) {
                    errorMessage = "Требуеться уникальный ID!";
                }
        } catch (Exception e) {
            errorMessage = "Не корректные данные!";
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
