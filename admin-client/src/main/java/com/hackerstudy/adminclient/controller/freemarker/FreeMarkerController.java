package com.hackerstudy.adminclient.controller.freemarker;

import com.hackerstudy.adminclient.pojo.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @class: FreeMarkerController
 * @description:
 * @author: yangpeng03614
 * @date: 2019-04-09 14:32
 */
@Controller
@RequestMapping("ftl")
public class FreeMarkerController {
    @Autowired
    private Resource resource;

    @RequestMapping("/resource")
    public String resource(ModelMap map){
        map.addAttribute("resource",resource);
        return "freemarker/resource/resource";
    }

    @RequestMapping("/freemarkerIndex")
    public String index(){
        return "freemarker/freemarker_index";
    }
}
