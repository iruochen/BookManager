package com.ruochen.controller;

import com.ruochen.domain.Statistics;
import com.ruochen.domain.User;
import com.ruochen.service.BookApplyService;
import com.ruochen.service.BookPurchaseService;
import com.ruochen.service.BookReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StatisticsController {

    @Autowired
    private BookPurchaseService bookPurchaseService;

    @Autowired
    private BookApplyService bookApplyService;

    @Autowired
    private BookReceiveService bookReceiveService;

    /**
     * 统计页面
     *
     * @param model
     * @param request
     * @return
     */
    @GetMapping("statisticIndex")
    public String statisticsIndex(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Integer role = user.getRole();
        List<Statistics> statistics = new ArrayList<>();
        if (role == 0) {
            // 管理员
            statistics = bookPurchaseService.selectCountLastSevenDays();
        } else if (role == 1) {
            // 学生
            statistics = bookReceiveService.selectCountLastSevenDays();
        } else {
            // 教师
            statistics = bookApplyService.selectCountLastSevenDays();
        }
        model.addAttribute("statistics", statistics);
        return "statistics/statisticIndex";
    }
}
