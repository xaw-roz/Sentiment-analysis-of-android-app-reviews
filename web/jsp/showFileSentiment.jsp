<%--
  Created by IntelliJ IDEA.
  User: rocks
  Date: 1/28/2017
  Time: 5:26 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Single page sentiment</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<marquee behavior="alternate" style="font-size: xx-large;">Sentiment Analysis of Android App reviews</marquee>
<br>
<br>
<br>
<a href="../index.jsp">
    <input type="button" class="btn-primary" value="Return to main Page">
</a>
<br>
<body style="padding: 5%">
<center>

    <h1>Positive Reviews</h1>
    <br>
    ${positiveCount}
<div class="form-group">
    <textarea rows="10" style="color: green;" class="form-control" cols="100" readonly>
        ${positiveReviews}
    </textarea>
</div>
    <br>
    <h1>Negative Reviews</h1>
    <br>
    ${negativeCount}
<div class="form-group">
 <textarea rows="10" cols="100" style="color: red;" class="form-control" readonly>${negativeReviews}</textarea>
</div>
    <br>
    <h1>Undecidable Reviews</h1>
    <br>
    ${undecidableCount}
<div class="form-group">
    <textarea rows="10" style="color: orange" class="form-control" cols="100" readonly>
        ${undecidableReviews}
    </textarea>
</div>

</center>
</body>
</html>
