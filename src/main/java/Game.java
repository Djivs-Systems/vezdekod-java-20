package main.java;

import lib.Window;
import lib.render.Texture;
import java.util.concurrent.ThreadLocalRandom;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Game extends Window {
    private int n;
    private double rotating_angle;
    private double circle_r = 300;
    private double thickness = 1;
    private int color = -100;

    private Point2D.Double circle_center = new Point2D.Double(400, 300);
    private ArrayList<Point2D.Double> pointsArray = new ArrayList<Point2D.Double>();
    private final Texture backgroundTexture = Texture.load("back.png");

    public Game() {
        super(800, 600, "Vezdekod :3", true, "Arial", 30);
        setN(10);
    }
    public void setN(int n) {
        this.n = Math.max(n, 3);
        pointsArray.clear();
        rotating_angle = 360.0/n;
        double passed_angle = 0;
        double x, y;
        pointsArray.add(new Point2D.Double(circle_center.x - circle_r, circle_center.y));
        for (int i = 0; i < n-1; ++i) {
            passed_angle += rotating_angle;
            x = circle_center.x - Math.cos(Math.toRadians(passed_angle)) * circle_r;
            y = circle_center.y - Math.sin(Math.toRadians(passed_angle)) * circle_r;
            pointsArray.add(new Point2D.Double(x, y));
        }
    }

    @Override
    protected void onFrame(double elapsed) {
        canvas.drawTexture(backgroundTexture, 0, 0, width, height, width, height);
        for (int i = 1; i < n; ++i) {
            canvas.drawLine(color, pointsArray.get(i).x, pointsArray.get(i).y,pointsArray.get(i-1).x, pointsArray.get(i-1).y, thickness);
        }
        canvas.drawLine(color, pointsArray.get(n-1).x, pointsArray.get(n-1).y,pointsArray.get(0).x, pointsArray.get(0).y, thickness);
    }

    public static void main(String[] args) {
        new Game().show();
    }
};
