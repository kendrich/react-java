package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import connection.HCP;
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
}
