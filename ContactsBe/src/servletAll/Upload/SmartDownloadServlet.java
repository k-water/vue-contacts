package servletAll.Upload;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * Servlet implementation class SmartDownloadServlet
 */
@WebServlet("/SmartDownloadServlet")
public class SmartDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SmartDownloadServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/x-msdownload");
		String filename = request.getParameter("filename");
		System.out.println(filename);
		SmartUpload su = new SmartUpload();
		su.initialize(getServletConfig(), request, response);
		su.setContentDisposition(null);
		try {
			su.downloadFile(getServletContext().getRealPath("/") + "uploadFile/" + filename);
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
	}
}
