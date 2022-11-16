/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.viewBloc;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author erick
 */
public class BlocAdmin {

    private String openFilePath;
    private File openFile = null;
    private boolean editing = false;

    private File chooseFile() {
        String userhome = System.getProperty("user.home");
        JFileChooser chooser = new JFileChooser(userhome + "/Desktop");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        chooser.setFileFilter(filter);

        int re = chooser.showOpenDialog(null);

        if (re != JFileChooser.APPROVE_OPTION) {
            return null;
        }

        return chooser.getSelectedFile();

    }

    public String saveFile(Component parent) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(filter);
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int respuesta = fc.showSaveDialog(parent);

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            return fc.getCurrentDirectory().getAbsolutePath();
        }
        return null;
    }

    void openFile() {
        File openedFile = chooseFile();

        openFilePath = openedFile.getAbsolutePath();
        openFile = openedFile;
        editing = true;
    }

    String readFile() throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader(openFile);
        BufferedReader reader = new BufferedReader(fileReader);

        String text = "";
        String line = reader.readLine();
        while (line != null) {

            text += line + System.getProperty("line.separator");
            line = reader.readLine();
        }
        reader.close();

        return text;
    }

    String getOpenedFilePath() {
        return openFilePath;
    }

    public void writeFile(String text, String AbsolutePath) throws IOException {

        File file = new File(AbsolutePath);

        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedWriter br = new BufferedWriter(new FileWriter(file));
        br.write(text);
        br.close();

    }

    void saveFile(String text) {

    }

}
