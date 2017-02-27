package servletAll.Upload;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.*;
import com.jspsmart.upload.SmartUpload;
import servletAll.common.sql_data;
import servletAll.contactPerson.*;

/**
 * Servlet implementation class SmartUploadServlet
 */
@WebServlet("/SmartUploadServlet")
public class SmartUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SmartUploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp, String fileName)
			throws ServletException, IOException {
		
		String path = "F:/workspace_J2EE/ContactsBe/uploadFile/" + fileName;
		JSONReader reader = new JSONReader(new FileReader(path));
		reader.startObject();
		reader.readString();

		String tmp = reader.readString();
		List<Person> list = JSON.parseArray(tmp, Person.class);
		for (Person person : list) {
			String name = person.getName();
			int id = person.getId();
			String birthday = person.getBirthday();
			String email = person.getEmail();
			String phoneNumber = person.getPhoneNumber();
			String address = person.getAddress();
			String site = person.getSite();
			String group = person.getGroup();

			String sql = "insert into personlist(name,id,birthday,email,phoneNumber,address,site,battery) values('"
					+ name + "','" + id + "','" + birthday + "','" + email + "','" + phoneNumber + "','" + address
					+ "','" + site + "','" + group + "')";

			sql_data db = new sql_data();
			db.executeInsert(sql);
		}
		
		reader.endObject();
		reader.close();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		// 设置CORS
		resp.setHeader("Access-Control-Allow-Origin", "*");
		// 允许该域发起跨域请求
		resp.setHeader("Access-Control-Allow-Origin", "*");// *允许任何域
		// 允许的外域请求方式
		resp.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, TRACE, OPTIONS");
		// 在999999秒内，不需要再发送预检验请求，可以缓存该结果
		resp.setHeader("Access-Control-Max-Age", "999999");
		// 允许跨域请求包含某请求头,x-requested-with请求头为异步请求
		resp.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		resp.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, X-Auth-Token");

		// 设置编码
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		
		
		String filePath = "F:/workspace_J2EE/ContactsBe/uploadFile";
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}
		SmartUpload su = new SmartUpload();

		su.initialize(getServletConfig(), req, resp);

		// 上传单个文件的大小 10M
		su.setMaxFileSize(1024 * 1024 * 10);

		su.setTotalMaxFileSize(1024 * 1024 * 100);

		// 允许上传文件的类型
		su.setAllowedFilesList("json");

		String result = "上传成功";

		try {
			su.setDeniedFilesList("rar,jsp,js");

			su.upload();

			int count = su.save(filePath);
			System.out.println("上传了" + count + "个文件");
		} catch (Exception e) {
			result = "上传成功";
			e.printStackTrace();
		}
		String fileName = null;
		for (int i = 0; i < su.getFiles().getCount(); i++) {
			com.jspsmart.upload.File tempFile = su.getFiles().getFile(i);
			fileName = tempFile.getFilePathName();
			System.out.println("---------------------------");
			System.out.println("上传文件name的名称：" + tempFile.getFieldName());
			System.out.println("上传的文件是：" + tempFile.getFieldName());
			System.out.println("上传的文件大小：" + tempFile.getSize());
			System.out.println("上传的文件后缀名：" + tempFile.getFileExt());
			System.out.println("上传的文件的全名：" + tempFile.getFilePathName());
			System.out.println("---------------------------");
		}
		req.setAttribute("result", result);

		// 调用doGet将上传文件拼接到mysql中
		doGet(req, resp, fileName);
	}
}
