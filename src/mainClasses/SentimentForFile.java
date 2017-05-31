package mainClasses;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by rocks on 1/29/2017.
 */
public class SentimentForFile {
    public String[] classifyPositiveAndNegative(String path,ImportReviews reviews)
    {
        String [] positiveAndNeg=new String[3];
        positiveAndNeg[0]="";
        positiveAndNeg[1]="";
        positiveAndNeg[2]="";
        int positive_count=0;
        int negative_count=0;
        int undecidable_count=0;
        NaiveBayersClassifier naiveBayersClassifier = new NaiveBayersClassifier(reviews);
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String review;
            while ((review = br.readLine()) != null) {

                String result = naiveBayersClassifier.calculateSentiment(review);

                if (result.equalsIgnoreCase("negative")) {
                    positiveAndNeg[1] = positiveAndNeg[1] + review + "\n";
                    negative_count++;
                } else if (result.equalsIgnoreCase("positive")) {
                    positiveAndNeg[0] = positiveAndNeg[0] + review + "\n";
                    positive_count++;
                } else {
                    positiveAndNeg[2] = positiveAndNeg[2] + review + "\n";
                    undecidable_count++;
                }
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
        positiveAndNeg[0]=positiveAndNeg[0]+"|"+positive_count;
        positiveAndNeg[1]=positiveAndNeg[1]+"|"+negative_count;
        positiveAndNeg[2]=positiveAndNeg[2]+"|"+undecidable_count;


        return positiveAndNeg;
    }
}
