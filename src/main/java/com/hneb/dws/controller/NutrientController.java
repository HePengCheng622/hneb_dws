package com.hneb.dws.controller;

import com.hneb.dws.service.NutrientService;
import com.hneb.fwk.domain.Result;
import com.hneb.fwk.utils.ResultUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/12/13.
 */
@RestController
@RequestMapping("/nutrient")
public class NutrientController {

    @Autowired
    private NutrientService nutrientService;

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public Result get(@RequestBody JSONObject params) throws Exception {
        String id = params.getString("id");
        String sex = params.getString("sex");
        Float age = Float.parseFloat(id.split("-")[0]);
        String type = "2";
        if(age>=6){
            type = id.split("-")[1];
        }
        return ResultUtils.success(this.nutrientService.get(sex,type,age));
    }
}
