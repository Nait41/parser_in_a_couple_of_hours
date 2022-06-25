import javax.swing.JFrame;
import java.awt.FileDialog;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
public class OpenDocument extends JFrame{
    String path;
    public OpenDocument() throws IOException {
            FileDialog Open = new FileDialog(this, null, FileDialog.LOAD);
            Open.setFile("*.TXT");
            Open.setVisible(true);
            String NameFile = Open.getDirectory() + Open.getFile();
            path = NameFile;
    }
}
