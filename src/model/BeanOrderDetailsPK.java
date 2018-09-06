package model;

import java.io.Serializable;
import java.util.Objects;

public class BeanOrderDetailsPK implements Serializable {
    private Integer orderId;
    private Integer prodId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanOrderDetailsPK that = (BeanOrderDetailsPK) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(prodId, that.prodId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId, prodId);
    }
}
