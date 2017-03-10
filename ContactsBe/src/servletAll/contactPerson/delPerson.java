package servletAll.contactPerson;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servletAll.common.*;

/**
 * Servlet implementation class delPerson
 * 
 * @author water
 * @time 2017-02-22 22:18
 * @version 1.0
 * @description Delete a sql statement
 * 
 */
@WebServlet("/delPerson")
public class delPerson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public delPerson() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 设置请求头格式
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");

		// 设置CORS
		response.setHeader("Access-Control-Allow-Origin", "*");
		// 允许该域发起跨域请求
		response.setHeader("Access-Control-Allow-Origin", "*");// *允许任何域
		// 允许的外域请求方式
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE");
		// 在999999秒内，不需要再发送预检验请求，可以缓存该结果
		response.setHeader("Access-Control-Max-Age", "999999");
		// 允许跨域请求包含某请求头,x-requested-with请求头为异步请求
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

		// 设置编码
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		// System.out.println(name);
		String sql = "delete from personlist where name='" + name + "'";
		sql_data db = new sql_data();
		db.executeDelete(sql);
	}
}
