package com.bjpowernode.app.controller;


import com.bjpowernode.app.dao.LoanDao;
import com.bjpowernode.app.entity.BLoanInfo;
import com.bjpowernode.app.properties.PageSizeProperties;
import com.bjpowernode.app.service.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class IndexController {

    // private int pageSize;
    /**
     *  pageSize, 对象由pageSizeProperties管理, 所有应该由对象提供
     */

    /*@Autowired
    private PageSizeProperties pageSizeProperties;*/

    //调用分页参数
    @Autowired
    PageInfo pageInfo;

    @Autowired
    LoanDao loanDao;

    @RequestMapping("/index")
    public String Index(Model model){

        List<Object> list = new ArrayList();

        Map<String,Object> map = new HashMap<>();
        map.put("productType",2);
        map.put("currentPage",1);
        map.put("pageRows",pageInfo.getPageSize());
        List<BLoanInfo> list2 = loanDao.findByproductType(map);
        int count = loanDao.count(2);
        pageInfo.setTotalRows(count);

        pageInfo.execute(count,1,list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("list",list2);
        return "index";
    }

}
