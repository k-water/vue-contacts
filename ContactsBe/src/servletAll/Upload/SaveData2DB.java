package servletAll.Upload;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import servletAll.contactPerson.Person;
import servletAll.common.*;

public class SaveData2DB {

	public void save(String path) throws IOException, SQLException {
		
		int size = 1000;
		Set<Integer> set = generateRandomArray(size);
		Object[] values = new Object[size];
		values = set.toArray();
		
		ReadExcel xlsMain = new ReadExcel();
		int id;
		String name = null;
		String email = null;
		String birthday = null;
		String address = null;
		String phoneNumber = null;
		String battery = null;
		String site = null;
		String homeNumber = null;
		List<Person> list = xlsMain.readXls(path);

		sql_data db = new sql_data();
		String sql = null;

		for (int i = 0; i < list.size(); i++) {

			id = (int) values[i];
			name = list.get(i).getName();
			email = list.get(i).getEmail();
			birthday = list.get(i).getBirthday();
			address = list.get(i).getAddress();
			phoneNumber = list.get(i).getPhoneNumber();
			battery = list.get(i).getBattery();
			site = list.get(i).getSite();
			homeNumber = list.get(i).getHomeNumber();

			sql = "insert into personlist(id,name,email,birthday,address,phoneNumber,battery,site,homeNumber) values('"
					+ id + "','" + name + "','" + email + "','" + birthday + "','" + address + "','" + phoneNumber
					+ "','" + battery + "','" + site + "','" + homeNumber + "')";

			db.executeInsert(sql);
		}
	}

	public static Set<Integer> generateRandomArray(int size) {

		Set<Integer> set = new LinkedHashSet<Integer>();
		Integer num = size;
		Integer range = size;
		Random ran = new Random();
		while (set.size() < num) {
			Integer tmp = ran.nextInt(range);
			set.add(tmp);
		}
		return set;
	}
}
