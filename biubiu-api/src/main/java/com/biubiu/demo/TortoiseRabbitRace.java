package com.biubiu.demo;

import lombok.SneakyThrows;

/**
 * TortoiseRabbitRace
 *
 * @author wbbaijq
 */
public class TortoiseRabbitRace implements Runnable {

    private static String winner;

    @SneakyThrows
    @Override

    public void run() {
        for (int i = 1; i <= 100; i++) {
            if ("兔子".equals(Thread.currentThread().getName()) && i % 10 == 0) {
                Thread.sleep(5);
            }

            if (gameOver(i)) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + "跑了" + i + "米");
        }
    }

    //是否赢了
    public boolean gameOver(int step) {
        if (winner != null) {
            return true;
        } else {
            if (step >= 100) {
                winner = Thread.currentThread().getName();
                System.out.println(Thread.currentThread().getName() + "跑赢了");
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TortoiseRabbitRace race = new TortoiseRabbitRace();
        new Thread(race, "乌龟").start();
        new Thread(race, "兔子").start();
    }
}
