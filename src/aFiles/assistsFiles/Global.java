package aFiles.assistsFiles;

import aFiles.Company;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Global {
    public static Company gCompany = new Company();
    public static List<TCell> gTrashList = new LinkedList();

    public static TableView gMainTable;
    public static TableView gTrashTable;

    public static Stage gMainWindow;
    public static Stage gBinWindow;
    public static Stage gChangeWindow;

    public static File gCurrentFile;

    public static Label gIsSave;
    public static Label gFileLocation;

    public static TCell gToChange;

}