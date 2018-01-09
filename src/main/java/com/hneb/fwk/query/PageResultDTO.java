package com.hneb.fwk.query;

import java.util.List;

/**
 * Created by Administrator on 2017/10/11 0011.
 */
public class PageResultDTO {
    private List resultList;
    private int total;
    private int pages;

    public List getResultList() {
        return resultList;
    }

    public void setResultList(List resultList) {
        this.resultList = resultList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
