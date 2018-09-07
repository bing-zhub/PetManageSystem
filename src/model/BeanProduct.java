package model;

import java.util.Objects;

public class BeanProduct {
    private Integer prodId;
    private String prodName;
    private BeanCategory prodCategory;
    private String prodBrand;
    private Integer prodPrice;
    private String prodBarcode;
    public static final String[] tableTitles={"ID","����","���","Ʒ��","�۸�","������"};

    public String getCell(int col){
        if(col==0) return this.prodId.toString();
        else if(col==1) return this.prodName.toString();
        else if(col==3) return this.prodBrand.toString();
        else if(col==4) return this.prodPrice.toString();
        else if(col==5) return this.prodBarcode.toString();
        else return "";
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }


    public String getProdBrand() {
        return prodBrand;
    }

    public void setProdBrand(String prodBrand) {
        this.prodBrand = prodBrand;
    }

    public Integer getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(Integer prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdBarcode() {
        return prodBarcode;
    }

    public void setProdBarcode(String prodBarcode) {
        this.prodBarcode = prodBarcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanProduct that = (BeanProduct) o;
        return Objects.equals(prodId, that.prodId) &&
                Objects.equals(prodName, that.prodName) &&
                Objects.equals(prodBrand, that.prodBrand) &&
                Objects.equals(prodPrice, that.prodPrice) &&
                Objects.equals(prodBarcode, that.prodBarcode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(prodId, prodName, prodBrand, prodPrice, prodBarcode);
    }

    @Override
    public String toString() {
        return getProdName();
    }


    public BeanCategory getProdCategory() {
        return prodCategory;
    }

    public void setProdCategory(BeanCategory prodCategory) {
        this.prodCategory = prodCategory;
    }
}
