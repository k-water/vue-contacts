package servletAll.contactPerson;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import servletAll.common.sql_data;

/**
 * Servlet implementation class SearchPerson
 */
@WebServlet("/SearchPerson")
public class SearchPerson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchPerson() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		
		// 设置CORS
		response.setHeader("Access-Control-Allow-Origin", "*");
		// 允许该域发起跨域请求
		response.setHeader("Access-Control-Allow-Origin", "*");// *允许任何域
		// 允许的外域请求方式
		response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, TRACE, OPTIONS");
		// 在999999秒内，不需要再发送预检验请求，可以缓存该结果
		response.setHeader("Access-Control-Max-Age", "999999");
		// 允许跨域请求包含某请求头,x-requested-with请求头为异步请求
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, X-Auth-Token");

		// 设置编码
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		String key = request.getParameter("key");

		// 测试
		System.out.println(key);

		String sql = "select * from personlist where name like '%" + key + "%' or email like '%" + key + "%'"
				+ "or birthday like '%" + key + "%'" 
				+ "or address like '%" + key + "%'"
				+ "or battery like '%" + key + "%'"
				+ "or homeNumber like '%" + key + "%'"
				+ "or phoneNumber like '%" + key + "%'";

		sql_data db = new sql_data();

		ArrayList<Object> list = new ArrayList<>();

		ResultSet rs = db.executeQuery(sql);
		try {
			while (rs.next()) {
				String birthday = rs.getString("birthday");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");
				String battery = rs.getString("battery");
				String site = rs.getString("site");
				String homeNumber = rs.getString("homeNumber");
				int id = rs.getInt("id");
				Person person = new Person();
				person.setBirthday(birthday);
				person.setName(name);
				person.setAddress(address);
				person.setPhoneNumber(phoneNumber);
				person.setEmail(email);
				person.setBattery(battery);
				person.setSite(site);
				person.setId(id);
				person.setHomeNumber(homeNumber);
				list.add(person);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String jsonString = JSON.toJSONString(list);
		out.println(jsonString);
	}
}
