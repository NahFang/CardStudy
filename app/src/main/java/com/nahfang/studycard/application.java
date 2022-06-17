package com.nahfang.studycard;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.nahfang.studycard.components.db.dataBase;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class application extends Application {
    public static Context context;
    public static dataBase dataBase;

    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    // Instantiates the queue of Runnables as a LinkedBlockingQueue
    private final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();

    // Sets the amount of time an idle thread waits before terminating
    private static final int KEEP_ALIVE_TIME = 10;
    // Sets the Time Unit to seconds
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    // Creates a thread pool manager
    public static ThreadPoolExecutor  threadPoolExecutor;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        dataBase = Room
                .databaseBuilder(this, com.nahfang.studycard.components.db.dataBase.class,"studycard")
                .build();
        threadPoolExecutor = new ThreadPoolExecutor(
                NUMBER_OF_CORES,       // Initial pool size
                NUMBER_OF_CORES,       // Max pool size
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                workQueue
        );
    }
}
