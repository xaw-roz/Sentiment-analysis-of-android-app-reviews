package mainClasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by rocks on 10/24/2016.
 */
public class ImportStopwords {
    public static ArrayList<String> importStopwords()
    {
        ArrayList <String> stopwords=new ArrayList<>();
        try {
            BufferedReader inp = new BufferedReader(new FileReader("C:\\itdepart\\ITDepartmentApplication\\MiniProjectWeb\\Files\\stopwords.txt"));
            String stopword;
            while ((stopword= inp.readLine())!=null)
            {
                stopwords.add(stopword);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return stopwords;
    }
}
