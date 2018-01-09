package com.hneb.dws.controller;

import com.hneb.fwk.domain.Result;
import com.hneb.fwk.query.PageQueryTools;
import com.hneb.fwk.query.PageResultDTO;
import com.hneb.fwk.security.CurrentUser;
import com.hneb.fwk.utils.ResultUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/10/16 0016.
 */
@RestController
@RequestMapping("/finish")
public class FinishController {

    @Autowired
    private PageQueryTools queryTools;

    @RequestMapping("/query")
    public Result query(@RequestBody JSONObject json) throws Exception {
        String sql = "SELECT\n" +
                "  tso.C_ORDER_ID                           AS 'orderId',\n" +
                "  tu.C_USER_NME                            AS 'buyerName',\n" +
                "  tso.C_PROD_TYPE                          AS 'orderType',\n" +
                "  date_format(tso.T_BUY_TM, '%Y-%m-%d %T') AS 'orderTm',\n" +
                "  tc.C_CHILD_NME                           AS 'childName',\n" +
                "  tc.C_CHILD_SEX                           AS 'childSex',\n" +
                "  tc.T_BIRTHDAY                            AS 'birthday',\n" +
                "  til.C_ILL_NME                            AS 'illName',\n" +
                "  til.C_ILL_DESC                           AS 'illDesc',\n" +
                "  date_format(til.C_ILL_TM, '%Y-%m-%d %T') AS 'illTm'\n" +
                "FROM t_svc_order tso, t_user tu, t_ill_desc til, t_children tc\n" +
                "WHERE tso.C_SALER_ID = '#userId'\n" +
                "      AND (tso.C_ORDER_STS = '3' OR tso.C_ORDER_STS = '4' )\n" +
                "      AND til.C_ORDER_ID = tso.C_ORDER_ID\n" +
                "      AND til.C_CHILD_ID = tc.C_CHILD_ID\n" +
                "      AND tu.C_USER_ID = tso.C_BUYER_ID\n" +
                "      AND tu.C_USER_NME LIKE '%#name%'\n" +
                "      AND tso.C_PROD_TYPE IN (#type)\n" +
                "      AND (tso.T_BUY_TM >= '#begin' AND tso.T_BUY_TM <= '#end')\n" +
                "ORDER BY tso.T_BUY_TM DESC";
        json.put("userId", CurrentUser.getUser().getUserId());
        // 如果获取出来的时间不等于空就进行截取数据
        PageResultDTO resultDTO = queryTools.querySql(sql, json);
        return ResultUtils.success(resultDTO, "查询成功");
    }
}
