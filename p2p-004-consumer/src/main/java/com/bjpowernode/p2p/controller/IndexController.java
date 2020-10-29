package com.bjpowernode.p2p.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpowernode.app.service.PageInfo;
import com.bjpowernode.p2p.service.entity.BLoanInfo;
import com.bjpowernode.p2p.service.loan.LoanService;
import com.bjpowernode.p2p.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  前台首页控制类
 */
@Controller
public class IndexController {

    @Reference(interfaceClass = com.bjpowernode.p2p.service.user.UserService.class)
    private UserService userService;

    @Reference(interfaceClass = com.bjpowernode.p2p.service.loan.LoanService.class)
    private LoanService loanService;

    @Autowired
    PageInfo pageInfo;


    @RequestMapping("/index.do")
    public String toIndex(Model model){

        // 获取动力金融,历史年化率
        double avgLoanRate = loanService.avgLoanRate();
        // 获取动力金融,用户数
        Integer userCount = userService.userCount();
        // 获取动力金融, 累计成交额
        double sumBidMoney = loanService.sumBidMoney();

        //封装参数
        Map<String,Object> map = new HashMap<>();

        // 查询新手宝项目
        //product_type`=#{productType} LIMIT #{currentPage},#{pageRows}
        map.put("productType",0);
        map.put("currentPage",0);
        map.put("pageRows",1);
        List<BLoanInfo> XSlist = loanService.findByproductType(map);

        // 查询: 优选产品首页展示
        map.put("productType",1);
        map.put("currentPage",0);
        map.put("pageRows",4);
        List<BLoanInfo> YXlist = loanService.findByproductType(map);

        // 查询: 散标产品页展示
        map.put("productType",2);
        map.put("currentPage",0);
        map.put("pageRows",8);
        List<BLoanInfo> SBlist = loanService.findByproductType(map);

        model.addAttribute("avgLoanRate",avgLoanRate);
        model.addAttribute("sumBidMoney",sumBidMoney);
        model.addAttribute("userCount",userCount);

        model.addAttribute("XSlist",XSlist);
        model.addAttribute("YXlist",YXlist);
        model.addAttribute("SBlist",SBlist);

        return "index";
    }


    @RequestMapping("/test.do")
    public String text(Model model){

        System.out.println(pageInfo.getPageSize()+"====");
        int pageSize = pageInfo.getPageSize();

        int currentPage = 1;

        //封装参数
        Map<String,Object> map = new HashMap<>();
        map.put("productType",2);
        map.put("currentPage",1);
        map.put("pageRows",pageSize);

        List<BLoanInfo> list = loanService.findByproductType(map);
        int i = loanService.totalRows(2);
        pageInfo.execute(i,currentPage,list);

        model.addAttribute("pageInfo",pageInfo);
        List data = pageInfo.getData();
        System.out.println(data);

        return "test";
    }
}
