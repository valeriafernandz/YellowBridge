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

import org.json.JSONObject;

/**
 * Servlet implementation class DeleteComment
 */
@WebServlet("/DeleteComment")
public class DeleteComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int media_id;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		HttpSession sesion = request.getSession();
		PrintWriter out= response.getWriter();
		Integer comment_id= Integer.parseInt(request.getParameter("comment_id"));
		setDB(comment_id);
		JSONObject j= new JSONObject();
		j.put("url", "/YellowBridge/VideoStreaming.html?media_id="+media_id);
		out.print(j.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}
	private void  setDB(Integer id) {
		DB db= DB.getInstance(); ClassNameFactory ClassNameFactory = new ClassNameFactory(); ClassNameFactory.PrintClassName("DB");
		String datos[];
		datos=new String[1];
		datos[0]="media_id";
		try {
		db.ExecuteUpdate("delete.comment",datos,id);
		media_id = Integer.parseInt(datos[0]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ocurrio un error en la interaccion con la bdd: "+e.getMessage());
			e.printStackTrace();
		}
		


	}
}
