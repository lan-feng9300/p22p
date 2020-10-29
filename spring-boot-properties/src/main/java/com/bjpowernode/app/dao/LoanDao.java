package com.bjpowernode.app.dao;


import com.bjpowernode.app.entity.BLoanInfo;

import java.util.List;
import java.util.Map;

public interface LoanDao {

    /**
     *  通过产品类型,查询产品信息
     */

    List<BLoanInfo> findByproductType(Map<String, Object> map);

    int count(int productType);
}
