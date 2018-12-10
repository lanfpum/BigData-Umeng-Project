package lxpsee.top.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.maxmind.db.Reader;
import lxpsee.top.common.GeoInfo;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/7 18:15.
 * <p>
 * 根据IP获取国家省份地区工具类，GeoLite2-City.mmdb文件需要放置在resources中，pox.xml文件应该引入下面文件
 * <dependency>
 * <groupId>com.maxmind.db</groupId>
 * <artifactId>maxmind-db</artifactId>
 * <version>1.0.0</version>
 * </dependency>
 */
public class GeoUtil {
    private static InputStream inputStream;
    private static Reader      reader;

    static {
        try {
            inputStream = ClassLoader.getSystemResourceAsStream("GeoLite2-City.mmdb");
            reader = new Reader(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCountry(String ip) {
        String result = "";
        try {
            JsonNode node = reader.get(InetAddress.getByName(ip));
            result = node.get("country").get("names").get("zh-CN").textValue();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getProvince(String ip) {
        String result = "";
        try {
            JsonNode node = reader.get(InetAddress.getByName(ip));
            result = node.get("subdivisions").get(0).get("names").get("zh-CN").textValue();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getCity(String ip) {
        String result = "";
        try {
            JsonNode node = reader.get(InetAddress.getByName(ip));
            result = node.get("city").get("names").get("zh-CN").textValue();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static GeoInfo getGeoInfo(String ip) {
        try {
            JsonNode node = reader.get(InetAddress.getByName(ip));
            String country = getCountry(ip);
            String province = getProvince(ip);

            if (isNotBlank(country) && isNotBlank(province)) {
                return new GeoInfo(country, province);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
