package com.agileengine.thread;

import com.agileengine.net.HttpClient;
import java.io.IOException;

public class ImageUpdateThread implements Runnable{

    private int updatePeriod;
    private HttpClient httpClient = HttpClient.getInstance();

    public ImageUpdateThread(int updatePeriod) {
        this.updatePeriod = updatePeriod;
    }

    public void run() {
        updateImages();
    }
    private void updateImages() {

        try {
            Thread.sleep(updatePeriod*1000);
            try {
                httpClient.callForData();
            } catch (IOException e) {
                e.printStackTrace();
            }
            updateImages();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
