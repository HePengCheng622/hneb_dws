package com.hneb.dws.vo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/8.
 */
@Entity
@Table(name = "t_carte_list")
public class CarteListVO {
    private String cPkId;
    private String cUserId;
    private String cRecipeId;
    private String cRecipeUserId;
    private String cMealMrk;
    private String cRecipeNme;
    private Integer nWeight;
    private String cDesc;
    private String cCrtId;
    private Date tCrtTm;
    private String cUpdId;
    private Date tUpdTm;
    private String cCarteId;
    private Integer nDayNum;

    @Id
    @Column(name = "C_PK_ID")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid.hex")
    public String getcPkId() {
        return cPkId;
    }

    public void setcPkId(String cPkId) {
        this.cPkId = cPkId;
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
    @Column(name = "C_RECIPE_ID")
    public String getcRecipeId() {
        return cRecipeId;
    }

    public void setcRecipeId(String cRecipeId) {
        this.cRecipeId = cRecipeId;
    }

    @Basic
    @Column(name = "C_RECIPE_USER_ID")
    public String getcRecipeUserId() {
        return cRecipeUserId;
    }

    public void setcRecipeUserId(String cRecipeUserId) {
        this.cRecipeUserId = cRecipeUserId;
    }

    @Basic
    @Column(name = "C_MEAL_MRK")
    public String getcMealMrk() {
        return cMealMrk;
    }

    public void setcMealMrk(String cMealMrk) {
        this.cMealMrk = cMealMrk;
    }

    @Basic
    @Column(name = "C_RECIPE_NME")
    public String getcRecipeNme() {
        return cRecipeNme;
    }

    public void setcRecipeNme(String cRecipeNme) {
        this.cRecipeNme = cRecipeNme;
    }

    @Basic
    @Column(name = "N_WEIGHT")
    public Integer getnWeight() {
        return nWeight;
    }

    public void setnWeight(Integer nWeight) {
        this.nWeight = nWeight;
    }

    @Basic
    @Column(name = "C_DESC")
    public String getcDesc() {
        return cDesc;
    }

    public void setcDesc(String cDesc) {
        this.cDesc = cDesc;
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

        CarteListVO that = (CarteListVO) o;

        if (cPkId != null ? !cPkId.equals(that.cPkId) : that.cPkId != null) return false;
        if (cUserId != null ? !cUserId.equals(that.cUserId) : that.cUserId != null) return false;
        if (cRecipeId != null ? !cRecipeId.equals(that.cRecipeId) : that.cRecipeId != null) return false;
        if (cRecipeUserId != null ? !cRecipeUserId.equals(that.cRecipeUserId) : that.cRecipeUserId != null)
            return false;
        if (cMealMrk != null ? !cMealMrk.equals(that.cMealMrk) : that.cMealMrk != null) return false;
        if (cRecipeNme != null ? !cRecipeNme.equals(that.cRecipeNme) : that.cRecipeNme != null) return false;
        if (nWeight != null ? !nWeight.equals(that.nWeight) : that.nWeight != null) return false;
        if (cDesc != null ? !cDesc.equals(that.cDesc) : that.cDesc != null) return false;
        if (cCrtId != null ? !cCrtId.equals(that.cCrtId) : that.cCrtId != null) return false;
        if (tCrtTm != null ? !tCrtTm.equals(that.tCrtTm) : that.tCrtTm != null) return false;
        if (cUpdId != null ? !cUpdId.equals(that.cUpdId) : that.cUpdId != null) return false;
        if (tUpdTm != null ? !tUpdTm.equals(that.tUpdTm) : that.tUpdTm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cPkId != null ? cPkId.hashCode() : 0;
        result = 31 * result + (cUserId != null ? cUserId.hashCode() : 0);
        result = 31 * result + (cRecipeId != null ? cRecipeId.hashCode() : 0);
        result = 31 * result + (cRecipeUserId != null ? cRecipeUserId.hashCode() : 0);
        result = 31 * result + (cMealMrk != null ? cMealMrk.hashCode() : 0);
        result = 31 * result + (cRecipeNme != null ? cRecipeNme.hashCode() : 0);
        result = 31 * result + (nWeight != null ? nWeight.hashCode() : 0);
        result = 31 * result + (cDesc != null ? cDesc.hashCode() : 0);
        result = 31 * result + (cCrtId != null ? cCrtId.hashCode() : 0);
        result = 31 * result + (tCrtTm != null ? tCrtTm.hashCode() : 0);
        result = 31 * result + (cUpdId != null ? cUpdId.hashCode() : 0);
        result = 31 * result + (tUpdTm != null ? tUpdTm.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "C_CARTE_ID")
    public String getcCarteId() {
        return cCarteId;
    }

    public void setcCarteId(String cCarteId) {
        this.cCarteId = cCarteId;
    }

    @Basic
    @Column(name = "N_DAY_NUM")
    public Integer getnDayNum() {
        return nDayNum;
    }

    public void setnDayNum(Integer nDayNum) {
        this.nDayNum = nDayNum;
    }
}
