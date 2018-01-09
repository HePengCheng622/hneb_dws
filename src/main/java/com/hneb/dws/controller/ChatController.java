package com.hneb.dws.controller;

import com.hneb.dws.config.PluginConfig;
import com.hneb.dws.dao.ChatMsgDao;
import com.hneb.dws.dao.IllDescDao;
import com.hneb.dws.dao.SvcOrderDao;
import com.hneb.dws.dao.UserDao;
import com.hneb.dws.vo.ChatMsgVO;
import com.hneb.dws.vo.IllDescVO;
import com.hneb.dws.vo.SvcOrderVO;
import com.hneb.dws.vo.UserVO;
import com.hneb.fwk.domain.Result;
import com.hneb.fwk.security.CurrentUser;
import com.hneb.fwk.utils.AgeUtils;
import com.hneb.fwk.utils.MD5Utils;
import com.hneb.fwk.utils.ResultUtils;
import com.qiniu.util.Auth;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/20 0020.
 */
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ChatMsgDao chatMsgDao;
    @Autowired
    private SvcOrderDao svcOrderDao;
    @Autowired
    private IllDescDao illDescDao;

    /**
     * 获取云通讯的签名
     * @return
     */
    @RequestMapping(value = "/getYtxSig",method = RequestMethod.POST)
    public Result getYtxSig(@RequestBody JSONObject json) throws NoSuchAlgorithmException {
        String userId = CurrentUser.getUser().getUserId();
        String appid= PluginConfig.YTX.APPID;
        String apptoken=PluginConfig.YTX.APPTOKEN;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String timestamp=format.format(date);
        String sig = appid+userId+timestamp+apptoken;
        sig = MD5Utils.string2MD5(sig);
        JSONObject result = new JSONObject();
        result.put("sig",sig);
        result.put("userId",userId);
        result.put("timestamp",timestamp);
        return ResultUtils.success(result,"获取签名成功");
    }

    /**
     * 获取七牛上传凭证
     * @return
     */
    @RequestMapping(value = "/getQiniuToken",method = RequestMethod.POST)
    public Result getQiniuToken(){
        //生成上传凭证
        String accessKey = PluginConfig.QINIU.AC;
        String secretKey = PluginConfig.QINIU.SK;
        String bucket = PluginConfig.QINIU.BUCKET_CHAT;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        JSONObject json = new JSONObject();
        json.put("upToken",upToken);
        json.put("domain",PluginConfig.QINIU.DOMAIN_CHAT);
        return ResultUtils.success(json,"成功生成TOKEN");
    }
    /**
     * 保存聊天
     */
    @RequestMapping("/saveChat")
    public Result saveChat(@RequestBody JSONObject json) throws Exception{
        String msgType = json.getString("type");
        String currentUserId = CurrentUser.getUser().getUserId();
        String content = json.getString("content");
        String orderId = json.getString("orderId");

        UserVO userVO = userDao.getUserRep().getUserVOByCUserId(currentUserId);
        String headImg = userVO.getcUserIconUrl();
        String userNme = userVO.getcUserNme();

        //医生发送的第一条数据 需要通知患者
        if("1".equals(userVO.getcRole())){
            // 统计回复条数
            int count = chatMsgDao.getChatMsgRep().countChatMsgVOByCOrderIdAndCUserId(orderId, currentUserId);
            if(count==0){
                //将订单设置为医生已回复
                svcOrderDao.getSvcOrderRep().updateSvcOrderVOByCOrderId("2", orderId);
                SvcOrderVO orderVO = svcOrderDao.getSvcOrderRep().getSvcOrderVOByCOrderId(orderId);
                UserVO sickUser = userDao.getUserRep().getUserVOByCUserId(orderVO.getcBuyerId());
                // 短信通知
//                this.getNoticeService().sendNewReplySmsToSick(sickUser.getcPhoneNo(), userNme);
//                String hfContent="";// 模板显示的内容
//                if("1".equals(msgType)){
//                    hfContent=content;
//                }else if("4".equals(msgType)){
//                    hfContent="收到一张图片请及时查看";
//                }
//                // 微信模板消息通知
//                this.getNoticeService().sendNewReplyToSick(orderVO.getcBuyerId(), userNme, orderVO.getcOrderId(), content, hfContent);
            }
        }
        Date now = new Date();
        ChatMsgVO msg = new ChatMsgVO();
        msg.setcMsgType(msgType);
        msg.setcOrderId(orderId);
        msg.setcUserId(currentUserId);
        msg.setcUserIconUrl(headImg);
        msg.setcUserNme(userNme);
        msg.settMsgTm(now);
        msg.setcContent(content);
        chatMsgDao.getChatMsgRep().saveAndFlush(msg);
        JSONObject object = new JSONObject();
        object.put("belong","me");
        object.put("type",msg.getcMsgType());
        object.put("time",msg.gettMsgTm().getTime());
        object.put("content",msg.getcContent());
        if("6".equals(msg.getcMsgType())){
            object.put("report",JSONObject.fromObject(msg.getcContent()));
        }
        return ResultUtils.success(object,"保存成功");
    }

    /**
     * 获取聊天对话
     */
    @RequestMapping("/getChat")
    public Result getChat(@RequestBody JSONObject json) throws Exception {
        String orderId = json.getString("orderId");
        JSONObject result = new JSONObject();
        //第一步： 获取聊天的对象信息，即自己是谁，对方是谁
        SvcOrderVO orderVO = svcOrderDao.getSvcOrderRep().getSvcOrderVOByCOrderId(orderId);
        UserVO userVO = userDao.getUserRep().getUserVOByCUserId(CurrentUser.getUser().getUserId());
        String selfId = userVO.getcUserId();
        String selfNme = userVO.getcUserNme();
        String selfHeadImg = userVO.getcUserIconUrl();
        String otherId ,otherNme ,otherHeadImg;
        if(selfId.equals(orderVO.getcBuyerId())){
            otherId = orderVO.getcSalerId();
        }else{
            otherId = orderVO.getcBuyerId();
        }
        // 获取对方的头像以及名字
        UserVO otherUser = userDao.getUserRep().getUserVOByCUserId(otherId);
        otherNme = otherUser.getcUserNme();
        otherHeadImg = otherUser.getcUserIconUrl();

        JSONArray jArray = new JSONArray();
        //第二步：获取病情描述
        String sql = "select " +
                "		a.C_ILL_NME as illNme," +
                "		a.C_ILL_TM as illTm," +
                "		a.C_ILL_DESC as illDesc," +
                "		a.C_IMG1 as img1," +
                "		a.C_IMG2 as img2," +
                "		a.C_IMG3 as img3," +
                "		b.C_CHILD_NME as childNme," +
                "		b.C_CHILD_NME as childSex," +
                "		b.T_BIRTHDAY as birthday" +
                "	from " +
                "		t_ill_desc a,t_children b " +
                "	where " +
                "		a.C_ORDER_ID=? and a.C_CHILD_ID=b.C_CHILD_ID";
        List<Map> maps = illDescDao.querySql(sql,orderId);
        String illNme = "";
        String illTm = "";
        String illDesc = "";
        String img1 = "";
        String img2 = "";
        String img3 = "";
        String childNme = "";
        String childSex = "";
        String childAge = "";
        if(maps!=null && maps.size()>0){
            Map<String,Object> map3= (Map<String, Object>) maps.get(0);
            illNme 	= map3.get("illNme")==null?"":map3.get("illNme").toString();
            illTm 	= map3.get("illTm")==null?"":map3.get("illTm").toString();
            illDesc = map3.get("illDesc")==null?"":map3.get("illDesc").toString();
            img1 	= map3.get("img1")==null?"":map3.get("img1").toString();
            img2 	= map3.get("img2")==null?"":map3.get("img2").toString();
            img3 	= map3.get("img3")==null?"":map3.get("img3").toString();
            childNme= map3.get("childNme")==null?"":map3.get("childNme").toString();
            childSex= map3.get("childSex")==null?"":map3.get("childSex").toString();
            childSex="M".equals(childSex)?"男":"女";
            Date birthday= (Date) map3.get("birthday");
            childAge = AgeUtils.calcAgeAsString(birthday, orderVO.gettOrderTm());
        }

        JSONObject illDescJson = new JSONObject();
        illDescJson.put("illNme", illNme);
        illDescJson.put("illTm", illTm);
        illDescJson.put("time", new Date());
        illDescJson.put("illDesc", illDesc);
        illDescJson.put("img1", img1);
        illDescJson.put("img2", img2);
        illDescJson.put("img3", img3);
        illDescJson.put("childNme", childNme);
        illDescJson.put("childSex", childSex);
        illDescJson.put("childAge", childAge);

        illDescJson.put("belong", "me");
        illDescJson.put("type", "illDesc");

        jArray.add(illDescJson);

        //第三步：获取聊天对话内容
        List<ChatMsgVO> msgVOList = chatMsgDao.getChatMsgRep().getChatMsgVOByCOrderIdOrderByTMsgTm(orderId);
        if(msgVOList!=null && msgVOList.size()>0){
            for(int i=0;i<msgVOList.size();i++){
                Map<String,Object> map4 = new HashMap<String,Object>();
                ChatMsgVO chatMsgVO = msgVOList.get(i);
                if(selfId.equals(chatMsgVO.getcUserId())){
                    map4.put("belong", "me");
                }else{
                    map4.put("belong", "other");
                }
                //如果是报告类型，则需要解析content的内容{nme:'',tm:'',url:''}
                if("6".equals(chatMsgVO.getcMsgType())){
                    String content = chatMsgVO.getcContent();
                    map4.put("report", JSONObject.fromObject(content));
                }
                map4.put("content",chatMsgVO.getcContent());
                map4.put("type", chatMsgVO.getcMsgType());
                map4.put("time",chatMsgVO.gettMsgTm().getTime());
                jArray.add(JSONObject.fromObject(map4));
            }
        }

        result.put("selfId", selfId);
        result.put("selfNme", selfNme);
        result.put("selfHeadImg", selfHeadImg);
        result.put("otherId", otherId);
        result.put("otherNme", otherNme);
        result.put("otherHeadImg", otherHeadImg);
        result.put("orderId", orderVO.getcOrderId());
        result.put("orderSts", orderVO.getcOrderSts());
        result.put("serviceSts", orderVO.getcServiceSts());
        result.put("messages", jArray);
        return ResultUtils.success(result,"查询成功");
    }
}
