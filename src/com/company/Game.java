package com.company;

import shape.Matrix;
import shape.Vector3d;

import java.awt.*;

public class Game {

    public Vector3d[] points;
    public Vector3d[] projections;

    public Matrix projectionMatrix;

    public Matrix rotationZMatrix;
    public Matrix rotationXMatrix;
    public Matrix rotationYMatrix;

    public float angle;
    public double cameraDistance;
    public double cameraAngle;

    public Game() {
        cameraDistance = 1;
        cameraAngle = 0;
        angle = 0.0f;
        projections = new Vector3d[8];
        points = new Vector3d[]{
                new Vector3d(-0.5f, -0.5f, -0.5f),
                new Vector3d(0.5f, -0.5f, -0.5f),
                new Vector3d(0.5f, 0.5f, -0.5f),
                new Vector3d(-0.5f, 0.5f, -0.5f),

                new Vector3d(-0.5f, -0.5f, 0.5f),
                new Vector3d(0.5f, -0.5f, 0.5f),
                new Vector3d(0.5f, 0.5f, 0.5f),
                new Vector3d(-0.5f, 0.5f, 0.5f),
        };

        projectionMatrix = new Matrix(new double[][]{
                new double[] { cameraDistance, 0, 0},
                new double[] { 0, cameraDistance, 0},
        });

        rotationZMatrix = new Matrix();
        rotationXMatrix = new Matrix();
        rotationYMatrix = new Matrix();

        tick();
    }

    public void render(Graphics g) {

        g.translate(Main.WIDTH/2, Main.HEIGHT/2);

        g.setColor(Color.black);

        for (int i = 0; i < 4; i++){
            connect(projections[i], projections[(i+1)%4], g);
            connect(projections[i + 4], projections[(i+1)%4 + 4], g);
            connect(projections[i], projections[i + 4], g);
        }
    }

    public void tick() {

        rotationZMatrix.setMatrix(new double[][]{
                new double[] { Math.cos(angle), Math.sin(angle), 0.0},
                new double[] { -Math.sin(angle), Math.cos(angle), 0.0},
                new double[] { 0.0, 0.0, 1},
        });

        rotationXMatrix.setMatrix(new double[][]{
                new double[] { 1, 0.0, 0.0},
                new double[] { 0.0, Math.cos(angle), Math.sin(angle)},
                new double[] { 0.0, -Math.sin(angle), Math.cos(angle)},
        });

        rotationYMatrix.setMatrix(new double[][]{
                new double[] { Math.cos(angle), 0.0 , -Math.sin(angle)},
                new double[] { 0.0, 1, 0.0},
                new double[] { Math.sin(angle), 0.0, Math.cos(angle)},
        });

        for (int i = 0; i < points.length; i++){
            Vector3d rotated = Matrix.matmul(rotationYMatrix, points[i]);
//            rotated = Matrix.matmul(rotationXMatrix, rotated);
//            rotated = Matrix.matmul(rotationYMatrix, rotated);
            Vector3d projected = Matrix.matmul(projectionMatrix, rotated);

            projected.mult(200);

            projections[i] = projected;
        }

        angle+= 0.01;
    }

    public void connect(Vector3d a, Vector3d b, Graphics g){
        g.drawLine((int)a.x, (int)a.y, (int)b.x, (int)b.y);
    }

    public void updateCameraDistance(double dist, double angle){
        cameraDistance += dist;
        cameraAngle += angle;
        projectionMatrix.setMatrix(new double[][]{
                new double[] { cameraDistance, 0, 0},
                new double[] { 0, cameraDistance, cameraAngle},
//                new double[] { 0, 0, cameraDistance},
        });
    }
}
