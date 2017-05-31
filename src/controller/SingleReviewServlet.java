package controller;


import mainClasses.SentimentForReview;

import javax.servlet.RequestDispatcher;
import java.io.IOException;


/**
 * Created by rocks on 1/28/2017.
 */
public class SingleReviewServlet extends javax.servlet.http.HttpServlet {


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
            String review = request.getParameter("review");
            SentimentForReview sentimentForReview = new SentimentForReview();
            SentimentForReview.SentimentAndScore sentimentAndScore;
            sentimentAndScore = sentimentForReview.calculateSentiment(review, InitiatorListener.reviews);
            System.out.println(sentimentAndScore.sentiment + sentimentAndScore.negative_probablity + sentimentAndScore.positive_probablity);

        request.setAttribute("sentiment",sentimentAndScore.sentiment);
        request.setAttribute("positive_probablity",sentimentAndScore.positive_probablity);
        request.setAttribute("negative_probablity",sentimentAndScore.negative_probablity);
        request.setAttribute("review",review);
        RequestDispatcher rd = request.getRequestDispatcher("jsp/showSentenceSentiment.jsp");
        rd.forward(request,response);

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        doPost(request,response);
    }
}
