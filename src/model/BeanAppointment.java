package model;

import java.sql.Date;
import java.util.Objects;

public class BeanAppointment {
    private Integer appId;
    private Integer petId;
    private Date appDate;
    private Date appDoneDate;
    private String appState;
    private Integer userId;
    private Integer appServ;
    public static final String[] tableTitles={"预约ID","预约用户","预约宠物","预约服务","预约时间","完成时间","状态"};

    public String getCell(int col){
        if(col==0) return Integer.toString(this.appId);
        else if(col==1) return Integer.toString(userId);
        else if(col==2) return Integer.toString(this.petId);
        else if(col==3) return Integer.toString(appServ);
        else if(col==4) return appDate.toString();
        else if(col==5) {
            if(appDoneDate.toString().equals((new Date(0)).toString())){
                return "未完成";
            } else {
                return appDoneDate.toString();
            }
        }
        else if(col==6) return appState;
        else return "";
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer appPet) {
        this.petId = appPet;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public Date getAppDoneDate() {
        return appDoneDate;
    }

    public void setAppDoneDate(Date appDoneDate) {
        this.appDoneDate = appDoneDate;
    }

    public String getAppState() {
        return appState;
    }

    public void setAppState(String appState) {
        this.appState = appState;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer appUser) {
        this.userId = appUser;
    }

    public Integer getAppServ() {
        return appServ;
    }

    public void setAppServ(Integer appServ) {
        this.appServ = appServ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanAppointment that = (BeanAppointment) o;
        return Objects.equals(appId, that.appId) &&
                Objects.equals(petId, that.petId) &&
                Objects.equals(appDate, that.appDate) &&
                Objects.equals(appDoneDate, that.appDoneDate) &&
                Objects.equals(appState, that.appState) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(appServ, that.appServ);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appId, petId, appDate, appDoneDate, appState, userId, appServ);
    }
}
