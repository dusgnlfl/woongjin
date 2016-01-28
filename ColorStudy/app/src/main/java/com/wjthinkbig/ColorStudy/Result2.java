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
 * Created by Administrator on 2016-01-24.
 */
public class Result2 extends Activity {

    private BackPressCloseSystem backPressCloseSystem;

    int color; //미션 컬러
    int firstCol, secondCol, thirdCol; //선택 컬러
    boolean anw; //정답 맞췄으면 true, 틀렸으면 false

    ImageView answer;
    Drawable anwTrue, anwFalse;
    Button btnTrue, btnFalse, exit;

    ScalableLayout rm2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_main2);

        backPressCloseSystem = new BackPressCloseSystem(this);

        ///////////////////////////////////////////////////////////////////
        int colors[] = {getResources().getColor(R.color.color_red), getResources().getColor(R.color.color_yellow), getResources().getColor(R.color.color_green)
                , getResources().getColor(R.color.color_blue), getResources().getColor(R.color.color_purple)
                , getResources().getColor(R.color.orange), getResources().getColor(R.color.yellowGreen), getResources().getColor(R.color.blueGreen)
                , getResources().getColor(R.color.bluishViolet), getResources().getColor(R.color.plum)
                , getResources().getColor(R.color.crimson), getResources().getColor(R.color.tangerine), getResources().getColor(R.color.greenYellow)
                , getResources().getColor(R.color.grassGreen), getResources().getColor(R.color.sea), getResources().getColor(R.color.prussianBlue), getResources().getColor(R.color.madderRed)};
        rm2 = (ScalableLayout)findViewById(R.id.rm2);
        //////////////////////////////////////////////////////////////////////////

        DBmanager dbManager=new DBmanager(getApplicationContext(), "secondstep.db", null, 1);

        dbManager.insert("insert into SECOND_COLOR values(null, 1, 0, 0, 10);");
        dbManager.insert("insert into SECOND_COLOR values(null, 0, 1, 1, 11);");
        dbManager.insert("insert into SECOND_COLOR values(null, 2, 1, 1, 12);");
        dbManager.insert("insert into SECOND_COLOR values(null, 1, 2, 2, 13);");
        dbManager.insert("insert into SECOND_COLOR values(null, 2, 3, 3, 14);");
        dbManager.insert("insert into SECOND_COLOR values(null, 4, 3, 3, 15);");
        dbManager.insert("insert into SECOND_COLOR values(null, 4, 0, 0, 16);");

        Intent intent = getIntent();
        if(intent != null) {
            color = intent.getIntExtra("color", 0);
            firstCol = intent.getIntExtra("firstCol", 0);
            secondCol = intent.getIntExtra("secondCol", 0);
            thirdCol = intent.getIntExtra("thirdCol", 0);
        }

        answer = (ImageView)findViewById(R.id.answer);
        anwTrue = getResources().getDrawable(R.drawable.anw_true);
        anwFalse = getResources().getDrawable(R.drawable.anw_false);

        btnTrue = (Button)findViewById(R.id.btnTrue); //다른 문제 풀기 버튼
        btnFalse = (Button)findViewById(R.id.btnFalse); //다시 풀기 버튼
        exit = (Button)findViewById(R.id.exit); //나가기 버튼

        anw=dbManager.secondCorrect(firstCol,secondCol,thirdCol,color);

        if(anw == true) { /////////////정답 맞췄을 때
            answer.setImageDrawable(anwTrue);
            btnTrue.setVisibility(View.VISIBLE);
            btnFalse.setVisibility(View.INVISIBLE);
            rm2.setBackgroundColor(colors[color]);
        }
        else { /////////////////////////정답 틀렸을 때
            answer.setImageDrawable(anwFalse);
            btnTrue.setVisibility(View.INVISIBLE);
            btnFalse.setVisibility(View.VISIBLE);
            rm2.setBackgroundColor(Color.parseColor("#696969"));
        }

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnTrue:
                        Intent goColorMix = new Intent(Result2.this, ColorMix2.class);
                        goColorMix.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(goColorMix);
                        finish();
                        break;

                    case R.id.btnFalse:
                        Intent goColorMixx = new Intent(Result2.this, ColorMix2.class);
                        goColorMixx.putExtra("color", color);
                        startActivity(goColorMixx);
                        finish();
                        break;

                    case R.id.exit: //나가기 버튼 -> 메인화면으로 이동
                        Intent goMain = new Intent(Result2.this, MainActivity.class);
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
