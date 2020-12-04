package cn.appsys.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: APP
 * @description: 管理员实体类
 * @author: Xiaoxu
 * @create: 2020-11-26 14:17
 **/
public class BackendUser implements Serializable {
    private Integer id;         //主键id
    private  String userCode;   //用户编码
    private  String userName;   //用户名
    private String userPassword;//用户密码
    private  Integer userType;   //用户类型号
    private  Integer createBy;  //创建者
    private Date creationDate;  //创建时间
    private  Integer modifyBy;  //更新者
    private  Date modifyDate;   //最新更新时间
    private String  userTypeName;//用户类型名字


    public BackendUser() {
    }

    @Override
    public String toString() {
        return "BackendUser{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", userType=" + userType +
                ", createBy=" + createBy +
                ", creationDate=" + creationDate +
                ", modifyBy=" + modifyBy +
                ", modifyDate=" + modifyDate +
                ", userPassword='" + userPassword + '\'' +
                ", userTypeName='" + userTypeName + '\'' +
                '}';
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public BackendUser(Integer id, String userCode, String userName, Integer userType, Integer createBy, Date creationDate, Integer modifyBy, Date modifyDate, String userPassword, String userTypeName) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
        this.userType = userType;
        this.createBy = createBy;
        this.creationDate = creationDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.userPassword = userPassword;
        this.userTypeName = userTypeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getuserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}