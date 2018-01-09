package com.hneb.dws.controller;

import com.hneb.dws.dao.FoodDao;
import com.hneb.dws.vo.FoodVO;
import com.hneb.fwk.domain.Result;
import com.hneb.fwk.json.JsonUtils;
import com.hneb.fwk.query.PageQueryTools;
import com.hneb.fwk.query.PageResultDTO;
import com.hneb.fwk.utils.ResultUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/11/29 0029.
 * 食物控制类
 */
@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private PageQueryTools queryTools;
    @Autowired
    private FoodDao foodDao;
    /**
     * 获取食物
     * @return
     */
    @RequestMapping(value = "/fetchFood",method = RequestMethod.POST)
    public Result fetchFood(@RequestBody JSONObject json) throws Exception {
        String sql="SELECT *\n" +
                "FROM t_food tf \n";
        JSONArray typeValue = json.getJSONArray("typeValue");
        if(typeValue.size()>0){// 选择了类型和名字
            if(typeValue.size()>1){
                sql+="WHERE tf.c_cde2 ='"+typeValue.get(typeValue.size()-1)+"' \n";
            }else{
                sql+="WHERE tf.c_cde1 ='"+typeValue.get(0)+"' \n";
            }
            if(!"".equals(json.getString("foodNme"))){
                sql+="AND tf.c_name like '%#foodNme%' \n";
            }
        }else{// 如果没有选择类型,判断有没有选择name
            if(!"".equals(json.getString("foodNme"))){
                sql+="WHERE tf.c_name like '%#foodNme%' \n";
            }
        }
        // 是否需要排序
        if(!"".equals(json.getString("orderByNme"))){
            sql+="ORDER BY tf."+json.getString("orderByNme")+" "+json.getString("orderByType");
        }
        // 如果获取出来的时间不等于空就进行截取数据
        PageResultDTO resultDTO = queryTools.querySql(sql, json);
        return ResultUtils.success(resultDTO, "查询成功");
    }

    /**
     * 获取单个食物
     * @return
     */
    public Result getSingleFood(@RequestBody JSONObject json){
        String cPkId = json.getString("cPkId");
        FoodVO foodVO = foodDao.getFoodRep().getFoodVOByCPkId(cPkId);
        JSONObject object = new JSONObject();
        object.put("food",JsonUtils.object2Json(foodVO));
        return ResultUtils.success(object);
    }

    /**
     * 保存或者更新食物
     * @param json
     * @return
     */
    public Result saveOrUpdateFood(@RequestBody JSONObject json){
        FoodVO food = (FoodVO) JSONObject.toBean(json.getJSONObject("food"), FoodVO.class);
        // 如果pkId为空的话说明是新增食物
        if("".equals(food.getcPkId())||food.getcPkId()==null){
            //TODO
        }
        // 保存和更新
        foodDao.getFoodRep().saveAndFlush(food);
        return ResultUtils.success("操作成功");
    }

    /**
     * 删除食物
     * @param json 根据cPkId来删除食物
     * @return
     */
    public Result deleteFood(@RequestBody JSONObject json){
        foodDao.getFoodRep().deleteFoodVOByCPkId(json.getString("cPkId"));
        return ResultUtils.success("操作成功");
    }

    /**
     * 获取所有的食物
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/listAll",method = RequestMethod.POST)
    public Result getFoods() throws Exception {
        List list = this.foodDao.getFoods();
        return ResultUtils.success(list);
    }


    /**
     * 通过类名名字获取食物
     * @param json
     * @return
     */
    @RequestMapping(value="/getFoodWithType",method = RequestMethod.POST)
    public Result getFoodWithType(@RequestBody JSONObject json){
        String typeName = json.getString("typeName");
        List<FoodVO> voList = null;
        if("type".equals(typeName)){
            voList = foodDao.getFoodRep().getFoodVOAllType();// 获取食材的所有大类
        }else{
            voList = foodDao.getFoodRep().getFoodVOByCSecKindNme(typeName);// 根据食材类别获取食材
        }
        return ResultUtils.success(voList);
    }

    /**
     * 通过关键字搜索食材
     * 供餐单中的特殊禁忌使用
     * @param json
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result search(@RequestBody JSONObject json) throws Exception {
        String key = json.getString("key");
        List list = this.foodDao.search(key);
        return ResultUtils.success(list);
    }
}
