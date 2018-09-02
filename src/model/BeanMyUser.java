package model;

import java.util.Objects;

public class BeanMyUser {
    private Integer userId;
    private String userName;
    private Integer userTel;
    private String userEmail;
    private String userContact;
    public static final String[] tableTitles={"ID","名称","手机号码","电子邮箱","其他联系方式"};

    public String getCell(int col){
        if(col==0) return this.userId.toString();
        else if(col==1) return this.userName.toString();
        else if(col==2) return this.userTel.toString();
        else if(col==3) return this.userEmail.toString();
        else if(col==4) return this.userContact.toString();
        else return "";
    }

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

    public Integer getUserTel() {
        return userTel;
    }

    public void setUserTel(Integer userTel) {
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
}
