package lxpsee.top.client.utils;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/7 15:53.
 * <p>
 * 模拟手机上报日志程序
 */
public class UploadUtil {
    public static void upload(String json) throws Exception {
        try {
//            URL url = new URL("http://localhost:9090/coll/index");
            URL url = new URL("http://ip201:8080/app-web/coll/index");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("clientTime", System.currentTimeMillis() + "");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStream out = conn.getOutputStream();
            out.write(json.getBytes());
            out.flush();
            out.close();
            int code = conn.getResponseCode();
            System.out.println(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
