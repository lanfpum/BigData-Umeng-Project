package lxpsee.top.web.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.maxmind.db.Reader;
import lxpsee.top.utils.GeoUtil;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;


/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/7 18:07.
 */
public class Test4Controller {

    @Test
    public void test4GeoLite() {
        try {
            InputStream in = ClassLoader.getSystemResourceAsStream("GeoLite2-City.mmdb");
            Reader reader = new Reader(in);
            JsonNode node = reader.get(InetAddress.getByName("140.211.11.105"));
            String country = node.get("country").get("names").get("zh-CN").textValue();
            String area = node.get("subdivisions").get(0).get("names").get("zh-CN").textValue();
            String city = node.get("city").get("names").get("zh-CN").textValue();
            System.out.println(country + "." + area + "." + city);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        String country = GeoUtil.getCountry("140.211.11.105");
        System.out.println(country);
    }
}
