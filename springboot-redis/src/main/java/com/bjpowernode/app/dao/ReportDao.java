package com.bjpowernode.app.dao;

public interface ReportDao {

    /**
     *  查询平台的历史年化率
     */
    double avgLoanRate();

    /**
     *  查询累计成交额
     */
    double sumBidMoney();

    /**
     * 查询所有的用户
     */
    Integer userCount();
}
