package com.bfdelivery.cavaliers.database.location;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "LOCATION_DATA".
 */
@Entity
public class LocationData implements java.io.Serializable {

    /**
     * 自增主键
     */
    @Id(autoincrement = true)
    private Long id;
    /**
     * 用户纬度
     */
    private Double latitude;
    /**
     * 用户经度
     */
    private Double longitude;
    /**
     * 更新的时间
     */
    private Long gentime;

    @Generated
    public LocationData() {
    }

    public LocationData(Long id) {
        this.id = id;
    }

    @Generated
    public LocationData(Long id, Double latitude, Double longitude, Long gentime) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.gentime = gentime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Long getGentime() {
        return gentime;
    }

    public void setGentime(Long gentime) {
        this.gentime = gentime;
    }

}
