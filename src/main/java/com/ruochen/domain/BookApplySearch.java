package com.ruochen.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 教材申请搜索实体
 */
public class BookApplySearch {
    private String bookId;
    private String bookName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date time;
    private Integer status;
    private Integer currentTeacherId;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCurrentTeacherId() {
        return currentTeacherId;
    }

    public void setCurrentTeacherId(Integer currentTeacherId) {
        this.currentTeacherId = currentTeacherId;
    }

    @Override
    public String toString() {
        return "BookApplySearch{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", time=" + time +
                ", status=" + status +
                ", currentTeacherId=" + currentTeacherId +
                '}';
    }
}
