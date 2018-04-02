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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>


<div class="fixed-top" style="background-color: #F1F1F1">
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
    <div align="center">
        <img src="img/Capture.PNG"style="height: 112px; width: 325px">
        <form action="ServletApp" method="post">
            <div class="form-group" style="width: 80%">
                <label for="search">Moogle Web App search engine powered by JAVA.</label>
                <input type="text" class="form-control" id="search" name="search" placeholder="Search : Enter movie title here (Blank = All movies)" onchange="">
                <input type="text" class="form-control" id="flag" name="flag" value="flag1" hidden="true">
                <br>
                <button class="btn btn-primary" style="width: 250px" type="submit" value="find">Find</button>
            </div>
        </form>
    </div>
</div>
<div class="container" align="center" style="margin-top: 380px">
    <%if(request.getAttribute("alert") != null)
        out.print(request.getAttribute("alert"));
    %>
</div>
<div class="container">
    <%
        if(request.getAttribute("result") != null)
        {
            out.print("<table class=\"table\">\n" +
                    "  <thead>\n" +
                    "  <tr>\n" +
                    "    <th scope=\"col\">Movie ID</th>\n" +
                    "    <th scope=\"col\">Name</th>\n" +
                    "    <th scope=\"col\">Year</th>\n" +
                    "    <th scope=\"col\">Rating</th>\n" +
                    "  </tr>\n" +
                    "  </thead>\n" +
                    "  <tbody>");
            out.println(request.getAttribute("result"));
        }
    %>
    </tbody>
    </table>
</div>
</body>
</html>