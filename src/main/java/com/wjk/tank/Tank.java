package com.wjk.tank;

import lombok.Data;
import java.awt.Graphics;
import java.util.Random;

import static com.wjk.tank.Commer.TANK_SPEED;
import static com.wjk.tank.Commer.TANK_X;
import static com.wjk.tank.Commer.TANK_Y;

/**
 * @Description: todo
 * @Author: Jiakai
 * @CreateDate: 2021/7/18 0:51
 */
@Data
public class Tank {

    private int x;
    private int y;
    private Dir dir;
    private int speed;
    private boolean moving;
    private TankFrame tankFrame;
    private Group group;

    private boolean living;

    public Tank(int x, int y, Dir dir, int speed, boolean moving, TankFrame tankFrame, Group group, boolean living) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.speed = speed;
        this.moving = moving;
        this.tankFrame = tankFrame;
        this.group = group;
        this.living = living;
    }

    public Tank(TankFrame tankFrame) {
        this(TANK_X, TANK_Y, Dir.DOWN, TANK_SPEED, false, tankFrame, Group.BAD, true);
    }

    public Tank(TankFrame tankFrame, Group group) {
        this(TANK_X, TANK_Y, Dir.DOWN, TANK_SPEED, false, tankFrame, group, true);
    }

    public Tank(int x, int y, boolean moving, TankFrame tankFrame) {
        this(x, y, Dir.DOWN, TANK_SPEED, moving, tankFrame, Group.BAD, true);
    }

    public void paint(Graphics g) {
        if (!living) {
            tankFrame.tanks.remove(this);
        }

        switch (dir) {
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            default:
                break;
        }

        move();
    }

    public void move() {
        if (!this.moving) {
            return;
        }
        switch (dir) {
            case UP:
                y -= this.speed;
                break;
            case DOWN:
                y += this.speed;
                break;
            case LEFT:
                x -= this.speed;
                break;
            case RIGHT:
                x += this.speed;
                break;
            default:
                break;
        }

        if (this.group.equals(Group.BAD) && new Random().nextInt(10) > 8){
            this.fire();
        }
    }

    public void fire() {
        int bulletX = this.x;
        int bulletY = this.y;
        switch (dir) {
            case UP:
                bulletX += ResourceMgr.tankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
                bulletY += ResourceMgr.bulletU.getHeight();
                break;
            case DOWN:
                bulletX += ResourceMgr.tankD.getWidth() / 2 - ResourceMgr.bulletD.getWidth() / 2;
                bulletY += ResourceMgr.tankD.getHeight() - ResourceMgr.bulletD.getHeight();
                break;
            case LEFT:
                bulletX += ResourceMgr.bulletL.getWidth();
                bulletY += ResourceMgr.tankL.getHeight() / 2 - ResourceMgr.bulletL.getHeight() / 2;
                break;
            case RIGHT:
                bulletX += ResourceMgr.tankR.getWidth() - ResourceMgr.bulletR.getWidth();
                bulletY += ResourceMgr.tankR.getHeight() / 2 - ResourceMgr.bulletR.getHeight() / 2;
                break;
        }
        Bullet bullet = new Bullet(bulletX, bulletY, this.dir, tankFrame, this.getGroup());
        tankFrame.bullets.add(bullet);
    }

    public void die() {
        this.living = false;
    }
}
