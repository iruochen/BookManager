package com.ruochen.service;

import com.ruochen.domain.BookPurchase;

import javax.servlet.http.HttpServletRequest;

public interface BookPurchaseService {
    /**
     * 教材采购
     *
     * @param bookId
     * @param bookPurchase
     * @param request
     */
    void bookPurchase(String bookId, BookPurchase bookPurchase, HttpServletRequest request);
}
