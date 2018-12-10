package lxpsee.top.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/7 15:33.
 * <p>
 * 属性复制工具类
 */
public class PropertiesUtil {

    /**
     * 通过内省复制属性
     * 1.取得源对象的BI
     * 2.取得原对象的 属性描述符
     * 3.遍历远对象描述符数组，取得getter和setter方法，并获取setter方法的名称和参数类型，获取src对象的get方法的返回值
     * 4.获得dest对象对应的方法并执行方法，有异常跳出
     */
    public static void copyProperties(Object src, Object dest) {
        try {
            BeanInfo biSrc = Introspector.getBeanInfo(src.getClass());
            PropertyDescriptor[] parr = biSrc.getPropertyDescriptors();

            for (PropertyDescriptor pd : parr) {
                Method getter = pd.getReadMethod();
                Method setter = pd.getWriteMethod();
                String methodName = setter.getName();
                Class<?>[] parameterTypes = setter.getParameterTypes();
                Object value = getter.invoke(src);

                Method destSetter = dest.getClass().getMethod(methodName, parameterTypes);
                destSetter.invoke(dest, value);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyProperties(Object src, Object[] destArr) {
        for (Object dest : destArr) {
            copyProperties(src, dest);
        }
    }
}
