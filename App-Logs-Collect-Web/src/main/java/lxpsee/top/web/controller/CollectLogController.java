package lxpsee.top.web.controller;

import lxpsee.top.common.AppLogEntity;
import lxpsee.top.web.utils.ControllerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import static lxpsee.top.web.utils.ControllerUtil.*;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/7 10:30.
 */
@Controller
@RequestMapping("/coll")
public class CollectLogController {

    @PostMapping("/index")
    @ResponseBody
    public AppLogEntity collect(@RequestBody AppLogEntity e, HttpServletRequest req) {
        System.out.println("-----------------------------------------------------------");
        String clientTime = req.getHeader("clientTime");
        long diff = System.currentTimeMillis() - Long.parseLong(clientTime);

        // 进行时间修正，复制基本属性，处理ip地址问题
        ControllerUtil.verifyTime(e, diff);
        ControllerUtil.processLogs(e);
        String clientIP = req.getRemoteAddr();
        ControllerUtil.processClientIP(e, clientIP);

        // TODO 发送给Kafka.
        return e;
    }


}
