package com.wjthinkbig.ColorStudy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016-01-19.
 */
public class ColorMix extends Activity {

    Button ColBtnRed, ColBtnYellow, ColBtnGreen, ColBtnBlue, ColBtnPurple;

    ImageView board_L, board_R;

    Drawable board_2_red1, board_2_red2, board_2_yellow1, board_2_yellow2;
    Drawable board_2_green1, board_2_green2, board_2_blue1, board_2_blue2;
    Drawable board_2_purple1, board_2_purple2;

    int turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_mix_main);

        //?¡À???? ????
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

        turn = 0;
        //?¡À? ??? ???? ???
        //turn?? 0??? ???? ??? ?????, turn?? 1??? ?????? ??? ?????.
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.ColBtnRed:
                        if(turn == 0) {
                            board_L.setImageDrawable(board_2_red1);
                            turn = 1;
                        }
                        else {
                            board_R.setImageDrawable(board_2_red2);
                            turn = 0;
                        }
                        break;

                    case R.id.ColBtnYellow:
                        if(turn == 0) {
                            board_L.setImageDrawable(board_2_yellow1);
                            turn = 1;
                        }
                        else {
                            board_R.setImageDrawable(board_2_yellow2);
                            turn = 0;
                        }
                        break;

                    case R.id.ColBtnGreen:
                        if(turn == 0) {
                            board_L.setImageDrawable(board_2_green1);
                            turn = 1;
                        }
                        else {
                            board_R.setImageDrawable(board_2_green2);
                            turn = 0;
                        }
                        break;

                    case R.id.ColBtnBlue:
                        if(turn == 0) {
                            board_L.setImageDrawable(board_2_blue1);
                            turn = 1;
                        }
                        else {
                            board_R.setImageDrawable(board_2_blue2);
                            turn = 0;
                        }
                        break;

                    case R.id.ColBtnPurple:
                        if(turn == 0) {
                            board_L.setImageDrawable(board_2_purple1);
                            turn = 1;
                        }
                        else {
                            board_R.setImageDrawable(board_2_purple2);
                            turn = 0;
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
}
