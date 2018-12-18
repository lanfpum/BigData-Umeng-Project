package lxpsee.top.applogs.web.controller;

import lxpsee.top.domain.StatBean;
import lxpsee.top.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/17 15:52.
 * <p>
 * 统计信息controller
 */
@Controller
@RequestMapping("/stat")
public class StatController {
    @Autowired
    private StatService statService;

    @RequestMapping("/index")
    public String toIndex() {
        return "index";
    }


    /**
     * appid = "sdk34734",本周每天新增用户数
     */
    @RequestMapping("/newusers")
    public String newUsers() {
        StatBean newUsers = statService.findNewUsers();
        System.out.println(newUsers.getCount());
        return "index";
    }


}
