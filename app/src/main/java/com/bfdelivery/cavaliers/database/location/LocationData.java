package com.bfdelivery.cavaliers.database.location;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * LocationData
 */
@Entity(nameInDb = "tb_location")
public class LocationData {
	/**
	 * data id
	 */
	@Id(autoincrement = true)
	private Long id;
	/**
	 * position's latitude
	 */
	private double latitude;
	/**
	 * position's longitude
	 */
	private double longitude;
	/**
	 * the data's generate time
	 */
	private long gentime;

	@Generated(hash = 1713664684)
	public LocationData(Long id, double latitude, double longitude, long gentime) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.gentime = gentime;
	}

	@Generated(hash = 1606831457)
	public LocationData() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public long getGentime() {
		return this.gentime;
	}

	public void setGentime(long gentime) {
		this.gentime = gentime;
	}
}
