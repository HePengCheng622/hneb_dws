package com.hneb.dws.controller;

import com.hneb.dws.dao.FoodDao;
import com.hneb.dws.dao.RecipeDao;
import com.hneb.dws.dao.TabooDao;
import com.hneb.dws.service.CarteService;
import com.hneb.fwk.domain.Result;
import com.hneb.fwk.utils.DateUtils;
import com.hneb.fwk.utils.ResultUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/25.
 */
@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private CarteService carteService;
    @Autowired
    private RecipeDao recipeDao;
    @Autowired
    private TabooDao tabooDao;
    @Autowired
    private FoodDao foodDao;

    /**
     * 获取烹饪清单
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public Result query(@RequestBody JSONObject params) throws Exception {
        String eatTm = params.getString("eatTm");
        String mealMrk = params.getString("mealMrk");
        List<Map> list = this.carteService.getCarte(eatTm,mealMrk);

        String carteId,bgnTm;
        long dayNum;
        List<Map> tmp;

        List<Map> result = new ArrayList<Map>();
        for(Map map : list){
            carteId = map.get("carteId").toString();
            bgnTm = map.get("bgnTm").toString();

            dayNum = (DateUtils.strToDate(bgnTm).getTime()-DateUtils.strToDate(eatTm).getTime())/(24*60*60*1000);
            dayNum = dayNum+1;


            tmp = this.recipeDao.getRecipe(carteId,dayNum);

            //合并相同的项目
            merge(result,tmp);
        }
        for (Map map :result) {
            if(!"[]".equals(map.get("taboo"))){
                map.put("taboo",getTabooName(dealString((String) map.get("taboo"))));
            }
            if(!"[]".equals(map.get("speTaboo"))){
                map.put("speTaboo",getSepTabooName(dealString((String) map.get("speTaboo"))));
            }
        }
        return ResultUtils.success(result);
    }


    private void merge(List<Map> result,List<Map> tmp){
        String recipeId1,taboo1,speTaboo1;
        String recipeId2,taboo2,speTaboo2;
        for(Map m1 : tmp){
            recipeId1 = m1.get("recipeId").toString();
            taboo1 = m1.get("taboo").toString();
            speTaboo1 = m1.get("speTaboo").toString();


            boolean mergred = false;
            for(Map m2:result){
                recipeId2 = m2.get("recipeId").toString();
                taboo2 = m2.get("taboo").toString();
                speTaboo2 = m2.get("speTaboo").toString();
                //如果食谱id相同，且竞技相同合并(即：份数+1）
                if(recipeId1.equals(recipeId2) && taboo1.equals(taboo2) && speTaboo1.equals(speTaboo2)){
                    m2.put("num",Integer.parseInt(m2.get("num").toString())+1);
                    mergred = true;
                    break;
                }
            }
            if(!mergred){
                m1.put("num",1);
                result.add(m1);
            }
        }
    }

    //    处理了字符串['123','sdsf','232']为'123','sdsf','232'
    private String dealString(String str){
        str = str.substring(1,str.length()-1);
        str = str.replace("\"","");
        String[] split = str.split(",");
        String strs="";
        for (String s:split) {
            strs+="'"+s+"',";
        }
        return strs.substring(0,strs.length()-1);
    }
    /**
     * 获取所有禁忌食材名称用于回显数据
     */
    public String getTabooName(String key) throws Exception {
        List<Map> taboos = tabooDao.getTaboos(key);
        String str="[";
        for (Map map:taboos) {
            str+="'"+map.get("label")+"',";
        }
        str=str.substring(0,str.length()-1)+"]";
        return str;
    }

    /**
     * 查询特殊忌口的食材名字
     * @throws Exception
     */
    public String getSepTabooName(String key) throws Exception {
        List<Map> foods = foodDao.searchList(key);
        String str="[";
        for (Map map:foods) {
            str+="'"+map.get("label")+"',";
        }
        str=str.substring(0,str.length()-1)+"]";
        return str;
    }
}
