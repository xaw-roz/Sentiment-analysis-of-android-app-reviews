<%--
  Created by IntelliJ IDEA.
  User: rocks
  Date: 12/17/2016
  Time: 8:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Sentiment Analysis of android App reviews</title>
  </head>
  <link rel="stylesheet" href="css/bootstrap.css">
  <body style="padding: 5%">
  <marquee behavior="alternate" style="font-size: xx-large" >
  Sentiment Analysis of Android App Review
    </marquee>
  <br>
  <br>
  <div class="singleSentence">
  <h3>Perform Sentiment Analysis for particular review</h3>
<form action="/sentiment" method="post">

  <div class="form-group">
<%--    <label> Enter the reviw for sentiment Analysis</label>--%>
  <textarea name="review" required rows="5" cols="30" class="form-control"></textarea>
  </div>
  <input type="submit" value="Check Sentiment" class="btn btn-primary">
</form>
    <br>
    <br>
    <h3>Perform sentiment analysis of reviews stored in a text file</h3>
  </div>

    <div class="fileUpload">
      Select the file to upload
      <form action="/fileupload" method="post"  enctype="multipart/form-data">
        <input type="file" name="file" />
        <input type="submit" value="upload" />
      </form>


    </div>
  </body>
</html>
