package com.wjk.tank;

import lombok.Data;

import java.awt.Graphics;

/**
 * @author Jiakai
 * @description: TODO
 * @date 2021/7/1810:33
 */
@Data
public class Explode {

    private int x;
    private int y;

    private boolean living;

    private int step;

    TankFrame tankFrame;

    public Explode(int x, int y, boolean living, int step, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.living = living;
        this.step = step;
        this.tankFrame = tankFrame;

    }

    public Explode(int x, int y, TankFrame tankFrame) {
        this(x, y, true, 0, tankFrame);
    }

    public void paint(Graphics graphics) {
        if (!living) {
            return;
        }
        graphics.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length) {
            living = false;
            step = 0;
        }
    }
}
