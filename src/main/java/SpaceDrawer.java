import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class SpaceDrawer implements Runnable {

    private final Random gen = new Random();
    private final int starCount = 800;
    private double speed;
    private Star[] stars = new Star[starCount];

    private GraphicsContext gc;
    private double w, h;
    private Thread t;

    public SpaceDrawer(GraphicsContext gc, double w, double h) {
        this.gc = gc;
        this.w = w;
        this.h = h;

        gc.setFill(Color.rgb(30, 30, 30));
        gc.fillRect(0, 0, w, h);
        setup();

        t = new Thread(this);
        t.start();
    }

    public void setSpeed(double speed) {
        this.speed = map(Math.abs(speed - w/2), 0, w/2, 0, 50);
    }

    @Override
    public void run() {
        while (true) {
            gc.setFill(Color.rgb(30, 30, 30));
            gc.fillRect(0, 0, w, h);
            gc.setFill(Color.WHITE);

            for (Star s: stars) {
                s.setSpeed(speed);
                double sx = map(s.x / s.z, 0, 1, 0, w);
                double sy = map(s.y / s.z, 0, 1, 0, h);
                double r = 16 - map(s.z, 0, w, 0, 16);
                //gc.fillOval(sx + w/2, sy + h/2, r, r);


                double psx = map(s.x / s.pz, 0, 1, 0, w);
                double psy = map(s.y / s.pz, 0, 1, 0, h);
                s.pz = s.z;
                gc.strokeLine(psx + w/2, psy + h/2, sx + w/2, sy + h/2);

                s.update();
            }


            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Thread getT() {
        return t;
    }

    private void setup() {
        gc.setStroke(Color.GRAY);
        for (int i=0; i<starCount; i++) {
            stars[i] = new Star(w, h);
        }
    }

    public static double map(double x, double inputLowerBound, double inputUpperBound, double outputLowerBound, double outputUpperBound) {
        double k = (outputUpperBound - outputLowerBound) / (inputUpperBound - inputLowerBound);
        x *= k;
//        if (x > outputUpperBound)
//            return outputUpperBound;
//        if (x < outputLowerBound)
//            return outputLowerBound;
        return x;
    }

}
