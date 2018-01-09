package com.hneb.dws.vo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/8.
 */
@Entity
@Table(name = "t_carte")
public class CarteVO {
    private String cPkId;
    private String cUserId;
    private String cParentId;
    private String cChildId;
    private String cChildNme;
    private String cChildSex;
    private Date tBirthday;
    private String cPhoneNo;
    private String cStd;
    private Date tBgnTm;
    private Date tEndTm;
    private Float nHeight;
    private Float nWeight;
    private String cHistory;
    private String cTaboo;
    private String cSpeTaboo;
    private String cMemo;
    private String cCrtId;
    private Date tCrtTm;
    private String cUpdId;
    private Date tUpdTm;

    @Id
    @Column(name = "C_PK_ID")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
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
    @Column(name = "C_PARENT_ID")
    public String getcParentId() {
        return cParentId;
    }

    public void setcParentId(String cParentId) {
        this.cParentId = cParentId;
    }

    @Basic
    @Column(name = "C_CHILD_ID")
    public String getcChildId() {
        return cChildId;
    }

    public void setcChildId(String cChildId) {
        this.cChildId = cChildId;
    }

    @Basic
    @Column(name = "C_CHILD_NME")
    public String getcChildNme() {
        return cChildNme;
    }

    public void setcChildNme(String cChildNme) {
        this.cChildNme = cChildNme;
    }

    @Basic
    @Column(name = "C_CHILD_SEX")
    public String getcChildSex() {
        return cChildSex;
    }

    public void setcChildSex(String cChildSex) {
        this.cChildSex = cChildSex;
    }

    @Basic
    @Column(name = "T_BIRTHDAY")
    public Date gettBirthday() {
        return tBirthday;
    }

    public void settBirthday(Date tBirthday) {
        this.tBirthday = tBirthday;
    }

    @Basic
    @Column(name = "C_PHONE_NO")
    public String getcPhoneNo() {
        return cPhoneNo;
    }

    public void setcPhoneNo(String cPhoneNo) {
        this.cPhoneNo = cPhoneNo;
    }

    @Basic
    @Column(name = "C_STD")
    public String getcStd() {
        return cStd;
    }

    public void setcStd(String cStd) {
        this.cStd = cStd;
    }

    @Basic
    @Column(name = "T_BGN_TM")
    public Date gettBgnTm() {
        return tBgnTm;
    }

    public void settBgnTm(Date tBgnTm) {
        this.tBgnTm = tBgnTm;
    }

    @Basic
    @Column(name = "T_END_TM")
    public Date gettEndTm() {
        return tEndTm;
    }

    public void settEndTm(Date tEndTm) {
        this.tEndTm = tEndTm;
    }

    @Basic
    @Column(name = "N_HEIGHT")
    public Float getnHeight() {
        return nHeight;
    }

    public void setnHeight(Float nHeight) {
        this.nHeight = nHeight;
    }

    @Basic
    @Column(name = "N_WEIGHT")
    public Float getnWeight() {
        return nWeight;
    }

    public void setnWeight(Float nWeight) {
        this.nWeight = nWeight;
    }

    @Basic
    @Column(name = "C_HISTORY")
    public String getcHistory() {
        return cHistory;
    }

    public void setcHistory(String cHistory) {
        this.cHistory = cHistory;
    }

    @Basic
    @Column(name = "C_TABOO")
    public String getcTaboo() {
        return cTaboo;
    }

    public void setcTaboo(String cTaboo) {
        this.cTaboo = cTaboo;
    }

    @Basic
    @Column(name = "C_SPE_TABOO")
    public String getcSpeTaboo() {
        return cSpeTaboo;
    }

    public void setcSpeTaboo(String cSpeTaboo) {
        this.cSpeTaboo = cSpeTaboo;
    }

    @Basic
    @Column(name = "C_MEMO")
    public String getcMemo() {
        return cMemo;
    }

    public void setcMemo(String cMemo) {
        this.cMemo = cMemo;
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

        CarteVO carteVO = (CarteVO) o;

        if (cPkId != null ? !cPkId.equals(carteVO.cPkId) : carteVO.cPkId != null) return false;
        if (cUserId != null ? !cUserId.equals(carteVO.cUserId) : carteVO.cUserId != null) return false;
        if (cParentId != null ? !cParentId.equals(carteVO.cParentId) : carteVO.cParentId != null) return false;
        if (cChildId != null ? !cChildId.equals(carteVO.cChildId) : carteVO.cChildId != null) return false;
        if (cChildNme != null ? !cChildNme.equals(carteVO.cChildNme) : carteVO.cChildNme != null) return false;
        if (cChildSex != null ? !cChildSex.equals(carteVO.cChildSex) : carteVO.cChildSex != null) return false;
        if (tBirthday != null ? !tBirthday.equals(carteVO.tBirthday) : carteVO.tBirthday != null) return false;
        if (cPhoneNo != null ? !cPhoneNo.equals(carteVO.cPhoneNo) : carteVO.cPhoneNo != null) return false;
        if (cStd != null ? !cStd.equals(carteVO.cStd) : carteVO.cStd != null) return false;
        if (tBgnTm != null ? !tBgnTm.equals(carteVO.tBgnTm) : carteVO.tBgnTm != null) return false;
        if (tEndTm != null ? !tEndTm.equals(carteVO.tEndTm) : carteVO.tEndTm != null) return false;
        if (nHeight != null ? !nHeight.equals(carteVO.nHeight) : carteVO.nHeight != null) return false;
        if (nWeight != null ? !nWeight.equals(carteVO.nWeight) : carteVO.nWeight != null) return false;
        if (cHistory != null ? !cHistory.equals(carteVO.cHistory) : carteVO.cHistory != null) return false;
        if (cTaboo != null ? !cTaboo.equals(carteVO.cTaboo) : carteVO.cTaboo != null) return false;
        if (cSpeTaboo != null ? !cSpeTaboo.equals(carteVO.cSpeTaboo) : carteVO.cSpeTaboo != null) return false;
        if (cMemo != null ? !cMemo.equals(carteVO.cMemo) : carteVO.cMemo != null) return false;
        if (cCrtId != null ? !cCrtId.equals(carteVO.cCrtId) : carteVO.cCrtId != null) return false;
        if (tCrtTm != null ? !tCrtTm.equals(carteVO.tCrtTm) : carteVO.tCrtTm != null) return false;
        if (cUpdId != null ? !cUpdId.equals(carteVO.cUpdId) : carteVO.cUpdId != null) return false;
        if (tUpdTm != null ? !tUpdTm.equals(carteVO.tUpdTm) : carteVO.tUpdTm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cPkId != null ? cPkId.hashCode() : 0;
        result = 31 * result + (cUserId != null ? cUserId.hashCode() : 0);
        result = 31 * result + (cParentId != null ? cParentId.hashCode() : 0);
        result = 31 * result + (cChildId != null ? cChildId.hashCode() : 0);
        result = 31 * result + (cChildNme != null ? cChildNme.hashCode() : 0);
        result = 31 * result + (cChildSex != null ? cChildSex.hashCode() : 0);
        result = 31 * result + (tBirthday != null ? tBirthday.hashCode() : 0);
        result = 31 * result + (cPhoneNo != null ? cPhoneNo.hashCode() : 0);
        result = 31 * result + (cStd != null ? cStd.hashCode() : 0);
        result = 31 * result + (tBgnTm != null ? tBgnTm.hashCode() : 0);
        result = 31 * result + (tEndTm != null ? tEndTm.hashCode() : 0);
        result = 31 * result + (nHeight != null ? nHeight.hashCode() : 0);
        result = 31 * result + (nWeight != null ? nWeight.hashCode() : 0);
        result = 31 * result + (cHistory != null ? cHistory.hashCode() : 0);
        result = 31 * result + (cTaboo != null ? cTaboo.hashCode() : 0);
        result = 31 * result + (cSpeTaboo != null ? cSpeTaboo.hashCode() : 0);
        result = 31 * result + (cMemo != null ? cMemo.hashCode() : 0);
        result = 31 * result + (cCrtId != null ? cCrtId.hashCode() : 0);
        result = 31 * result + (tCrtTm != null ? tCrtTm.hashCode() : 0);
        result = 31 * result + (cUpdId != null ? cUpdId.hashCode() : 0);
        result = 31 * result + (tUpdTm != null ? tUpdTm.hashCode() : 0);
        return result;
    }
}
