package jzl.remotecraft.sensor.util.Common;

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
}
