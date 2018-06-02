package cdds;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LikeServlet
 */
@WebServlet("/LikeServlet")
public class LikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()request.getParameter("media_id")
     */
    public LikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession();
		Integer id = (Integer) sesion.getAttribute("user_id");
		System.out.println(("el parametro pasado por el like es"+request.getParameter("media_id")));
		Integer media_id= Integer.parseInt(request.getParameter("media_id"));
		setDB(id,media_id);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void  setDB(Integer id,Integer media_id) {
		try {
			DB db= DB.getInstance(); ClassNameFactory ClassNameFactory = new ClassNameFactory(); ClassNameFactory.PrintClassName("DB");
			Integer id_user=id;
			db.ExecuteUpdate("insert.like", null, id_user,media_id);
	}catch (SQLException e) {
		System.out.println("no se pudo guardar en la base de datos");
	}
	
	}

}
