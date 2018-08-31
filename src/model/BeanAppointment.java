package model;

import java.sql.Date;
import java.util.Objects;

public class BeanAppointment {
    private Integer appId;
    private Integer appPet;
    private Date appDate;
    private Date appDoneDate;
    private String appState;
    private Integer appUser;
    private Integer appServ;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getAppPet() {
        return appPet;
    }

    public void setAppPet(Integer appPet) {
        this.appPet = appPet;
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

    public Integer getAppUser() {
        return appUser;
    }

    public void setAppUser(Integer appUser) {
        this.appUser = appUser;
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
                Objects.equals(appPet, that.appPet) &&
                Objects.equals(appDate, that.appDate) &&
                Objects.equals(appDoneDate, that.appDoneDate) &&
                Objects.equals(appState, that.appState) &&
                Objects.equals(appUser, that.appUser) &&
                Objects.equals(appServ, that.appServ);
    }

    @Override
    public int hashCode() {

        return Objects.hash(appId, appPet, appDate, appDoneDate, appState, appUser, appServ);
    }
}
