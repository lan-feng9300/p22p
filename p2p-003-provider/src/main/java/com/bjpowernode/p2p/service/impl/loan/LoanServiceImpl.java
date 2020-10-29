package com.bjpowernode.p2p.service.impl.loan;

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.p2p.dao.LoanDao;
import com.bjpowernode.p2p.dao.ReportDao;
import com.bjpowernode.p2p.service.entity.BLoanInfo;
import com.bjpowernode.p2p.service.loan.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
@Service(interfaceClass = com.bjpowernode.p2p.service.loan.LoanService.class)
public class LoanServiceImpl implements LoanService {

    @Autowired
    ReportDao reportDao;

    @Autowired
    LoanDao loanDao;

   /* @Autowired
    RedisTemplate redisTemplate;*/

    @Override
    public double avgLoanRate() {

       /* // 1: 获取redis数据
        Double avgLoanRate = (Double) redisTemplate.opsForValue().get("avgLoanRate");

        //外层判断,粗略筛选,数据是否有值
        if (!ObjectUtils.allNotNull(avgLoanRate)) {

            // 2: 判断数据库是否为空, 是否需要进入数据库
            synchronized (this){
                // 确保所有线程的变量一致性, 需要再次刷新变量数据
                avgLoanRate = (Double) redisTemplate.opsForValue().get("avgLoanRate");
                if (!ObjectUtils.allNotNull(avgLoanRate)) {
                    avgLoanRate = reportDao.avgLoanRate();
                    redisTemplate.opsForValue().set("avgLoanRate",avgLoanRate,30, TimeUnit.SECONDS);
                    return avgLoanRate;
                }
                else {
                    return avgLoanRate;
                }
            }
        }
        return avgLoanRate;*/

       return reportDao.avgLoanRate();
    }

    @Override
    public double sumBidMoney() {

       /* Double sumBidMoney = (Double) redisTemplate.opsForValue().get("sumBidMoney");

        if (!ObjectUtils.allNotNull(sumBidMoney)) {
            synchronized (this){

                sumBidMoney = (Double) redisTemplate.opsForValue().get("sumBidMoney");
                if (!ObjectUtils.allNotNull(sumBidMoney)) {

                    sumBidMoney = reportDao.sumBidMoney();
                    redisTemplate.opsForValue().set("sumBidMoney",sumBidMoney,30,TimeUnit.SECONDS);
                    return sumBidMoney;
                }
                else {
                    return sumBidMoney;
                }
            }
        }

        return reportDao.sumBidMoney();*/

       return reportDao.sumBidMoney();
    }

    @Override
    public List<BLoanInfo> findByproductType(Map<String, Object> map) {

        // 依据,productType, 有几个结果集合, 所有需要,分别对几个结果集查询

        /*model.addAttribute("XSlist",XSlist);
        model.addAttribute("YXlist",YXlist);
        model.addAttribute("SBlist",SBlist);*/

        /**
         *  前台,访问数据库传入的参数 是 map, 而要访问数据库取得值是 , List集合
         *  因此,若往redis中存入map,依然会访问数据库
         *  存入, list? --> 这个怎么操作 ?
         */

        return loanDao.findByproductType(map);
    }

    @Override
    public int totalRows(int productType) {
        return loanDao.totalRows(productType);
    }


}
