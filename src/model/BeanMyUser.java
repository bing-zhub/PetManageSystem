package model;

import java.util.Objects;

public class BeanMyUser {
    private Integer userId;
    private String userName;
    private String userTel;
    private String userEmail;
    private String userContact;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanMyUser that = (BeanMyUser) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userTel, that.userTel) &&
                Objects.equals(userEmail, that.userEmail) &&
                Objects.equals(userContact, that.userContact);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, userName, userTel, userEmail, userContact);
    }

    @Override
    public String toString() {
        return getUserName();
    }
}
