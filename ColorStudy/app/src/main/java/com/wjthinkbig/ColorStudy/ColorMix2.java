package com.wjthinkbig.ColorStudy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

/**
 * Created by Administrator on 2016-01-21.
 */
public class ColorMix2 extends Activity implements SensorEventListener {

    Button ColBtnRed, ColBtnYellow, ColBtnGreen, ColBtnBlue, ColBtnPurple;

    private BackPressCloseSystem backPressCloseSystem;

    /////////////////////////
    Button missionColor;
    public int random, color;//*************************************************************************************************************************
    boolean anw;//*************************************************************************************************************************

    Button ChangeBtn;
    ImageView character;

    ImageView board_1, board_2, board_3;

    Drawable board_3_red1, board_3_red2, board_3_red3, board_3_yellow1, board_3_yellow2, board_3_yellow3;
    Drawable board_3_green1, board_3_green2, board_3_green3, board_3_blue1, board_3_blue2, board_3_blue3;
    Drawable board_3_purple1, board_3_purple2, board_3_purple3;

    long lastTime;
    float speed, lastX, lastY, lastZ, x, y, z;

    static final int SHAKE_THRESHOLD = 800;
    static final int DATA_X = SensorManager.DATA_X;
    static final int DATA_Y = SensorManager.DATA_Y;
    static final int DATA_Z = SensorManager.DATA_Z;

    SensorManager sensorManager;
    Sensor accelerormeterSensor;

    Button goToMain;

    int turn, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_mix_main2);

        backPressCloseSystem = new BackPressCloseSystem(this);

        ///////////////////////미션 색 나오는 버튼////////////////////
        int colors[] = {getResources().getColor(R.color.color_red), getResources().getColor(R.color.color_yellow), getResources().getColor(R.color.color_green)
                , getResources().getColor(R.color.color_blue), getResources().getColor(R.color.color_purple)};

        random = (int) (Math.random() * 5); //0~4 까지 랜덤 수 만들기
        color = random; //인텐트 넘길때 쓸 color 정보

        Intent fromResult = getIntent();//*************************************************************************************************************************
        if(fromResult.getIntExtra("color", 1000) != 1000) {
            color = fromResult.getIntExtra("color", 1000);
            random = color;
        }//*************************************************************************************************************************

        missionColor = (Button)findViewById(R.id.missionColor); //미션 색 나타내기
        missionColor.setBackgroundColor(colors[random]);
        //////////////////////////////////////////////////////////////////////////////

        ChangeBtn=(Button)findViewById(R.id.Changebtn);
        character=(ImageView)findViewById(R.id.cookie);

        character.setImageResource(R.drawable.apple);

        //팔레트 버튼 가져오기
        ColBtnRed = (Button) findViewById(R.id.ColBtnRed);
        ColBtnYellow = (Button) findViewById(R.id.ColBtnYellow);
        ColBtnGreen = (Button) findViewById(R.id.ColBtnGreen);
        ColBtnBlue = (Button) findViewById(R.id.ColBtnBlue);
        ColBtnPurple = (Button) findViewById(R.id.ColBtnPurple);

        //이미지 뷰 가져오기
        board_1 = (ImageView) findViewById(R.id.board_1);
        board_2 = (ImageView) findViewById(R.id.board_2);
        board_3 = (ImageView) findViewById(R.id.board_3);

        //이미지 가져오기
        board_3_red1 = getResources().getDrawable(R.mipmap.board_3_red1);
        board_3_red2 = getResources().getDrawable(R.mipmap.board_3_red2);
        board_3_red3 = getResources().getDrawable(R.mipmap.board_3_red3);
        board_3_yellow1 = getResources().getDrawable(R.mipmap.board_3_yellow1);
        board_3_yellow2 = getResources().getDrawable(R.mipmap.board_3_yellow2);
        board_3_yellow3 = getResources().getDrawable(R.mipmap.board_3_yellow3);
        board_3_green1 = getResources().getDrawable(R.mipmap.board_3_green1);
        board_3_green2 = getResources().getDrawable(R.mipmap.board_3_green2);
        board_3_green3 = getResources().getDrawable(R.mipmap.board_3_green3);
        board_3_blue1 = getResources().getDrawable(R.mipmap.board_3_blue1);
        board_3_blue2 = getResources().getDrawable(R.mipmap.board_3_blue2);
        board_3_blue3 = getResources().getDrawable(R.mipmap.board_3_blue3);
        board_3_purple1 = getResources().getDrawable(R.mipmap.board_3_purple1);
        board_3_purple2 = getResources().getDrawable(R.mipmap.board_3_purple2);
        board_3_purple3 = getResources().getDrawable(R.mipmap.board_3_purple3);

        goToMain = (Button)findViewById(R.id.goToMain);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        turn = 0;//turn이 0이면 첫번째 색 조각 채워짐, 1이면 두 번째, 2이면 세 번째 색이 채워진다.
        result = 0; //result가 1이면 shake 가능
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.ColBtnRed:
                        if(turn == 0) {
                            board_1.setImageDrawable(board_3_red1);
                            turn = 1;
                            result = 0;
                        }
                        else if(turn == 1){
                            board_2.setImageDrawable(board_3_red2);
                            turn = 2;
                            result = 0;
                        }
                        else {
                            board_3.setImageDrawable(board_3_red3);
                            turn = 0;
                            result = 1;
                            Toast.makeText(getApplicationContext(), "휴대폰을 한 번 흔들어 색을 섞어보세요!", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.ColBtnYellow:
                        if(turn == 0) {
                            board_1.setImageDrawable(board_3_yellow1);
                            turn = 1;
                            result = 0;
                        }
                        else if(turn == 1){
                            board_2.setImageDrawable(board_3_yellow2);
                            turn = 2;
                            result = 0;
                        }
                        else {
                            board_3.setImageDrawable(board_3_yellow3);
                            turn = 0;
                            result = 1;
                            Toast.makeText(getApplicationContext(), "휴대폰을 한 번 흔들어 색을 섞어보세요!", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.ColBtnGreen:
                        if(turn == 0) {
                            board_1.setImageDrawable(board_3_green1);
                            turn = 1;
                            result = 0;
                        }
                        else if(turn == 1){
                            board_2.setImageDrawable(board_3_green2);
                            turn = 2;
                            result = 0;
                        }
                        else {
                            board_3.setImageDrawable(board_3_green3);
                            turn = 0;
                            result = 1;
                            Toast.makeText(getApplicationContext(), "휴대폰을 한 번 흔들어 색을 섞어보세요!", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.ColBtnBlue:
                        if(turn == 0) {
                            board_1.setImageDrawable(board_3_blue1);
                            turn = 1;
                            result = 0;
                        }
                        else if(turn == 1){
                            board_2.setImageDrawable(board_3_blue2);
                            turn = 2;
                            result = 0;
                        }
                        else {
                            board_3.setImageDrawable(board_3_blue3);
                            turn = 0;
                            result = 1;
                            Toast.makeText(getApplicationContext(), "휴대폰을 한 번 흔들어 색을 섞어보세요!", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.ColBtnPurple:
                        if(turn == 0) {
                            board_1.setImageDrawable(board_3_purple1);
                            turn = 1;
                            result = 0;
                        }
                        else if(turn == 1){
                            board_2.setImageDrawable(board_3_purple2);
                            turn = 2;
                            result = 0;
                        }
                        else {
                            board_3.setImageDrawable(board_3_purple3);
                            turn = 0;
                            result = 1;
                            Toast.makeText(getApplicationContext(), "휴대폰을 한 번 흔들어 색을 섞어보세요!", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.goToMain:
                        Intent i = new Intent(ColorMix2.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(i);
                        break;
                }
            }
        };

        //팔레트 버튼 리스터 등록
        ColBtnRed.setOnClickListener(listener);
        ColBtnYellow.setOnClickListener(listener);
        ColBtnGreen.setOnClickListener(listener);
        ColBtnBlue.setOnClickListener(listener);
        ColBtnPurple.setOnClickListener(listener);
        goToMain.setOnClickListener(listener);

    }

    public void monClick(View v) {
        PopupMenu popup=new PopupMenu(this, v);
        MenuInflater inflater=popup.getMenuInflater();
        Menu menu=popup.getMenu();
        inflater.inflate(R.menu.cookiemenu, menu);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.banana:
                        character.setImageResource(R.drawable.bana);
                        break;
                    case R.id.berry:
                        character.setImageResource(R.drawable.berry);
                        break;
                    case R.id.apple:
                        character.setImageResource(R.drawable.apple);
                        break;
                    case R.id.choco:
                        character.setImageResource(R.drawable.choco);
                        break;
                    case R.id.kiwi:
                        character.setImageResource(R.drawable.kiwi);
                        break;
                }
                return false;
            }
        });
        popup.show();
    }
    ///////////////////////////////////////////////////////////가속도 센서//////////////////
    public void onStart() {
        super.onStart();
        if (accelerormeterSensor != null)
            sensorManager.registerListener(this, accelerormeterSensor,
                    SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (sensorManager != null)
            sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();
            long gabOfTime = (currentTime - lastTime);
            if (gabOfTime > 100) {
                lastTime = currentTime;
                x = event.values[SensorManager.DATA_X];
                y = event.values[SensorManager.DATA_Y];
                z = event.values[SensorManager.DATA_Z];

                speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;

                if ((speed > SHAKE_THRESHOLD) && (result == 1)) {
                    ////////////////// 이벤트발생!!
                    Intent intent_ColorMix2 = new Intent(ColorMix2.this, Result2.class);
                    intent_ColorMix2.putExtra("color", color);//*************************************************************************************************************************
                    intent_ColorMix2.putExtra("anw", anw);//*************************************************************************************************************************
                    intent_ColorMix2.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);//*************************************************************************************************************************
                    startActivity(intent_ColorMix2);
                }

                lastX = event.values[DATA_X];
                lastY = event.values[DATA_Y];
                lastZ = event.values[DATA_Z];
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onBackPressed() {
        backPressCloseSystem.onBackPressed();
    }
}
