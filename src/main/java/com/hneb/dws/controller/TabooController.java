package com.hneb.dws.controller;

import com.hneb.dws.dao.TabooDao;
import com.hneb.dws.vo.TabooVO;
import com.hneb.fwk.domain.Result;
import com.hneb.fwk.json.JsonUtils;
import com.hneb.fwk.query.PageQueryTools;
import com.hneb.fwk.query.PageResultDTO;
import com.hneb.fwk.security.CurrentUser;
import com.hneb.fwk.utils.DateUtils;
import com.hneb.fwk.utils.ResultUtils;
import com.hneb.fwk.utils.VoUtils;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/5.
 */
@RestController
@RequestMapping("/taboo")
public class TabooController {

    @Autowired
    private PageQueryTools queryTools;
    @Autowired
    private TabooDao tabooDao;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result save(@RequestBody JSONObject param) throws Exception {
        String taboo = param.getString("cTaboo");
        Object foods = param.get("cFoods");
        String pkId = param.getString("cPkId");

        TabooVO bean = new TabooVO();
        bean.setcPkId(pkId);
        bean.setcFoods(foods.toString());
        bean.setcTaboo(taboo);
        bean.setcUserId(CurrentUser.getUser().getUserId());
        bean.setcEffMrk("1");
        bean.setcCrtId(param.get("cCrtId").toString());
        bean.settCrtTm(param.get("tCrtTm")==null?null: DateUtils.strToDate(param.getString("tCrtTm")));
        VoUtils.touch(bean,new Date(), CurrentUser.getUser().getUserId());
        this.tabooDao.getTabooRepository().saveAndFlush(bean);
        return ResultUtils.success();
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public Result get(@RequestBody JSONObject param){
        String pkId = param.getString("pkId");
        TabooVO taboo = this.tabooDao.getTabooRepository().getByCPkId(pkId);
        return ResultUtils.success(JsonUtils.object2Json(taboo));
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result delete(@RequestBody JSONObject param){
        String pkId = param.getString("pkId");
        this.tabooDao.getTabooRepository().updateEffMrk(pkId,"0");
        return ResultUtils.success();
    }

    @RequestMapping(value = "query",method = RequestMethod.POST)
    public Result query(@RequestBody JSONObject json) throws Exception {
        String sql="select " +
                    "   c_pk_id as pkId, " +
                    "   c_taboo as taboo, " +
                    "   c_foods as foods, " +
                    "   c_user_id as userId, " +
                    "   (select c_user_nme from t_user a where a.c_user_id=b.c_user_id) as userNme " +
                    "from " +
                    "   t_taboo b " +
                    "where " +
                    "   b.c_eff_mrk='1' ";
        PageResultDTO resultDTO = queryTools.querySql(sql, json);
        List list = resultDTO.getResultList();
        String foodNames;
        String currentUserId = CurrentUser.getUser().getUserId();
        for(int i=0;i<list.size();i++){
            foodNames = this.tabooDao.getFoodNames(((Map)list.get(i)).get("foods").toString());
            ((Map)list.get(i)).put("foods",foodNames);
            if(currentUserId.equals(((Map)list.get(i)).get("userId").toString())){
                ((Map)list.get(i)).put("owner","1");
            }else{
                ((Map)list.get(i)).put("owner","0");
            }
        }
        return ResultUtils.success(resultDTO, "查询成功");
    }

    @RequestMapping(value = "select",method = RequestMethod.POST)
    public Result select(@RequestBody JSONObject json) throws Exception {
        List list = this.tabooDao.select();
        String foodNames;
        for(int i=0;i<list.size();i++){
            foodNames = this.tabooDao.getFoodNames(((Map)list.get(i)).get("foods").toString());
            ((Map)list.get(i)).put("foods",foodNames);
        }
        return ResultUtils.success(JsonUtils.listObject2JsonArray(list), "查询成功");
    }
}
