package lxpsee.top.applogs.web.controller;

import lxpsee.top.domain.StatBean;
import lxpsee.top.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 本周每天新增用户数
     */
    @RequestMapping("/newusers")
    public String newUsers() {
        StatBean newUsers = statService.findNewUsers();
        System.out.println(newUsers.getCount());
        return "index";
    }

    /**
     * appid = "sdk34734",本周每天新增用户数
     */
    @RequestMapping("/weekNewUser")
    @ResponseBody
    public Map<String, Object> dayNewUsersOfThisWeek() {
        Map<String, Object> map = new HashMap<String, Object>(2);
        List<StatBean> statBeans = statService.getDayNewUsersOfThisWeek("sdk34734");
        int size = statBeans.size();
        String[] xlabels = new String[size];
        long[] newUsers = new long[size];

        for (int i = 0; i < size; i++) {
            xlabels[i] = statBeans.get(i).getDate();
            newUsers[i] = statBeans.get(i).getCount();
        }

        map.put("labels", xlabels);
        map.put("data", newUsers);
        return map;
    }

    @RequestMapping("/stat1")
    @ResponseBody
    public StatBean stat1() {
        return new StatBean("2018/12/18", 10);
    }

    @RequestMapping("/stat2")
    @ResponseBody
    public List<StatBean> stat2() {
        List<StatBean> statBeans = new ArrayList<StatBean>();

        for (int i = 0; i < 10; i++) {
            statBeans.add(new StatBean("2018/12/" + (10 + i), 10 + i));
        }

        return statBeans;
    }


}
