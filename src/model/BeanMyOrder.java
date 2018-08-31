package model;

import java.util.Objects;

public class BeanMyOrder {
    private Integer orderId;
    private Integer orderProd;
    private Integer orderUser;
    private Integer orderNum;
    private Integer orderPrice;
    private String orderState;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderProd() {
        return orderProd;
    }

    public void setOrderProd(Integer orderProd) {
        this.orderProd = orderProd;
    }

    public Integer getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(Integer orderUser) {
        this.orderUser = orderUser;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanMyOrder that = (BeanMyOrder) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(orderProd, that.orderProd) &&
                Objects.equals(orderUser, that.orderUser) &&
                Objects.equals(orderNum, that.orderNum) &&
                Objects.equals(orderPrice, that.orderPrice) &&
                Objects.equals(orderState, that.orderState);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId, orderProd, orderUser, orderNum, orderPrice, orderState);
    }
}
