package com.company;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;

public class MouseInput extends MouseAdapter {

    private Game game;
    private double dist;
    private double angle;
    private int ticks;

    public MouseInput(Game game){
        this.game = game;
        ticks = 0;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        dist = (double) e.getWheelRotation() / 100;
        ticks = 0;
    }

//    @Override
//    public void mouseMoved(MouseEvent e) {
//        if (e.getY() > Main.HEIGHT/2){
//            angle = 0.01;
//        } else {
//            angle = -0.01;
//        }
//        ticks = 0;
//    }

    public void tick(){
        if (ticks < 10){
            game.updateCameraDistance(dist, angle);
            ticks++;
        }
    }
}
