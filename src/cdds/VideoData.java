package cdds;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 * Servlet implementation class VideoData
 */
@WebServlet("/VideoData")
public class VideoData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VideoData() {
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
			String datos[];
			datos=new String[4];
			datos[0]="media_name";
			datos[1]="media_des";
			datos[2]="media_views";
			datos[3]="created_at";
			
			 db.ExecuteQuery("select.media",datos,media_id);
			 //setDB(media_id);
			JSONObject j = new JSONObject();
			j.put("media_name",datos[0]).put("media_des",datos[1]).put("media_views",datos[2]).put("time",datos[3]);
			System.out.println(j.toString());
			out.print(j.toString());
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
		doGet(request, response);
	}
	/*private void  setDB(Integer media_id) {
		DB db= DB.getInstance(); ClassNameFactory ClassNameFactory = new ClassNameFactory(); ClassNameFactory.PrintClassName("DB");
		String datos[];
		datos=new String[1];
		datos[0]="media_id";
		try {
		db.ExecuteUpdate("update.views",datos,media_id);
		media_id = Integer.parseInt(datos[0]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ocurrio un error en la interaccion con la bdd: "+e.getMessage());
			e.printStackTrace();
		}
		


	}*/

}
