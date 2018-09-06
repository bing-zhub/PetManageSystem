package model;

import java.io.Serializable;
import java.util.Objects;

public class BeanOrderDetails implements Serializable{
    private Integer orderId;
    private Integer prodId;
    private Integer num;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanOrderDetails that = (BeanOrderDetails) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(prodId, that.prodId) &&
                Objects.equals(num, that.num);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId, prodId, num);
    }
}
