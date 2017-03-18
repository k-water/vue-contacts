package servletAll.Download;

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;

import servletAll.common.sql_data;

/**
 * Servlet implementation class POIoutTemplate
 */
@WebServlet("/POIoutTemplate")
public class POIoutTemplate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public POIoutTemplate() {
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
		response.setContentType("text/html;charset=utf-8");

		// String headers[] = { "id", "姓名", "邮箱", "生日", "地址", "手机", "分组",
		// "个人网站", "家庭电话" };// 标题

		sql_data db = new sql_data();
		String sql = "SELECT * FROM `personlist`";

		ResultSet rs = db.executeQuery(sql);

		try {
			Workbook wb = fillExcelDataWithTemplate(rs, "Template.xls");
			export(response, wb, "利用模板导出的数据.xls");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据模板导出用户
	 * 
	 * @throws Exception
	 */
	public Workbook fillExcelDataWithTemplate(ResultSet rs, String templateFileName) throws Exception {
		InputStream in = POIoutTemplate.class.getResourceAsStream("/servletAll/Download/" + templateFileName);
		POIFSFileSystem fs = new POIFSFileSystem(in);// 解析Excel文件
		Workbook wb = new HSSFWorkbook(fs);// 以解析的excel文件格式进行创建
		Sheet sheet = wb.getSheetAt(0);
		// 获取列数
		int cellNums = sheet.getRow(0).getLastCellNum();

		int rowIndex = 1;// 从1开始的原因，模板的第一行固定了，所以从下一行开始
		while (rs.next()) {
			Row row = sheet.createRow(rowIndex++);
			for (int i = 0; i < cellNums; i++) {
				row.createCell(i).setCellValue(rs.getObject(i + 1).toString());
			}
		}
		return wb;
	}

	/**
	 * 导出
	 * 
	 * @param response
	 * @param wb
	 * @param fileName
	 * @throws Exception
	 */
	public static void export(HttpServletResponse response, Workbook wb, String fileName) throws Exception {
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso8859-1"));
		response.setContentType("application/ynd.ms-excel;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		wb.write(out);
		out.flush();
		out.close();
	}

}
