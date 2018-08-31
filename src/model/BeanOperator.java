package model;

import java.util.Objects;

public class BeanOperator {
    public static BeanOperator currentLoginUser=null;
    private Integer opId;
    private String opName;
    private Integer opLevel;
    private String opPwd;

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public Integer getOpLevel() {
        return opLevel;
    }

    public void setOpLevel(Integer opLevel) {
        this.opLevel = opLevel;
    }

    public String getOpPwd() {
        return opPwd;
    }

    public void setOpPwd(String opPwd) {
        this.opPwd = opPwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanOperator that = (BeanOperator) o;
        return Objects.equals(opId, that.opId) &&
                Objects.equals(opName, that.opName) &&
                Objects.equals(opLevel, that.opLevel) &&
                Objects.equals(opPwd, that.opPwd);
    }

    @Override
    public int hashCode() {

        return Objects.hash(opId, opName, opLevel, opPwd);
    }
}
