package com.hneb.dws.vo;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Administrator on 2017/10/13 0013.
 */
@Entity
@Table(name = "t_user")
public class UserVO {
    private String cUserId;
    private String cUserNme;
    private String cPhoneNo;
    private String cPassword;
    private String cRole;
    private String cDptId;
    private String cCrtId;
    private Date tCrtTm;
    private String cUpdId;
    private Date tUpdTm;
    private String cSign;
    private String cDataSrc;
    private String cUserIconUrl;
    private String cUnionId;
    private Integer nFansNum;
    private Integer nZanNum;
    private String cWxId;
    private String cWxQrcodeUrl;
    private String cWxQrcodeUrl2;
    private String cSubscribeMrk;
    private String cPro;
    private String cCity;
    private String cZone;



    @Id
    @Column(name = "C_USER_ID")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid.hex")
    public String getcUserId() {
        return cUserId;
    }

    public void setcUserId(String cUserId) {
        this.cUserId = cUserId;
    }

    @Basic
    @Column(name = "C_USER_NME")
    public String getcUserNme() {
        return cUserNme;
    }

    public void setcUserNme(String cUserNme) {
        this.cUserNme = cUserNme;
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
    @Column(name = "C_PASSWORD")
    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }

    @Basic
    @Column(name = "C_ROLE")
    public String getcRole() {
        return cRole;
    }

    public void setcRole(String cRole) {
        this.cRole = cRole;
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

    @Basic
    @Column(name = "C_SIGN")
    public String getcSign() {
        return cSign;
    }

    public void setcSign(String cSign) {
        this.cSign = cSign;
    }

    @Basic
    @Column(name = "C_DATA_SRC")
    public String getcDataSrc() {
        return cDataSrc;
    }

    public void setcDataSrc(String cDataSrc) {
        this.cDataSrc = cDataSrc;
    }

    @Basic
    @Column(name = "C_USER_ICON_URL")
    public String getcUserIconUrl() {
        return cUserIconUrl;
    }

    public void setcUserIconUrl(String cUserIconUrl) {
        this.cUserIconUrl = cUserIconUrl;
    }

    @Basic
    @Column(name = "C_UNION_ID")
    public String getcUnionId() {
        return cUnionId;
    }

    public void setcUnionId(String cUnionId) {
        this.cUnionId = cUnionId;
    }

    @Basic
    @Column(name = "N_FANS_NUM")
    public Integer getnFansNum() {
        return nFansNum;
    }

    public void setnFansNum(Integer nFansNum) {
        this.nFansNum = nFansNum;
    }

    @Basic
    @Column(name = "N_ZAN_NUM")
    public Integer getnZanNum() {
        return nZanNum;
    }

    public void setnZanNum(Integer nZanNum) {
        this.nZanNum = nZanNum;
    }

    @Basic
    @Column(name = "C_WX_ID")
    public String getcWxId() {
        return cWxId;
    }

    public void setcWxId(String cWxId) {
        this.cWxId = cWxId;
    }

    @Basic
    @Column(name = "C_WX_QRCODE_URL")
    public String getcWxQrcodeUrl() {
        return cWxQrcodeUrl;
    }

    public void setcWxQrcodeUrl(String cWxQrcodeUrl) {
        this.cWxQrcodeUrl = cWxQrcodeUrl;
    }

    @Basic
    @Column(name = "C_WX_QRCODE_URL2")
    public String getcWxQrcodeUrl2() {
        return cWxQrcodeUrl2;
    }

    public void setcWxQrcodeUrl2(String cWxQrcodeUrl2) {
        this.cWxQrcodeUrl2 = cWxQrcodeUrl2;
    }

    @Basic
    @Column(name = "C_SUBSCRIBE_MRK")
    public String getcSubscribeMrk() {
        return cSubscribeMrk;
    }

    public void setcSubscribeMrk(String cSubscribeMrk) {
        this.cSubscribeMrk = cSubscribeMrk;
    }

    @Basic
    @Column(name = "C_PRO")
    public String getcPro() {
        return cPro;
    }

    public void setcPro(String cPro) {
        this.cPro = cPro;
    }

    @Basic
    @Column(name = "C_CITY")
    public String getcCity() {
        return cCity;
    }

    public void setcCity(String cCity) {
        this.cCity = cCity;
    }

    @Basic
    @Column(name = "C_ZONE")
    public String getcZone() {
        return cZone;
    }

    public void setcZone(String cZone) {
        this.cZone = cZone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserVO userVO = (UserVO) o;

        if (cUserId != null ? !cUserId.equals(userVO.cUserId) : userVO.cUserId != null) return false;
        if (cUserNme != null ? !cUserNme.equals(userVO.cUserNme) : userVO.cUserNme != null) return false;
        if (cPhoneNo != null ? !cPhoneNo.equals(userVO.cPhoneNo) : userVO.cPhoneNo != null) return false;
        if (cPassword != null ? !cPassword.equals(userVO.cPassword) : userVO.cPassword != null) return false;
        if (cRole != null ? !cRole.equals(userVO.cRole) : userVO.cRole != null) return false;
        if (cDptId != null ? !cDptId.equals(userVO.cDptId) : userVO.cDptId != null) return false;
        if (cCrtId != null ? !cCrtId.equals(userVO.cCrtId) : userVO.cCrtId != null) return false;
        if (tCrtTm != null ? !tCrtTm.equals(userVO.tCrtTm) : userVO.tCrtTm != null) return false;
        if (cUpdId != null ? !cUpdId.equals(userVO.cUpdId) : userVO.cUpdId != null) return false;
        if (tUpdTm != null ? !tUpdTm.equals(userVO.tUpdTm) : userVO.tUpdTm != null) return false;
        if (cSign != null ? !cSign.equals(userVO.cSign) : userVO.cSign != null) return false;
        if (cDataSrc != null ? !cDataSrc.equals(userVO.cDataSrc) : userVO.cDataSrc != null) return false;
        if (cUserIconUrl != null ? !cUserIconUrl.equals(userVO.cUserIconUrl) : userVO.cUserIconUrl != null)
            return false;
        if (cUnionId != null ? !cUnionId.equals(userVO.cUnionId) : userVO.cUnionId != null) return false;
        if (nFansNum != null ? !nFansNum.equals(userVO.nFansNum) : userVO.nFansNum != null) return false;
        if (nZanNum != null ? !nZanNum.equals(userVO.nZanNum) : userVO.nZanNum != null) return false;
        if (cWxId != null ? !cWxId.equals(userVO.cWxId) : userVO.cWxId != null) return false;
        if (cWxQrcodeUrl != null ? !cWxQrcodeUrl.equals(userVO.cWxQrcodeUrl) : userVO.cWxQrcodeUrl != null)
            return false;
        if (cWxQrcodeUrl2 != null ? !cWxQrcodeUrl2.equals(userVO.cWxQrcodeUrl2) : userVO.cWxQrcodeUrl2 != null)
            return false;
        if (cSubscribeMrk != null ? !cSubscribeMrk.equals(userVO.cSubscribeMrk) : userVO.cSubscribeMrk != null)
            return false;
        if (cPro != null ? !cPro.equals(userVO.cPro) : userVO.cPro != null) return false;
        if (cCity != null ? !cCity.equals(userVO.cCity) : userVO.cCity != null) return false;
        if (cZone != null ? !cZone.equals(userVO.cZone) : userVO.cZone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cUserId != null ? cUserId.hashCode() : 0;
        result = 31 * result + (cUserNme != null ? cUserNme.hashCode() : 0);
        result = 31 * result + (cPhoneNo != null ? cPhoneNo.hashCode() : 0);
        result = 31 * result + (cPassword != null ? cPassword.hashCode() : 0);
        result = 31 * result + (cRole != null ? cRole.hashCode() : 0);
        result = 31 * result + (cDptId != null ? cDptId.hashCode() : 0);
        result = 31 * result + (cCrtId != null ? cCrtId.hashCode() : 0);
        result = 31 * result + (tCrtTm != null ? tCrtTm.hashCode() : 0);
        result = 31 * result + (cUpdId != null ? cUpdId.hashCode() : 0);
        result = 31 * result + (tUpdTm != null ? tUpdTm.hashCode() : 0);
        result = 31 * result + (cSign != null ? cSign.hashCode() : 0);
        result = 31 * result + (cDataSrc != null ? cDataSrc.hashCode() : 0);
        result = 31 * result + (cUserIconUrl != null ? cUserIconUrl.hashCode() : 0);
        result = 31 * result + (cUnionId != null ? cUnionId.hashCode() : 0);
        result = 31 * result + (nFansNum != null ? nFansNum.hashCode() : 0);
        result = 31 * result + (nZanNum != null ? nZanNum.hashCode() : 0);
        result = 31 * result + (cWxId != null ? cWxId.hashCode() : 0);
        result = 31 * result + (cWxQrcodeUrl != null ? cWxQrcodeUrl.hashCode() : 0);
        result = 31 * result + (cWxQrcodeUrl2 != null ? cWxQrcodeUrl2.hashCode() : 0);
        result = 31 * result + (cSubscribeMrk != null ? cSubscribeMrk.hashCode() : 0);
        result = 31 * result + (cPro != null ? cPro.hashCode() : 0);
        result = 31 * result + (cCity != null ? cCity.hashCode() : 0);
        result = 31 * result + (cZone != null ? cZone.hashCode() : 0);
        return result;
    }
}
