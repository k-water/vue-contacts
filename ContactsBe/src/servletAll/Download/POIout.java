package servletAll.Download;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import servletAll.common.sql_data;

/**
 * Servlet implementation class POIout
 */
@WebServlet("/POIout")
public class POIout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public POIout() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
	}

	public void destroy() {
		super.destroy();
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

		String headers[] = { "id", "姓名", "邮箱", "生日", "地址", "手机", "分组", "个人网站", "家庭电话" };// 标题

		sql_data db = new sql_data();
		String sql = "SELECT * FROM `personlist`";

		ResultSet rs = db.executeQuery(sql);

		Workbook wb = new HSSFWorkbook();
		try {
			fillExcelData(rs, wb, headers);
			export(response, wb, "ImportContacts.xls");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 导出用户
	 * 
	 * @throws Exception
	 */
	public void fillExcelData(ResultSet rs, Workbook wb, String[] headers) throws Exception {
		int rowIndex = 0; // 第一行
		Sheet sheet = wb.createSheet(); // 创建sheet页
		Row row = sheet.createRow(rowIndex++);
		// 创建标题
		for (int i = 0; i < headers.length; i++) {
			row.createCell(i).setCellValue(headers[i]);
		}
		// 导出数据库中的数据
		while (rs.next()) {
			row = sheet.createRow(rowIndex++);
			for (int i = 0; i < headers.length; i++) {
				row.createCell(i).setCellValue(rs.getObject(i + 1).toString());
				// rs.getObject(i +
				// 1)得到一个对象，即数据库中一行的结果，每一列就是属性凑成一行变成对象。因为id是从1开始，所以要+1。
			}
		}
	}

	/**
	 * 把数据放入到.xls文件中并下载到本地
	 * 
	 * @param response
	 * @param wb
	 * @param fileName
	 * @throws Exception
	 */
	public void export(HttpServletResponse response, Workbook wb, String fileName) throws Exception {
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso8859-1"));// 设置头信息
		response.setContentType("application/ynd.ms-excel;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		wb.write(out);// 进行输出，下载到本地
		out.flush();
		out.close();
	}
}
