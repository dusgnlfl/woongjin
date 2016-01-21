package com.wjthinkbig.ColorStudy;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016-01-21.
 */
public class ColorMix2 extends Activity{

    Button ColBtnRed, ColBtnYellow, ColBtnGreen, ColBtnBlue, ColBtnPurple;

    ImageView board_1, board_2, board_3;

    Drawable board_3_red1, board_3_red2, board_3_red3, board_3_yellow1, board_3_yellow2, board_3_yellow3;
    Drawable board_3_green1, board_3_green2, board_3_green3, board_3_blue1, board_3_blue2, board_3_blue3;
    Drawable board_3_purple1, board_3_purple2, board_3_purple3;

    int turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_mix_main2);

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

        turn = 0;
        //turn이 0이면 첫번째 색 조각 채워짐, 1이면 두 번째, 2이면 세 번째 색이 채워진다.
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.ColBtnRed:
                        if(turn == 0) {
                            board_1.setImageDrawable(board_3_red1);
                            turn = 1;
                        }
                        else if(turn == 1){
                            board_2.setImageDrawable(board_3_red2);
                            turn = 2;
                        }
                        else {
                            board_3.setImageDrawable(board_3_red3);
                            turn = 0;
                        }
                        break;

                    case R.id.ColBtnYellow:
                        if(turn == 0) {
                            board_1.setImageDrawable(board_3_yellow1);
                            turn = 1;
                        }
                        else if(turn == 1){
                            board_2.setImageDrawable(board_3_yellow2);
                            turn = 2;
                        }
                        else {
                            board_3.setImageDrawable(board_3_yellow3);
                            turn = 0;
                        }
                        break;

                    case R.id.ColBtnGreen:
                        if(turn == 0) {
                            board_1.setImageDrawable(board_3_green1);
                            turn = 1;
                        }
                        else if(turn == 1){
                            board_2.setImageDrawable(board_3_green2);
                            turn = 2;
                        }
                        else {
                            board_3.setImageDrawable(board_3_green3);
                            turn = 0;
                        }
                        break;

                    case R.id.ColBtnBlue:
                        if(turn == 0) {
                            board_1.setImageDrawable(board_3_blue1);
                            turn = 1;
                        }
                        else if(turn == 1){
                            board_2.setImageDrawable(board_3_blue2);
                            turn = 2;
                        }
                        else {
                            board_3.setImageDrawable(board_3_blue3);
                            turn = 0;
                        }
                        break;

                    case R.id.ColBtnPurple:
                        if(turn == 0) {
                            board_1.setImageDrawable(board_3_purple1);
                            turn = 1;
                        }
                        else if(turn == 1){
                            board_2.setImageDrawable(board_3_purple2);
                            turn = 2;
                        }
                        else {
                            board_3.setImageDrawable(board_3_purple3);
                            turn = 0;
                        }
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

    }
}
