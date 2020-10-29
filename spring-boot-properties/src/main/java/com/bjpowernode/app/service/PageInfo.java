package com.bjpowernode.app.service;

import java.util.List;

/**
 *  思考: IndexController所需要的参数,是直接调用 pageSize取,还是通过 PageSizeProperties间接获取 ??
 *        第一想法: 是通过 pageSize直接调用获取, 然而这样 PageSizeProperties 存在的意义何在 ??
 *        自我解答: PageSizeProperties,是专门跟 ,application.properties 文件交互的
 *
 *  前端页面所需要的信息:
 *  根参数:    pageSize;
 *            currentPage;
 *            totalRows;
 */


public class PageInfo<T> {

    //具体参数:
    int pageSize;
    int currentPage;
    int totalRows;
    int totalPage;
    int firstPage;
    int previousPage;
    int nextPage;
    int lastPage;
    List<T> data;

    public PageInfo() {
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(int previousPage) {
        this.previousPage = previousPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    /**
     *  配置业务方法:
     *  除了, pageSize,由配置文件提供, 其他两个跟参数: totalRows 和 currentPage需要由,调用者提供
     */
    public void execute(int totalRows, int currentPage, List<T> data){

        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalRows = totalRows;

        this.totalPage = totalRows % pageSize==0? totalRows / pageSize : (totalRows/pageSize)+1;
        this.firstPage = 1;
        //前一页
        if (currentPage > 1) {
            this.previousPage = currentPage-1;
        }else {
            this.previousPage = 1;
        }
        //下一页
        if (this.totalPage>this.currentPage) {
            this.nextPage = currentPage+1;
        }else {
            this.nextPage = currentPage;
        }

        this.lastPage = this.totalPage;
        this.data=data;

    }
}
