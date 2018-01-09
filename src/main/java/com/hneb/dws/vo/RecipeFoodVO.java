package com.hneb.dws.vo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/4.
 */
@Entity
@Table(name = "t_recipe_food")
public class RecipeFoodVO {
    private String cPkId;
    private String cFoodId;
    private Integer nWeight;
    private String cUserId;
    private String cCrtId;
    private Date tCrtTm;
    private String cUpdId;
    private Date tUpdTm;
    private String cFoodNme;
    private String cRecipeId;

    public void setnWeight(Integer nWeight) {
        this.nWeight = nWeight;
    }

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid.hex")
    @Column(name = "C_PK_ID")
    public String getcPkId() {
        return cPkId;
    }

    public void setcPkId(String cPkId) {
        this.cPkId = cPkId;
    }

    @Basic
    @Column(name = "C_FOOD_ID")
    public String getcFoodId() {
        return cFoodId;
    }

    public void setcFoodId(String cFoodId) {
        this.cFoodId = cFoodId;
    }

    @Basic
    @Column(name = "N_WEIGHT")
    public Integer getnWeight() {
        return nWeight;
    }

    public void setnWeight(int nWeight) {
        this.nWeight = nWeight;
    }


    @Basic
    @Column(name = "C_USER_ID")
    public String getcUserId() {
        return cUserId;
    }

    public void setcUserId(String cUserId) {
        this.cUserId = cUserId;
    }

    @Basic
    @Column(name = "C_CRT_ID")
    public String getcCrtId() {
        return cCrtId;
    }

    public void setcCrtId(String cCrtId) {
        this.cCrtId = cCrtId;
    }

    @Basic
    @Column(name = "T_CRT_TM")
    public Date gettCrtTm() {
        return tCrtTm;
    }

    public void settCrtTm(Date tCrtTm) {
        this.tCrtTm = tCrtTm;
    }

    @Basic
    @Column(name = "C_UPD_ID")
    public String getcUpdId() {
        return cUpdId;
    }

    public void setcUpdId(String cUpdId) {
        this.cUpdId = cUpdId;
    }

    @Basic
    @Column(name = "T_UPD_TM")
    public Date gettUpdTm() {
        return tUpdTm;
    }

    public void settUpdTm(Date tUpdTm) {
        this.tUpdTm = tUpdTm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeFoodVO that = (RecipeFoodVO) o;

        if (cPkId != null ? !cPkId.equals(that.cPkId) : that.cPkId != null) return false;
        if (cFoodId != null ? !cFoodId.equals(that.cFoodId) : that.cFoodId != null) return false;
        if (nWeight != null ? !nWeight.equals(that.nWeight) : that.nWeight != null) return false;
        if (cUserId != null ? !cUserId.equals(that.cUserId) : that.cUserId != null) return false;
        if (cCrtId != null ? !cCrtId.equals(that.cCrtId) : that.cCrtId != null) return false;
        if (tCrtTm != null ? !tCrtTm.equals(that.tCrtTm) : that.tCrtTm != null) return false;
        if (cUpdId != null ? !cUpdId.equals(that.cUpdId) : that.cUpdId != null) return false;
        if (tUpdTm != null ? !tUpdTm.equals(that.tUpdTm) : that.tUpdTm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cPkId != null ? cPkId.hashCode() : 0;
        result = 31 * result + (cFoodId != null ? cFoodId.hashCode() : 0);
        result = 31 * result + (nWeight != null ? nWeight.hashCode() : 0);
        result = 31 * result + (cUserId != null ? cUserId.hashCode() : 0);
        result = 31 * result + (cCrtId != null ? cCrtId.hashCode() : 0);
        result = 31 * result + (tCrtTm != null ? tCrtTm.hashCode() : 0);
        result = 31 * result + (cUpdId != null ? cUpdId.hashCode() : 0);
        result = 31 * result + (tUpdTm != null ? tUpdTm.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "C_FOOD_NME")
    public String getcFoodNme() {
        return cFoodNme;
    }

    public void setcFoodNme(String cFoodNme) {
        this.cFoodNme = cFoodNme;
    }

    @Basic
    @Column(name = "C_RECIPE_ID")
    public String getcRecipeId() {
        return cRecipeId;
    }

    public void setcRecipeId(String cRecipeId) {
        this.cRecipeId = cRecipeId;
    }
}
