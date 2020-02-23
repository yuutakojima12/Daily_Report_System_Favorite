package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Favorite;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsDestroyFavoriteServlet
 */
@WebServlet("/reports/destroy")
public class ReportsDestroyFavoriteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsDestroyFavoriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }



    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Favorite f = em.find(Favorite.class, (Integer)(request.getSession().getAttribute("favorite_id")));

            em.getTransaction().begin();
            em.remove(f);
            em.getTransaction().commit();
            em.close();

            request.getSession().removeAttribute("favorite_id");

            response.sendRedirect(request.getContextPath() + "/reports/index");
    }

}
}
