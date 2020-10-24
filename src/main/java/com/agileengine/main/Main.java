package com.agileengine.main;

import com.agileengine.thread.ImageUpdateThread;

public class Main {

    public static void main(String[] args) {

        ImageUpdateThread imageUpdateThread = new ImageUpdateThread(5);
        imageUpdateThread.run();
    }
}
