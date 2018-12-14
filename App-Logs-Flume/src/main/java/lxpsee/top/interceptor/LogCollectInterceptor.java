package lxpsee.top.interceptor;

import com.alibaba.fastjson.JSONObject;
import lxpsee.top.common.AppBaseLog;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;
import java.util.Map;

import static org.apache.flume.interceptor.TimestampInterceptor.Constants.*;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/12 10:06.
 * <p>
 * 自定义flume的拦截器,提取body中的createTimeMS字段作为header
 * 模拟TimestampInterceptor写
 */
public class LogCollectInterceptor implements Interceptor {

    private final boolean preserveExisting;

    private LogCollectInterceptor(boolean preserveExisting) {
        this.preserveExisting = preserveExisting;
    }

    public void initialize() {
    }

    public Event intercept(Event event) {
        // 处理时间，处理log类型的头
        Map<String, String> headers = event.getHeaders();

        String bodyMsg = new String(event.getBody());
        AppBaseLog appBaseLog = JSONObject.parseObject(bodyMsg, AppBaseLog.class);
        Long time = appBaseLog.getCreatedAtMs();
        headers.put(TIMESTAMP, Long.toString(time));

        String logType = "";
        if (bodyMsg.contains("pageId")) {
            //pageLog
            logType = "pageLog";
        } else if (bodyMsg.contains("eventId")) {
            //eventLog
            logType = "eventLog";
        } else if (bodyMsg.contains("singleUseDurationSecs")) {
            //usageLog
            logType = "usageLog";
        } else if (bodyMsg.contains("errorBrief")) {
            //error
            logType = "errorLog";
        } else if (bodyMsg.contains("network")) {
            //startup
            logType = "startupLog";
        }

        headers.put("logType", logType);
        return event;
    }

    public List<Event> intercept(List<Event> events) {
        for (Event event : events) {
            intercept(event);
        }
        return events;
    }

    public void close() {
    }

    public static class Builder implements Interceptor.Builder {

        private boolean preserveExisting = PRESERVE_DFLT;

        public Interceptor build() {
            return new LogCollectInterceptor(preserveExisting);
        }

        public void configure(Context context) {
            preserveExisting = context.getBoolean(PRESERVE, PRESERVE_DFLT);
        }

    }

    public static class Constants {
        public static String TIMESTAMP = "timestamp";
        public static String PRESERVE  = "preserveExisting";

        public static boolean PRESERVE_DFLT = false;
    }

}