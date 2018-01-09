package com.hneb.dws.controller;

import com.hneb.dws.dao.CommCodeDao;
import com.hneb.dws.vo.CommCodeVO;
import com.hneb.fwk.domain.Result;
import com.hneb.fwk.json.JsonUtils;
import com.hneb.fwk.security.CurrentUser;
import com.hneb.fwk.utils.ResultUtils;
import com.qiniu.util.Json;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/30 0030.
 */
@RestController
@RequestMapping("/commCode")
public class CommCodeController {
    @Autowired
    private CommCodeDao commCodeDao;



//    /**
//     * 初始化下拉列表
//     * @see SelectController.init
//     * @param params
//     * @return
//     * @throws Exception
//     */
//    @Deprecated
//    @RequestMapping(value = "/initSelect",method = RequestMethod.POST)
//    public Result initSelect(@RequestBody JSONObject params) throws Exception {
//        JSONArray parentCodes = params.getJSONArray("parentCodes");
//        JSONObject result = new JSONObject();
//        for(Object parentCode:parentCodes){
//            List<Map> tmp= commCodeDao.getCommCodeByParentCode(parentCode.toString());
//            for(Map map : tmp){
//                if(Integer.parseInt(map.get("children").toString())==0){
//                    map.remove("children");
//                    map.put("isLeaf",true);
//                }else{
//                    map.put("children",new JSONArray());
//                    map.put("isLeaf",false);
//                }
//            }
//            result.put(parentCode,JSONArray.fromObject(tmp));
//        }
//        return ResultUtils.success(result);
//    }

    /**
     * 获取当前节点的子节点
     * @param json
     * @return
     */
    @RequestMapping(value = "/getNode",method = RequestMethod.POST)
    public Result getNode(@RequestBody JSONObject json) throws Exception {
        String code = json.getString("code");
//        List<CommCodeVO>  list = commCodeDao.getCommCodeRep().findCommCodeVOByCParentCode(code);
        List  list = commCodeDao.getCommCodeByParentCode(code);
        JSONObject jsons = new JSONObject();
        // 如果list不为空才设置
        if(list!=null&&list.size()>0){
            jsons.put("resultList", JsonUtils.listObject2Json(list));
        }
        return ResultUtils.success(jsons);
    }

    /**
     * 获取食品类型
     * @param json
     * @return
     */
    @RequestMapping(value = "/getTypes",method = RequestMethod.POST)
    public Result getTypes(@RequestBody JSONObject json) throws Exception {
        JSONArray codes = json.getJSONArray("codes");
        List parents=new ArrayList();
        List sons=new ArrayList();
        // 循环获取所有的父类和子类
        for (Object code : codes) {
            // 获取大类
            CommCodeVO parent = commCodeDao.getCommCodeRep().findCommCodeVOByCCodeAndCEffMrk(code.toString(),"Y").get(0);
            parents.add(parent);
            // 获取子类
            List<CommCodeVO> son = commCodeDao.getCommCodeRep().findCommCodeVOByCParentCodeAndCEffMrk(code.toString(),"Y");
            sons.add(son.size()>0?son:new ArrayList<>());
        }
        JSONObject obj = new JSONObject();
        obj.put("parent", JsonUtils.listObject2Json(parents));
        obj.put("son", JSONArray.fromObject(sons));
        return ResultUtils.success(obj);
    }

    /**
     * 删除节点，不做真真的删除，只需要将他的子节点，和自己的节点改为*加code
     * @param json
     */
    @Transactional
    @RequestMapping(value = "/deleteNode",method = RequestMethod.POST)
    public Result deleteNode(@RequestBody JSONObject json){
        String code = json.getString("code");// 获取当前的节点code
        // 删除自己的节点
        commCodeDao.getCommCodeRep().deleteCommCodeVO(code);
        // 删除所有的子节点
        commCodeDao.getCommCodeRep().deleteSonCommCodeVO(code);
        return ResultUtils.success();
    }

    /*添加节点*/
    @RequestMapping(value = "/addNode",method = RequestMethod.POST)
    public Result addNode(@RequestBody JSONObject json){
        String parent = json.getString("parent");
        String text = json.getString("text");
        CommCodeVO codeVO = new CommCodeVO();
        // 获取出当前节点的子节点中的最大code值
        String code = commCodeDao.getCommCodeRep().getMaxCodeOfSon(parent);
        if(code!=null&&!"".equals(code)){// 如果有子节点就获取子节点中最大的code值，进行加一
            int i = Integer.parseInt(code);
            codeVO.setcCode("0"+(i+1));
        }else{// 如果没有子节点就是parent+01作为第一个子节点的code值
            codeVO.setcCode(json.getString("parent")+"01");
        }
        // 设置其parentCode
        codeVO.setcParentCode(parent);
        // 设置text
        codeVO.setcText(text);
        // 设置操作者id
        codeVO.setcUserId(CurrentUser.getUser().getUserId());
        // 设置是否有效标识
        codeVO.setcEffMrk("Y");
        // 更新插入到数据库中去
        commCodeDao.getCommCodeRep().save(codeVO);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",codeVO);
        return ResultUtils.success(jsonObject);
    }
}
