package com.company;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

    private JFrame frame;
    private Main game;

    public Window(String title, int width, int height, Main game){

        this.game = game;

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setLocationRelativeTo(null);
        frame.add(this.game);
        frame.setVisible(true);
        game.start();

    }
}
