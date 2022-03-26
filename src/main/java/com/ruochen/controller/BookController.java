package com.ruochen.controller;

import com.github.pagehelper.PageInfo;
import com.ruochen.domain.Book;
import com.ruochen.service.BookService;
import com.ruochen.utils.COSConfig;
import com.ruochen.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * 教材管理首页
     *
     * @return
     */
    @GetMapping("bookIndex")
    public String bookIndex() {
        return "book/bookIndex";
    }

    /**
     * 获取教材信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("bookAll")
    @ResponseBody
    public DataInfo bookIndex(@RequestParam("page") Integer pageNum, @RequestParam("size") Integer pageSize) {
        PageInfo<Book> pageInfo = bookService.selectBooksAll(pageNum, pageSize);
        return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 教材添加页面跳转
     *
     * @return
     */
    @GetMapping("bookAdd")
    public String bookAdd(Model model) {
        model.addAttribute("ImgPreUrl", COSConfig.preUrl + COSConfig.cosKeyName);
        return "book/bookAdd";
    }

    /**
     * 添加教材
     *
     * @param book
     * @return
     */
    @RequestMapping("addBookSubmit")
    @ResponseBody
    public DataInfo addBookSubmit(Book book) {
        bookService.addBook(book);
        return DataInfo.ok();
    }

    /**
     * 根据ID查询教材信息
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("selectBookById")
    public String selectBookById(Integer id, Model model) {
        Book book = bookService.selectBookById(id);
        model.addAttribute("book", book);
        return "book/updateBook";
    }

    /**
     * 修改教材提交
     *
     * @param book
     * @return
     */
    @RequestMapping("updateBookSubmit")
    @ResponseBody
    public DataInfo updateBookSubmit(Book book) {
        bookService.updateBook(book);
        return DataInfo.ok();
    }
}
