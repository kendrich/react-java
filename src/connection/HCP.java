package connection;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

public class HCP {
	private static HikariDataSource dataSource;
	static{
		try {
			DBM db = ConnectionType.set("production"); //production
			dataSource = new HikariDataSource();
			dataSource.setDriverClassName(db.getDriver());
			
			dataSource.setJdbcUrl(db.getHost());
			dataSource.setUsername(db.getUser());
			dataSource.setPassword(db.getPassword());
			
			dataSource.setMinimumIdle(10);
			dataSource.setMaximumPoolSize(2000);//The maximum number of connections, idle or busy, that can be present in the pool.
			dataSource.setAutoCommit(false);
			dataSource.setLoginTimeout(3);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static DataSource getDataSource(){
		return dataSource;
	}
}
