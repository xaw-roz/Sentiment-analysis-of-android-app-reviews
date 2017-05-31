package mainClasses;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rocks on 10/20/2016.
 */
public class  ImportReviews {
   public class PostitveAndNegativeCount {
        Integer positive = 0;
        Integer negative = 0;
    }
   public  HashMap<String,PostitveAndNegativeCount> frequencyTable=new HashMap<>();
    public  Integer totalPositiveWords=0;
    public Integer totalNegativeWords=0;
    public  ArrayList <ArrayList<String>> ReadAndReturnPositiveReviews()
    {
        ArrayList <ArrayList<String>> positiveReviews=new ArrayList<>();
        PreProcessing preProcessing=new PreProcessing();
        try {

            BufferedReader bufferedReader=new BufferedReader(new FileReader("C:\\itdepart\\ITDepartmentApplication\\MiniProjectWeb\\Files\\positivereviews.txt"));
            String review;

            Lemmatizer lemmatizer=new Lemmatizer();
            while ((review=bufferedReader.readLine())!=null)
            {

                review=review.toLowerCase();
//                review=jazzySpellChecker.getCorrectedLine(review);


                ArrayList <String> lemmatizedTokens=lemmatizer.returnLemma(review);
                ArrayList<String> processedTokens=new ArrayList<>();
                for (String token:lemmatizedTokens)
                {

                    if(preProcessing.checkStopWordsOrSpace(token)==false && preProcessing.checknumbers(token)==false)
                    {
                        processedTokens.add(token);
                        totalPositiveWords++;
                        if(frequencyTable.containsKey(token))
                        {
                            frequencyTable.get(token).positive= frequencyTable.get(token).positive+1;
                        }
                        else
                        {
                            PostitveAndNegativeCount postitveAndNegativeCount=new PostitveAndNegativeCount();
                            frequencyTable.put(token,postitveAndNegativeCount);
                        }
                    }

                }
                positiveReviews.add(processedTokens);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

       // System.out.println(positiveReviews);
        return positiveReviews;

    }
    public  ArrayList <ArrayList<String>> ReadAndReturnNegativeReviews() throws IOException {
        ArrayList <ArrayList<String>> negativeReviews=new ArrayList<>();

        PreProcessing preProcessing=new PreProcessing();
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader("C:\\itdepart\\ITDepartmentApplication\\MiniProjectWeb\\Files\\negativereviews.txt"));
            String review;

            Lemmatizer lemmatizer=new Lemmatizer();
            while ((review=bufferedReader.readLine())!=null)
            {
                review=review.toLowerCase();

  //              review=jazzySpellChecker.getCorrectedLine(review);
                ArrayList <String> lemmatizedTokens=lemmatizer.returnLemma(review);
                ArrayList <String> keywords=new ArrayList<>();


                String prev="";
                int count=0;
                for (String token:lemmatizedTokens) {
                    if (preProcessing.checkStopWordsOrSpace(token) == false) {
                        keywords.add(token);
                        totalNegativeWords++;
                        if(frequencyTable.containsKey(token))
                        {
                            frequencyTable.get(token).negative=frequencyTable.get(token).negative+1;
                        }
                        else
                        {
                            PostitveAndNegativeCount postitveAndNegativeCount=new PostitveAndNegativeCount();
                            frequencyTable.put(token,postitveAndNegativeCount);
                        }
                    }
//
                }
                negativeReviews.add(keywords);


            }
         //   System.out.println(negativeReviews);
        } catch (Exception e) {
            e.printStackTrace();
        }
      //  System.out.println(negativeReviews);


        //System.out.println(frequencyTable.get("fix").negative+"freqneg"+frequencyTable.get("fix").positive+"pos");

       return negativeReviews;


    }
    public void importReviewAndCountWords()
    {
        try {
            ReadAndReturnPositiveReviews();
            ReadAndReturnNegativeReviews();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ImportReviews importReviews=new ImportReviews();

        try {
            importReviews.ReadAndReturnPositiveReviews();
            importReviews.ReadAndReturnNegativeReviews();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
