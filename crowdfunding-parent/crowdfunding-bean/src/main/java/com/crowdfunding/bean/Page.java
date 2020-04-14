package com.crowdfunding.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 分页类
 *
 * @param <T>
 */
public class Page<T> implements Serializable {
    private Integer pageSize; //每页显示个数
    private Integer pageNo;//当前页面
    private Integer totalSize;//总条数
    private Integer totalNo;//总页数
    private List<T> date;//每页显示的数据

    public Page(Integer pageNo, Integer pageSize) {
        if (pageNo <= 0) {
            pageNo = 1;
        } else {
            this.pageNo = pageNo;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        } else {
            this.pageSize = pageSize;

        }

    }


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
        //计算总页数
        this.totalNo = (totalSize%pageSize)==0?(totalSize/pageSize):(totalSize/pageSize+1);
    }

    public Integer getTotalNo() {
        return totalNo;
    }

    public void setTotalNo(Integer totalNo) {
        this.totalNo = totalNo;

    }

    public List<T> getDate() {
        return date;
    }

    public void setDate(List<T> date) {
        this.date = date;
    }
    //开始索引的位置
    public Integer getStartIndex(){
        return (this.pageNo-1)*pageSize;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", totalSize=" + totalSize +
                ", totalNo=" + totalNo +
                ", date=" + date +
                '}';
    }
}
