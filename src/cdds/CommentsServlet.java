package cdds;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

/**
 * Servlet implementation class CommentsServlet
 */
@MultipartConfig
@WebServlet("/CommentsServlet")
public class CommentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out= response.getWriter();
		
//		HttpSession sesion = request.getSession();
//		Integer id_user= (Integer) sesion.getAttribute("user_id");
		Integer media_id=Integer.parseInt(request.getParameter("media_id"));
		DB db= DB.getInstance(); ClassNameFactory ClassNameFactory = new ClassNameFactory(); ClassNameFactory.PrintClassName("DB");
		try {
			JSONArray comment= db.ExecuteCommentList("select.comment",media_id);
			System.out.println(comment);
			out.print(comment.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession();
		Integer id = (Integer) sesion.getAttribute("user_id");
		Integer media_id= Integer.parseInt(request.getParameter("media_id"));
		System.out.println("media id enviado en el formdata "+media_id);
		String comment= request.getParameter("comment");
		System.out.println("comentario enviado en el formdata "+comment);
		setDB(id,media_id,comment);
		doGet(request, response);
	}
	private void  setDB(Integer id,Integer media_id,String comment) {
			
			ClassNameFactory ClassNameFactory = new ClassNameFactory(); 
			ClassNameFactory.PrintClassName("DB");
			DB db= DB.getInstance(); 
			String datos[];
			datos=new String[1];
			try {
			long date=new java.util.Date().getTime();
			db.ExecuteUpdate("insert.comment",null,media_id,id,date,comment);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("ocurrio un error en la interaccion con la bdd: "+e.getMessage());
				e.printStackTrace();
			}
			
	
	
	}

}