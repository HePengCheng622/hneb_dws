package com.hneb.dws.vo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/10/10.
 */
@Entity
@Table(name = "t_setting")
public class SettingVO {
    private String cPkId;
    private String cUserId;
    private String cTxtConsult;
    private String cTelConsult;
    private String cOrderPlus;
    private String cTxtAdj;
    private String cTelAdj;
    private String cPlusAdj;
    private String cTxtAuto;
    private String cTelAuto;
    private String cPlusAuto;
    private String cCrtId;
    private Date tCrtTm;
    private String cUpdId;
    private Date tUpdTm;

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
    @Column(name = "C_TXT_CONSULT")
    public String getcTxtConsult() {
        return cTxtConsult;
    }

    public void setcTxtConsult(String cTxtConsult) {
        this.cTxtConsult = cTxtConsult;
    }

    @Basic
    @Column(name = "C_TEL_CONSULT")
    public String getcTelConsult() {
        return cTelConsult;
    }

    public void setcTelConsult(String cTelConsult) {
        this.cTelConsult = cTelConsult;
    }

    @Basic
    @Column(name = "C_ORDER_PLUS")
    public String getcOrderPlus() {
        return cOrderPlus;
    }

    public void setcOrderPlus(String cOrderPlus) {
        this.cOrderPlus = cOrderPlus;
    }

    @Basic
    @Column(name = "C_TXT_ADJ")
    public String getcTxtAdj() {
        return cTxtAdj;
    }

    public void setcTxtAdj(String cTxtAdj) {
        this.cTxtAdj = cTxtAdj;
    }

    @Basic
    @Column(name = "C_TEL_ADJ")
    public String getcTelAdj() {
        return cTelAdj;
    }

    public void setcTelAdj(String cTelAdj) {
        this.cTelAdj = cTelAdj;
    }

    @Basic
    @Column(name = "C_PLUS_ADJ")
    public String getcPlusAdj() {
        return cPlusAdj;
    }

    public void setcPlusAdj(String cPlusAdj) {
        this.cPlusAdj = cPlusAdj;
    }

    @Basic
    @Column(name = "C_TXT_AUTO")
    public String getcTxtAuto() {
        return cTxtAuto;
    }

    public void setcTxtAuto(String cTxtAuto) {
        this.cTxtAuto = cTxtAuto;
    }

    @Basic
    @Column(name = "C_TEL_AUTO")
    public String getcTelAuto() {
        return cTelAuto;
    }

    public void setcTelAuto(String cTelAuto) {
        this.cTelAuto = cTelAuto;
    }

    @Basic
    @Column(name = "C_PLUS_AUTO")
    public String getcPlusAuto() {
        return cPlusAuto;
    }

    public void setcPlusAuto(String cPlusAuto) {
        this.cPlusAuto = cPlusAuto;
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

        SettingVO settingVO = (SettingVO) o;

        if (cPkId != null ? !cPkId.equals(settingVO.cPkId) : settingVO.cPkId != null) return false;
        if (cUserId != null ? !cUserId.equals(settingVO.cUserId) : settingVO.cUserId != null) return false;
        if (cTxtConsult != null ? !cTxtConsult.equals(settingVO.cTxtConsult) : settingVO.cTxtConsult != null)
            return false;
        if (cTelConsult != null ? !cTelConsult.equals(settingVO.cTelConsult) : settingVO.cTelConsult != null)
            return false;
        if (cOrderPlus != null ? !cOrderPlus.equals(settingVO.cOrderPlus) : settingVO.cOrderPlus != null) return false;
        if (cTxtAdj != null ? !cTxtAdj.equals(settingVO.cTxtAdj) : settingVO.cTxtAdj != null) return false;
        if (cTelAdj != null ? !cTelAdj.equals(settingVO.cTelAdj) : settingVO.cTelAdj != null) return false;
        if (cPlusAdj != null ? !cPlusAdj.equals(settingVO.cPlusAdj) : settingVO.cPlusAdj != null) return false;
        if (cTxtAuto != null ? !cTxtAuto.equals(settingVO.cTxtAuto) : settingVO.cTxtAuto != null) return false;
        if (cTelAuto != null ? !cTelAuto.equals(settingVO.cTelAuto) : settingVO.cTelAuto != null) return false;
        if (cPlusAuto != null ? !cPlusAuto.equals(settingVO.cPlusAuto) : settingVO.cPlusAuto != null) return false;
        if (cCrtId != null ? !cCrtId.equals(settingVO.cCrtId) : settingVO.cCrtId != null) return false;
        if (tCrtTm != null ? !tCrtTm.equals(settingVO.tCrtTm) : settingVO.tCrtTm != null) return false;
        if (cUpdId != null ? !cUpdId.equals(settingVO.cUpdId) : settingVO.cUpdId != null) return false;
        if (tUpdTm != null ? !tUpdTm.equals(settingVO.tUpdTm) : settingVO.tUpdTm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cPkId != null ? cPkId.hashCode() : 0;
        result = 31 * result + (cUserId != null ? cUserId.hashCode() : 0);
        result = 31 * result + (cTxtConsult != null ? cTxtConsult.hashCode() : 0);
        result = 31 * result + (cTelConsult != null ? cTelConsult.hashCode() : 0);
        result = 31 * result + (cOrderPlus != null ? cOrderPlus.hashCode() : 0);
        result = 31 * result + (cTxtAdj != null ? cTxtAdj.hashCode() : 0);
        result = 31 * result + (cTelAdj != null ? cTelAdj.hashCode() : 0);
        result = 31 * result + (cPlusAdj != null ? cPlusAdj.hashCode() : 0);
        result = 31 * result + (cTxtAuto != null ? cTxtAuto.hashCode() : 0);
        result = 31 * result + (cTelAuto != null ? cTelAuto.hashCode() : 0);
        result = 31 * result + (cPlusAuto != null ? cPlusAuto.hashCode() : 0);
        result = 31 * result + (cCrtId != null ? cCrtId.hashCode() : 0);
        result = 31 * result + (tCrtTm != null ? tCrtTm.hashCode() : 0);
        result = 31 * result + (cUpdId != null ? cUpdId.hashCode() : 0);
        result = 31 * result + (tUpdTm != null ? tUpdTm.hashCode() : 0);
        return result;
    }
}
