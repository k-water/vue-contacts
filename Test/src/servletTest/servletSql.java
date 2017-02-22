package servletTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import servletTest.sqlSearch;
import servletTest.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.*;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class servletSql
 * 
 * @author water
 * @version 1.0
 * @email 625592890@qq.com
 * @date 2017-02-22
 */

@WebServlet("/servletSql")
public class servletSql extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection conn;

	/**
	 * @param request
	 * @param response
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 加载访问数据库的驱动
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		// 访问数据库的路径
		final String DB_URL = "jdbc:mysql://localhost/TEST?useUnicode=true&characterEncoding=utf-8";
		// 连接数据库的用户名和密码
		final String USER = "root";
		final String PASS = "root";

		// 设置请求头格式
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		// 设置CORS
		response.setHeader("Access-Control-Allow-Origin", "*");
		// 设置编码
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Statement stmt = null;
		try {

			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = ((Connection) conn).createStatement();
			String sql;
			sql = "SELECT birthday,battery,site,name,province,city,address,detailAddress,phoneNumber,email,zip FROM personList";
			ResultSet rs = stmt.executeQuery(sql);
			ArrayList<Object> list = new ArrayList<>();

			while (rs.next()) {

				String birthday = rs.getString("birthday");
				String name = rs.getString("name");
				String province = rs.getString("province");
				String city = rs.getString("city");
				String address = rs.getString("address");
				String detailAddress = rs.getString("detailAddress");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");
				String group = rs.getString("battery");
				String site = rs.getString("site");
				int zip = rs.getInt("zip");
				sqlSearch person = new sqlSearch();
				person.setBirthday(birthday);
				person.setName(name);
				person.setProvince(province);
				person.setCity(city);
				person.setAddress(address);
				person.setDetailAddress(detailAddress);
				person.setPhoneNumber(phoneNumber);
				person.setEmail(email);
				person.setZip(zip);
				person.setGroup(group);
				person.setSite(site);
				list.add(person);
			}
			String jsonString = JSON.toJSONString(list);
			out.println(jsonString);

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {

			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	/**
	 * @param req
	 * @param resp
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(req, resp);
		// 设置请求头格式
		resp.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");

		// 设置CORS
		resp.setHeader("Access-Control-Allow-Origin", "*");
		// 允许该域发起跨域请求
		resp.setHeader("Access-Control-Allow-Origin", "*");// *允许任何域
		// 允许的外域请求方式
		resp.setHeader("Access-Control-Allow-Methods", "POST, GET");
		// 在999999秒内，不需要再发送预检验请求，可以缓存该结果
		resp.setHeader("Access-Control-Max-Age", "999999");
		// 允许跨域请求包含某请求头,x-requested-with请求头为异步请求
		resp.setHeader("Access-Control-Allow-Headers", "x-requested-with");

		// 设置编码
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");

		// 获取前台返回的数据
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phoneNumber = req.getParameter("phoneNumber");
		String birthday = req.getParameter("birthday");
		String address = req.getParameter("address");
		String site = req.getParameter("site");
		String group = req.getParameter("battery");

		// System.out.println(birthday);
		// System.out.println(email);
		// System.out.println(name);

		// 存储到mysql中
		sqlSearch person = new sqlSearch();
		person.setName(name);
		person.setEmail(email);
		person.setPhoneNumber(phoneNumber);
		person.setBirthday(birthday);
		person.setAddress(address);
		person.setSite(site);
		person.setGroup(group);
		insertDate(person);
	}

	// 插入sql语句的函数
	/**
	 * 
	 * @param person
	 */
	private void insertDate(sqlSearch person) {
		Connection con = null; // 一个连接对象
		con = (Connection) DB.getcon(con); // 得到一个连接
		PreparedStatement ps = null; // 用于插入数据
		// sql语句，向表user里面，插入name和pass的值
		String sql = "insert into personlist(name,email,phoneNumber,birthday,address,site,battery) values(?,?,?,?,?,?,?)";
		ps = (PreparedStatement) DB.getpsta(con, sql);
		try {
			ps.setString(1, person.getName());
			ps.setString(2, person.getEmail());
			ps.setString(3, person.getPhoneNumber());
			ps.setString(4, person.getBirthday());
			ps.setString(5, person.getAddress());
			ps.setString(6, person.getSite());
			ps.setString(7, person.getGroup());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}