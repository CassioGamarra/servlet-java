package com.br.cassiogamarra.projeto3.servlet;

import com.br.cassiogamarra.projeto3.entity.Film;
import com.br.cassiogamarra.projeto3.repository.FilmRepository;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@WebServlet(name = "FilmListServlet", value = "/dashboard")
public class FilmListServlet extends HttpServlet {

    FilmRepository filmRepository = new FilmRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String banco = request.getParameter("sgbd");
        if(!filmRepository.setup(banco)) {
            response.sendRedirect("404.html");
        } else {
            List<Film> films = filmRepository.findALl();
            filmRepository.close();
            //Valores de exibição
            long totalFilmes = 0;
            String mediaPreco = "";

            double valorTotalFilmes = 0;

            totalFilmes = films.size();
            for(Film film : films) {
                valorTotalFilmes += film.getRentalRate();
            }

            mediaPreco = String.format("%.2f", (valorTotalFilmes/totalFilmes));

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter printWriter = response.getWriter();

            try {
                printWriter.println("<!DOCTYPE html>\n" +
                        "<html lang=\"pt-br\"> ");
                //Head
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
                        "  <div class=\"wrapper \">\n" +
                        "    <div class=\"main-panel\">\n" +
                        "      <!-- Navbar -->\n" +
                        "      <nav class=\"navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top \" id=\"navigation-example\">\n" +
                        "        <div class=\"container-fluid\">\n" +
                        "          <div class=\"navbar-wrapper\" style=\"margin-top: -10px\">\n" +
                        "            <img src=\"resources/img/logo.png\" style=\"width: 100%; margin-bottom: 10px\"/>\n" +
                        "          </div>\n" +
                        "          <div class=\"collapse navbar-collapse justify-content-end\">\n" +
                        "            <form action=\"filme\" method=\"POST\" class=\"navbar-form\">\n" +
                        "              <div class=\"input-group no-border\">\n" +
                        "                <input required name=\"title\" type=\"text\" value=\"\" class=\"form-control\" placeholder=\"Buscar por nome...\">\n" +
                        "                <input required name=\"sgbd\" type=\"text\" value="+banco+" class=\"form-control\" style=\"display: none\">\n" +
                        "                <button type=\"submit\" class=\"btn btn-default btn-round btn-just-icon\">\n" +
                        "                  <i class=\"material-icons\">search</i>\n" +
                        "                  <div class=\"ripple-container\"></div>\n" +
                        "                </button>\n" +
                        "              </div>\n" +
                        "            </form>\n" +
                        "<ul class=\"navbar-nav\" style=\"margin-left: 5px;\">\n" +
                        "              <div class=\"input-group no-border\"> \n" +
                        "                 <button onclick=\"history.back()\" type=\"button\" class=\"btn btn-default\">\n" +
                        "                   <i class=\"material-icons\">arrow_back</i> \n" +
                        "                   Voltar\n" +
                        "                 </button>\n" +
                        "              </div>\n" +
                        "            </ul>"+
                        "          </div>\n" +
                        "        </div>\n" +
                        "      </nav>\n" +
                        "      <!-- End Navbar -->\n" +
                        "      <div class=\"content\">\n" +
                        "        <div class=\"container-fluid\">\n" +
                        "          <div class=\"row\">\n" +
                        "            <div class=\"col-xl-6 col-lg-6 col-md-6 col-sm-6\">\n" +
                        "              <div class=\"card card-stats\">\n" +
                        "                <div class=\"card-header card-header-warning card-header-icon\">\n" +
                        "                  <div class=\"card-icon\">\n" +
                        "                    <i class=\"material-icons\">movie</i>\n" +
                        "                  </div>\n" +
                        "                  <p class=\"card-category\">Total de Filmes</p>\n" +
                        "                  <h3 class=\"card-title\">"+totalFilmes+"\n" +
                        "                  </h3>\n" +
                        "                </div>\n" +
                        "                <div class=\"card-footer\">\n" +
                        "                </div>\n" +
                        "              </div>\n" +
                        "            </div>\n" +
                        "            <div class=\"col-xl-6 col-lg-6 col-md-6 col-sm-6\">\n" +
                        "              <div class=\"card card-stats\">\n" +
                        "                <div class=\"card-header card-header-success card-header-icon\">\n" +
                        "                  <div class=\"card-icon\">\n" +
                        "                    <i class=\"material-icons\">store</i>\n" +
                        "                  </div>\n" +
                        "                  <p class=\"card-category\">Média de Preço</p>\n" +
                        "                  <h3 class=\"card-title\">R$"+mediaPreco+"</h3>\n" +
                        "                </div>\n" +
                        "                <div class=\"card-footer\">\n" +
                        "                </div>\n" +
                        "              </div>\n" +
                        "            </div>\n" +
                        "          </div>\n" +
                        "          <div class=\"row\">\n" +
                        "            <div class=\"col-lg-12 col-md-12\">\n" +
                        "              <div class=\"card\">\n" +
                        "                <div class=\"card-header card-header-primary\">\n" +
                        "                  <h4 class=\"card-title\">Lista de FIlmes</h4>\n" +
                        "                </div>\n" +
                        "                <div class=\"card-body table-responsive\">\n" +
                        "                  <table class=\"table table-hover\">\n" +
                        "                    <thead class=\"text-warning\">\n" +
                        "                      <th>#</th>\n" +
                        "                      <th>Título</th>\n" +
                        "                      <th>Descrição</th>\n" +
                        "                      <th>Ano de Lançamento</th>\n" +
                        "                      <th>Valor de Locação</th>\n" +
                        "                    </thead>\n" +
                        "                    <tbody>");
                //Lista
                for(Film film : films) {
                    printWriter.println("<tr>\n" +
                            "                        <td>"+film.getId()+"</td>\n" +
                            "                        <td>"+film.getTitle()+"</td>\n" +
                            "                        <td>"+film.getDescription()+"</td>\n" +
                            "                        <td>"+film.getReleaseYear()+"</td>\n" +
                            "                        <td>R$"+film.getRentalRate()+"</td>\n" +
                            "                      </tr>");
                }
                printWriter.println("</tbody>\n" +
                        "                  </table>\n" +
                        "                </div>\n" +
                        "              </div>\n" +
                        "            </div>\n" +
                        "          </div>\n" +
                        "        </div>\n" +
                        "      </div>\n" +
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

    /*protected HttpServletResponse findAll() {

    }*/

    /*protected HttpServletResponse find() {

    }*/
}
