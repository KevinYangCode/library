package xyz.jianzha.library.utils;

/**
 * 工具类
 *
 * @author Y_Kevin
 * @date 2020-01-15 14:54
 */
public class Tools {
    /**
     * 不为空
     *
     * @param object 参数
     * @return 不为空true
     */
    public static boolean isNotEmpty(Object object) {
        return object != null && !"".equals(object);
    }

    /**
     * 为空
     *
     * @param object 参数
     * @return 为空true
     */
    public static boolean isEmpty(Object object) {
        return object == null || "".equals(object);
    }
}
