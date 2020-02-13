package com.company;

import shape.Matrix;
import shape.Matrix4d;
import shape.Vector3d;
import shape.Vector4d;

import java.awt.*;

public class Game {

    public Vector4d[] points;
    public Vector4d[] projections;

    public Matrix4d projectionMatrix;

    public Matrix4d rotationZMatrix;
    public Matrix4d rotationXMatrix;
    public Matrix4d rotationYMatrix;

    public Matrix4d rotationXYMatrix;
    public Matrix4d rotationYZMatrix;
    public Matrix4d rotationXZMatrix;
    public Matrix4d rotationXWMatrix;
    public Matrix4d rotationYWMatrix;
    public Matrix4d rotationZWMatrix;

    public float angle;
    public double cameraDistance;
    public double cameraAngle;

    public Game() {
        cameraDistance = 1;
        cameraAngle = 0;
        angle = 0.0f;
        projections = new Vector4d[16];
        points = new Vector4d[]{
                new Vector4d(-0.5f, -0.5f, -0.5f, 0.5f),
                new Vector4d(0.5f, -0.5f, -0.5f, 0.5f),
                new Vector4d(0.5f, 0.5f, -0.5f, 0.5f),
                new Vector4d(-0.5f, 0.5f, -0.5f, 0.5f),

                new Vector4d(-0.5f, -0.5f, 0.5f, 0.5f),
                new Vector4d(0.5f, -0.5f, 0.5f, 0.5f),
                new Vector4d(0.5f, 0.5f, 0.5f, 0.5f),
                new Vector4d(-0.5f, 0.5f, 0.5f, 0.5f),

                new Vector4d(-0.5f, -0.5f, -0.5f, -0.5f),
                new Vector4d(0.5f, -0.5f, -0.5f, -0.5f),
                new Vector4d(0.5f, 0.5f, -0.5f, -0.5f),
                new Vector4d(-0.5f, 0.5f, -0.5f, -0.5f),

                new Vector4d(-0.5f, -0.5f, 0.5f, -0.5f),
                new Vector4d(0.5f, -0.5f, 0.5f, -0.5f),
                new Vector4d(0.5f, 0.5f, 0.5f, -0.5f),
                new Vector4d(-0.5f, 0.5f, 0.5f, -0.5f),
        };

        projectionMatrix = new Matrix4d(new double[][]{
                new double[]{cameraDistance, 0, 0, 0},
                new double[]{0, cameraDistance, 0, 0},
                new double[]{0, 0, cameraDistance, 0},
        });

        rotationZMatrix = new Matrix4d();
        rotationXMatrix = new Matrix4d();
        rotationYMatrix = new Matrix4d();

        rotationXZMatrix = new Matrix4d();
        rotationXYMatrix = new Matrix4d();
        rotationYZMatrix = new Matrix4d();
        rotationXWMatrix = new Matrix4d();
        rotationYWMatrix = new Matrix4d();
        rotationZWMatrix = new Matrix4d();

        tick();
    }

    public void render(Graphics g) {

        g.translate(Main.WIDTH / 2, Main.HEIGHT / 2);

        g.setColor(Color.black);

        for (int i = 0; i < 4; i++) {
            connect(projections[i], projections[(i + 1) % 4], g);
            connect(projections[i + 4], projections[(i + 1) % 4 + 4], g);
            connect(projections[i], projections[i + 4], g);

            connect(projections[i + 8], projections[(i + 1) % 4 + 8], g);
            connect(projections[i + 4 + 8], projections[(i + 1) % 4 + 4 + 8], g);
            connect(projections[i + 8], projections[i + 4 + 8], g);

            connect(projections[i], projections[i + 8], g);
            connect(projections[i + 4], projections[i + 12], g);
        }

//        for (int i = 0; i < projections.length; i++){
//            g.fillOval((int)projections[i].x, (int)projections[i].y, 10, 10);
//        }
    }

    public void tick() {

//        rotationZMatrix.setMatrix(new double[][]{
//                new double[] { Math.cos(angle), Math.sin(angle), 0.0, 0.0},
//                new double[] { -Math.sin(angle), Math.cos(angle), 0.0, 0.0},
//                new double[] { 0.0, 0.0, 1, 0.0},
//                new double[] { 0.0, 0.0, 0.0, 1},
//        });
//
//        rotationXMatrix.setMatrix(new double[][]{
//                new double[] { 1, 0.0, 0.0, 0.0},
//                new double[] { 0.0, Math.cos(angle), Math.sin(angle), 0.0},
//                new double[] { 0.0, -Math.sin(angle), Math.cos(angle), 0.0},
//                new double[] { 0.0, 0.0, 0.0, 1},
//        });
//
//        rotationYMatrix.setMatrix(new double[][]{
//                new double[] { Math.cos(angle), 0.0 , -Math.sin(angle), 0.0},
//                new double[] { 0.0, 1, 0.0, 0.0},
//                new double[] { Math.sin(angle), 0.0, Math.cos(angle), 0.0},
//                new double[] { 0.0, 0.0, 0.0, 0.0},
//        });

        rotationXZMatrix.setMatrix(new double[][]{
                new double[]{Math.cos(angle), 0.0, -Math.sin(angle), 0.0},
                new double[]{0.0, 1, 0.0, 0.0},
                new double[]{Math.sin(angle), 0.0, Math.cos(angle), 0.0},
                new double[]{0.0, 0.0, 0.0, 1},
        });

        rotationXYMatrix.setMatrix(new double[][]{
                new double[]{Math.cos(angle), Math.sin(angle), 0.0, 0.0},
                new double[]{-Math.sin(angle), Math.cos(angle), 0.0, 0.0},
                new double[]{0.0, 0.0, 1, 0.0},
                new double[]{0.0, 0.0, 0.0, 1},
        });

        rotationYZMatrix.setMatrix(new double[][]{
                new double[]{1, 0.0, 0.0, 0.0},
                new double[]{0.0, Math.cos(angle), Math.sin(angle), 0.0},
                new double[]{0.0, -Math.sin(angle), Math.cos(angle), 0.0},
                new double[]{0.0, 0.0, 0.0, 1},
        });

        rotationXWMatrix.setMatrix(new double[][]{
                new double[]{Math.cos(angle), 0.0, 0.0, Math.sin(angle)},
                new double[]{0.0, 1, 0.0, 0.0},
                new double[]{0.0, 0.0, 1, 0.0},
                new double[]{-Math.sin(angle), 0.0, 0.0, Math.cos(angle)},
        });

        rotationYWMatrix.setMatrix(new double[][]{
                new double[]{1, 0.0, 0.0, 0.0},
                new double[]{0.0, Math.cos(angle), 0.0, -Math.sin(angle)},
                new double[]{0.0, 0.0, 1, 0.0},
                new double[]{0.0, Math.sin(angle), 0.0, Math.cos(angle)},
        });

        rotationZWMatrix.setMatrix(new double[][]{
                new double[]{1, 0.0, 0.0, 0.0},
                new double[]{0.0, 1, 0.0, 0.0},
                new double[]{0.0, 0.0, Math.cos(angle), -Math.sin(angle)},
                new double[]{0.0, 0.0, Math.sin(angle), Math.cos(angle)},
        });


        for (int i = 0; i < points.length; i++) {


            Vector4d rotated = Matrix4d.matmul(rotationYWMatrix, points[i]);
            rotated = Matrix4d.matmul(rotationZWMatrix, rotated);
            rotated = Matrix4d.matmul(rotationXWMatrix, rotated);

            rotated = Matrix4d.matmul(rotationYZMatrix, rotated);
            rotated = Matrix4d.matmul(rotationXYMatrix, rotated);
            rotated = Matrix4d.matmul(rotationXZMatrix, rotated);

//            Vector4d rotated = Matrix4d.matmul(rotationYMatrix, points[i]);
//            rotated = Matrix4d.matmul(rotationXMatrix, rotated);
//            rotated = Matrix4d.matmul(rotationYMatrix, rotated);

            double dist = 2;
            double w = 1 / (dist - rotated.w);
            projectionMatrix.setMatrix(new double[][]{
                    new double[]{w, 0, 0, 0},
                    new double[]{0, w, 0, 0},
                    new double[]{0, 0, w, 0},
            });

            Vector4d projected = Matrix4d.matmul(projectionMatrix, rotated);

            projected.mult(200);

            projections[i] = projected;
        }

        angle += 0.01;
    }

    public void connect(Vector4d a, Vector4d b, Graphics g) {
        g.drawLine((int) a.x, (int) a.y, (int) b.x, (int) b.y);
    }

    public void updateCameraDistance(double dist, double angle) {
//        cameraDistance += dist;
//        cameraAngle += angle;
//        projectionMatrix.setMatrix(new double[][]{
//                new double[] { cameraDistance, 0, 0, 0},
//                new double[] { 0, cameraDistance, 0, 0},
//                new double[] { 0, 0, cameraDistance, 0},
//        });
    }
}
