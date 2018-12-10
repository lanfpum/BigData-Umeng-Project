package lxpsee.top.client.utils;

import org.junit.Test;

import java.util.Arrays;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/7 15:16.
 */
public class Test4GenData {

    @Test
    public void testinitDeviceId() {
        String base = "device22";
        String[] result = new String[100];
        for (int i = 0; i < 100; i++) {
            result[i] = base + i ;
        }

        System.out.println(Arrays.toString(result));
    }

    @Test
    public void testinitCreatedAtMs() {
        Long createdAtMs = System.currentTimeMillis();
        Long[] result = new Long[11];
        for (int i = 0; i < 10; i++) {
            result[i] = createdAtMs - (long) (i * 24 * 3600 * 1000);
        }
        result[10] = createdAtMs;
        System.out.println(Arrays.toString(result));
    }
}
