package mainClasses;

import java.util.ArrayList;


/**
 * Created by rocks on 10/24/2016.
 */
public class PreProcessing {


    public boolean checkStopWordsOrSpace(String keyword)
    {
        Boolean isStopword=false;
        ArrayList<String> stopWords=new ArrayList<>();
        stopWords=ImportStopwords.importStopwords();
        if(stopWords.contains(keyword))
        {
            isStopword=true;
        }
        return isStopword;
    }

    public boolean checknumbers(String keyword)
    {
        Boolean isNumber=false;
        if (keyword.matches("[1|2|3|4|5|6|7|8|9|0].*"))
        {
            isNumber=true;
        }
        return isNumber;
    }


    public static void main(String[] args) {
        PreProcessing p = new PreProcessing();

    }
}
