package lxpsee.top.applogs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/18 10:28.
 */
public class ServiceStarter {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        while (true) {
            Thread.sleep(1000);
        }
    }
}
