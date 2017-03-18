package servletAll.perosnGroup;

import java.sql.*;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servletAll.common.sql_data;

/**
 * Servlet implementation class addGroup
 * 
 * @author water
 * @date 2017-02-24 22:11
 */
@WebServlet("/addGroup")
public class addGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addGroup() {
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
		response.setHeader("Access-Control-Allow-Methods", "POST, GET");
		// 在999999秒内，不需要再发送预检验请求，可以缓存该结果
		response.setHeader("Access-Control-Max-Age", "999999");
		// 允许跨域请求包含某请求头,x-requested-with请求头为异步请求
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

		// 设置编码
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		String text = request.getParameter("text");
		String value = request.getParameter("value");
		System.out.println(value);
		String searchSql = "SELECT * FROM `grouplist`";
		String insertSql = "insert into grouplist(text,value) values('" + text + "','" + value + "')";

		sql_data db = new sql_data();
		int flag = judge(searchSql, value);
		 System.out.println(flag);
		if (text == "" || value == "" || flag == 1) {
			System.out.println("没有插入数据");
			return;
		} else {
			db.executeInsert(insertSql);
			System.out.println("插入了新的分组：" + value);
		}

	}

	// sql查询语句 比较前台返回的数据是否存在相同的
	public int judge(String sql, String value) {
		sql_data db = new sql_data();
		ResultSet rs = db.executeQuery(sql);
		int flag = 0;
		try {
			while (rs.next()) {
				String valueSql = rs.getString("value");
				System.out.println(valueSql);
				if (value.equals(valueSql)) {
					flag = 1;
					return flag;
				} else {
					continue;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

}
