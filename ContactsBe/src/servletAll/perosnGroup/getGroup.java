package servletAll.perosnGroup;

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
import servletAll.perosnGroup.Group;

/**
 * Servlet implementation class getGroup
 * 
 * @author water
 * @date 2017-02-24 19:03
 * 
 */
@WebServlet("/getGroup")
public class getGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getGroup() {
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

		// 设置请求头格式
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		// 设置CORS
		response.setHeader("Access-Control-Allow-Origin", "*");
		// 设置编码
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		sql_data db = new sql_data();

		try {

			String sql = "SELECT * FROM `grouplist`";
			ResultSet rs = db.executeQuery(sql);
			ArrayList<Object> list = new ArrayList<>();

			while (rs.next()) {
				String text = rs.getString("text");
				String value = rs.getString("value");
				Group grouplist = new Group();
				grouplist.setText(text);
				grouplist.setValue(value);
				list.add(grouplist);
			}
			String jsonString = JSON.toJSONString(list);
			out.println(jsonString);

			rs.close();
		} catch (SQLException se) {

			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
