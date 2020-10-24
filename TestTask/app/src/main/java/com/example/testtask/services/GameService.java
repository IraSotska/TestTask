package com.example.testtask.services;

import android.widget.ImageView;
import android.widget.TextView;
import com.example.testtask.GameActivity;
import com.example.testtask.R;
import java.util.Random;

public class GameService {

    private static GameService gameService;

    private boolean[] imageChecked;

    private boolean[] resolvedImages = new boolean[16];

    private ImageView previousIv;
    private int previousIvId;

    private int[] imagesId = new int[8];

    private int startImageId;

    private int activeImageInd;

    private int maxActiveImages = 0;

    private int stepCounter;

    private ImageView[] imageViews;

    private int hidenImageId, readyIconId;

    private int[] combination = {1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8};

    private TextView stepCounterTextview;

    private int notResolvedImages;

    private GameActivity gameActivity;

    private GameService(){}

    public static GameService getGameServiceInstance() {

        if (gameService == null) {
            gameService = new GameService();
        }
        return gameService;
    }

    public void startGame(ImageView[] imageViews, TextView tv, GameActivity gameActivity) {

        this.gameActivity = gameActivity;

        notResolvedImages = 16;

        stepCounterTextview = tv;
        stepCounter = 0;

        String s = String.valueOf(stepCounter);

        stepCounterTextview.setText(s);

        this.imageViews = imageViews;
        generateCombination();

        loadImages();
        setStartImages();
    }

    public void generateCombination() {

        Random rnd = new Random();
        for(int i = 0; i < combination.length; i++) {
            int index = rnd.nextInt(i + 1);
            int a = combination[index];
            combination[index] = combination[i];
            combination[i] = a;
        }
    }

    public void setImage(int ind, ImageView clickedImage) {

        if(maxActiveImages==2) {
            hideOpenedImages();
            previousIv = null;
            activeImageInd = -1;

            maxActiveImages = 0;

        }
        if((activeImageInd == combination[ind]) && (previousIv != clickedImage)) {
            if(previousIv!=null) {

                resolvedImages[ind] = true;
                resolvedImages[previousIvId] = true;

                notResolvedImages-=2;
                clickedImage.setClickable(false);

                previousIv.setImageResource(readyIconId);
                previousIv.setClickable(false);
                clickedImage.setImageResource(readyIconId);

                if(notResolvedImages == 0) {
                    gameActivity.endGame();
                }
            }
        }
        else {

            stepCounter++;

            String res = String.valueOf(stepCounter);

            stepCounterTextview.setText(res);

            previousIv = clickedImage;
            previousIvId = ind;
            activeImageInd = combination[ind];
            clickedImage.setImageResource(imagesId[combination[ind] - 1]);
            imageChecked[ind] = true;
            maxActiveImages++;
        }
    }

    private void setStartImages() {

        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setImageResource(startImageId);
        }
    }

    private void hideOpenedImages() {
        for(int i = 0; i<16; i++) {

            if((imageChecked[i]) && (!resolvedImages[i])) {
                imageViews[i].setImageResource(hidenImageId);
            }
        }
    }

    private void loadImages() {

        imageChecked = new boolean[16];

        for(int i = 0; i<16; i++) {
            imageChecked[i] = false;
        }

        for(int i = 0; i<16; i++) {
            resolvedImages[i] = false;
        }

        imagesId[0] = R.drawable.img1;
        imagesId[1] = R.drawable.img2;
        imagesId[2] = R.drawable.img3;
        imagesId[3] = R.drawable.img4;
        imagesId[4] = R.drawable.img5;
        imagesId[5] = R.drawable.img6;
        imagesId[6] = R.drawable.img7;
        imagesId[7] = R.drawable.img8;

        startImageId = R.drawable.paper;

        hidenImageId = R.drawable.paper;
        readyIconId = R.drawable.readyicon;
    }
}
