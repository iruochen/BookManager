package com.ruochen.domain;

import java.util.Date;

/**
 * 教材申请实体
 */
public class BookApply {
    private Integer id;
    private Integer bookId;
    private Integer teaId;
    private Date time;
    private Integer count;
    /**
     * 申请教材
     */
    private Book book;
    /**
     * 申请教师
     */
    private Teacher teacher;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getTeaId() {
        return teaId;
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
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

    @Override
    public String toString() {
        return "BookApply{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", teaId=" + teaId +
                ", time=" + time +
                ", count=" + count +
                ", book=" + book +
                ", teacher=" + teacher +
                '}';
    }
}
