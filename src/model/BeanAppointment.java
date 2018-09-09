package model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BeanAppointment {
    private Integer appId;
    private String appState;
    private BeanMyUser user;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getAppState() {
        return appState;
    }

    public void setAppState(String appState) {
        this.appState = appState;
    }

    @Override
    public String toString() {
        return this.getAppId().toString();
    }

    public BeanMyUser getUser() {
        return user;
    }

    public void setUser(BeanMyUser user) {
        this.user = user;
    }
}
