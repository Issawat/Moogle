package com.moogle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "ServletApp", urlPatterns = "/ServletApp")
public class ServletApp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String flag = request.getParameter("flag");

        if(flag.equals("flag1"))
        {
            String in = request.getParameter("search");
            String url = getServletContext().getRealPath("");
            String alert = null;
            SimpleMovieSearchEngine s = new SimpleMovieSearchEngine();
            String movies = url + "/data-sample/movies.csv";
            String ratings = url + "/data-sample/ratings.csv";
            s.loadData(movies, ratings);
            List<Movie> result = s.searchByTitle(in, false);
            result.sort(Comparator.comparing(Movie::getTitle));
            StringBuilder resultString = new StringBuilder();
            int counter = 0;
            for (Movie i : result) {
                String star = "" ;
                for(int j = 0 ; j < i.getMeanRating().shortValue();j++ )
                star += "★";
                resultString.append(
                        "<tr>" +
                                "<th scope=\"row\">" + i.getID() + "</th>" +
                                "<td>" + i.getTitle() + "</td>" +
                                "<td>" + i.getYear() + "</td>" +
                                "<td style=\"color:#ffc935\">" + star + "</td>" +
                                "</tr>"
                );
                counter++;
            }
            if (in != null) {
                request.setAttribute("result", resultString);
                request.setAttribute("counter", counter);
            }
            if (counter != 0)
                alert = "<div class=\"alert alert-success\" style=\"width: 80%;\"> <strong>" + counter + " movies found!</strong> These are the results for \"" + in + "\". </div>";
            else {
                    alert = "<div class=\"alert alert-danger\" style=\"width: 80%;\"> <strong>Not found!</strong> Please try again. </div>";
            }
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("Search.jsp").forward(request, response);
        }

        else if(flag.equals("flag2"))
        {
            String title = null;
            if(request.getParameter("title") != "")
            title = request.getParameter("title");
            String tag =  null ;
            if(request.getParameter("tag") != "")
            tag = request.getParameter("tag");
            int year = -1;
            if(request.getParameter("y") != "")
                year = Integer.parseInt(request.getParameter("y"));
            int sort = Integer.parseInt(request.getParameter("sort"));
            String alert = null;
            String url = getServletContext().getRealPath("");
            SimpleMovieSearchEngine s = new SimpleMovieSearchEngine();
            String movies = url + "/data-sample/movies.csv";
            String ratings = url + "/data-sample/ratings.csv";
            s.loadData(movies, ratings);
            List<Movie> mlist = s.advanceSearch(title,tag,year);
            if(sort == 2)
            {
                mlist.sort(Comparator.comparing(Movie::getTitle));
            }
            else if(sort == 3)
            {
                mlist.sort(Comparator.comparing(Movie::getMeanRating).reversed());
            }
            StringBuilder resultString = new StringBuilder();
            int counter = 0;
            for (Movie i : mlist) {
                String star = "" ;
                for(int j = 0 ; j < i.getMeanRating().shortValue();j++ )
                    star += "★";
                resultString.append(
                        "<tr>" +
                                "<th scope=\"row\">" + i.getID() + "</th>" +
                                "<td>" + i.getTitle() + "</td>" +
                                "<td>" + i.getTags().toString() + "</td>" +
                                "<td>" + i.getYear() + "</td>" +
                                "<td style=\"color:#ffc935\">" + star + "</td>" +
                                "</tr>"
                );
                counter++;
            }
        if (counter != 0)
            alert = "<div class=\"alert alert-success\" style=\"width: 80%;\"> <strong>" + counter + " movies found!</strong> </div>";
        else alert = "<div class=\"alert alert-danger\" style=\"width: 80%;\"> <strong>Not found!</strong> Please try again. </div>";
            if (title != null || tag != null || year != 0) {
                request.setAttribute("result", resultString);
                request.setAttribute("counter", counter);
            }
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("customSearch.jsp").forward(request, response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
