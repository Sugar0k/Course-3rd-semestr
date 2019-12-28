package aFiles.assistsFiles;

import javafx.stage.FileChooser;

import java.io.*;

import static aFiles.assistsFiles.Global.*;
import static aFiles.assistsFiles.Other.chooseFile;

public class Save {
    public static void programSave(){
        if (gCurrentFile != null){
            gIsSave.setText("Сохранено");
            save(gCurrentFile);
        } else {
            File sFile =  chooseFile().showSaveDialog(gMainWindow);
            if (sFile != null) {
                gIsSave.setText("Сохранено");
                gCurrentFile = sFile;
                save(sFile);
            }
        }
    }

    public static void programSaveAs() {
        FileChooser chooser = chooseFile();
        if (gCurrentFile != null) chooser.setInitialDirectory(gCurrentFile.getParentFile());
        File sFile = chooser.showSaveDialog(gMainWindow);

        if (sFile != null){
            gIsSave.setText("Сохранено");
            gCurrentFile = sFile;
            save(sFile);
        }
        else gFileLocation.setText("Файл не выбран");
    }

    static void save(File file) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file.getAbsolutePath()));
            outputStream.writeObject(gCompany);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gFileLocation.setText(file.getAbsolutePath());
    }
}
