package connection;

public class ConnectionType {
	public static DBM set(String TYPE) {
		DBM db = new DBM();
		switch (TYPE) {
		case "production":
			db.setHost("localhost");
			db.setDatabase("react");
			db.setUser("root");
			db.setPassword("20root17");
			break;

		default:
			db.setHost("localhost");
			db.setDatabase("react");
			db.setUser("root");
			db.setPassword("20root17");
			break;
		}
		return db;
	}
}
