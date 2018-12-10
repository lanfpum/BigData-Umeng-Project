package lxpsee.top.web.utils;

import lxpsee.top.common.AppBaseLog;
import lxpsee.top.common.AppLogEntity;
import lxpsee.top.common.AppStartupLog;
import lxpsee.top.common.GeoInfo;
import lxpsee.top.utils.GeoUtil;
import lxpsee.top.utils.PropertiesUtil;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/7 18:42.
 */
public class ControllerUtil {
    // 地理信息缓存处理
    private static Map<String, GeoInfo> cache = new HashMap<String, GeoInfo>();

    /**
     * 处理ip client地址问题
     */
    public static void processClientIP(AppLogEntity e, String clientIP) {
        GeoInfo geoInfo = cache.get(clientIP);

        if (geoInfo == null) {
            geoInfo = GeoUtil.getGeoInfo(clientIP);
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
