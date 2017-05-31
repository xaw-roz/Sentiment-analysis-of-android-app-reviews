package mainClasses;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rocks on 11/6/2016.
 */
public class NaiveBayersClassifier {
    public NaiveBayersClassifier(ImportReviews rev)
    {
        initialize(rev);

    }
    public class PostitveAndNegativeCount {
        Integer positive = 0;
        Integer negative = 0;
    }
    Integer totalPositiveWords=0;
    Integer totalNegativeWords=0;
    public HashMap<String,ImportReviews.PostitveAndNegativeCount> frequencyTable=new HashMap<>();

    public void initialize(ImportReviews importReviews)
    {

        frequencyTable=importReviews.frequencyTable;
        totalNegativeWords=importReviews.totalNegativeWords;
        totalPositiveWords=importReviews.totalPositiveWords;
      //  System.out.println("freq"+frequencyTable.size());
    }

    public Double calculatePositiveConditionalProbablityOfEachWord(String word)
    {

        Double conditionProbablity=1.0;
        if (frequencyTable.containsKey(word)) {
            conditionProbablity = (((double) frequencyTable.get(word).positive +1)/ (double) totalPositiveWords);
        }
        return conditionProbablity;

    }
    public Double calculateNegaitiveConditionalProbablityOfEachWord(String word)
    {
        Double conditionProbablity=1.0;
        if(frequencyTable.containsKey(word)) {

            conditionProbablity = (((double) frequencyTable.get(word).negative+1 )/ (double) totalNegativeWords);
        }
        return conditionProbablity;
    }
    public String calculateSentiment(String review)
    {


        //System.out.println("complete initialization");
        String decision="";
        ArrayList <ArrayList<String>> positiveReviews=new ArrayList<>();
        PreProcessing preProcessing=new PreProcessing();
        double positiveScore=1.00;
        double negativeScore=1.00;
        try {
            Lemmatizer lemmatizer = new Lemmatizer();
            review=review.toLowerCase();

            ArrayList<String> lemmatizedTokens=lemmatizer.returnLemma(review);
            for (String token:lemmatizedTokens)
            {
                if (preProcessing.checkStopWordsOrSpace(token)==false && preProcessing.checknumbers(token)==false) {
                    positiveScore = positiveScore * calculatePositiveConditionalProbablityOfEachWord(token);
                    negativeScore=negativeScore*calculateNegaitiveConditionalProbablityOfEachWord(token);
                }
            }
            if((positiveScore*0.5)==(negativeScore*0.5))
            {
                decision="undecidable";
            }
            else if((positiveScore*0.5)>(negativeScore*0.5))
            {
                decision="positive";

            }
            else {
                decision="negative";
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return decision;
    }


}
