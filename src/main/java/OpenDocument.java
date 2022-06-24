import javax.swing.JFrame;
import java.awt.FileDialog;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
public class OpenDocument extends JFrame{
    public OpenDocument() throws FileNotFoundException {
            FileDialog Open = new FileDialog(this, null, FileDialog.LOAD);
            Open.setFile("*.txt");
            Open.setVisible(true);
            String NameFile = Open.getDirectory() + Open.getFile();
            BufferedReader reader = new BufferedReader(new FileReader(NameFile));
            ArrayList<String> rows = new ArrayList<String>();
            String row;
            reader.readLine();
            row=reader.readLine();
            if(row.equals(SaveData.DataAboutProduct))
            {
                if(Table.Columns==Table.ColumnsForSeller)
                {
                    CurrData.SellerTable();
                    CurrData.ChangeModel();
                }
            }
            else if(row.equals(SaveData.DataAboutSeller))
            {
                if(Table.Columns==Table.ColumnsForProduct)
                {
                    CurrData.ProductTable();
                    CurrData.ChangeModel();
                }
            }
            else
            {
                reader.close();
                return;
            }
            reader.readLine();
            reader.readLine();
            reader.readLine();
            row=reader.readLine();
            while(row != null)
            {
                String[] rowSplit = row.split("\\|");
                if(rowSplit.length == Table.Columns.length)
                    rows.add(new Table.Row(rowSplit));
                row=reader.readLine();
            }
            CurrData.Data = new Table(rows);
            CurrData.Data.SetData(model);
            reader.close();
            return;
    }
}
