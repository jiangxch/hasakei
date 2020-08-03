package com.github.jiangxch.hasakei.raft.thread;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: jiangxch
 * @date: 2020/7/25 上午12:51
 */
public class LeaderElectorThread extends Thread {
    private Random random = new Random();

    public LeaderElectorThread() {
        super("LeaderElectorThread");
        Thread t = new Thread();
        this.setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(getElectionTimeOutMillisecond());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getElectionTimeOutMillisecond() {
        return 150 + random.nextInt(50);
    }
}
