package com.wh.aop.service;

import android.util.Log;

/**
 * Created by wanghao on 2017/4/19.
 */

public class DebugLog {
    private DebugLog() {}

    /**
     * Send a debug log message
     *
     * @param tag Source of a log message.
     * @param message The message you would like logged.
     */
    public static void log(String tag, String message) {
        Log.d(tag, message);
    }
}
