package com.wjthinkbig.ColorStudy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ssomai.android.scalablelayout.ScalableLayout;

/**
 * Created by Administrator on 2016-01-22.
 */
public class Result extends Activity {

    private BackPressCloseSystem backPressCloseSystem;

    int color; //미션 컬러
    int firstCol, secondCol; //선택 컬러
    boolean anw; //정답 맞췄으면 true, 틀렸으면 false

    ImageView answer;
    Drawable anwTrue, anwFalse;
    Button btnTrue, btnFalse, exit;

    ScalableLayout rm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_main);

        backPressCloseSystem = new BackPressCloseSystem(this);

        ///결과화면 배경 지정하기 위해 ///
        ///////////////////////////////////////////////////////////////////
        int colors[] = {getResources().getColor(R.color.color_red), getResources().getColor(R.color.color_yellow), getResources().getColor(R.color.color_green)
                , getResources().getColor(R.color.color_blue), getResources().getColor(R.color.color_purple)
                , getResources().getColor(R.color.orange), getResources().getColor(R.color.yellowGreen), getResources().getColor(R.color.blueGreen)
                , getResources().getColor(R.color.bluishViolet), getResources().getColor(R.color.plum)
                , getResources().getColor(R.color.crimson), getResources().getColor(R.color.tangerine), getResources().getColor(R.color.greenYellow)
                , getResources().getColor(R.color.grassGreen), getResources().getColor(R.color.sea), getResources().getColor(R.color.prussianBlue), getResources().getColor(R.color.madderRed)};
        rm = (ScalableLayout)findViewById(R.id.rm);
        //////////////////////////////////////////////////////////////////////////

        DBmanager dbManager=new DBmanager(getApplicationContext(), "firststep.db", null, 1);

        dbManager.insert("insert into FIRST_COLOR values(null, 1, 0, 5);");
        dbManager.insert("insert into FIRST_COLOR values(null, 2, 1, 6);");
        dbManager.insert("insert into FIRST_COLOR values(null, 2, 3, 7);");
        dbManager.insert("insert into FIRST_COLOR values(null, 3, 4, 8);");
        dbManager.insert("insert into FIRST_COLOR values(null, 0, 4, 9);");

        ///////////////////////첫번째 선택 색, 두번째 선택 색, 미션색 받음
        Intent intent = getIntent();
        if(intent != null) {
            color = intent.getIntExtra("color", 0);
            firstCol = intent.getIntExtra("firstCol", 0);
            secondCol = intent.getIntExtra("secondCol", 0);
        }

        answer = (ImageView)findViewById(R.id.answer);
        anwTrue = getResources().getDrawable(R.drawable.anw_true);
        anwFalse = getResources().getDrawable(R.drawable.anw_false);

        btnTrue = (Button)findViewById(R.id.btnTrue); //다른 문제 풀기 버튼
        btnFalse = (Button)findViewById(R.id.btnFalse); //다시 풀기 버튼
        exit = (Button)findViewById(R.id.exit); //나가기 버튼

        anw=dbManager.firstCorrect(firstCol,secondCol,color);

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
                        finish();
                        break;

                    case R.id.btnFalse:
                        Intent goColorMixx = new Intent(Result.this, ColorMix.class);
                        goColorMixx.putExtra("color", color);
                        startActivity(goColorMixx);
                        finish();
                        break;

                    case R.id.exit: //나가기 버튼 -> 메인화면으로 이동
                        Intent goMain = new Intent(Result.this, MainActivity.class);
                        goMain.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(goMain);
                        finish();
                        break;
                }
            }
        };

        btnTrue.setOnClickListener(listener);
        btnFalse.setOnClickListener(listener);
        exit.setOnClickListener(listener);

    }

    @Override
    public void onBackPressed() {
        backPressCloseSystem.onBackPressed();
    }
}