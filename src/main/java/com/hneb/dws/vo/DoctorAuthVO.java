package com.hneb.dws.vo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/25.
 */
@Entity
@Table(name = "t_doctor_auth")
public class DoctorAuthVO {
    private String cUserId;
    private String cDptId;
    private String cOfficeId;
    private String cProfess;
    private String cSpeciality;
    private String cSelfIntroduction;
    private String cOther;
    private String cCertfUrl1;
    private String cCertfUrl2;
    private String cAdjustMrk;
    private String cAdjustMsg;
    private String cDptId1;
    private String cOfficeId1;
    private String cDptId2;
    private String cOfficeId2;
    private String cCrtId;
    private Date tCrtTm;
    private String cUpdId;
    private Date tUpdTm;

    @Id
    @Column(name = "C_USER_ID")
    public String getcUserId() {
        return cUserId;
    }

    public void setcUserId(String cUserId) {
        this.cUserId = cUserId;
    }

    @Basic
    @Column(name = "C_DPT_ID")
    public String getcDptId() {
        return cDptId;
    }

    public void setcDptId(String cDptId) {
        this.cDptId = cDptId;
    }

    @Basic
    @Column(name = "C_OFFICE_ID")
    public String getcOfficeId() {
        return cOfficeId;
    }

    public void setcOfficeId(String cOfficeId) {
        this.cOfficeId = cOfficeId;
    }

    @Basic
    @Column(name = "C_PROFESS")
    public String getcProfess() {
        return cProfess;
    }

    public void setcProfess(String cProfess) {
        this.cProfess = cProfess;
    }

    @Basic
    @Column(name = "C_SPECIALITY")
    public String getcSpeciality() {
        return cSpeciality;
    }

    public void setcSpeciality(String cSpeciality) {
        this.cSpeciality = cSpeciality;
    }

    @Basic
    @Column(name = "C_SELF_INTRODUCTION")
    public String getcSelfIntroduction() {
        return cSelfIntroduction;
    }

    public void setcSelfIntroduction(String cSelfIntroduction) {
        this.cSelfIntroduction = cSelfIntroduction;
    }

    @Basic
    @Column(name = "C_OTHER")
    public String getcOther() {
        return cOther;
    }

    public void setcOther(String cOther) {
        this.cOther = cOther;
    }

    @Basic
    @Column(name = "C_CERTF_URL1")
    public String getcCertfUrl1() {
        return cCertfUrl1;
    }

    public void setcCertfUrl1(String cCertfUrl1) {
        this.cCertfUrl1 = cCertfUrl1;
    }

    @Basic
    @Column(name = "C_CERTF_URL2")
    public String getcCertfUrl2() {
        return cCertfUrl2;
    }

    public void setcCertfUrl2(String cCertfUrl2) {
        this.cCertfUrl2 = cCertfUrl2;
    }

    @Basic
    @Column(name = "C_ADJUST_MRK")
    public String getcAdjustMrk() {
        return cAdjustMrk;
    }

    public void setcAdjustMrk(String cAdjustMrk) {
        this.cAdjustMrk = cAdjustMrk;
    }

    @Basic
    @Column(name = "C_ADJUST_MSG")
    public String getcAdjustMsg() {
        return cAdjustMsg;
    }

    public void setcAdjustMsg(String cAdjustMsg) {
        this.cAdjustMsg = cAdjustMsg;
    }

    @Basic
    @Column(name = "C_DPT_ID1")
    public String getcDptId1() {
        return cDptId1;
    }

    public void setcDptId1(String cDptId1) {
        this.cDptId1 = cDptId1;
    }

    @Basic
    @Column(name = "C_OFFICE_ID1")
    public String getcOfficeId1() {
        return cOfficeId1;
    }

    public void setcOfficeId1(String cOfficeId1) {
        this.cOfficeId1 = cOfficeId1;
    }

    @Basic
    @Column(name = "C_DPT_ID2")
    public String getcDptId2() {
        return cDptId2;
    }

    public void setcDptId2(String cDptId2) {
        this.cDptId2 = cDptId2;
    }

    @Basic
    @Column(name = "C_OFFICE_ID2")
    public String getcOfficeId2() {
        return cOfficeId2;
    }

    public void setcOfficeId2(String cOfficeId2) {
        this.cOfficeId2 = cOfficeId2;
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

        DoctorAuthVO that = (DoctorAuthVO) o;

        if (cUserId != null ? !cUserId.equals(that.cUserId) : that.cUserId != null) return false;
        if (cDptId != null ? !cDptId.equals(that.cDptId) : that.cDptId != null) return false;
        if (cOfficeId != null ? !cOfficeId.equals(that.cOfficeId) : that.cOfficeId != null) return false;
        if (cProfess != null ? !cProfess.equals(that.cProfess) : that.cProfess != null) return false;
        if (cSpeciality != null ? !cSpeciality.equals(that.cSpeciality) : that.cSpeciality != null) return false;
        if (cSelfIntroduction != null ? !cSelfIntroduction.equals(that.cSelfIntroduction) : that.cSelfIntroduction != null)
            return false;
        if (cOther != null ? !cOther.equals(that.cOther) : that.cOther != null) return false;
        if (cCertfUrl1 != null ? !cCertfUrl1.equals(that.cCertfUrl1) : that.cCertfUrl1 != null) return false;
        if (cCertfUrl2 != null ? !cCertfUrl2.equals(that.cCertfUrl2) : that.cCertfUrl2 != null) return false;
        if (cAdjustMrk != null ? !cAdjustMrk.equals(that.cAdjustMrk) : that.cAdjustMrk != null) return false;
        if (cAdjustMsg != null ? !cAdjustMsg.equals(that.cAdjustMsg) : that.cAdjustMsg != null) return false;
        if (cDptId1 != null ? !cDptId1.equals(that.cDptId1) : that.cDptId1 != null) return false;
        if (cOfficeId1 != null ? !cOfficeId1.equals(that.cOfficeId1) : that.cOfficeId1 != null) return false;
        if (cDptId2 != null ? !cDptId2.equals(that.cDptId2) : that.cDptId2 != null) return false;
        if (cOfficeId2 != null ? !cOfficeId2.equals(that.cOfficeId2) : that.cOfficeId2 != null) return false;
        if (cCrtId != null ? !cCrtId.equals(that.cCrtId) : that.cCrtId != null) return false;
        if (tCrtTm != null ? !tCrtTm.equals(that.tCrtTm) : that.tCrtTm != null) return false;
        if (cUpdId != null ? !cUpdId.equals(that.cUpdId) : that.cUpdId != null) return false;
        if (tUpdTm != null ? !tUpdTm.equals(that.tUpdTm) : that.tUpdTm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cUserId != null ? cUserId.hashCode() : 0;
        result = 31 * result + (cDptId != null ? cDptId.hashCode() : 0);
        result = 31 * result + (cOfficeId != null ? cOfficeId.hashCode() : 0);
        result = 31 * result + (cProfess != null ? cProfess.hashCode() : 0);
        result = 31 * result + (cSpeciality != null ? cSpeciality.hashCode() : 0);
        result = 31 * result + (cSelfIntroduction != null ? cSelfIntroduction.hashCode() : 0);
        result = 31 * result + (cOther != null ? cOther.hashCode() : 0);
        result = 31 * result + (cCertfUrl1 != null ? cCertfUrl1.hashCode() : 0);
        result = 31 * result + (cCertfUrl2 != null ? cCertfUrl2.hashCode() : 0);
        result = 31 * result + (cAdjustMrk != null ? cAdjustMrk.hashCode() : 0);
        result = 31 * result + (cAdjustMsg != null ? cAdjustMsg.hashCode() : 0);
        result = 31 * result + (cDptId1 != null ? cDptId1.hashCode() : 0);
        result = 31 * result + (cOfficeId1 != null ? cOfficeId1.hashCode() : 0);
        result = 31 * result + (cDptId2 != null ? cDptId2.hashCode() : 0);
        result = 31 * result + (cOfficeId2 != null ? cOfficeId2.hashCode() : 0);
        result = 31 * result + (cCrtId != null ? cCrtId.hashCode() : 0);
        result = 31 * result + (tCrtTm != null ? tCrtTm.hashCode() : 0);
        result = 31 * result + (cUpdId != null ? cUpdId.hashCode() : 0);
        result = 31 * result + (tUpdTm != null ? tUpdTm.hashCode() : 0);
        return result;
    }
}
