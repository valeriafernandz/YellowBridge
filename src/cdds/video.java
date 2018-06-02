package cdds;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class video
 */
@WebServlet("/video")
public class video extends HttpServlet {
	String username;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public video() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream out = response.getOutputStream();
//		HttpSession sesion=request.getSession();
		String media_filename=getDB(Integer.parseInt((request.getParameter("media_id"))));
		
		String v=username;
		System.out.println(request.getParameter("media_id"));
		
		String path = "c:\\BridgeGreen"+ "\\" +v +"\\" + media_filename;
		InputStream in = new FileInputStream (path);
		String mimeType = "video/mp4";
		byte[] bytes = new byte[1024];
		int bytesRead;

		response.setContentType(mimeType);

		
		while ((bytesRead = in.read(bytes)) != -1) {
		    out.write(bytes, 0, bytesRead);
		}

		in.close();
		out.close();		
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	private String getDB(Integer req) {
		try {
			DB db= DB.getInstance(); ClassNameFactory ClassNameFactory = new ClassNameFactory(); ClassNameFactory.PrintClassName("DB");
			String datos[];
			datos=new String[1];
			datos[0]="media_filename";
			db.ExecuteQuery("select.media", datos, req);
		String media_filename = datos[0];
		datos[0]="username";
		db.ExecuteQuery("select.user.media",datos,req);
		 username=datos[0];
		return media_filename;
		}catch (SQLException e) {
			System.out.println("error al descargar el archivo");
		}
		return null;
	}
}
