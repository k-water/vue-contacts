package servletAll.Upload;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import servletAll.contactPerson.Person;

public class ReadExcel {
	public List<Person> readXls(String path) throws IOException {
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		Person person = null;
		List<Person> list = new ArrayList<Person>();
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 循环行Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					person = new Person();
					HSSFCell name = hssfRow.getCell(0);
					HSSFCell email = hssfRow.getCell(1);
					HSSFCell birthday = hssfRow.getCell(2);
					HSSFCell address = hssfRow.getCell(3);
					HSSFCell phoneNumber = hssfRow.getCell(4);
					HSSFCell battery = hssfRow.getCell(5);
					HSSFCell site = hssfRow.getCell(6);
					HSSFCell homeNumber = hssfRow.getCell(7);

					person.setName(getValue(name));
					person.setEmail(getValue(email));
					person.setBirthday(getValue(birthday));
					person.setAddress(getValue(address));
					person.setPhoneNumber(getValue(phoneNumber));
					person.setBattery(getValue(battery));
					person.setSite(getValue(site));
					person.setHomeNumber(getValue(homeNumber));
					list.add(person);
				}
			}
		}
		return list;
	}

	@SuppressWarnings("static-access")
	private String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			// 返回布尔类型的值
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			// 返回数值类型的值
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			// 返回字符串类型的值
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
}
