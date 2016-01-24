package com.wjthinkbig.ColorStudy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Administrator on 2016-01-19.
 */
public class ColorMix extends Activity implements SensorEventListener {

    Button ColBtnRed, ColBtnYellow, ColBtnGreen, ColBtnBlue, ColBtnPurple;

    /////////////////////////
    Button missionColor;
    int color;

    Button ChangeBtn;
    ImageView character;

    ImageView board_L, board_R;

    Drawable board_2_red1, board_2_red2, board_2_yellow1, board_2_yellow2;
    Drawable board_2_green1, board_2_green2, board_2_blue1, board_2_blue2;
    Drawable board_2_purple1, board_2_purple2;

    long lastTime;
    float speed, lastX, lastY, lastZ, x, y, z;

    static final int SHAKE_THRESHOLD = 800;
    static final int DATA_X = SensorManager.DATA_X;
    static final int DATA_Y = SensorManager.DATA_Y;
    static final int DATA_Z = SensorManager.DATA_Z;

    SensorManager sensorManager;
    Sensor accelerormeterSensor;

    int turn, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_mix_main);

        ///////////////////////미션 색 나오는 버튼////////////////////
        int colors[] = {getResources().getColor(R.color.color_red), getResources().getColor(R.color.color_yellow), getResources().getColor(R.color.color_green)
                , getResources().getColor(R.color.color_blue), getResources().getColor(R.color.color_purple)};

        int random = (int) (Math.random() * 5); //0~4 까지 랜덤 수 만들기
        color = random; //인텐트 넘길때 쓸 color 정보

        missionColor = (Button)findViewById(R.id.missionColor); //미션 색 나타내기
        missionColor.setBackgroundColor(colors[random]);
        //////////////////////////////////////////////////////////////////////////////

        ChangeBtn=(Button)findViewById(R.id.Changebtn);
        character=(ImageView)findViewById(R.id.cookie);
        //?��???? ????
        ColBtnRed = (Button) findViewById(R.id.ColBtnRed);
        ColBtnYellow = (Button) findViewById(R.id.ColBtnYellow);
        ColBtnGreen = (Button) findViewById(R.id.ColBtnGreen);
        ColBtnBlue = (Button) findViewById(R.id.ColBtnBlue);
        ColBtnPurple = (Button) findViewById(R.id.ColBtnPurple);

        //????? ?? ????
        board_L = (ImageView) findViewById(R.id.board_L);
        board_R = (ImageView) findViewById(R.id.board_R);

        //????? ????????
        board_2_red1 = getResources().getDrawable(R.mipmap.board_2_red1);
        board_2_red2 = getResources().getDrawable(R.mipmap.board_2_red2);
        board_2_yellow1 = getResources().getDrawable(R.mipmap.board_2_yellow1);
        board_2_yellow2 = getResources().getDrawable(R.mipmap.board_2_yellow2);
        board_2_green1 = getResources().getDrawable(R.mipmap.board_2_green1);
        board_2_green2 = getResources().getDrawable(R.mipmap.board_2_green2);
        board_2_blue1 = getResources().getDrawable(R.mipmap.board_2_blue1);
        board_2_blue2 = getResources().getDrawable(R.mipmap.board_2_blue2);
        board_2_purple1 = getResources().getDrawable(R.mipmap.board_2_purple1);
        board_2_purple2 = getResources().getDrawable(R.mipmap.board_2_purple2);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        turn = 0;
        result = 0; //result가 1이면 shake 가능
        //turn이 0이면 왼쪽 반원 채워짐, 1이면 오른쪽 반원 채워짐.
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.ColBtnRed:
                        if(turn == 0) {
                            board_L.setImageDrawable(board_2_red1);
                            turn = 1;
                            result = 0;
                        }
                        else {
                            board_R.setImageDrawable(board_2_red2);
                            turn = 0;
                            result = 1;
                            Toast.makeText(getApplicationContext(), "휴대폰을 한 번 흔들어 색을 섞어보세요!", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.ColBtnYellow:
                        if(turn == 0) {
                            board_L.setImageDrawable(board_2_yellow1);
                            turn = 1;
                            result = 0;
                        }
                        else {
                            board_R.setImageDrawable(board_2_yellow2);
                            turn = 0;
                            result = 1;
                            Toast.makeText(getApplicationContext(), "휴대폰을 한 번 흔들어 색을 섞어보세요!", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.ColBtnGreen:
                        if(turn == 0) {
                            board_L.setImageDrawable(board_2_green1);
                            turn = 1;
                            result = 0;
                        }
                        else {
                            board_R.setImageDrawable(board_2_green2);
                            turn = 0;
                            result = 1;
                            Toast.makeText(getApplicationContext(), "휴대폰을 한 번 흔들어 색을 섞어보세요!", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.ColBtnBlue:
                        if(turn == 0) {
                            board_L.setImageDrawable(board_2_blue1);
                            turn = 1;
                            result = 0;
                        }
                        else {
                            board_R.setImageDrawable(board_2_blue2);
                            turn = 0;
                            result = 1;
                            Toast.makeText(getApplicationContext(), "휴대폰을 한 번 흔들어 색을 섞어보세요!", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.ColBtnPurple:
                        if(turn == 0) {
                            board_L.setImageDrawable(board_2_purple1);
                            turn = 1;
                            result = 0;
                        }
                        else {
                            board_R.setImageDrawable(board_2_purple2);
                            turn = 0;
                            result = 1;
                            Toast.makeText(getApplicationContext(), "휴대폰을 한 번 흔들어 색을 섞어보세요!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        };

        //????? ?????? ???
        ColBtnRed.setOnClickListener(listener);
        ColBtnYellow.setOnClickListener(listener);
        ColBtnGreen.setOnClickListener(listener);
        ColBtnBlue.setOnClickListener(listener);
        ColBtnPurple.setOnClickListener(listener);

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
                    Intent intent_ColorMix = new Intent(ColorMix.this, Result.class);
                    startActivity(intent_ColorMix);
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
}
