package com.ruochen.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BookReceiveSearch {
    private String bookId;
    private String bookName;
    private Integer currentStudentId;
    private String stuId;
    private String stuName;
    private Integer deptId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date time;

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

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getCurrentStudentId() {
        return currentStudentId;
    }

    public void setCurrentStudentId(Integer currentStudentId) {
        this.currentStudentId = currentStudentId;
    }

    @Override
    public String toString() {
        return "BookReceiveSearch{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", currentStudentId=" + currentStudentId +
                ", stuId='" + stuId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", deptId=" + deptId +
                ", time=" + time +
                '}';
    }
}
