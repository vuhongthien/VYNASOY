package vn.fis.logfile.vinasoy.config.security.model;//package vn.fis.logfile.vinasoy.config.security.model;

import javax.persistence.*;

@Entity
@Table(name = "App_User")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Id")
    private Long userId;

    @Column(name = "User_Name")
    private String userName;

    @Column(name = "Encryted_Password")
    private String encrytedPassword;

    @Column(name = "Enabled")
    private Long enabled;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncrytedPassword() {
        return encrytedPassword;
    }

    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }

    public Long getEnabled() {
        return enabled;
    }

    public void setEnabled(Long enabled) {
        this.enabled = enabled;
    }
}