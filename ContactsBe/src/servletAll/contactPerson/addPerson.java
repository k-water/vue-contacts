package servletAll.contactPerson;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;
import servletAll.common.*;
import servletAll.contactPerson.Person;
/**
 * Servlet implementation class addPerson
 */
@WebServlet("/addPerson")
public class addPerson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addPerson() {
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
		// TODO Auto-generated method stub
		// 设置请求头格式
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");

		// 设置CORS
		response.setHeader("Access-Control-Allow-Origin", "*");
		// 允许该域发起跨域请求
		response.setHeader("Access-Control-Allow-Origin", "*");// *允许任何域
		// 允许的外域请求方式
		response.setHeader("Access-Control-Allow-Methods", "POST, GET");
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
		String group = request.getParameter("battery");

		// System.out.println(birthday);
		// System.out.println(email);
		// System.out.println(name);

		// 存储到mysql中
		Person person = new Person();
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
	private void insertDate(Person person) {
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
