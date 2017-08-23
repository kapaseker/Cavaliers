package com.bfdelivery.cavaliers.database.userinfo;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "USER_INFO".
 */
@Entity
public class UserInfo implements java.io.Serializable {

    /**
     * 自增主键
     */
    @Id(autoincrement = true)
    private Long id;
    /**
     * 骑手的用户id
     */
    private String userId;
    /**
     * 用户名字
     */
    private String userName;
    /**
     * 用户联系电话
     */
    private String userPhone;
    /**
     * 用户的email
     */
    private String email;
    /**
     * 用户的创建时间
     */
    private Long createTime;
    /**
     * 用户信息的更新时间
     */
    private Long updateTime;

    @Generated
    public UserInfo() {
    }

    public UserInfo(Long id) {
        this.id = id;
    }

    @Generated
    public UserInfo(Long id, String userId, String userName, String userPhone, String email, Long createTime, Long updateTime) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.email = email;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

}
