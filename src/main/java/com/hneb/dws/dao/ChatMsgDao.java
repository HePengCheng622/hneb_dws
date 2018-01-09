package com.hneb.dws.dao;

import com.hneb.dws.dao.repository.ChatMsgRepository;
import com.hneb.fwk.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/10/20 0020.
 */
@Repository
public class ChatMsgDao extends BaseDao {
    @Autowired
    private ChatMsgRepository chatMsgRep;

    public ChatMsgRepository getChatMsgRep() {
        return chatMsgRep;
    }

    public void setChatMsgRep(ChatMsgRepository chatMsgRep) {
        this.chatMsgRep = chatMsgRep;
    }
}
