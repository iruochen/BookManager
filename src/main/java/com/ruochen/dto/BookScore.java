package com.ruochen.dto;

/**
 * 教材评分
 */
public class BookScore {
    private Integer bid;
    private Integer score;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "BookScore{" +
                "bid=" + bid +
                ", score=" + score +
                '}';
    }
}
