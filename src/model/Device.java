package model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class Device {
    public static final String STATUS_UNKNOWN = "unknown";
    public static final String STATUS_ONLINE = "online";
    public static final String STATUS_OFFLINE = "offline";
    
    @Getter @Setter private int id;
    
    @Getter @Setter private String name;
    @Getter @Setter private String model;
    @Getter @Setter private String platenumber;
    @Getter @Setter private String gpsnumber;
    @Getter @Setter private boolean active; 
    @Getter @Setter private String groupid;
    private String status;
    private Date lastUpdate;

    public Date getLastUpdate() {
        if (lastUpdate != null) {
            return new Date(lastUpdate.getTime());
        } else {
            return null;
        }
    }

    public void setLastUpdate(Date lastUpdate) {
        if (lastUpdate != null) {
            this.lastUpdate = new Date(lastUpdate.getTime());
        } else {
            this.lastUpdate = null;
        }
    }

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status != null ? status : STATUS_OFFLINE;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
