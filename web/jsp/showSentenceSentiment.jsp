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
<marquee behavior="alternate" style="font-size: xx-large">Sentiment Analysis of Android App reviews</marquee>
<br>
<br>
<a href="../index.jsp">
<input type="button" class="btn-primary" value="Return to main Page">
</a>
<br>
<body style="padding: 5%">
<center>
<h1>${review}</h1>
<br>
<c:if test="${sentiment=='positive'}">
    <p class="alert alert-success"
<h2> positive</h2>
    </p>
</c:if>
<c:if test="${sentiment=='negative'}">
    <p class="alert alert-danger"
          <h2> negative</h2>
    </p>
</c:if>
    <c:if test="${sentiment=='undecidable'}">
        <p class="alert alert-warning"
        <h2> undecidable</h2>
        </p>
    </c:if>

<table class="table" style="padding-left: 15%;padding-right: 15%" >
    <thead>
    <tr>
        <th>Positive Probablity</th>
        <th>Negative Probablity</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${positive_probablity}</td>
        <td>${negative_probablity}</td>

    </tr>
    </tbody>
    </table>


</center>
</body>
</html>
