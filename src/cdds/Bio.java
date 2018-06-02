package cdds;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

/**
 * Servlet implementation class Bio
 */
@WebServlet("/Bio")
public class Bio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out= response.getWriter();
		HttpSession sesion = request.getSession();
		Integer id = (Integer) sesion.getAttribute("user_id");
		System.out.println(System.getProperty("props.dir"));
		DB db= DB.getInstance(); ClassNameFactory ClassNameFactory = new ClassNameFactory(); ClassNameFactory.PrintClassName("DB");
		try {
			JSONArray executeUser = db.ExecuteBio("user.bio",id);
			System.out.println(executeUser.toString());
			out.print(executeUser.toString());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("no se pudo retirar de la base de datos");
			e1.printStackTrace();
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
