package com.wh.aop;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.wh.aop.annotation.DebugTrace;

/**
 * Created by wanghao on 2017/4/19.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        testAnnotatedMethod();
        testLog("testAop");
        cacle();
    }

    @DebugTrace
    private void testAnnotatedMethod() {
        try {
            Thread.sleep(10);
            Log.i("MainActivity","休眠10ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void testLog(String message){
        Log.i("MainActivity","调用了TestLog");
    }

    @DebugTrace
    public void cacle(){
        Log.i("MainActivity","执行业务");
    }

}
