package model;

import java.util.Arrays;
import java.util.Objects;

public class BeanPet {
    private Integer petId;
    private String petNikename;
    private String petAlias;
    private byte[] petImg;
    private BeanMyUser user;

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public String getPetNikename() {
        return petNikename;
    }

    public void setPetNikename(String petNikename) {
        this.petNikename = petNikename;
    }

    public String getPetAlias() {
        return petAlias;
    }

    public void setPetAlias(String petAlias) {
        this.petAlias = petAlias;
    }


    public byte[] getPetImg() {
        return petImg;
    }

    public void setPetImg(byte[] petImg) {
        this.petImg = petImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanPet beanPet = (BeanPet) o;
        return Objects.equals(petId, beanPet.petId) &&
                Objects.equals(petNikename, beanPet.petNikename) &&
                Objects.equals(petAlias, beanPet.petAlias) &&
                Arrays.equals(petImg, beanPet.petImg);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(petId, petNikename, petAlias);
        result = 31 * result + Arrays.hashCode(petImg);
        return result;
    }

    public BeanMyUser getUser() {
        return user;
    }

    public void setUser(BeanMyUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return this.getPetNikename();
    }
}
