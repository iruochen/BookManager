package com.ruochen.domain;

/**
 * 管理员实体
 */
public class Admin {
    private Integer id;
    private String adminId;
    private String adminName;
    private String sex;
    private Integer userId;
    /**
     * 对应用户
     */
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adminId='" + adminId + '\'' +
                ", adminName='" + adminName + '\'' +
                ", userId=" + userId +
                ", sex='" + sex + '\'' +
                ", user=" + user +
                '}';
    }
}
