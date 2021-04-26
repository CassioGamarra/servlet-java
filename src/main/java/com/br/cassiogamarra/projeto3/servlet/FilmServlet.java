package com.br.cassiogamarra.projeto3.servlet;

import com.br.cassiogamarra.projeto3.entity.Film;
import com.br.cassiogamarra.projeto3.repository.FilmRepository;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

@WebServlet(name = "FilmServlet", value = "/filme")
public class FilmServlet extends HttpServlet {

    FilmRepository filmRepository = new FilmRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String banco = request.getParameter("sgbd");

        filmRepository.setup(banco);
        Film film = filmRepository.findByTitle(title.toUpperCase().trim());
        filmRepository.setup(banco);
        //TODO BUSCA
        if(film == null) {
            response.sendRedirect("404.html");
        } else {
            PrintWriter printWriter = response.getWriter();
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            String ratingClass = "";

            if(film.getRating().equals("G")) {
                ratingClass = "bg-success";
            } else if(film.getRating().equals("PG")) {
                ratingClass = "bg-info text-dark";
            } else if(film.getRating().equals("PG-13")) {
                ratingClass = "bg-warning text-dark";
            } else if(film.getRating().equals("R")) {
                ratingClass = "bg-danger";
            } else if(film.getRating().equals("NC-17")) {
                ratingClass = "bg-dark";
            }

            try {
                printWriter.println("<!DOCTYPE html>\n" +
                        "<html lang=\"pt-br\"> ");
                printWriter.println("<head>\n" +
                        "  <meta charset=\"utf-8\" />\n" +
                        "  <link rel=\"apple-touch-icon\" sizes=\"76x76\" href=\"resources/img/apple-icon.png\">\n" +
                        "  <link rel=\"icon\" type=\"image/png\" href=\"resources/img/favicon.png\">\n" +
                        "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />\n" +
                        "  <title>\n" +
                        "    Projeto 3 - VideoLocadora\n" +
                        "  </title>\n" +
                        "  <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />\n" +
                        "  <!--     Fonts and icons     -->\n" +
                        "  <link rel=\"stylesheet\" type=\"text/css\" href=\"https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons\" />\n" +
                        "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css\">\n" +
                        "  <!-- CSS Files -->\n" +
                        "  <link href=\"resources/css/material-dashboard.css?v=2.1.0\" rel=\"stylesheet\" />\n" +
                        "</head>");
                //Inicio até TBODY
                printWriter.println("<body class=\"dark-edition\">\n" +
                        "  <div class=\"wrapper \"> \n" +
                        "    <div class=\"main-panel\">\n" +
                        "      <!-- Navbar -->\n" +
                        "      <nav class=\"navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top \" id=\"navigation-example\">\n" +
                        "        <div class=\"container-fluid\">\n" +
                        "          <div class=\"navbar-wrapper\" style=\"margin-top: -10px\">\n" +
                        "            <img src=\"resources/img/logo.png\" style=\"width: 100%; margin-bottom: 10px\"/>\n" +
                        "          </div>\n" +
                        "          <div class=\"collapse navbar-collapse justify-content-end\"> \n" +
                        "            <ul class=\"navbar-nav\">\n" +
                        "              <div class=\"input-group no-border\">\n" +
                        "                <button onclick=\"history.back()\" type=\"button\" class=\"btn btn-default\">\n" +
                        "                <i class=\"material-icons\">arrow_back</i>\n" +
                        "                  Voltar\n" +
                        "                </button>\n" +
                        "              </div>\n" +
                        "            </ul>\n" +
                        "          </div>\n" +
                        "        </div>\n" +
                        "      </nav>\n" +
                        "      <!-- End Navbar -->\n" +
                        "      <div class=\"content\">\n" +
                        "        <div class=\"container-fluid\">\n" +
                        "          <div class=\"row\">\n" +
                        "            <div class=\"col-md-12\">\n" +
                        "              <div class=\"card\">\n" +
                        "                <div class=\"card-header card-header-primary\">\n" +
                        "                  <h4 class=\"card-title\">"+film.getTitle()+"</h4>\n" +
                        "                  <p class=\"card-category\">"+film.getDescription()+"</p>\n" +
                        "                </div>\n" +
                        "                <div class=\"card-body\">\n" +
                        "                  <form>\n" +
                        "                    <div class=\"row\">\n" +
                        "                      <div class=\"col-md-2\">\n" +
                        "                        <div class=\"form-group\">\n" +
                        "                          <label class=\"bmd-label-floating\">ANO DE LANÇAMENTO</label>\n" +
                        "                          <input type=\"text\" value=\""+film.getReleaseYear()+"\" class=\"form-control\" disabled>\n" +
                        "                        </div>\n" +
                        "                      </div>\n" +
                        "                      <div class=\"col-md-2\">\n" +
                        "                        <div class=\"form-group\">\n" +
                        "                          <label class=\"bmd-label-floating\">TEMPO MÁXIMO DE LOCAÇÃO</label>\n" +
                        "                          <input type=\"text\" value=\""+film.getRentalDuration()+" dias\" class=\"form-control\" disabled>\n" +
                        "                        </div>\n" +
                        "                      </div>\n" +
                        "                      <div class=\"col-md-2\">\n" +
                        "                        <div class=\"form-group\">\n" +
                        "                          <label class=\"bmd-label-floating\">PREÇO DE LOCAÇÃO</label>\n" +
                        "                          <input type=\"text\" value=\"R$"+film.getRentalRate()+"\" class=\"form-control\" disabled>\n" +
                        "                        </div>\n" +
                        "                      </div>\n" +
                        "                       <div class=\"col-md-2\">\n" +
                        "                        <div class=\"form-group\">\n" +
                        "                          <label class=\"bmd-label-floating\">PREÇO DE LOCAÇÃO</label>\n" +
                        "                          <input type=\"text\" value=\"R$"+film.getRentalRate()+"\" class=\"form-control\" disabled>\n" +
                        "                        </div>\n" +
                        "                      </div>\n" +
                        "                      <div class=\"col-md-2\">\n" +
                        "                        <div class=\"form-group\">\n" +
                        "                          <label class=\"bmd-label-floating\">PREÇO DE REPOSIÇÃO</label>\n" +
                        "                          <input type=\"text\" value=\"R$"+film.getReplacementCost()+"\" class=\"form-control\" disabled>\n" +
                        "                        </div>\n" +
                        "                      </div> \n" +
                        "                      <div class=\"col-md-2\">\n" +
                        "                        <div class=\"form-group\">\n" +
                        "                          <label class=\"bmd-label-floating\">CLASSIFICAÇÃO</label>\n" +
                        "                          <input type=\"text\" value=\""+film.getRating()+"\" class=\""+ratingClass+" text-center form-control\" disabled>  \n" +
                        "                        </div>\n" +
                        "                      </div>\n" +
                        "                      <div class=\"col-md-12\">\n" +
                        "                        <div class=\"form-group\">\n" +
                        "                          <label class=\"bmd-label-floating\">CONTEÚDO ESPECIAL</label>\n" +
                        "                          <textarea type=\"text\" class=\"form-control\" disabled>"+film.getSpecialFeatures().replace("{", "").replace("}", "").replaceAll(",", ",  ")+"</textarea>\n" +
                        "                        </div>\n" +
                        "                      </div> \n" +
                        "                    </div>\n" +
                        "                  </form>\n" +
                        "                </div>\n" +
                        "              </div>\n" +
                        "            </div> \n" +
                        "          </div>\n" +
                        "        </div>\n" +
                        "      </div>");
                //Footer
                printWriter.println(
                        "      <footer class=\"footer\">\n" +
                        "        <div class=\"container-fluid\">\n" +
                        "          Cássio Gamarra - Tópicos Avançados II\n" +
                        "        </div>\n" +
                        "      </footer>\n" +
                        "    </div>\n" +
                        "  </div>\n" +
                        "</body>\n" +
                        "</html>");
            } finally {
                printWriter.close();
            }
        }
    }
}
