<%@ page import="java.io.*" %>
<%@ page import="com.moogle.User" %>
<%@ page import="com.moogle.MoogleTester" %>
<%@ page import="com.moogle.SimpleMovieSearchEngine" %><%--
  Created by IntelliJ IDEA.
  User: issa-
  Date: 21/3/2561
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Moogle Web App</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
  </head>
  <body>


<div class="fixed-top">
  <nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <a class="navbar-brand" href="index.jsp">Moogle App Preview</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="customSearch.jsp">Custom Search</a>
      </li>
    </ul>
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <a class="nav-link" href="http://tomcat.apache.org/">Tomcat</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="http://www.oracle.com/technetwork/java/javaee/jsp/index.html">jsp</a>
        </li>
      </ul>
    </div>
  </nav>
  <br>
</div>

<div class="container" align="center" style="margin-top: 15%">
  <img src="img/Capture.PNG"style="height: 112px; width: 325px">
  <form action="ServletApp" method="post">
    <div class="form-group" style="width: 80%">
      <label for="search">Moogle Web App search engine powered by JAVA.</label><br><br>
      <input type="text" class="form-control form-control-lg" id="search" name="search" placeholder="Search : Enter movie title here (Blank = All movies)" onchange="">
      <input type="text" class="form-control" id="flag" name="flag" value="flag1" hidden="true">
      <br><br>
      <button class="btn btn-primary" style="width: 250px" type="submit" value="find">Find</button>
    </div>
  </form>
</div>
</div>
  </body>
</html>