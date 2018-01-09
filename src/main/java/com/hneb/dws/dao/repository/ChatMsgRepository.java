package com.hneb.dws.dao.repository;

import com.hneb.dws.vo.ChatMsgVO;
import com.hneb.dws.vo.SettingVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/10/10 0010.
 */
@Repository
public interface ChatMsgRepository extends JpaRepository<ChatMsgVO,Long>{


    /**
     * 获取单个chatMsgVo对象
     * @param cPkId
     * @return ChatMsgVO
     */
    ChatMsgVO getByCPkId(String cPkId);


    /**
     * 获取订单聊天记录
     * @param orderId
     * @return List<ChatMsgVO>
     */
    List<ChatMsgVO> getChatMsgVOByCOrderIdOrderByTMsgTm(String orderId);

    /**
     * 根据orderId和userId统计ChatMsgVo
     * @param orderId
     * @param userId
     * @return int型
     */
    int countChatMsgVOByCOrderIdAndCUserId(String orderId,String userId);

}
