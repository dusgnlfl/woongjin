package com.wjthinkbig.ColorStudy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ssomai.android.scalablelayout.ScalableLayout;

/**
 * Created by Administrator on 2016-01-22.
 */
public class Result extends Activity {

    int color; //미션 컬러
    boolean anw; //정답 맞췄으면 true, 틀렸으면 false

    ImageView answer;
    Drawable anwTrue, anwFalse;
    Button btnTrue, btnFalse, exit;

    ScalableLayout rm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_main);

        ///////////////////////////////////////////////////////////////////
        int colors[] = {getResources().getColor(R.color.color_red), getResources().getColor(R.color.color_yellow), getResources().getColor(R.color.color_green)
                , getResources().getColor(R.color.color_blue), getResources().getColor(R.color.color_purple)};
        rm = (ScalableLayout)findViewById(R.id.rm);
        //////////////////////////////////////////////////////////////////////////

        Intent intent = getIntent();
        if(intent != null) {
            color = intent.getIntExtra("color", 0);
            anw = intent.getBooleanExtra("anw", true);
        }

        answer = (ImageView)findViewById(R.id.answer);
        anwTrue = getResources().getDrawable(R.drawable.anw_true);
        anwFalse = getResources().getDrawable(R.drawable.anw_false);

        btnTrue = (Button)findViewById(R.id.btnTrue); //다른 문제 풀기 버튼
        btnFalse = (Button)findViewById(R.id.btnFalse); //다시 풀기 버튼
        exit = (Button)findViewById(R.id.exit); //나가기 버튼

        if(anw == true) { /////////////정답 맞췄을 때
            answer.setImageDrawable(anwTrue);
            btnTrue.setVisibility(View.VISIBLE);
            btnFalse.setVisibility(View.INVISIBLE);
            rm.setBackgroundColor(colors[color]);
        }
        else { /////////////////////////정답 틀렸을 때
            answer.setImageDrawable(anwFalse);
            btnTrue.setVisibility(View.INVISIBLE);
            btnFalse.setVisibility(View.VISIBLE);
            rm.setBackgroundColor(Color.parseColor("#696969"));
        }

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnTrue:
                        Intent goColorMix = new Intent(Result.this, ColorMix.class);
                        goColorMix.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(goColorMix);
                        break;

                    case R.id.btnFalse:
                        Intent goColorMixx = new Intent(Result.this, ColorMix.class);
                        goColorMixx.putExtra("color", color);
                        startActivity(goColorMixx);
                        break;

                    case R.id.exit: //나가기 버튼 -> 메인화면으로 이동
                        Intent goMain = new Intent(Result.this, MainActivity.class);
                        goMain.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(goMain);
                        break;
                }
            }
        };

        btnTrue.setOnClickListener(listener);
        btnFalse.setOnClickListener(listener);
        exit.setOnClickListener(listener);

    }

}
