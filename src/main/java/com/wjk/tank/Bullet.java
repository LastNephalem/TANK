package com.wjk.tank;

import lombok.Data;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import static com.wjk.tank.Commer.BULLET_HEIGHT;
import static com.wjk.tank.Commer.BULLET_SPEED;
import static com.wjk.tank.Commer.BULLET_WIDTH;
import static com.wjk.tank.Commer.EXPLODE_HEIGHT;
import static com.wjk.tank.Commer.EXPLODE_WIDTH;
import static com.wjk.tank.Commer.GAME_HEIGHT;
import static com.wjk.tank.Commer.GAME_WIDTH;
import static com.wjk.tank.Commer.TANK_HEIGHT;
import static com.wjk.tank.Commer.TANK_WIDTH;

/**
 * @Description: todo
 * @Author: Jiakai
 * @CreateDate: 2021/7/18 0:51
 */
@Data
public class Bullet {

    private int x;
    private int y;
    private Dir dir;
    private int bulletSpeed;
    private boolean living;
    private TankFrame tankFrame;
    private Group group;

    Bullet(int x, int y, Dir dir, int bulletSpeed, boolean living, TankFrame tankFrame, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.bulletSpeed = bulletSpeed;
        this.living = living;
        this.tankFrame = tankFrame;
        this.group = group;
    }

    Bullet(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        this(x, y, dir, BULLET_SPEED, true, tankFrame, group);
    }

    void paint(Graphics graphics) {
        if (!living) {
            tankFrame.bullets.remove(this);
        }
        switch (dir) {
            case UP:
                graphics.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case LEFT:
                graphics.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            default:
                break;
        }
        move();
    }

    public void move() {
        switch (dir) {
            case UP:
                y -= this.bulletSpeed;
                break;
            case DOWN:
                y += this.bulletSpeed;
                break;
            case LEFT:
                x -= this.bulletSpeed;
                break;
            case RIGHT:
                x += this.bulletSpeed;
                break;
            default:
                break;
        }

        if (x < 0 || y < 0 || x > GAME_WIDTH || y > GAME_HEIGHT) {
            this.living = false;
        }

    }

    void collideWith(Tank tank) {
        if (this.group.equals(tank.getGroup())) {
            return;
        }
        // todo: 用一个 rect 记录子弹的位置
        Rectangle bulletRectangle = new Rectangle(this.x, this.y, BULLET_WIDTH, BULLET_HEIGHT);
        Rectangle tankRectangle = new Rectangle(tank.getX(), tank.getY(), TANK_WIDTH, TANK_HEIGHT);
        if (bulletRectangle.intersects(tankRectangle)) {
//            Explode explodes = new Explode(tank.getX(), tank.getY(), tankFrame);
//            explodes.paint(tankFrame.getGraphics());
            tank.die();
            this.die();
        }
    }

    private void die() {
        this.living = false;
    }
}
