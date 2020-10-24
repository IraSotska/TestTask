package com.example.testtask;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testtask.services.GameService;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10, imageView11, imageView12, imageView13, imageView14, imageView15, imageView16;

    private GameService gameService;

    Button restartGame;

    TextView stepCounterLabel;

    private ImageView[] imageViews = new ImageView[16];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameService = GameService.getGameServiceInstance();

        stepCounterLabel = findViewById(R.id.stepCounterLabel);

        restartGame = findViewById(R.id.restartGame);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        imageView13 = findViewById(R.id.imageView13);
        imageView14 = findViewById(R.id.imageView14);
        imageView15 = findViewById(R.id.imageView15);
        imageView16 = findViewById(R.id.imageView16);


        imageViews[0] = imageView1;
        imageViews[1] = imageView2;
        imageViews[2] = imageView3;
        imageViews[3] = imageView4;
        imageViews[4] = imageView5;
        imageViews[5] = imageView6;
        imageViews[6] = imageView7;
        imageViews[7] = imageView8;
        imageViews[8] = imageView9;
        imageViews[9] = imageView10;
        imageViews[10] = imageView11;
        imageViews[11] = imageView12;
        imageViews[12] = imageView13;
        imageViews[13] = imageView14;
        imageViews[14] = imageView15;
        imageViews[15] = imageView16;

        restartGame.setOnClickListener(this);

        gameService.startGame(imageViews, stepCounterLabel, this);
        setClickListeners();

    }

    public void endGame() {

        Toast.makeText(this,"You are win!",Toast.LENGTH_SHORT).show();

        restartGame();
    }

    private void setClickListeners() {

        for(int i = 0; i<16; i++) {
            imageViews[i].setOnClickListener(this);
        }
    }

    private void setClickable() {

        for(int i = 0; i<16; i++) {
            imageViews[i].setClickable(true);
        }
    }

    private void restartGame() {
        gameService.startGame(imageViews, stepCounterLabel, this);
        setClickable();
        setClickListeners();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.imageView1:
                gameService.setImage(0, imageView1);
                break;
            case R.id.imageView2:
                gameService.setImage(1, imageView2);
                break;
            case R.id.imageView3:
                gameService.setImage(2, imageView3);
                break;
            case R.id.imageView4:
                gameService.setImage(3, imageView4);
                break;
            case R.id.imageView5:
                gameService.setImage(4, imageView5);
                break;
            case R.id.imageView6:
                gameService.setImage(5, imageView6);
                break;
            case R.id.imageView7:
                gameService.setImage(6, imageView7);
                break;
            case R.id.imageView8:
                gameService.setImage(7, imageView8);
                break;
            case R.id.imageView9:
                gameService.setImage(8, imageView9);
                break;
            case R.id.imageView10:
                gameService.setImage(9, imageView10);
                break;
            case R.id.imageView11:
                gameService.setImage(10, imageView11);
                break;
            case R.id.imageView12:
                gameService.setImage(11, imageView12);
                break;
            case R.id.imageView13:
                gameService.setImage(12, imageView13);
                break;
            case R.id.imageView14:
                gameService.setImage(13, imageView14);
                break;
            case R.id.imageView15:
                gameService.setImage(14, imageView15);
                break;
            case R.id.imageView16:
                gameService.setImage(15, imageView16);
                break;

            case R.id.restartGame:
                restartGame();
                break;

            default: break;
        }
    }
}