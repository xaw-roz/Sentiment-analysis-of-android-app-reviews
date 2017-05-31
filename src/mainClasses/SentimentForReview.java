package mainClasses;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by rocks on 1/29/2017.
 */
public class SentimentForReview {
    public class SentimentAndScore {
        public String sentiment;
        public Double positive_probablity;
        public Double negative_probablity;
    }

    public SentimentAndScore calculateSentiment(String review,ImportReviews rev) {
        SentimentAndScore sentimentAndScore = new SentimentAndScore();
        PreProcessing preProcessing = new PreProcessing();
        NaiveBayersClassifier n = new NaiveBayersClassifier(rev);
        double positiveScore = 1.00;
        double negativeScore = 1.00;
        try {
            Lemmatizer lemmatizer = new Lemmatizer();
            review = review.toLowerCase();

            ArrayList<String> lemmatizedTokens = lemmatizer.returnLemma(review);
            for (String token : lemmatizedTokens) {
                if (preProcessing.checkStopWordsOrSpace(token) == false && preProcessing.checknumbers(token) == false) {
                    positiveScore = positiveScore * n.calculatePositiveConditionalProbablityOfEachWord(token);
                    negativeScore = negativeScore * n.calculateNegaitiveConditionalProbablityOfEachWord(token);
                }
            }
            if ((positiveScore * 0.5) == (negativeScore * 0.5)) {
                sentimentAndScore.sentiment = "undecidable";
                positiveScore=0.0;
                negativeScore=0.0;

            }
            else if ((positiveScore * 0.5) > (negativeScore * 0.5)) {
                sentimentAndScore.sentiment = "positive";

            } else {
                sentimentAndScore.sentiment = "negative";
            }

            sentimentAndScore.negative_probablity = negativeScore;
            sentimentAndScore.positive_probablity = positiveScore;
            System.out.println(review);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sentimentAndScore;

    }
}
