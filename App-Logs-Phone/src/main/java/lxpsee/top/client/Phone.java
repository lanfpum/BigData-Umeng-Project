package lxpsee.top.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/7 11:24.
 * <p>
 * 模拟手机上报日志程序
 * <p>
 * 1.从配置文件中读取设定好的数据流
 * 2.创建连接，设置请求方式，运行上传数据，请求头信息，内容类型
 * 3.获取输出流，将内容写到连接的输出流
 * 4.获取响应码并输出
 */
public class Phone {
    public static void main(String[] args) {
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("log.json");
            URL url = new URL("http://localhost:9090/coll/index");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            OutputStream outputStream = connection.getOutputStream();

            byte[] buf = new byte[1024];
            int len;

            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();

            int code = connection.getResponseCode();
            System.out.println(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
