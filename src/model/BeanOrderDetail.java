package model;

import java.util.Objects;

public class BeanOrderDetail {
    private Integer detailId;
    private Integer prodNum;
    private BeanProduct product;
    private BeanMyOrder order;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getProdNum() {
        return prodNum;
    }

    public void setProdNum(Integer prodNum) {
        this.prodNum = prodNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanOrderDetail that = (BeanOrderDetail) o;
        return Objects.equals(detailId, that.detailId) &&
                Objects.equals(prodNum, that.prodNum);
    }

    @Override
    public int hashCode() {

        return Objects.hash(detailId, prodNum);
    }

    public BeanProduct getProduct() {
        return product;
    }

    public void setProduct(BeanProduct product) {
        this.product = product;
    }

    public BeanMyOrder getOrder() {
        return order;
    }

    public void setOrder(BeanMyOrder order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return this.getOrder().getOrderId().toString();
    }
}
