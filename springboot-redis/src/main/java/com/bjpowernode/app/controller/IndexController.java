package com.bjpowernode.app.controller;

import com.bjpowernode.app.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    LoanService loanService;

    @RequestMapping("/index.do")
    public String toIndex(Model model){

        double avgLoanRate = loanService.avgLoanRate();
        System.out.println(loanService.avgLoanRate());
        model.addAttribute("avgLoanRate",avgLoanRate);
        return "index";
    }
}
