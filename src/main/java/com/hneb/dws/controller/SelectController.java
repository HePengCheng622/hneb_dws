package com.hneb.dws.controller;

import com.hneb.dws.dao.CommCodeDao;
import com.hneb.dws.dao.FoodDao;
import com.hneb.dws.dao.TabooDao;
import com.hneb.fwk.domain.Result;
import com.hneb.fwk.json.JsonUtils;
import com.hneb.fwk.security.CurrentUser;
import com.hneb.fwk.utils.ResultUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/8.
 */
@RestController
@RequestMapping("/select")
public class SelectController {

    @Autowired
    private CommCodeDao commCodeDao;
    @Autowired
    private TabooDao tabooDao;
    @Autowired
    private FoodDao foodDao;

    //初始化cascader组件数据
    @RequestMapping(value = "/initCascader",method = RequestMethod.POST)
    public Result initCascader(@RequestBody JSONObject params) throws Exception {
        JSONArray parentCodes = params.getJSONArray("parentCodes");
        JSONObject result = new JSONObject();
        for(Object parentCode:parentCodes){
            List<Map> list= commCodeDao.getCommCodeLikeCode(parentCode.toString());
            JSONArray ja = new JSONArray();
            for(Map map:list){
                if(parentCode.toString().equals(map.get("parent"))){
                    merge(map,list);
                    ja.add(map);
                }
            }

            result.put(parentCode,ja);
        }
        return ResultUtils.success(result);
    }

    @RequestMapping(value = "/initSelect", method = RequestMethod.POST)
    public Result init(@RequestBody JSONObject params) throws Exception {
        JSONArray paramsJSONArray = params.getJSONArray("params");
        JSONObject result = new JSONObject();
        String type,code;
        for(Object item:paramsJSONArray){
            type = ((JSONObject)item).getString("type");
            code = ((JSONObject)item).getString("code");

            if("commCode".equals(type)){
                result.put(type+"_"+code,JSONArray.fromObject(initCommCode(code)));
            }else if("taboo".equals(type)){
                result.put(type+"_"+code,JSONArray.fromObject(initTaboo()));
            }else if("foods".equals(type)){
                result.put(type+"_"+code,JSONArray.fromObject(initFoods()));
            }
        }
        return ResultUtils.success(result);
    }

    /**
     * 根据食材类型选择食材（目前在菜品编辑页面的选择食材中使用）
     * 从食材分类的顶级，cascader到二级分类，再到最终的食材
     * @param params
     * @return
     */
    @RequestMapping(value = "/foodsCascader",method = RequestMethod.POST)
    public Result foods(@RequestBody JSONObject params) throws Exception {
        String key = params.getString("key");
        List result = null;
        if(key.length()==2||key.length()==4){
            List<Map> list = commCodeDao.getCommCodeByParentCode(key);
            for(Map map:list){
                map.put("children",new JSONArray());
            }
            result = list;
        }else if(key.length()==6){
            result = foodDao.getFoods(key);
        }

        return ResultUtils.success(JsonUtils.listObject2JsonArray(result));
    }
    //递归调用处理cascader分级
    private void merge(Map map,List<Map> list){
        for(Map tmp:list){
            if(map.get("value").toString().equals(tmp.get("parent").toString())){
                merge(tmp,list);
                if(map.get("children")!=null){
                    ((JSONArray)map.get("children")).add(tmp);
                }else{
                    JSONArray ja = new JSONArray();
                    ja.add(tmp);
                    map.put("children",ja);
                }
            }
        }
    }

    private Object initCommCode(String code) throws Exception {
        List<Map> tmp= commCodeDao.getCommCodeByParentCode(code);
        for(Map map : tmp){
            if(Integer.parseInt(map.get("children").toString())==0){
                map.remove("children");
                map.put("isLeaf",true);
            }else{
                map.put("children",new JSONArray());
                map.put("isLeaf",false);
            }
        }
        return tmp;
    }

    private Object initTaboo() throws Exception {
        return this.tabooDao.initTaboo();
    }

    private Object initFoods() throws Exception{
        return this.foodDao.getFoods();
    }
}
