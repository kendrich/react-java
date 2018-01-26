package connection;

import lombok.Data;
import utils.Helper;

@Data
public class DBM {
	private String Driver = "com.mysql.cj.jdbc.Driver";
	private String Host;
	private String Port = "3306";
	private String User;
	private String Password;
	private String Database;
	private String IP;
	/**
	 * @return the host
	 */
	public String getHost() {
//		Helper.parameterBuilder("allowMultiQueries", "true").concat("&") + 
		return "jdbc:mysql://".concat(Host).concat(":".concat(getPort()).concat("/").concat(getDatabase()).concat("?").concat(
					Helper.parameterBuilder("useSSL", "false").concat("&") + 
					Helper.parameterBuilder("noAccessToProcedureBodies", "true")
					)
				);
	}
}
