/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.Date;

/**
 *
 * @author frei-
 */
public class AccountBean {
    private long accountId;
    private Long expertiseIdFK;
    private String email;
    private String fullName;
    private String password;
    private Date birthDate;
    private boolean isDoctor;
    
    public AccountBean() {}

    public AccountBean(long accountId, Long expertiseIdFK, String email, String fullName, String password, Date birthDate, boolean isDoctor) {
        this.accountId = accountId;
        this.expertiseIdFK = expertiseIdFK;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.birthDate = birthDate;
        this.isDoctor = isDoctor;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public Long getExpertiseIdFK() {
        return expertiseIdFK;
    }

    public void setExpertiseIdFK(Long expertiseIdFK) {
        this.expertiseIdFK = expertiseIdFK;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isIsDoctor() {
        return isDoctor;
    }

    public void setIsDoctor(boolean isDoctor) {
        this.isDoctor = isDoctor;
    }
}
