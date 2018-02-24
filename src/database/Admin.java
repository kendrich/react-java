package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connection.HCP;

public class Admin {

	public static Map<String, List<List<String>>> Cars(){
		Map<String,List<List<String>>> cars = new HashMap<>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = HCP.getDataSource().getConnection();
			ps = connection.prepareStatement("SELECT a.*, b.name, b.description FROM tbl_car a LEFT JOIN tbl_department b ON a.dept_id=b.id");
			rs = ps.executeQuery();
			while(rs.next()) {
				
				List<String> _car = new ArrayList<>();
				_car.add(rs.getString("id"));
				_car.add(rs.getString("car_name"));
				_car.add(rs.getString("car_platenumber"));
				_car.add(rs.getString("gps_number"));
				_car.add(rs.getString("active"));
				_car.add(rs.getString("name"));
				
				if(cars.containsKey(rs.getString("name"))) {
					cars.get(rs.getString("name")).add(_car);
				}else {
					List<List<String>> car = new ArrayList<>();
					cars.put(rs.getString("name"), car);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) {e.printStackTrace();}
			if (ps != null) try { ps.close(); } catch (SQLException e) {e.printStackTrace();}
			if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
		}
		return cars;
	}
}
