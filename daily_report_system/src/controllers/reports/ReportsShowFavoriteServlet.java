package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Favorite;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsShowServlet
 */
@WebServlet("/reports/showfavorite")
public class ReportsShowFavoriteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsShowFavoriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        Favorite f = em.find(Favorite.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("favorite", f);
        request.setAttribute("_token", request.getSession().getId());

        request.getSession().setAttribute("favorite_id", f.getId());




        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/showfavorite.jsp");
        rd.forward(request, response);
    }

}
