package com.hneb.dws.vo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/10/20 0020.
 */
@Entity
@Table(name = "t_chat_msg")
public class ChatMsgVO {
    private String cPkId;
    private String cMsgType;
    private String cOrderId;
    private String cUserId;
    private String cUserIconUrl;
    private String cUserNme;
    private Date tMsgTm;
    private String cContent;
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
    @Column(name = "C_MSG_TYPE")
    public String getcMsgType() {
        return cMsgType;
    }

    public void setcMsgType(String cMsgType) {
        this.cMsgType = cMsgType;
    }

    @Basic
    @Column(name = "C_ORDER_ID")
    public String getcOrderId() {
        return cOrderId;
    }

    public void setcOrderId(String cOrderId) {
        this.cOrderId = cOrderId;
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
    @Column(name = "C_USER_ICON_URL")
    public String getcUserIconUrl() {
        return cUserIconUrl;
    }

    public void setcUserIconUrl(String cUserIconUrl) {
        this.cUserIconUrl = cUserIconUrl;
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
    @Column(name = "T_MSG_TM")
    public Date gettMsgTm() {
        return tMsgTm;
    }

    public void settMsgTm(Date tMsgTm) {
        this.tMsgTm = tMsgTm;
    }

    @Basic
    @Column(name = "C_CONTENT")
    public String getcContent() {
        return cContent;
    }

    public void setcContent(String cContent) {
        this.cContent = cContent;
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

        ChatMsgVO chatMsgVO = (ChatMsgVO) o;

        if (cPkId != null ? !cPkId.equals(chatMsgVO.cPkId) : chatMsgVO.cPkId != null) return false;
        if (cMsgType != null ? !cMsgType.equals(chatMsgVO.cMsgType) : chatMsgVO.cMsgType != null) return false;
        if (cOrderId != null ? !cOrderId.equals(chatMsgVO.cOrderId) : chatMsgVO.cOrderId != null) return false;
        if (cUserId != null ? !cUserId.equals(chatMsgVO.cUserId) : chatMsgVO.cUserId != null) return false;
        if (cUserIconUrl != null ? !cUserIconUrl.equals(chatMsgVO.cUserIconUrl) : chatMsgVO.cUserIconUrl != null)
            return false;
        if (cUserNme != null ? !cUserNme.equals(chatMsgVO.cUserNme) : chatMsgVO.cUserNme != null) return false;
        if (tMsgTm != null ? !tMsgTm.equals(chatMsgVO.tMsgTm) : chatMsgVO.tMsgTm != null) return false;
        if (cContent != null ? !cContent.equals(chatMsgVO.cContent) : chatMsgVO.cContent != null) return false;
        if (cCrtId != null ? !cCrtId.equals(chatMsgVO.cCrtId) : chatMsgVO.cCrtId != null) return false;
        if (tCrtTm != null ? !tCrtTm.equals(chatMsgVO.tCrtTm) : chatMsgVO.tCrtTm != null) return false;
        if (cUpdId != null ? !cUpdId.equals(chatMsgVO.cUpdId) : chatMsgVO.cUpdId != null) return false;
        if (tUpdTm != null ? !tUpdTm.equals(chatMsgVO.tUpdTm) : chatMsgVO.tUpdTm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cPkId != null ? cPkId.hashCode() : 0;
        result = 31 * result + (cMsgType != null ? cMsgType.hashCode() : 0);
        result = 31 * result + (cOrderId != null ? cOrderId.hashCode() : 0);
        result = 31 * result + (cUserId != null ? cUserId.hashCode() : 0);
        result = 31 * result + (cUserIconUrl != null ? cUserIconUrl.hashCode() : 0);
        result = 31 * result + (cUserNme != null ? cUserNme.hashCode() : 0);
        result = 31 * result + (tMsgTm != null ? tMsgTm.hashCode() : 0);
        result = 31 * result + (cContent != null ? cContent.hashCode() : 0);
        result = 31 * result + (cCrtId != null ? cCrtId.hashCode() : 0);
        result = 31 * result + (tCrtTm != null ? tCrtTm.hashCode() : 0);
        result = 31 * result + (cUpdId != null ? cUpdId.hashCode() : 0);
        result = 31 * result + (tUpdTm != null ? tUpdTm.hashCode() : 0);
        return result;
    }
}
