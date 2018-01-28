package models;

import lombok.Data;

@Data
public final class User {
	private String ID;
	private String Username;
	private String Name;
	private String Password;
	private String CreateAt;
	private String UpdatedAt;
	private String token;
	private boolean isLoggedIn;
}
