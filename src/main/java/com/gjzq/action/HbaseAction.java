package com.gjzq.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HbaseAction {
    @RequestMapping("getChartDatas")
    public ModelAndView getChartDatas(HttpServletResponse response){
        JSONArray array = new JSONArray();
        JSONObject temp = new JSONObject();
        temp.put("name","北京");
        temp.put("value",52);
        array.add(temp);
        temp = new JSONObject();
        temp.put("name","上海");
        temp.put("value",26);
        array.add(temp);
        temp = new JSONObject();
        temp.put("name","广州");
        temp.put("value",78);
        array.add(temp);
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().print(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
