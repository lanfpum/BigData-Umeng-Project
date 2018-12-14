package lxpsee.top.web.utils;

import com.alibaba.fastjson.JSONObject;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import lxpsee.top.common.*;
import lxpsee.top.utils.GeoUtil;
import lxpsee.top.utils.PropertiesUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/7 18:42.
 */
public class ControllerUtil {
    // 地理信息缓存处理
    private static Map<String, GeoInfo> cache = new HashMap<String, GeoInfo>();

    /**
     * 发送信息到kafka
     * 1.创建配置对象
     * 2.创建生产者
     * 3.逐个发送kafka消息
     * 4.关闭 生产者
     */
    public static void sendMessage(AppLogEntity e) {
        Properties props = new Properties();
        props.put("metadata.broker.list", "ip202:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");

        Producer<Integer, String> producer = new Producer<Integer, String>(new ProducerConfig(props));

        sendSingleLog(producer, e.getAppStartupLogs(), Constants.TOPIC_APP_START_UP);
        sendSingleLog(producer, e.getAppErrorLogs(), Constants.TOPIC_APP_ERROR);
        sendSingleLog(producer, e.getAppEventLogs(), Constants.TOPIC_APP_EVENT);

        sendSingleLog(producer, e.getAppPageLogs(), Constants.TOPIC_APP_PAGE);
        sendSingleLog(producer, e.getAppUsageLogs(), Constants.TOPIC_APP_USAGE);

        producer.close();
    }

    private static void sendSingleLog(Producer<Integer, String> producer, AppBaseLog[] appBaseLogs, String topic) {
        for (AppBaseLog appBaseLog : appBaseLogs) {
            String logMsg = JSONObject.toJSONString(appBaseLog);
            KeyedMessage<Integer, String> data = new KeyedMessage<Integer, String>(topic, logMsg);
            producer.send(data);
        }
    }


    /**
     * 处理ip client地址问题
     */
    public static void processClientIP(AppLogEntity e, String clientIP) {
        GeoInfo geoInfo = cache.get(clientIP);

        if (geoInfo == null) {
            geoInfo = GeoUtil.getGeoInfo(clientIP);
//            geoInfo = new GeoInfo("中国", "浙江省");
            cache.put(clientIP, geoInfo);
        }

        for (AppStartupLog log : e.getAppStartupLogs()) {
            log.setCountry(geoInfo.getCountry());
            log.setProvince(geoInfo.getProvince());
            log.setIpAddress(clientIP);
        }

    }

    /**
     * 修正时间,通过对客户端时间与服务器的时间差进行计算，然后加上之前已经设定好的各类日志时间
     * 既可换算成对应的服务器时间，也就是所需的正确时间，避免因为客户端时间设定不准确，时差等导致数据混乱
     */
    public static void verifyTime(AppLogEntity e, long diff) {
        for (AppBaseLog log : e.getAppStartupLogs()) {
            log.setCreatedAtMs(log.getCreatedAtMs() + diff);
        }

        for (AppBaseLog log : e.getAppErrorLogs()) {
            log.setCreatedAtMs(log.getCreatedAtMs() + diff);
        }

        for (AppBaseLog log : e.getAppEventLogs()) {
            log.setCreatedAtMs(log.getCreatedAtMs() + diff);
        }

        for (AppBaseLog log : e.getAppPageLogs()) {
            log.setCreatedAtMs(log.getCreatedAtMs() + diff);
        }

        for (AppBaseLog log : e.getAppUsageLogs()) {
            log.setCreatedAtMs(log.getCreatedAtMs() + diff);
        }
    }

    /**
     * 将所有的日志都拷贝到AppLogEntity,复制基本属性
     * 修正版本，直接调用重载的方法
     */
    public static void processLogs(AppLogEntity e) {
        PropertiesUtil.copyProperties(e, e.getAppStartupLogs());
        PropertiesUtil.copyProperties(e, e.getAppErrorLogs());
        PropertiesUtil.copyProperties(e, e.getAppEventLogs());
        PropertiesUtil.copyProperties(e, e.getAppPageLogs());
        PropertiesUtil.copyProperties(e, e.getAppUsageLogs());
    }
}
