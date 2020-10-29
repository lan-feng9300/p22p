package com.bjpowernode.p2p.dao;

import com.bjpowernode.p2p.service.entity.BLoanInfo;

import java.util.List;
import java.util.Map;

public interface LoanDao {

    /**
     *  通过产品类型,查询产品信息
     */

    List<BLoanInfo> findByproductType(Map<String,Object> map);

    int totalRows(int productType);
}
