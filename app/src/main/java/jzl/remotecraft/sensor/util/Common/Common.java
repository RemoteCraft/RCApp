package jzl.remotecraft.sensor.util.common;

import java.util.List;

/**
 * Created by jzl on 2016/6/4.
 */
public class Common {
    public static boolean isEmpty(List<?> list) {
        return list == null || list.size() == 0;
    }
    public static boolean isEmpty(List<?> list, int index) {
        return list == null || list.size() == 0 || list.size() <= index;
    }
    /**
     * return if str is empty
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0 || str.equalsIgnoreCase("null") || str.isEmpty() || str.equals("")) {
            return true;
        } else {
            return false;
        }
    }
}
