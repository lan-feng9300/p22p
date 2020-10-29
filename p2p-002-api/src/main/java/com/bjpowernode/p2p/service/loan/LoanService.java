package com.bjpowernode.p2p.service.loan;

import com.bjpowernode.p2p.service.entity.BLoanInfo;

import java.util.List;
import java.util.Map;

public interface LoanService {

    /**
     *  查询平台的历史年化率
     */
    double avgLoanRate();

    /**
     *  查询累计成交额
     */
    double sumBidMoney();

    /**
     *  通过产品类型,查询产品信息
     */

    List<BLoanInfo> findByproductType(Map<String,Object> map);

    int totalRows(int productType);

}
