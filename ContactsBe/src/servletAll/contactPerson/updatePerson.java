package servletAll.contactPerson;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servletAll.common.*;

/**
 * Servlet implementation class updatePerson
 * 
 * @author water
 * @time 2017-02-22 23:03
 * @version 1.0
 * @description update a person sql statement
 */
@WebServlet("/updatePerson")
public class updatePerson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updatePerson() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
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

		// 获取前台返回的数据
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String birthday = request.getParameter("birthday");
		String address = request.getParameter("address");
		String site = request.getParameter("site");
		String battery = request.getParameter("battery");
		String homeNumber = request.getParameter("homeNumber");
		// System.out.println(name);

		sql_data db = new sql_data();
		String searchSql = "SELECT * FROM `personlist`";
		int id = 0;
		ResultSet rs = db.executeQuery(searchSql);
		try {
			while (rs.next()) {
				if (rs.getString("name").equals(name)) {
					id = rs.getInt("id");
					break;
				} else {
					continue;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql = "update personlist set name='" + name + "',email='" + email + "',phoneNumber='" + phoneNumber
				+ "',address='" + address + "',homeNumber='" + homeNumber + "',site='" + site + "',battery='" + battery
				+ "',birthday='" + birthday + "'where id='" + id + "'";

		db.executeUpdate(sql);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
