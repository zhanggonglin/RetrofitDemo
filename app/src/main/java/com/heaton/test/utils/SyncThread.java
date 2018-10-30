package com.heaton.test.utils;

import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * Created by zhanggonglin on 2018/10/30.
 */

public class SyncThread implements Runnable {
    private static int count;
    private final String TAG = "SyncThread";

    public SyncThread() {
        count = 0;
        Log.d(TAG, "我执行了，count=" + count);
    }

    public void run() {
       /* synchronized (this) {
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(TAG, Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }*/

        getSynMoth();
    }

    public synchronized void getSynMoth() {
        for (int i = 0; i < 5; i++) {
            try {
                Log.d(TAG, Thread.currentThread().getName() + ":" + (count++));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
