package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import connection.HCP;
import models.Location;
import models.User;

public class DB {
	public static User login(String username, String password) {
		User user=new User();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = HCP.getDataSource().getConnection();
			ps = connection.prepareStatement("SELECT * FROM accounts WHERE username=?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()) {
				user.setPassword(rs.getString("password"));
				if(BCrypt.checkpw(password, user.getPassword())) {
					user.setID(rs.getString("account_id"));
					user.setName(rs.getString("firstname"));
					user.setCreateAt(rs.getString("date_created"));
					user.setUpdatedAt(rs.getString("date_updated"));
					user.setSuperuser(rs.getBoolean("is_superuser"));
					user.setLoggedIn(true);
				}
			}
			user.setUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) {e.printStackTrace();}
			if (ps != null) try { ps.close(); } catch (SQLException e) {e.printStackTrace();}
			if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
		}
		return user;
	}
	
	public static Map<String, Location> GetLocation() {
		Map<String, Location> location = new HashMap<String, Location>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = HCP.getDataSource().getConnection();
			ps = connection.prepareStatement("SELECT a.*, b.car_name, b.car_platenumber, b.color_line, b.driver_fullname FROM tbl_sms_log a\r\n" + 
					"LEFT JOIN tbl_car b ON a.gps_number=b.gps_number\r\n" + 
					"WHERE gps_datetime IN (\r\n" + 
					"	SELECT MAX(a.gps_datetime)\r\n" + 
					"	FROM tbl_sms_log a WHERE DATE(gps_datetime)=DATE('2018-02-01') GROUP BY gps_number DESC\r\n" + 
					") \r\n" + 
					"GROUP BY gps_number, lat, lon, address");
			rs = ps.executeQuery();
			while(rs.next()) {
				Location loc = new Location();
				loc.setGPSNumber(rs.getString("gps_number"));
				loc.setLat(rs.getString("lat"));
				loc.setLon(rs.getString("lon"));
				loc.setAddress(rs.getString("address"));
				loc.setCourse(rs.getString("course"));
				loc.setSpeed(rs.getString("speed"));
				location.put(rs.getString("gps_number") ,loc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) {e.printStackTrace();}
			if (ps != null) try { ps.close(); } catch (SQLException e) {e.printStackTrace();}
			if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
		}
		
		return location;
	}
}
