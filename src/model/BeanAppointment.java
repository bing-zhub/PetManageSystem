package model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BeanAppointment {
    private Integer appId;
    private Integer petId;
    private Date appDate;
    private Date appDoneDate;
    private String appState;
    private Integer userId;
    private Integer appServ;

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

    @Override
    public String toString() {
        return this.getAppId().toString();
    }
}
