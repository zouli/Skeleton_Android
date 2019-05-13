package com.riverside.skeleton.android.util.log;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

/**
 * 通用Log输出类 2.0
 * b_e  2017/11/26
 * 修改结构 2019/05/13
 */
public final class SLog {

    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_DEBUG = 1;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_WARNING = 3;
    public static final int LEVEL_ERROR = 4;
    public static final int LEVEL_FATAL = 5;

    private static int sLevel = LEVEL_VERBOSE;
    private static String TAG = "SLog";

    private static Boolean isDebug = null;

    private SLog() {
    }

    /**
     * 初始化CLog
     *
     * @param context
     */
    public static void init(Context context) {
        if (isDebug == null) {
            isDebug = context.getApplicationInfo() != null &&
                    (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        }
    }

    /**
     * 返回当前是否为Debug
     *
     * @return
     */
    public static boolean isDebug() {
        return isDebug == null ? false : isDebug;
    }

    /**
     * 设置Log级别，比设置的Log级别低的不会被打印
     *
     * @param level
     */
    public static void setLogLevel(int level) {
        sLevel = level;
    }

    public static void v(String msg) {
        v(getTag(), msg);
    }

    public static void v(String tag, String msg) {
        v(tag, msg, (Throwable) null);
    }

    public static void v(String msg, Throwable throwable) {
        v(getTag(), msg, throwable);
    }

    public static void v(String tag, String msg, Throwable throwable) {
        print(LEVEL_VERBOSE, tag, msg, throwable);
    }

    public static void v(String msg, Object... args) {
        v(getTag(), msg, args);
    }

    public static void v(String tag, String msg, Object... args) {
        print(LEVEL_VERBOSE, tag, msg, null, args);
    }

    //===========================================================
    public static void d(String msg) {
        d(getTag(), msg);
    }

    public static void d(String tag, String msg) {
        d(tag, msg, (Throwable) null);
    }

    public static void d(String msg, Object... args) {
        d(getTag(), msg, args);
    }

    public static void d(String tag, String msg, Object... args) {
        print(LEVEL_DEBUG, tag, msg, null, args);
    }

    public static void d(String msg, Throwable throwable) {
        d(getTag(), msg, throwable);
    }

    public static void d(String tag, String msg, Throwable throwable) {
        print(LEVEL_DEBUG, tag, msg, throwable);
    }

    //===========================================================
    public static void i(String msg) {
        i(getTag(), msg);
    }

    public static void i(String tag, String msg) {
        i(tag, msg, (Throwable) null);
    }

    public static void i(String msg, Object... args) {
        i(getTag(), msg, args);
    }

    public static void i(String tag, String msg, Object... args) {
        print(LEVEL_INFO, tag, msg, null, args);
    }

    public static void i(String msg, Throwable throwable) {
        i(getTag(), msg, throwable);
    }

    public static void i(String tag, String msg, Throwable throwable) {
        print(LEVEL_INFO, tag, msg, throwable);
    }

    //===========================================================
    public static void w(String msg) {
        w(getTag(), msg);
    }

    public static void w(String tag, String msg) {
        w(tag, msg, (Throwable) null);
    }

    public static void w(String msg, Object... args) {
        w(getTag(), msg, args);
    }

    public static void w(String tag, String msg, Object... args) {
        print(LEVEL_WARNING, tag, msg, null, args);
    }

    public static void w(String msg, Throwable throwable) {
        w(getTag(), msg, throwable);
    }

    public static void w(String tag, String msg, Throwable throwable) {
        print(LEVEL_WARNING, tag, msg, throwable);
    }

    //===========================================================
    public static void e(String msg) {
        e(getTag(), msg);
    }

    public static void e(String tag, String msg) {
        e(tag, msg, (Throwable) null);
    }

    public static void e(String msg, Object... args) {
        e(getTag(), msg, args);
    }

    public static void e(String tag, String msg, Object... args) {
        print(LEVEL_ERROR, tag, msg, null, args);
    }

    public static void e(String msg, Throwable throwable) {
        e(getTag(), msg, throwable);
    }

    public static void e(String tag, String msg, Throwable throwable) {
        print(LEVEL_ERROR, tag, msg, throwable);
    }

    //===========================================================
    public static void f(String msg) {
        f(getTag(), msg);
    }

    public static void f(String tag, String msg) {
        f(tag, msg, (Throwable) null);
    }

    public static void f(String msg, Object... args) {
        f(getTag(), msg, args);
    }

    public static void f(String tag, String msg, Object... args) {
        print(LEVEL_ERROR, tag, msg, null, args);
    }

    public static void f(String msg, Throwable throwable) {
        f(getTag(), msg, throwable);
    }

    public static void f(String tag, String msg, Throwable throwable) {
        print(LEVEL_ERROR, tag, msg, throwable);
    }

    /**
     * 打印Log信息
     *
     * @param level
     * @param tag
     * @param msg
     * @param throwable
     * @param args
     */
    private static void print(int level, String tag, String msg, Throwable throwable, Object... args) {
        if (args.length > 0) msg = String.format(msg, args);
        if (level < sLevel) return;
        switch (level) {
            case LEVEL_VERBOSE:
                if (throwable != null) {
                    Log.v(tag, msg, throwable);
                } else {
                    Log.v(tag, msg);
                }
                break;
            case LEVEL_DEBUG:
                if (throwable != null) {
                    Log.d(tag, msg, throwable);
                } else {
                    Log.d(tag, msg);
                }
                break;
            case LEVEL_INFO:
                if (throwable != null) {
                    Log.i(tag, msg, throwable);
                } else {
                    Log.i(tag, msg);
                }
                break;
            case LEVEL_WARNING:
                if (throwable != null) {
                    Log.w(tag, msg, throwable);
                } else {
                    Log.w(tag, msg);
                }
                break;
            case LEVEL_ERROR:
                if (throwable != null) {
                    Log.e(tag, msg, throwable);
                } else {
                    Log.e(tag, msg);
                }
                break;
            case LEVEL_FATAL:
                if (throwable != null) {
                    Log.wtf(tag, msg, throwable);
                } else {
                    Log.wtf(tag, msg);
                }
                break;
        }
    }

    /**
     * 自动生成TAG
     *
     * @return
     */
    private static String getTag() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return TAG;
        }

        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }

            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }

            if (st.getClassName().equals(SLog.class.getName())) {
                continue;
            }

            return "[" + Thread.currentThread().getName()
                    + "(" + Thread.currentThread().getId() + "): "
                    + st.getFileName()
                    + "(" + st.getLineNumber() + "):"
                    + st.getMethodName() + "]";
        }

        return TAG;
    }
}
