package com.wjk.tank;

import static com.wjk.tank.Commer.ENEMIES_SIZE;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        TankFrame tankFrame = new TankFrame();


        // 初始化敌方坦克
        for (int i = 0; i < ENEMIES_SIZE; i++) {
            tankFrame.tanks.add(new Tank(80 + i * 50, 100, false, tankFrame));
        }


        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
