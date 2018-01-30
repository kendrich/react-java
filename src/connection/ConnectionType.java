package connection;

public class ConnectionType {
	public static DBM set(String TYPE) {
		DBM db = new DBM();
		switch (TYPE) {
		case "production":
			db.setHost("10.44.0.35");
			db.setDatabase("gpstracker");
			db.setUser("gps");
			db.setPassword("GPS.20root17");
			break;

		default:
			db.setHost("localhost");
			db.setDatabase("dev_gpstracker");
			db.setUser("root");
			db.setPassword("20root17");
			break;
		}
		return db;
	}
}
