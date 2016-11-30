package hanhan.test.yunifang.com.yunifang.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/11/1.
 */

public class LogUtils {
    public static final boolean isdebug = true;

    public static void i(String TAG, String info) {
        if (isdebug) {
            Log.i(TAG, info);
        }
    }

    public static void d(String TAG, String info) {
        if (isdebug) {
            Log.d(TAG, info);
        }
    }

    public static void e(String TAG, String info) {
        if (isdebug) {
            Log.e(TAG, info);
        }
    }


}
