package aFiles.assistsFiles;

import aFiles.Company;
import aFiles.Worker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Global {
    public static Company gCompany = new Company();
    public static List<Worker> gTrashList = new LinkedList();
    public static Filters gFilter = new Filters(gCompany.getMap());

    public static TableView gMainTable;
    public static TableView gTrashTable;
    public static TableView gSecondTable;

    public static Stage gMainWindow;
    public static Stage gBinWindow;

    public static File gCurrentFile;

    public static Label gIsSave;
    public static Label gFileLocation;

}
