package com.baidu.controller;

import com.baidu.service.StressTestService;
import com.baidu.vo.Msg;
import com.baidu.vo.ServiceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by edwardsbean on 14-10-27.
 */
@Controller
@RequestMapping("/stress")
public class StressTestController {
    private final static Logger log = LoggerFactory.getLogger(StressTestController.class.getName());

    @Autowired
    private StressTestService stressTestService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "stress-create";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String list() {
        return "stress-index";
    }

    @ResponseBody
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public Msg checkService(@RequestParam String serviceName) throws Exception {
        String id = stressTestService.queryService(serviceName);
        Msg msg = new Msg();
        ServiceQuery serviceQuery = new ServiceQuery();
        serviceQuery.setServiceName(serviceName);
        serviceQuery.setServiceId(id);
        msg.setReturnData(serviceQuery);
        return msg;
    }

    @ResponseBody
    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public Msg loadService(@RequestParam String serviceId) throws Exception {
        stressTestService.download(serviceId);
        Msg msg = new Msg();
        return msg;
    }


    @ResponseBody
    @RequestMapping(value = "/methods", method = RequestMethod.GET)
    public Msg getServiceMethods(@RequestParam String serviceId,@RequestParam String serviceName) throws Exception {
        List list = stressTestService.getMethods(serviceName,serviceId);
        Msg msg = new Msg();
        msg.setReturnData(list);
        return msg;
    }

/**
 * 内网速度快，暂时不用进度条
 */
//    @ResponseBody
//    @RequestMapping(value="/progress", method= RequestMethod.POST)
//    public String query(@ModelAttribute("serviceName")ServiceQuery serviceQuery) {
//        return "";
//    }


}
