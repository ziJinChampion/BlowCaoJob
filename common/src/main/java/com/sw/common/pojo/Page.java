package com.sw.common.pojo;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {

    private long currentPage = 1;

    private long total;

    private int size = 10;

    // 下页
    private int next;

    private List<T> list;

    // 最后一页
    private int last;

    private int lPage;

    private int rPage;

    private long start;

    public int offSize = 2;

    public Page() {
        super();
    }

    public void setCurrentPage(long currentPage, long total, long pageSize) {
        long pageCount = total / pageSize;

        int totalPages = (int) (total % pageSize == 0 ? total / pageSize : (total / pageSize) + 1);

        //总页数
        this.last = totalPages;

        if (currentPage > totalPages) {
            this.currentPage = totalPages;
        } else {
            this.currentPage = currentPage;
        }

        //
        this.start = (this.currentPage - 1) * pageSize;
    }

    public long getUpper() {
        return currentPage > 1 ? currentPage - 1 : currentPage;
    }

    public void setLast(int last) {
        this.last = (int) (total % size == 0 ? total / size : (total / size) + 1);
    }

    public Page(long total, int currentPage, int pageSize, int offSize) {
        this.offSize = offSize;
        initPage(total, currentPage, pageSize);
    }

    public Page(long total, int currentPage, int pageSize) {
        initPage(total, currentPage, pageSize);
    }

    public void initPage(long total, int currentPage, int pageSize) {
        this.total = total;
        this.size = pageSize;

        setCurrentPage(currentPage, total, pageSize);

        int leftCount = this.offSize,
                rightCount = this.offSize;

        this.lPage = currentPage;
        this.rPage = currentPage;

        this.lPage = currentPage - leftCount;

        int topdiv = this.last - rPage;
        this.lPage = topdiv < 0 ? this.lPage + topdiv : this.lPage;


        this.rPage = this.lPage <= 0 ? this.rPage + (this.lPage * -1) + 1 : this.rPage;


        this.lPage = this.lPage <= 0 ? 1 : this.lPage;

        this.rPage = this.rPage > last ? this.last : this.rPage;
    }

    public long getNext() {
        return currentPage < last ? currentPage + 1 : last;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getLast() {
        return last;
    }

    public long getlPage() {
        return lPage;
    }

    public void setlPage(int lPage) {
        this.lPage = lPage;
    }

    public long getrPage() {
        return rPage;
    }

    public void setrPage(int rPage) {
        this.rPage = rPage;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
