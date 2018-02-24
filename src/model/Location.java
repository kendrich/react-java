package model;

import lombok.Data;

@Data
public class Location {
	private String ID;
	private String GPSNumber;
	private String lat;
	private String lon;
	private String address;
	private String Course;
	private String Speed;
	private String DateTime;
	
	private String CarName;
	private String Platenumber;
	private String ColorLine;
	private String DriverName;
}
