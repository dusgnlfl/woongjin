package com.wjthinkbig.ColorStudy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Administrator on 2016-01-22.
 */
public class Result extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_main);

        Intent intent = getIntent();
        if(intent != null) {

        }
    }
}
