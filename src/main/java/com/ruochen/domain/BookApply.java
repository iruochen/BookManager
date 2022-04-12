package com.ruochen.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 教材申请实体
 */
public class BookApply {
    private Integer id;
    private Integer bId;
    private Integer tId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date time;
    private Integer count;
    private Integer status;

    /**
     * 申请教材
     */
    private Book book;
    /**
     * 申请教师
     */
    private Teacher teacher;

    /**
     * 某教材申请总数，数据表中无此字段
     */
    private Integer applyCount;
    /**
     * 需要采购的数量，数据表中无此字段
     */
    private Integer needPurchaseCount;
    /**
     * 需要采购的总金额，数据表中无此字段
     */
    private Double priceCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getbId() {
        return bId;
    }

    public void setbId(Integer bId) {
        this.bId = bId;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Integer getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Integer applyCount) {
        this.applyCount = applyCount;
    }

    public Integer getNeedPurchaseCount() {
        return needPurchaseCount;
    }

    public void setNeedPurchaseCount(Integer needPurchaseCount) {
        this.needPurchaseCount = needPurchaseCount;
    }

    public Double getPriceCount() {
        return priceCount;
    }

    public void setPriceCount(Double priceCount) {
        this.priceCount = priceCount;
    }

    @Override
    public String toString() {
        return "BookApply{" +
                "id=" + id +
                ", bId=" + bId +
                ", tId=" + tId +
                ", time=" + time +
                ", count=" + count +
                ", status=" + status +
                ", book=" + book +
                ", teacher=" + teacher +
                ", applyCount=" + applyCount +
                ", needPurchaseCount=" + needPurchaseCount +
                ", priceCount=" + priceCount +
                '}';
    }
}

